package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;
import com.esb.sharedlibrary.entities.Image;

public class ImageDAO extends BasicDAO<Image, ObjectId>{
	public ImageDAO() {
		super(MorphiaService.getDatastore());
	}
}
