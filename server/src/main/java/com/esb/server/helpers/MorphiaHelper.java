package com.esb.server.helpers;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public final class MorphiaHelper {
	private final Morphia morphia;
	private final Datastore datastore;
	 //private static String url = "localhost";
	 //private static String url = "mongo";
	private static String url = "51.254.99.218";

	/** Holder */
	private static class SingletonHolder {
		private final static MorphiaHelper instance = new MorphiaHelper();
	}

	private static MorphiaHelper getInstance() {
		return SingletonHolder.instance;
	}

	private MorphiaHelper() {
		final MongoClient mongoClient = new MongoClient(url);
		this.morphia = new Morphia();
		final String databaseName = "eschoolbag";
		this.datastore = morphia.createDatastore(mongoClient, databaseName);

		// morphia.getMapper().getConverters().addConverter(new
		// LocalDateTimeConverter());
	}

	public static Morphia getMorphia() {
		return getInstance().morphia;
	}

	public static Datastore getDatastore() {
		return getInstance().datastore;
	}

}