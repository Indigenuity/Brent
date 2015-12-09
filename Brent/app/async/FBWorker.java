package async;

import play.Logger;
import akka.actor.UntypedActor;

public class FBWorker extends UntypedActor {

	@Override
	public void onReceive(Object work) throws Exception {
		try{
			System.out.println("got work");
			
			
		}
		catch(Exception e) {
			Logger.error("Caught exception in FBWorker  : " + e);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void postRestart(Throwable reason) throws Exception {
		Logger.error("FB worker restarting");
		preStart();
	}
	

}
