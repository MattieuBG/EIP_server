package com.esb.server.entities.management;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class PlanningSession {

	@Id
	public String id = ObjectId.get().toString();
	public Date startDate;
	public Date endDate;
	public String title;
	@Reference
	public User speaker;

	public PlanningSession() {
		startDate = new Date();
		endDate = new Date();
	}

}
