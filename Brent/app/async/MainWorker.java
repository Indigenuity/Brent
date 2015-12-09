package async;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.http.conn.ConnectTimeoutException;

import play.Logger;
import play.db.DB;
import utilities.DSFormatter;
import utilities.UrlSniffer;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class MainWorker extends UntypedActor {

	@Override
	public void onReceive(Object work) throws Exception {
		try{
		
			
		}
		catch(Exception e) {
			Logger.error("Caught exception in MainWorker  " + e);
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void postRestart(Throwable reason) throws Exception {
		Logger.error("Main worker restarting");
		preStart();
	}
	

}
