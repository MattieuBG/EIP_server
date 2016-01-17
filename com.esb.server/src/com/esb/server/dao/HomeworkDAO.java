package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.Homework;
import com.esb.server.morphia.MorphiaService;

public class HomeworkDAO extends BasicDAO<Homework, ObjectId>{
	public HomeworkDAO() {
		super(MorphiaService.getDatastore());
	}
}
