import java.util.ArrayDeque;
import java.util.Deque;

public class SharedResource {

	int totalHits =0;
	Deque<Pair<Long, Metric>> queue = new ArrayDeque<Pair<Long, Metric>>();
	
	public synchronized void addToQueue(String line) {
		Metric metric = retriveMetrics(line);
		
		Long currentTime = Long.parseLong(metric.getEpochTime());
		if (!queue.isEmpty() && queue.getLast().getKey() == currentTime) {
			Pair<Long, Metric> latest = queue.removeLast();
			Metric updatedMetric = new Metric();
			updatedMetric.setCount(latest.getValue().getCount() + 1);
			Pair<Long, Metric> newPair = new Pair<Long, Metric>(latest.getKey(), updatedMetric);
			queue.offer(newPair);
		} else {
			metric.setCount(1);
			Pair<Long, Metric> newPair = new Pair<Long, Metric>(currentTime, metric);
			queue.offer(newPair);
		}
		totalHits++;
	}

	public synchronized void removeFromQueue() {
		if(!queue.isEmpty()) {
			Pair<Long, Metric> p = queue.poll();
			totalHits = totalHits - p.getValue().getCount();
		}
	}
	public Metric retriveMetrics(String line) {

		String[] record = line.split(",");
		String epochTime = record[3];
		String section = record[4];
		Metric metric = new Metric();
		metric.setEpochTime(epochTime);
		metric.setSection(section);
		return metric;
	}

	public int getTotalHits() {
		return totalHits;
	}

	public void setTotalHits(int totalHits) {
		this.totalHits = totalHits;
	}

	public Deque<Pair<Long, Metric>> getQueue() {
		return queue;
	}

	public void setQueue(Deque<Pair<Long, Metric>> queue) {
		this.queue = queue;
	}

}