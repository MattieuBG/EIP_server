package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;
import com.esb.sharedlibrary.entities.Answer;

public class AnswerDAO extends BasicDAO<Answer, ObjectId>{
	public AnswerDAO() {
		super(MorphiaService.getDatastore());
	}
}
