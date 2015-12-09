package async;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import async.work.WorkItem;
import async.work.WorkSet;
import async.work.WorkStatus;

public class GenericMaster extends UntypedActor {
	

	private final int numWorkers;
	
	private final ActorRef listener;
	private Router router;
	
	private Class<?> clazz;
	
	public GenericMaster(int numWorkers, ActorRef listener, Class<?> clazz) {
		this.numWorkers = numWorkers;
		this.listener = listener;
		this.clazz = clazz;
		List<Routee> routees = new ArrayList<Routee>();
	    for (int i = 0; i < this.numWorkers; i++) {
	      ActorRef r = getContext().actorOf(Props.create(clazz));
	      getContext().watch(r);
	      routees.add(new ActorRefRoutee(r));
	    }
	    router = new Router(new RoundRobinRoutingLogic(), routees);
	}
	
	@Override
	public void onReceive(Object work) throws Exception {
		try{
			System.out.println("received message in generic master (" + this.clazz + ") : " + work);
			if(work instanceof WorkItem) {
				WorkItem workItem = (WorkItem) work;
				if(workItem.getWorkStatus() == WorkStatus.TO_DO){
					AsyncMonitor.instance().addWip(workItem.getWorkType().toString(), workItem.getId());
					workItem.setWorkStatus(WorkStatus.IN_PROGRESS);
					router.route(workItem, getSelf());
				}
				else if(workItem.getWorkStatus() == WorkStatus.COMPLETED){
					AsyncMonitor.instance().finishWip(workItem.getWorkType().toString(), workItem.getId());
					Asyncleton.instance().getMainMaster().tell(workItem, getSelf());
				}
				else if(workItem.getWorkStatus() == WorkStatus.IN_PROGRESS){	//Worker ended in error
					AsyncMonitor.instance().finishWip(workItem.getWorkType().toString(), workItem.getId());
				}
				
			}
			else if(work instanceof Terminated) {
				Logger.error("Generic Master (" + this.clazz + ") received terminated worker");
				router = router.removeRoutee(((Terminated) work).actor());
				ActorRef worker = getContext().actorOf(Props.create(this.clazz));
				getContext().watch(worker);
				router = router.addRoutee(new ActorRefRoutee(worker));
			}
			else {
				System.out.println("Got unknown work in Generic Master (" + this.clazz + ") : " + work);
				Logger.warn("Got unknown work in Generic Master (" + this.clazz + ") : " + work);
			}
		}
		catch(Exception e){
			Logger.error("Exception caught in Generic Master (" + this.clazz + ") : " + e);
		}
	}

}
