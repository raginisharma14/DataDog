import java.util.Deque;

public class Processor {
	// timeCheck is 120sec/2mins as per the requirement.
	private static final int TIME_CHECK = 120;

	// these values can be configurable as required
	private static final int MIN_THRESHOLD = 10;
	private static final int MAX_THRESHOLD = 100;
	SharedResource sharedResource;

	Processor(SharedResource sharedResource) {
		this.sharedResource = sharedResource;
	}

	public synchronized void processQueue() {
		Deque<Pair<Long, Metric>> queue = sharedResource.getQueue();
		Pair<Long, Metric> initialTime = queue.getFirst();
		Pair<Long, Metric> latestTime = queue.getLast();
		if (latestTime.getKey() - initialTime.getKey() > TIME_CHECK) {
			sharedResource.removeFromQueue();
			healthCheck(MAX_THRESHOLD, MIN_THRESHOLD, latestTime.getKey());
		}
	}
	/*
	 * Iterates over the queue and count the number of hits in last 120 sec/2mins
	 */
	public void healthCheck(int maxThreshold, int minThreshold, long triggeredTime) {
		int count = sharedResource.getTotalHits();

		if (count / TIME_CHECK > MAX_THRESHOLD) {
			System.out.println("High traffic generated an alert -hits =" + count + " , triggered at " + triggeredTime);
		}
		if (count / TIME_CHECK < MIN_THRESHOLD) {
			System.out.println("Low traffic generated an alert -hits =" + count + " , triggered at " + triggeredTime);
		}
	}

}
