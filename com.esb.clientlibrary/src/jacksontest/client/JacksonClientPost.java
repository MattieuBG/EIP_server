package jacksontest.client;

import com.esb.client.ESBClient;
import com.esb.poco.Track;

public class JacksonClientPost {
	public static void main(String[] args) {

		Track t = new Track();
		t.setSinger("my singer");
		t.setTitle("new title !");

		ESBClient.sendObject("metallica", t);
	}
}
