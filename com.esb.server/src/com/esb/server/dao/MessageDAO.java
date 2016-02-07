package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;
import com.esb.sharedlibrary.entities.Message;

public class MessageDAO extends BasicDAO<Message, ObjectId>{
	public MessageDAO() {
		super(MorphiaService.getDatastore());
	}
}
