package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.Conversation;
import com.esb.server.morphia.MorphiaService;

public class ConversationDAO extends BasicDAO<Conversation, ObjectId>{
	public ConversationDAO() {
		super(MorphiaService.getDatastore());
	}
}
