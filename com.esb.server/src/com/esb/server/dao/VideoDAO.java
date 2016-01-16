package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import com.esb.server.entities.Video;

public interface VideoDAO extends DAO<Video, ObjectId>
{   	 
    
}