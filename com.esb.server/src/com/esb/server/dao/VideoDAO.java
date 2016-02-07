package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;
import com.esb.sharedlibrary.entities.Video;

public class VideoDAO extends BasicDAO<Video, ObjectId> {
	public VideoDAO() {
		super(MorphiaService.getDatastore());
	}
}
