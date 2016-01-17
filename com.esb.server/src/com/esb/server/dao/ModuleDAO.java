package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.entities.Module;
import com.esb.server.morphia.MorphiaService;

public class ModuleDAO extends BasicDAO<Module, ObjectId>{
	public ModuleDAO() {
		super(MorphiaService.getDatastore());
	}
}
