package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.Course;
import com.esb.server.morphia.MorphiaService;

public class CourseDAO extends BasicDAO<Course, ObjectId>{
	public CourseDAO() {
		super(MorphiaService.getDatastore());
	}
}
