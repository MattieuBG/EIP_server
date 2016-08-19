package com.esb.server.entities.exercices;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.esb.server.entities.management.PlanningSession;

@Entity
public class ExerciceSetTemplate {
	public enum EExerciceSetType {
		HOMEWORK, EXAM
	}
	@Id
	public String id = ObjectId.get().toString();
	public String title;
	public EExerciceSetType type;
	@Reference
	public PlanningSession todoDate;
	@Reference
	public List<Exercice> exercices;

	public ExerciceSetTemplate() {
		exercices = Lists.newArrayList();
	}
}
