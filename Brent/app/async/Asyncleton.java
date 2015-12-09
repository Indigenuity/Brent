package async;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import play.Logger;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import async.work.WorkType;
import async.work.WorkerRegistry;

public class Asyncleton {

	private static final Asyncleton instance = new Asyncleton();
	
	public static final int DEFAULT_NUM_WORKERS = 5;
	private final Map<WorkType, ActorRef> masters = Collections.synchronizedMap(new HashMap<WorkType, ActorRef>());
	
	private ActorSystem mainSystem;
	private ActorRef mainListener;
	private ActorRef mainMaster;
	
	
	private boolean initialized;
	
	protected Asyncleton(){
		initialize();
		for(Entry<WorkType, Class<?>> entry: WorkerRegistry.getInstance().getRegistry().entrySet()){
			if(entry.getValue() == MainWorker.class){
				masters.put(entry.getKey(), mainMaster);
			}
			else{
				ActorRef master = mainSystem.actorOf(Props.create(GenericMaster.class, DEFAULT_NUM_WORKERS, mainListener, entry.getValue()));
				masters.put(entry.getKey(), master);
			}
		}
	}
	
	public ActorRef getMaster(WorkType workType) {
		return masters.get(workType);
	}
	
	public void initialize() {
		if(!initialized) {
			
			Logger.info("Starting up main async system");
			mainSystem = ActorSystem.create("mainSystem");
			mainListener = mainSystem.actorOf(Props.create(Listener.class), "mainListener");
			mainMaster = mainSystem.actorOf(Props.create(Master.class, 60, mainListener));
			Logger.info("Main async system ready for jobs");
		}
		initialized = true;
	}

	public static Asyncleton instance() { 
		return instance;
	}

	public static Asyncleton getInstance() {
		return instance;
	}

	public ActorSystem getMainSystem() {
		return mainSystem;
	}

	public ActorRef getMainListener() {
		return mainListener;
	}

	public ActorRef getMainMaster() {
		return mainMaster;
	}
	
	

}