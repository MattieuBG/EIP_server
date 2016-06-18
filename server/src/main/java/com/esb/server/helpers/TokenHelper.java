package com.esb.server.helpers;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import com.esb.server.entities.management.User;

public final class TokenHelper {
	private static SecureRandom random = new SecureRandom();
	private static Map<String, User> tokens = new HashMap<String, User>();

	private TokenHelper() {

	}

	public static User getUser(String token) {
		return tokens.get(token);
	}

	public static String setUser(User user) {
		String token = generateToken();
		tokens.put(token, user);
		return token;
	}

	private static String generateToken() {
		return new BigInteger(130, random).toString(32);
	}
}
