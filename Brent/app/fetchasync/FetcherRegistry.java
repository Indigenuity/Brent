package fetchasync;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import fetching.Fetcher;

public class FetcherRegistry {
	private static final FetcherRegistry instance = new FetcherRegistry();
	
	
	private final Map<Class<?>, Fetcher> registry = Collections.synchronizedMap(new HashMap<Class<?>, Fetcher>());
	
	protected FetcherRegistry(){
	}
	
	public static FetcherRegistry getInstance() {
		return instance;
	}
	
	public Fetcher getRegistrant(Class<?> endpointType) {
		return registry.get(endpointType);
	}
	
	//Synchronize for multiple operations on the registry
	public boolean register(Class<?> endpointType, Fetcher clazz) {
		synchronized(registry){
			if(endpointType == null || clazz == null || registry.containsKey(endpointType)) {
				return false;
			}
			registry.put(endpointType, clazz);
			return true;
		}
	}
	
	public void unRegister(Class<?> endpointType) {
		registry.remove(endpointType);
	}
	
	public void replaceRegister(Class<?> endpointType, Fetcher clazz) {
		registry.replace(endpointType, clazz);
	}
	
	//Return a copy of the registry.  Don't want anything iterating over it outside of this object
	public Map<Class<?>, Fetcher> getRegistry() {
		Map<Class<?>, Fetcher> copy = new HashMap<Class<?>, Fetcher>();
		copy.putAll(registry);
		return copy;
	}
}