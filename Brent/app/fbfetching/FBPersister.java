package fbfetching;

import fetching.Persister;

public class FBPersister implements Persister{

	@Override
	public  boolean persist(Object fetchable) {
		System.out.println("Persisting fetchable: " + fetchable);
		return false;
	}

}
