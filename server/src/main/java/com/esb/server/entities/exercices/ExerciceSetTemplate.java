package com.esb.server.entities.exercices;

import java.util.Date;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class ExerciceSetTemplate {
	public static enum EExerciceSetType {
		HOMEWORK, EXAM
	}
	@Id
	public String id = ObjectId.get().toString();
	public EExerciceSetType type;
	public Date todoDate;
	@Reference
	public List<Exercice> exercices;

	public ExerciceSetTemplate() {
		todoDate = new Date();
		exercices = Lists.newArrayList();
	}
}
