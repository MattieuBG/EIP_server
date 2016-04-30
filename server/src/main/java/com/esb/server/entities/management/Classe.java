package com.esb.server.entities.management;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Classe {

	@Id
	public String id = ObjectId.get().toString();
	public String title;
	@Reference
	public User supervisor;
}
