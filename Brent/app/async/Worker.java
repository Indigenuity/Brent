package async;

import java.io.IOException;

import java.net.MalformedURLException;
import java.sql.SQLException;

import org.hibernate.mapping.Fetchable;

import fetching.*;
import play.Logger;
import akka.actor.UntypedActor;

public class Worker extends UntypedActor {

	@Override
	public void onReceive(Object work) throws Exception {
		try {
			
			if(work instanceof Fetch) {
				Fetch<? extends Fetchable> fetch = (Fetch<? extends Fetchable>) work;
				System.out.println("got work");
				if(fetch.getStatus() == StatusCode.NOT_STARTED){
					Endpoint endpoint = fetch.getEndpoint();
					Fetcher fetcher = endpoint.getFetcher();
					System.out.println("askingn to fetch");
					FetchResult result = fetcher.fetchAll(endpoint);
					System.out.println("fetched : " + result);
				}
				
				
			}
			
		}
		catch(Exception e) {
			Logger.error("Caught exception in Worker  " + e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void postRestart(Throwable reason) throws Exception {
		Logger.error("Worker restarting");
		preStart();
	}
	

}
