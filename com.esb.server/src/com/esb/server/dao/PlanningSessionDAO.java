package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;
import com.esb.sharedlibrary.entities.PlanningSession;

public class PlanningSessionDAO extends BasicDAO<PlanningSession, ObjectId>{
	public PlanningSessionDAO() {
		super(MorphiaService.getDatastore());
	}
}
