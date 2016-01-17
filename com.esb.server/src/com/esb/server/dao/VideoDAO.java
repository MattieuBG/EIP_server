package com.esb.server.dao;

import org.bson.types.ObjectId;
import com.esb.server.entities.Video;

import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;

public class VideoDAO extends BasicDAO<Video, ObjectId> {
	public VideoDAO() {
		super(MorphiaService.getDatastore());

	}
}
