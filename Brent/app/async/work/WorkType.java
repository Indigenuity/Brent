package async.work;


 
//The types of work to be done by workers in this program.
//ORDER MATTERS. 
//Higher tasks may need to be completed before lower tasks are possible to complete
public enum WorkType {
	PAGE_FETCH,
	FEED_FETCH;

	private Class<?> defaultWorker;
	
	private WorkType(Class<?> defaultWorker){
		this.defaultWorker = defaultWorker;
	}
	
	private WorkType() {
		this.defaultWorker = null;
	}
	
	public Class<?> getDefaultWorker(){
		return this.defaultWorker;
	}
}
