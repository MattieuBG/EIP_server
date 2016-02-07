package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;
import com.esb.sharedlibrary.entities.Homework;

public class HomeworkDAO extends BasicDAO<Homework, ObjectId>{
	public HomeworkDAO() {
		super(MorphiaService.getDatastore());
	}
}
