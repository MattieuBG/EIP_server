package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;
import com.esb.sharedlibrary.entities.Exercice;

public class ExerciceDAO extends BasicDAO<Exercice, ObjectId>{
	public ExerciceDAO() {
		super(MorphiaService.getDatastore());
	}
}
