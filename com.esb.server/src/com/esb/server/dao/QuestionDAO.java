package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;
import com.esb.sharedlibrary.entities.Question;

public class QuestionDAO extends BasicDAO<Question, ObjectId>{
	public QuestionDAO() {
		super(MorphiaService.getDatastore());
	}
}
