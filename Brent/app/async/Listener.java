package async;

import play.Logger;
import play.db.jpa.JPA;
import akka.actor.UntypedActor;

public class Listener  extends UntypedActor {

	@Override
	public void onReceive(Object work) throws Exception {
		try {
				JPA.withTransaction(new play.libs.F.Function0<Long>() {
					public Long apply() throws Throwable {
						return 42L;
					}
				});
//				Asyncleton.instance().getMainMaster().tell(siteWork, getSelf());
			
		} catch (Throwable e) {
			Logger.error("Error while saving SiteInformation or SiteSummary in MainListener : " + e);
			System.out.println("Error in mainlistener : " + e);
			e.printStackTrace(); 
		} 
	}

}