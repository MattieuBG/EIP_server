package com.esb.server.entities.exercices;

import java.util.List;
import java.util.Map;

import jersey.repackaged.com.google.common.collect.Maps;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class ExerciceSet {

	@Id
	public String id = ObjectId.get().toString();
	@Reference
	public ExerciceSetTemplate template;
	@Embedded
	public Notation notation;
	@Embedded
	public Map<ObjectId, List<Answer>> answers;

	public ExerciceSet() {
		answers = Maps.newHashMap();
		notation = new Notation();
	}

}
