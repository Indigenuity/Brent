package fetching;

public interface FetchResult<T> {
	
	public T getPayload();
	
	public StatusCode getResultCode();
	
	public String getThrownMessage();
	
	public String getStackTrace();

}
