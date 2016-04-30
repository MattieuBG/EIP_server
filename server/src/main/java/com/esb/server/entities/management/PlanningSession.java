package com.esb.server.entities.management;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class PlanningSession {

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
