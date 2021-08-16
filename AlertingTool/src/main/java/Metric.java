/*
 * metric object stores the meta data like the section of the website, timestamp and no of hits of the section
 */
public class Metric {

	int count;
	String section;
	String epochTime;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getEpochTime() {
		return epochTime;
	}
	public void setEpochTime(String epochTime) {
		this.epochTime = epochTime;
	}
	
	
	
}
