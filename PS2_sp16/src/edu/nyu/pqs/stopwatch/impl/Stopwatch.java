package edu.nyu.pqs.stopwatch.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * Stopwatch class implements the IStopwatch Interface.
 * This has the functionality implemented for a stop watch
 * @author himaja
 *
 */
public class Stopwatch implements IStopwatch{

	private enum State {RUNNING,PAUSED,RESET};
	private long start;
	private long pausedTime;
	private final String id;
	private List<Long> lapTimes;
	private long elapsedTime;
	private long stopToStartTime;
	State currentState;

	private static final String ERR_NOT_RUNNING = "Stop watch not running currently";
	private static final String ERR_ALREADY_RUNNING = "Stop watch already running";

	private final Object lock = new Object();

	Stopwatch(String id){
		this.id = id;
		start = 0;
		pausedTime = 0;
		elapsedTime = 0;
		stopToStartTime = 0;
		lapTimes = new CopyOnWriteArrayList<Long>();
		currentState = State.RESET;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void start() {
		long time = System.currentTimeMillis();
		synchronized(lock){
			if (isValidState(State.RUNNING)){
				throw new IllegalStateException(ERR_ALREADY_RUNNING);
			}
			else if (isValidState(State.RESET)){
				start = time;
			}
			else if (isValidState(State.PAUSED)){
				stopToStartTime += time - pausedTime;
			}
			currentState = State.RUNNING;
		}
	}

	@Override
	public void lap() {
		long time = System.currentTimeMillis();
		synchronized(lock){
			if(!isValidState(State.RUNNING)){
				throw new IllegalStateException(ERR_NOT_RUNNING);
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
			if (!isValidState(State.RUNNING)){
				throw new IllegalStateException(ERR_NOT_RUNNING);
			}
			pausedTime = time;
			currentState = State.PAUSED;
		}
	}

	@Override
	public void reset() {
		synchronized(lock){
			start = 0;
			pausedTime = 0;
			elapsedTime = 0;
			stopToStartTime = 0;
			lapTimes.clear();
			currentState = State.RESET;
		}
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
	/**
	 * Checks the current state of the stopwatch. Can be used to check the state before performing 
	 * any operation on the stop watch. 
	 * @param reqState - a state of enum type State
	 * @return true if the current state of the stop watch matches with the state we are checking for.
	 */
	private boolean isValidState(State reqState){
		if (currentState.equals(reqState)){
			return true;
		}
		return false;

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
		Stopwatch other = (Stopwatch) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
