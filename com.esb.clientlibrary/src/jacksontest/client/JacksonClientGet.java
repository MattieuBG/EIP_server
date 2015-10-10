package jacksontest.client;

import com.esb.client.ESBClient;
import com.esb.poco.Track;

public class JacksonClientGet {
	public static void main(String[] args) {

		Track t = ESBClient.getObject("metallica", Track.class);
		
		System.out.println("Output from Server .... \n");
		System.out.println("Track : " + t);

	}
}
