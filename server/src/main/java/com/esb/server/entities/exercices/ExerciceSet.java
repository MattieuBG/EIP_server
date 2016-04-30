package com.esb.server.entities.exercices;

import java.util.List;
import java.util.Map;

import jersey.repackaged.com.google.common.collect.Maps;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class ExerciceSet {
	public static enum EExerciceSetType {
		HOMEWORK, EXAM
	}

	@Id
	public String id = ObjectId.get().toString();
	public EExerciceSetType type;
	@Embedded
	public Notation notation;
	@Embedded
	public Map<ObjectId, List<Answer>> answers;

	public ExerciceSet() {
		answers = Maps.newHashMap();
	}

}
