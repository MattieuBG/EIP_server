package com.esb.server.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

/**
 * holds our Morphia and Datastore instances
 * @author Alex
 *
 */
public class MorphiaService
{ 
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	private Morphia morphia;
	private Datastore datastore;
	/*
	###############################
	#         Constructor         #
	###############################
	*/
	public MorphiaService() {

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
	###############################
	#           Getter            #
	###############################
	*/
	public Morphia getMorphia() {
		return morphia;
	}

	public Datastore getDatastore() {
		return datastore;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	public void setMorphia(Morphia morphia) {
		this.morphia = morphia;
	}
}