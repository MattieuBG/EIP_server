package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.Drawing;
import com.esb.server.morphia.MorphiaService;

public class DrawingDAO extends BasicDAO<Drawing, ObjectId>{
	public DrawingDAO() {
		super(MorphiaService.getDatastore());
	}
}
