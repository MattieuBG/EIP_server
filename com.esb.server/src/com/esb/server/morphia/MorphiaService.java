package com.esb.server.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

/**
 * holds our Morphia and Datastore instances
 * 
 * @author Alex
 *
 */

public final class MorphiaService {
	/*
	 * ##############
	 * # Attributes #
	 * ##############
	 */
	private Morphia morphia;
	private Datastore datastore;

	/** Holder */
	private static class SingletonHolder {
		private final static MorphiaService instance = new MorphiaService();
	}

	private static MorphiaService getInstance() {
		return SingletonHolder.instance;
	}

	/*
	 * ###############
	 * # Constructor #
	 * ###############
	 */
	private MorphiaService() {

		// we use MongoClient to connect the local host (127.0.0.1)
		// (assuming this is where your mongodb instance is running)
		// on port 27017 (the default port)
		MongoClient mongoClient = new MongoClient("xxx.x.x.x:xxxx");

		// create a new morphia instance
		this.morphia = new Morphia();
		String databaseName = "eschoolBag";
		this.datastore = morphia.createDatastore(mongoClient, databaseName);
	}

	/*
	 * ###########
	 * # Getter #
	 * ###########
	 */
	public static Morphia getMorphia() {
		return getInstance().morphia;
	}

	public static Datastore getDatastore() {
		return getInstance().datastore;
	}

	/*
	 * ###########
	 * # Setter #
	 * ###########
	 */
	public static void setDatastore(Datastore datastore) {
		getInstance().datastore = datastore;
	}

	public static void setMorphia(Morphia morphia) {
		getInstance().morphia = morphia;
	}
}