import java.util.ArrayDeque;

import org.junit.Assert;
import org.junit.Test;
public class ProcessorTest {

	private static final Long LatestTime = 1234L;
	private static final Long CurrentTime = 1400L;
	@Test
	public void test_processQueue() {
		
		SharedResource sr = new SharedResource();
		ArrayDeque<Pair<Long, Metric>> queue = new ArrayDeque<Pair<Long, Metric>>();
		Pair<Long, Metric> p1 = new Pair<Long, Metric>(LatestTime, new Metric());
		Pair<Long, Metric> p2 = new Pair<Long, Metric>(CurrentTime, new Metric());
		
		queue.add(p1);
		queue.add(p2);
		sr.setQueue(queue);
		
		Processor processor = new Processor(sr);
		processor.processQueue();
		
		Assert.assertEquals(sr.getTotalHits(), 0);
		
	}
	
}
