package com.esb.server.dao.impl;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.dao.VideoDAO;
import com.esb.server.entities.Video;

public class VideoServiceImpl extends BasicDAO<Video, ObjectId> implements VideoDAO
{
	public VideoServiceImpl(Class<Video> entityClass, Datastore ds) {
		super(entityClass, ds);
	}
}
