package edu.nyu.pqs.stopwatch.demo;

import java.util.List;
import java.util.logging.Logger;

import edu.nyu.pqs.stopwatch.api.IStopwatch;
import edu.nyu.pqs.stopwatch.impl.StopwatchFactory;

/**
 * This is a simple program that demonstrates just some of
 * the functionality of the IStopwatch interface and StopwatchFactory class.
 * Just because this class runs successfully does not mean that the assignment is
 * complete.  It is up to you to implement the methods of IStopwatch and StopwatchFactory
 *
 */
public class SlowThinker {

	/** use a logger instead of System.out.println */
	private static final Logger logger = 
	    Logger.getLogger("edu.nyu.pqs.ps4.demo.SlowThinker");

	/**
	 * Run the SlowThinker demo application
	 * @param args a single argument specifying the number of threads
	 */
	public static void main(String[] args) {
		SlowThinker thinker = new SlowThinker();
		thinker.go();
	}

	/**
	 * Starts the slowthinker object
	 * It will get a stopwatch, set a number of lap times, stop the watch
	 * and then print out all the lap times
	 *
	 */
	private void go() {
		Runnable runnable = new Runnable() {
			public void run() {
				IStopwatch stopwatch = StopwatchFactory.getStopwatch(
				    "ID " );//+ Thread.currentThread().getId());
			  stopwatch.start();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ie) { /* safely ignore this */ }

			  
			  
			  stopwatch.lap();
			  
			  try {
					Thread.sleep(2000);
				} catch (InterruptedException ie) { /* safely ignore this */ }

			  stopwatch.stop();
			  
			  try {
					Thread.sleep(4000);
				} catch (InterruptedException ie) { /* safely ignore this */ }
			  
			  stopwatch.start();
			  
			  try {
					Thread.sleep(4000);
				} catch (InterruptedException ie) { /* safely ignore this */ }
			  
			  stopwatch.lap();
			  
			  try {
					Thread.sleep(3000);
				} catch (InterruptedException ie) { /* safely ignore this */ }
			  
			  stopwatch.stop();
			  
			  try {
					Thread.sleep(3000);
				} catch (InterruptedException ie) { /* safely ignore this */ }
			  
			  stopwatch.start();
			  
			  try {
					Thread.sleep(2000);
				} catch (InterruptedException ie) { /* safely ignore this */ }
			  
			  stopwatch.lap();
			  
			  try {
					Thread.sleep(2000);
				} catch (InterruptedException ie) { /* safely ignore this */ }
			  

			  
			  
			
//				stopwatch.start();
//				for (int i = 0; i < 10; i++) {
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException ie) { /* safely ignore this */ }
//					stopwatch.lap();
//				}
//				stopwatch.stop();
				List<Long> times = stopwatch.getLapTimes();
				logger.info(times.toString());
				
			}
		};
		Thread thinkerThread = new Thread(runnable);
		Thread thinkThread2 = new Thread(runnable);
		Thread thinkThread3 = new Thread(runnable);
		Thread thinkThread4 = new Thread(runnable);
		Thread thinkThread5 = new Thread(runnable);
		Thread thinkThread6 = new Thread(runnable);
		Thread thinkThread7 = new Thread(runnable);
		Thread thinkThread8 = new Thread(runnable);
		Thread thinkThread9 = new Thread(runnable);
		thinkerThread.start();
		thinkThread2.start();
		thinkThread3.start();
		thinkThread4.start();
		thinkThread5.start();
		thinkThread6.start();
		thinkThread7.start();
		thinkThread8.start();
		thinkThread9.start();
		
	}
}
