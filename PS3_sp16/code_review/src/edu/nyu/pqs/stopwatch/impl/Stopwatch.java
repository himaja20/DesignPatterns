package edu.nyu.pqs.stopwatch.impl;

import edu.nyu.pqs.stopwatch.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * Implementation of Stopwatch interface
 * @author abhishek bhunia
 *
 */
public final class Stopwatch implements IStopwatch  {

  private static enum States {
    Running, Stopped
  };
  
  //instance variables for different properties of stopwatch
  private Object lock;
  private final String sID;
  private States stopwatchState 	= States.Stopped;  
  private long startTime 		= 0;
  private ArrayList<Long> laps 		= new ArrayList<Long>();

  /**
   * Stopwatch constructor needs a valid string to create object
   * No two stopwatches can have the same id @see StopwatchFactory
   * @param s
   */
  Stopwatch(String s) {	
	if (s == null || s == "") {
	 throw new IllegalArgumentException ("Stopwatch name can't be missing.");
	}
    this.sID 		= s;
    this.lock 		= new Object();    	  
  }
  
  /**
   * Clears the stored laps and resets the timer
   */
  @Override
  public void reset() {
	synchronized(lock) {
      if (stopwatchState != States.Stopped) {        
        laps.clear();  
        stopwatchState 	        = States.Stopped;
        startTime 		= 0;
      }      
    }
  }
  
  /**
   * Starts the timer and stores start time as the current time in milliseconds
   * @throws exception if stopwatch is already running
   */
  @Override
  public void start() {
	synchronized(lock) {
	  if (stopwatchState == States.Running) {
	    throw new IllegalStateException ("Start Failed: Stopwatch is already running");
	  } else {
	      startTime 		= getSystemTime();
		  stopwatchState 	= States.Running;
	  }
	}	
  }
  
  /**
   * Stops the timer, increments current lap and marks the state change as 'stopped'
   * @throws exception if timer is already stopped
   */
  @Override
  public void stop() {
    if (stopwatchState == States.Stopped) {
      throw new IllegalStateException ("Stopwatch is already stopped");      
    } else {
        synchronized(lock) {
        	lap();
        	stopwatchState = States.Stopped;        	
        }
    }
  }
  
  /**
   * Records a lap and resets the start time
   * @throws exception if the watch is already stopped
   */
  @Override
  public void lap() {
    synchronized(lock) {
      if (stopwatchState == States.Stopped) {
        throw new IllegalStateException ("Can't record lap: Stopwatch is not running");
      } else {
    	  long newStartTime     = getSystemTime();    	  
    	  laps.add(newStartTime - startTime);
    	  startTime 		= newStartTime;
      }
    }
  }
  
  /**
   * @return list of recorded laps
   */
  @Override
  public List<Long> getLapTimes() {
	List<Long> lapTimes = new ArrayList<Long>();
    synchronized(lock) {      
      for (Long lap : laps) {
        lapTimes.add(lap);
      }
    }
    return lapTimes;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(sID.hashCode());
  }
  
  /**
   * @return id of the stopwatch
   */
  @Override
  public String getId() {
	return sID; 
  }
  
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("Stopwatch: ");
    result.append(getId());
    return result.toString();
  }
  
  @Override
  public boolean equals(Object ob) {
    if (ob != null && (ob instanceof Stopwatch)) {
      Stopwatch watch = (Stopwatch)ob;
      return getId().equals(watch.getId());
    } else {
        return false;
    }
  }
  
  /**
   * gets system time in nanoseconds and then converts it to milliseconds for all 
   * time keeping purposes
   * @return
   */
  private long getSystemTime() {
    return TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
  }
  
}
