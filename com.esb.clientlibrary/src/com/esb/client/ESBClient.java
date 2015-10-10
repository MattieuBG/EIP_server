package com.esb.client;

import com.esb.sharedlibrary.json.JSONConvertor;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public final class ESBClient {

	private static String baseUrl = "http://localhost:8080/com.esb.server/rest/json/";

	private ESBClient() {
	}

	static public <T> T getObject(String appName, Class<T> ctype) {
		String url = baseUrl + appName + "/get";
		T res = null;
		try {

			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			String output = response.getEntity(String.class);

			res = JSONConvertor.toObject(output, ctype);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	static public String sendObject(String appName, Object obj) {
		String output = new String();
		String url = baseUrl + appName + "/post";
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			String input = new String();

			input = JSONConvertor.toJSON(obj);

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			output = response.getEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
}
