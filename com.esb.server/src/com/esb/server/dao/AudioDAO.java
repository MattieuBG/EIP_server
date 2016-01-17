package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.Audio;
import com.esb.server.morphia.MorphiaService;

public class AudioDAO extends BasicDAO<Audio, ObjectId>{
	public AudioDAO() {
		super(MorphiaService.getDatastore());
	}
}
