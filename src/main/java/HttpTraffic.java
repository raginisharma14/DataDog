public class HttpTraffic {
	
	private static final String FILE_NAME = "src/main/resources/sample_csv.txt";
		public static void main(String args[]) {		
		SharedResource msgQueue = new SharedResource();
		// reader to read the input from any source
		
		Reader reader = new Reader(msgQueue, FILE_NAME);
		reader.readInput();		
		// processor to process the input
		Processor processor = new Processor(msgQueue);
		processor.processQueue();		
	}
}
