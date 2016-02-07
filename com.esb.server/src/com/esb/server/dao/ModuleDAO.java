package com.esb.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import com.esb.server.morphia.MorphiaService;
import com.esb.sharedlibrary.entities.Module;

public class ModuleDAO extends BasicDAO<Module, ObjectId>{
	public ModuleDAO() {
		super(MorphiaService.getDatastore());
	}
}
