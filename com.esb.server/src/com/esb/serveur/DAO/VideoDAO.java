package com.esb.serveur.DAO;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.esb.server.entities.Video;


public class VideoDAO extends BasicDAO<Video, String>
{
	public VideoDAO(Morphia morphia, Mongo mongo ) {
        super(mongo, morphia, "myDb");
    }
}
