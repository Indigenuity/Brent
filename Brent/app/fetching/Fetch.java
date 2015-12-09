package fetching;

public class Fetch<T> {
	
	private StatusCode status = StatusCode.NOT_STARTED;
	
	private Endpoint<T> endpoint;
	
	private T payload;
	
	public Fetch(){
		
	}

	public StatusCode getStatus() {
		return status;
	}

	public void setStatus(StatusCode status) {
		this.status = status;
	}

	public Endpoint<T> getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Endpoint<T> endpoint) {
		this.endpoint = endpoint;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}
	

}
