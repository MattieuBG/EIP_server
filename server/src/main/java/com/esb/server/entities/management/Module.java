package com.esb.server.entities.management;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.esb.server.entities.exercices.ExerciceSet;
import com.esb.server.entities.exercices.Notation;

@Entity
public class Module {
	@Id
	public String id = ObjectId.get().toString();
	@Reference
	public ModuleTemplate template;
	@Reference
	public User user;
	@Embedded
	public Notation notation;
	@Reference
	public List<ExerciceSet> exerciceSets;

	public Module() {
		notation = new Notation();
		exerciceSets = Lists.newArrayList();
	}

}
