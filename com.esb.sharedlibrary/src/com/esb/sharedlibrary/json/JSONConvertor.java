package com.esb.sharedlibrary.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
			//mapper.registerModule(new JavaTimeModule());
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
			//mapper.registerModule(new JavaTimeModule());
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
