package fbfetching;

import java.util.HashSet;
import java.util.Set;

import persistence.FBPage;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Page;
import com.restfb.types.Post;

import fbfetching.fetchables.FBPageFetchable;
import fetching.Endpoint;
import fetching.Fetch;
import fetching.FetchResult;
import fetching.Fetcher;

public class FBFetcher implements Fetcher {
	

	private static final String APP_SECRET = "17b49f9d44934c08902965568727117e";
	private static final String ACCESS_TOKEN = "847051758681799|zrbGGyV-TuC5d4q29_qKTCXk_94";
	
	private static final FBFetcher instance = new FBFetcher();
	
	private static long bob = System.currentTimeMillis();
	
	private FacebookClient client;
	private FBRateLimiter rateLimiter;
	
	private FBFetcher() {
		client = new DefaultFacebookClient(ACCESS_TOKEN, APP_SECRET);
		rateLimiter = new FBRateLimiter();
	}
	
	public static FBFetcher getInstance() {
		return instance;
	}
	
	@Override
	public <T> FetchResult<T> fetchAll(Endpoint<T> endpoint) {
		System.out.println("fetching from endpoint: " + endpoint.getClass());
		System.out.println("fetching type : "  + endpoint.getFetchableType());
		if(!FBFetchable.class.isAssignableFrom(endpoint.getFetchableType())){
			throw new IllegalArgumentException("FBFetcher can only fetch items of type FBFetchable");
		}
		
		if(FBPageFetchable.class.isAssignableFrom(endpoint.getFetchableType())){
			fetchPage((FBPageEndpoint)endpoint);
		}
				
		return null;
	}

	@Override
	public Set<Class> getSupportedFetchables() {
		Set<Class> supported = new HashSet<Class>();
		supported.add(FBPageFetchable.class);
		return supported;
	}
	
	public Fetch<Page> fetchPage(FBPageEndpoint endpoint) {
		String identifier = endpoint.getIdentifier();
		rateLimiter.acquire();
		long temp = System.currentTimeMillis();
		System.out.println("fetching a page : " + identifier);
		bob = temp;
		Page page = client.fetchObject(identifier, Page.class);
		System.out.println("page : " + page.getName());
		return null;
	}
	
	public void fetchExperiment() {
		rateLimiter.acquire();
		Connection<Post> pageFeed = client.fetchConnection("SAS/feed", Post.class);
		int count = 1;
		System.out.println("Page size : " + pageFeed.getData().size());
		
		while (pageFeed.hasNext() && count < 10){
			rateLimiter.acquire();
			String nextUrl = pageFeed.getNextPageUrl();
			System.out.println("nextUrl : " + nextUrl);
			
			pageFeed = client.fetchConnectionPage(nextUrl,  Post.class);
			
			count++;
			System.out.println(count + " : " + pageFeed.getData().size());
		}
		
	}

}
