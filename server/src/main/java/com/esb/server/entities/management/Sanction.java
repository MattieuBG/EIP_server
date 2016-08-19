package com.esb.server.entities.management;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Sanction {
	@Id
	public String id = ObjectId.get().toString();
	public Date creationDate;
	@Reference
	public User supervisor;
	public String reason;
	@Reference
	public User student;

}
