package com.esb.server.entities.management;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Absence {
	@Id
	public String id = ObjectId.get().toString();
	public Date startDate;
	public Date endDate;
	public String justification;
	@Reference
	public User user;
}
