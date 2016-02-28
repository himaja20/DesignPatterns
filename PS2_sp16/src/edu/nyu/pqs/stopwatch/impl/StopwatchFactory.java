package edu.nyu.pqs.stopwatch.impl;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.pqs.stopwatch.api.IStopwatch;
import edu.nyu.pqs.stopwatch.stopwatchImpl.StopwatchOperations;

/**
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects.
 * It maintains references to all created IStopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {

	private static List<IStopwatch> obList = new CopyOnWriteArrayList<IStopwatch>(); 
	
	/**
	 * Creates and returns a new IStopwatch object
	 * @param id The identifier of the new object
	 * @return The new IStopwatch object
	 * @throws IllegalArgumentException if <code>id</code> is empty, null, or already
   *     taken.
	 */
	public static IStopwatch getStopwatch(String id) {
		if (id == null){
			throw new IllegalArgumentException("object null. Try again");
		}
		IStopwatch newOb;
		
		synchronized(StopwatchFactory.class){
			Iterator<IStopwatch> iterator = obList.iterator();
			while(iterator.hasNext()){
				IStopwatch ob = iterator.next();
				if ((ob.getId()) == id){
					throw new IllegalArgumentException("invalid stopwatch object. Try again with a different ID");
				}
			}
			newOb = StopwatchOperations.newInstance(id);
		obList.add(newOb);
		}
		return newOb;
	}

	/**
	 * Returns a list of all created stopwatches
	 * @return a List of al creates IStopwatch objects.  Returns an empty
	 * list if no IStopwatches have been created.
	 */
	public static List<IStopwatch> getStopwatches() {
		List<IStopwatch> obListCopy = new CopyOnWriteArrayList<IStopwatch>();
		for (IStopwatch ob : obList){
			obListCopy.add(ob);
		}
		return obListCopy;
	}
}
