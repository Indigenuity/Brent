package async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

import play.Logger;

public class AsyncMonitor {
	
	public final static int SNIFFER_WORK = 1;
	public final static int CRAWL_WORK = 2;
	public final static int MATCHING_WORK = 3;
	public final static int STRING_EXTRACTION_WORK = 4;
	public final static int STAFF_EXTRACTION_WORK = 5;
	
	private final Map<String, Map<Long, WorkInProgress>> wipLists = new LinkedHashMap<String, Map<Long, WorkInProgress>>();
	
	private static final AsyncMonitor instance = new AsyncMonitor();
	
	private AsyncMonitor() {
		wipLists.put("Crawl", new HashMap<Long, WorkInProgress>());
		wipLists.put("Doc Analysis", new HashMap<Long, WorkInProgress>());
		wipLists.put("Meta Analysis", new HashMap<Long, WorkInProgress>());
		wipLists.put("Amalgamation", new HashMap<Long, WorkInProgress>());
		wipLists.put("Text Analysis", new HashMap<Long, WorkInProgress>());
		wipLists.put("Inference", new HashMap<Long, WorkInProgress>());
		wipLists.put("Sniffer", new HashMap<Long, WorkInProgress>());
 	}
	
	public static AsyncMonitor instance() {
		return instance;
	}
	
	
	private final Map<Long, WorkInProgress> snifferWIP = new ConcurrentHashMap<Long, WorkInProgress>();
	private final Map<Long, WorkInProgress> crawlWIP = new ConcurrentHashMap<Long, WorkInProgress>();
	private final Map<Long, WorkInProgress> docAnalysisWIP = new ConcurrentHashMap<Long, WorkInProgress>();
	private final Map<Long, WorkInProgress> metaAnalysisWIP = new ConcurrentHashMap<Long, WorkInProgress>();
	private final Map<Long, WorkInProgress> amalgamationWIP = new ConcurrentHashMap<Long, WorkInProgress>();
	private final Map<Long, WorkInProgress> textAnalysisWIP = new ConcurrentHashMap<Long, WorkInProgress>();
	private final Map<Long, WorkInProgress> inferenceWIP = new ConcurrentHashMap<Long, WorkInProgress>();
	
	public Collection<WorkInProgress> getWipList(String listName) {
		Map<Long, WorkInProgress> wipMap = wipLists.get(listName);
		return wipMap.values();
	}
	
	public void addWip(String listName, long id) {
		Map<Long, WorkInProgress> wipList = wipLists.get(listName);
		if(wipList == null) {
			wipList = new ConcurrentHashMap<Long, WorkInProgress>();
			wipLists.put(listName, wipList);
		}
		WorkInProgress wip = new WorkInProgress(id);
		wipList.put(wip.siteId,  wip);
	}
	
	public void finishWip(String listName, long id) {
		Map<Long, WorkInProgress> wipList = wipLists.get(listName);
		if(wipList == null) {
			Logger.error("Requested wip list : " + listName + " couldn't be found to be finished");
			return;
		}
		wipList.remove(id);
	}
	
	public Map<String, Map<Long, WorkInProgress>> getWipLists() {
		return wipLists;
	}




	public static class WorkInProgress {
		private  long siteId;
		private  String siteName;
		private  String siteUrl;
		private  int workType;
		
		public WorkInProgress(long siteId, String siteName, String siteUrl, int workType) {
			this.siteId = siteId;
			this.siteName = siteName;
			this.siteUrl = siteUrl;
			this.workType = workType;
		}
		
		public WorkInProgress(long siteId, String siteName, String siteUrl) {
			this.siteId = siteId;
			this.siteName = siteName;
			this.siteUrl = siteUrl;
		}
		
		public WorkInProgress(long siteId) {
			this.siteId = siteId;
		}
		

		public long getSiteId() {
			return siteId;
		}

		public String getSiteName() {
			return siteName;
		}

		public String getSiteUrl() {
			return siteUrl;
		}
		
		public int getWorkType() {
			return workType;
		}

		public void setSiteId(long siteId) {
			this.siteId = siteId;
		}

		public void setSiteName(String siteName) {
			this.siteName = siteName;
		}

		public void setSiteUrl(String siteUrl) {
			this.siteUrl = siteUrl;
		}

		public void setWorkType(int workType) {
			this.workType = workType;
		}
		
	}
	
	public static class CompletedWork {
		
		private final long siteId;
		private final int workType;
		
		public CompletedWork(long siteId, int workType) {
			this.siteId = siteId;
			this.workType = workType;
		}
		
		public long getSiteId() {
			return siteId;
		}
		
		public int getWorkType() {
			return workType;
		}
	}
}
