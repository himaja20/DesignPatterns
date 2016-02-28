package edu.nyu.pqs.stopwatch.stopwatchImpl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

public class StopwatchOperations implements IStopwatch{

	private enum State {RUNNING,PAUSED,RESET};
	private long start;
	private long pausedTime;
	private final String id;
	private List<Long> lapTimes;
	private long elapsedTime;
	private long stopToStartTime;
	State currentState;
	
	private final Object lock = new Object();
	
	private StopwatchOperations(String id){
		this.id = id;
		start = 0;
		pausedTime = 0;
		elapsedTime = 0;
		stopToStartTime = 0;
		lapTimes = new CopyOnWriteArrayList<Long>();
		currentState = State.RESET;
	}
	
	public static IStopwatch newInstance(String id){
		return new StopwatchOperations(id);
	}

	@Override
	public String getId() {
		String idCopy = id;
		return idCopy;
	}

	@Override
	public synchronized void start() {
		long time = System.currentTimeMillis();
		synchronized(lock){
			if (currentState.equals(State.RUNNING)){
				throw new IllegalStateException("Stopwatch is already running");
			}
			else if (currentState.equals(State.RESET)){
				start = time;
			}
			else if (currentState.equals(State.PAUSED)){
				stopToStartTime += time - pausedTime;
			}
		currentState = State.RUNNING;
		}
	}

	@Override
	public void lap() {
		long time = System.currentTimeMillis();
		synchronized(lock){
			if(!currentState.equals(State.RUNNING)){
				throw new IllegalStateException("Stop watch not running currently");
			}
			elapsedTime = time - start;
			lapTimes.add(elapsedTime - stopToStartTime);
			start = time;
			stopToStartTime = 0;
		}
	}
		
	@Override
	public void stop() {
		long time = System.currentTimeMillis();
		synchronized(lock){
			if (!currentState.equals(State.RUNNING)){
				throw new IllegalStateException("Stop watch is not running currently");
			}
			pausedTime = time;
			currentState = State.PAUSED;
		}
	}

	@Override
	public synchronized void reset() {
		start = 0;
		pausedTime = 0;
		elapsedTime = 0;
		stopToStartTime = 0;
		lapTimes.clear();
		currentState = State.RESET;
	}

	@Override
	public List<Long> getLapTimes() {
		List<Long> lapTimesCopy = new CopyOnWriteArrayList<Long>();
		synchronized(lock){
			for (long i : lapTimes){
				lapTimesCopy.add(i);
			}
		}
		return lapTimesCopy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StopwatchOperations other = (StopwatchOperations) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
