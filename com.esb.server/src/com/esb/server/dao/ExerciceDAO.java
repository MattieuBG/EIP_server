package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.Exercice;
import com.esb.server.morphia.MorphiaService;

public class ExerciceDAO extends BasicDAO<Exercice, ObjectId>{
	public ExerciceDAO() {
		super(MorphiaService.getDatastore());
	}
}
