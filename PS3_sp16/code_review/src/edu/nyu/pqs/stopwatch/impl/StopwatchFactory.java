package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects.
 * It maintains references to all created IStopwatch objects and provides a
 * convenient method for getting a list of those objects.
 * @author abhishek bhunia
 */
public class StopwatchFactory {
  /**
   * ConcurrentHashMap is used for improved concurrency
   */
  private static ConcurrentHashMap<String,IStopwatch> factoryWatches = new ConcurrentHashMap<String,IStopwatch>();
  
  /**
  * Creates and returns a new IStopwatch object
  * @param id The identifier of the new object
  * @return The new IStopwatch object
  * @throws IllegalArgumentException if <code>id</code> is empty, null, or already
  *     taken.
  */
  public static IStopwatch getStopwatch(String id) {
    if (id == null || id == "") {
	  throw new IllegalArgumentException("Stopwatch id can't be null/empty");
	} else {
		Stopwatch factoryWatch = null;
		synchronized(factoryWatches) {
		  if (factoryWatches.containsKey(id)) {
			  throw new IllegalArgumentException("Stopwatch id is already taken");
		  } else {
			    factoryWatch = new Stopwatch(id);
			    factoryWatches.put(id, factoryWatch);			    
		  }
		}
		return factoryWatch;
	}
  }

  /**
  *  
  * @return a List of IStopwatch objects created so far.  
  * Returns an empty list if no IStopwatches have been created.
  */
  public static List<IStopwatch> getStopwatches() {
	synchronized(factoryWatches) {
	  return new ArrayList<IStopwatch>(factoryWatches.values());
	}    
  }

  /**
   * Prevent Instantiation
   */
  private StopwatchFactory() {
		
  }
}
