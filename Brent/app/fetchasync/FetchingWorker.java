package fetchasync;

import play.Logger;
import akka.actor.UntypedActor;
import async.work.WorkItem;
import async.work.WorkStatus;
import fetching.Endpoint;
import fetching.FetchResult;
import fetching.Fetcher;

public class FetchingWorker extends UntypedActor {

	@Override
	public void onReceive(Object work) throws Exception {
		System.out.println("got work in fetching worker");
		WorkItem workItem = (WorkItem) work;
		workItem.setWorkStatus(WorkStatus.IN_PROGRESS);
		if(!Endpoint.class.isAssignableFrom(workItem.getDirective().getClass())){
			System.out.println("Got undoable work in Fetching worker : " + workItem.getDirective());
			getSender().tell(workItem, getSelf());
			return;
		}
		try{
			Endpoint<?> endpoint = (Endpoint<?>) workItem.getDirective();
			Class<?> endpointType = endpoint.getFetchableType();
			Fetcher fetcher = FetcherRegistry.getInstance().getRegistrant(endpointType);
			FetchResult<?> fetchResult = fetcher.fetchAll(endpoint);
			workItem.setResult(fetchResult);
			workItem.setWorkStatus(WorkStatus.COMPLETED);
		}
		catch(Exception e) {
			Logger.error("Caught exception in FetchingWorker  : " + e);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void postRestart(Throwable reason) throws Exception {
		Logger.error("Fetching worker restarting");
		preStart();
	}
	

}
