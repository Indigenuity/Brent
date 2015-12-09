package fbfetching;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import fbfetching.fetchables.FBFeed;
import fbfetching.fetchables.FBPageFetchable;
import fetching.Endpoint;
import fetching.Fetcher;
import fetching.Persister;

@Entity
public class FBFeedEndpoint implements Endpoint<FBFeed> {
	
	

	@Transient
	private Class<FBFeed> fetchableType = FBFeed.class;

	@Transient
	private Fetcher fetcher;
	
	@Column(nullable = true, columnDefinition="varchar(" + FB.FB_ID_LENGTH + ")")
	private String fbId;

	public FBFeedEndpoint(String fbId) {
		setFbId(fbId);
		this.fetcher = FBFetcher.getInstance();
	}
	
	@Override
	public String getLocation() {
		return fbId;
	}

	@Override
	public Fetcher getFetcher() {
		return fetcher;
	}

	@Override
	public void setFetcher(Fetcher fetcher) {
		this.fetcher = fetcher;
	}

	@Override
	public Class<FBFeed>  getFetchableType() {
		return fetchableType;
	}

	@Override
	public boolean isPaged() {
		return true;
	}

	public String getFbId() {
		return this.fbId;
	}
	
	public void setFbId(String fbId) {
		if(fbId.length() > FB.FB_ID_LENGTH)
			throw new IllegalArgumentException("Can't have fbId longer than " + FB.FB_ID_LENGTH + " characters");
		this.fbId = StringUtils.abbreviate(fbId, FB.FB_ID_LENGTH);
	}
}
