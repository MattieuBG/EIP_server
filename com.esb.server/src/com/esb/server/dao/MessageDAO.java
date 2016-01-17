package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.Message;
import com.esb.server.morphia.MorphiaService;

public class MessageDAO extends BasicDAO<Message, ObjectId>{
	public MessageDAO() {
		super(MorphiaService.getDatastore());
	}
}
