package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.Question;
import com.esb.server.morphia.MorphiaService;

public class QuestionDAO extends BasicDAO<Question, ObjectId>{
	public QuestionDAO() {
		super(MorphiaService.getDatastore());
	}
}
