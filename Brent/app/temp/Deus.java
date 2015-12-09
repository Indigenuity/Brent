package temp;

import akka.actor.ActorRef;
import async.Asyncleton;
import fbfetching.FBFetchResult;
import fbfetching.FBFetchable;
import fbfetching.FBFetcher;
import fbfetching.FBPageEndpoint;
import fbfetching.fetchables.FBPageFetchable;
import fetching.Endpoint;
import fetching.Fetch;
import fetching.FetchResult;
import fetching.Fetcher;
import fetching.Persister;

public class Deus {
	
	public static void fetchEexperiment() {
		FBPageEndpoint endpoint = new FBPageEndpoint();
		endpoint.setUrl("https://www.facebook.com/SAS");
		endpoint.setIdentifier("SAS");
		endpoint.setFetcher(FBFetcher.getInstance());
		
		ActorRef master = Asyncleton.instance().getMainMaster();
		Fetch<FBPageFetchable> fetch = new Fetch();
		fetch.setEndpoint(endpoint);
		
		FBFetchResult result = FBFetcher.getInstance().fetchPage(endpoint);
		
//		fetch(endpoint);
//		fetch(endpoint);
//		fetch(endpoint);
//		fetch(endpoint);
		
	}
	
	

	
	
	
	
}
