package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;
import com.esb.sharedlibrary.entities.User;

public class UserDAO extends BasicDAO<User, ObjectId>{
	public UserDAO() {
		super(MorphiaService.getDatastore());
	}
}
