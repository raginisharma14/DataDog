import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*
 * Reader class reads input from CSV line by line and stores into the msgQueue.
 * 
 */
public class Reader {

	SharedResource msgQueue;
	String fileName;
	Reader(SharedResource msgQueue, String fileName) {
		this.msgQueue = msgQueue;
		this.fileName= fileName;
	}

	public void readInput() {
		// Read CSV file line by line
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			br.readLine();
			String line = null;
			line = br.readLine();
			while (line != null) {
				msgQueue.addToQueue(line);
				line = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}
