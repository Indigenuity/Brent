package fetching;

public interface Endpoint<T> {
	
	public String getLocation();
	
	public Fetcher getFetcher();
	public void setFetcher(Fetcher fetcher);
	
	public Class<T> getFetchableType();
	
	public boolean isPaged();
	
	default public boolean hasPersister() {
		return false;
	}
	
	default public Persister getPersister() {
		throw new UnsupportedOperationException();
	}
	
	default public void setPersister(Persister persister) {
		throw new UnsupportedOperationException();
	}
}
