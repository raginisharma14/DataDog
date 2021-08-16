import java.util.Deque;

import org.junit.Assert;
import org.junit.Test;

public class SharedResourceTest {
	
	@Test
	public void test_addtoQueue() {
		
		SharedResource sr = new SharedResource();
		sr.addToQueue("\"10.0.0.2\",\"-\",\"apache\",1549573860,\"GET /api/user HTTP/1.0\",200,1234");
		Deque<Pair<Long, Metric>> queue  = sr.getQueue();
		
		Assert.assertEquals(queue.getFirst().getKey(), (Long)1549573860L);
	}
	

	@Test
	public void test_removeFromQueue() {
		SharedResource sr = new SharedResource();
		sr.addToQueue("\"10.0.0.2\",\"-\",\"apache\",1549573860,\"GET /api/user HTTP/1.0\",200,1234");
		sr.removeFromQueue();
		Deque<Pair<Long, Metric>> queue  = sr.getQueue();
		
		Assert.assertTrue(queue.isEmpty());
		
	}
}
