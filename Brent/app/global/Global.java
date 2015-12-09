package global;
import java.sql.Connection;
import java.sql.SQLException;

import fbfetching.FBFetcher;
import fbfetching.FBPageEndpoint;
import fetchasync.FetcherRegistry;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import async.Asyncleton;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.DB;


public class Global extends GlobalSettings {

	
	public void onStart(Application app) {
		System.out.println("in startup");
		Asyncleton.instance().initialize();
		FetcherRegistry.getInstance().register(FBPageEndpoint.class, FBFetcher.getInstance());
	}
	
	public void onStop(Application app) {
        Logger.info("Application shutdown...");
        Logger.info(app.toString());
    }
	
	
}
