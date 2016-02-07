package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;
import com.esb.sharedlibrary.entities.Drawing;

public class DrawingDAO extends BasicDAO<Drawing, ObjectId>{
	public DrawingDAO() {
		super(MorphiaService.getDatastore());
	}
}
