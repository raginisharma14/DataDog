import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;


public class ReaderTest {
	
	private static final String FILE_NAME = "src/main/resources/sample_csv.txt";
	
	@Test
	public void test_readInput() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
		String actual = br.readLine();
		String[] sarray = actual.split(",");
		Assert.assertEquals(sarray[0], "\"remotehost\"");
		br.close();
	}
	
}
