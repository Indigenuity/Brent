package fetching;

import java.util.Set;

public interface Fetcher {
	public <T> FetchResult<T> fetchAll(Endpoint<T> endpoint);
	
	public<T> Set<T> getSupportedFetchables();
}
