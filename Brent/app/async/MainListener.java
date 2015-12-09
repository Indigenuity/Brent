package async;

import play.Logger;
import play.db.jpa.JPA;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class MainListener extends UntypedActor {

	@Override
	public void onReceive(Object work) throws Exception {
		try {
				
			
		} catch (Throwable e) {
			Logger.error("Error while saving SiteInformation or SiteSummary in MainListener : " + e);
			System.out.println("Error in mainlistener : " + e);
			e.printStackTrace(); 
		} 
	}

}