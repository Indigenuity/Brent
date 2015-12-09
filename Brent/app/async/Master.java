package async;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import fetching.*;
import play.Logger;

public class Master extends UntypedActor {

	private final int numWorkers;
	
	private final ActorRef listener;
	private Router router;
	
	
	private int numResults;

	public Master(int numWorkers, ActorRef listener) {
		this.numWorkers = numWorkers;
		this.listener = listener;
		numResults = 0;
		
//		router = getContext().actorOf(new BalancingPool(3).props(Props.create(CrawlingWorker.class)), "router10");
		List<Routee> routees = new ArrayList<Routee>();
	    for (int i = 0; i < this.numWorkers; i++) {
	    	
	      ActorRef r = getContext().actorOf(Props.create(Worker.class));
	      getContext().watch(r);
	      routees.add(new ActorRefRoutee(r));
	    }
	    router = new Router(new RoundRobinRoutingLogic(), routees);
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		try{
			if(message instanceof Fetch) {
				router.route(message, getSelf());
			}
		}
		catch(Exception e){
			Logger.error("Exception caught in Master : " + e);
		}
	}
}
