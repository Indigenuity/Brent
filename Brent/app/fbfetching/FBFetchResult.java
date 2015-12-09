package fbfetching;

import fetching.FetchResult;
import fetching.StatusCode;

public class FBFetchResult implements FetchResult<FBFetchable> {

	private FBFetchable fetchable;
	private StatusCode resultCode = StatusCode.IN_PROGRESS;
	private String thrownMessage = "No errors";
	private String stackTrace = "No errors";
	
	@Override
	public FBFetchable getPayload() {
		return fetchable;
	}

	@Override
	public StatusCode getResultCode() {
		// TODO Auto-generated method stub
		return resultCode;
	}

	@Override
	public String getThrownMessage() {
		return thrownMessage;
	}

	@Override
	public String getStackTrace() {
		return stackTrace;
	}

	public FBFetchable getFetchable() {
		return fetchable;
	}

	public void setPayload(FBFetchable fetchable) {
		this.fetchable = fetchable;
	}

	public void setResultCode(StatusCode resultCode) {
		this.resultCode = resultCode;
	}

	public void setThrownMessage(String thrownMessage) {
		this.thrownMessage = thrownMessage;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	
	

}
