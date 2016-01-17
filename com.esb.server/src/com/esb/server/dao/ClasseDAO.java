package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.Classe;
import com.esb.server.morphia.MorphiaService;

public class ClasseDAO extends BasicDAO<Classe, ObjectId>{
	public ClasseDAO() {
		super(MorphiaService.getDatastore());
	}
}
