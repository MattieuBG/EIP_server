package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.User;
import com.esb.server.morphia.MorphiaService;

public class UserDAO extends BasicDAO<User, ObjectId>{
	public UserDAO() {
		super(MorphiaService.getDatastore());
	}
}
