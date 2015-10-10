package com.esb.sharedlibrary.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public final class JSONConvertor {

	private JSONConvertor() {
	}

	/**
	 * Usage Object obj = new Object(...); String input =
	 * JSONConvertor.toJSON(obj);
	 **/
	static public String toJSON(Object obj) {
		String res = new String();

		try {
			ObjectMapper mapper = new ObjectMapper();
			res = mapper.writeValueAsString(obj);

			System.out.println("JSONConvertor.toJSON : " + res);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Usage String output [...]; => JSON string; Object obj =
	 * JSONConvertor.toObject(output, Object.class);
	 **/
	static public <T> T toObject(String str, Class<T> ctype) {
		T res = null;

		try {
			ObjectMapper mapper = new ObjectMapper();
			res = mapper.readValue(str, ctype);

			System.out.println("JSONConvertor.toObject : " + res.toString());

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

}
