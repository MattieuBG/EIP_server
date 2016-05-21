package com.esb.server.entities.exercices;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Exercice {
	public static enum EExerciceState {
		TODO, DOING, FINISHED, CORRECTED
	}

	@Id
	public String id = ObjectId.get().toString();
	public EExerciceState state;
	public String title;
	public String statement;
	@Embedded
	public List<Question> questions;
	
	public Exercice()
	{
		state = EExerciceState.TODO;
		questions = Lists.newArrayList();
	}
}
