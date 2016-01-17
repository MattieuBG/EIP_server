package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.Response;
import com.esb.server.morphia.MorphiaService;

public class ResponseDAO extends BasicDAO<Response, ObjectId>{
	public ResponseDAO() {
		super(MorphiaService.getDatastore());
	}
}
