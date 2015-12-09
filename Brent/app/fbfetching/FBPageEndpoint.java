package fbfetching;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import fbfetching.fetchables.FBPageFetchable;
import fetching.Endpoint;
import fetching.Fetcher;
import fetching.Persister;

@Entity
public class FBPageEndpoint implements Endpoint<FBPageFetchable> {
	@Transient
	private Class<FBPageFetchable> fetchableType = FBPageFetchable.class;

	@Transient
	private Fetcher fetcher;
	
	@Transient
	private Persister persister;
	
	
	private String url;
	private String fbId;
	private String identifier;

	
	@Override
	public Fetcher getFetcher() {
		return fetcher;
	}
	
	@Override
	public void setFetcher(Fetcher fetcher) {
		this.fetcher = fetcher;
	}

	@Override
	public Class<FBPageFetchable> getFetchableType() {
		return fetchableType;
	}

	@Override
	public String getLocation() {
		return identifier;
	}
	
	@Override
	public Persister getPersister() {
		return persister;
	}
	
	@Override
	public void setPersister(Persister persister) {
		this.persister = persister;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if(url.length() > FB.PAGE_URL_LENGTH)
			throw new IllegalArgumentException("Can't have url longer than " + FB.PAGE_URL_LENGTH + " characters");
		this.url = StringUtils.abbreviate(url, FB.PAGE_URL_LENGTH);
	}


	public String getFbId() {
		return fbId;
	}

	public void setFbId(String fbId) {
		if(fbId.length() > FB.FB_ID_LENGTH)
			throw new IllegalArgumentException("Can't have fbId longer than " + FB.FB_ID_LENGTH + " characters");
		this.fbId = StringUtils.abbreviate(fbId, FB.FB_ID_LENGTH);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		if(identifier.length() > FB.IDENTIFIER_LENGTH)
			throw new IllegalArgumentException("Can't have identifier longer than " + FB.IDENTIFIER_LENGTH + " characters");
		this.identifier = StringUtils.abbreviate(identifier, FB.IDENTIFIER_LENGTH);
	}

	@Override
	public boolean isPaged() {
		return false;
	}
}
