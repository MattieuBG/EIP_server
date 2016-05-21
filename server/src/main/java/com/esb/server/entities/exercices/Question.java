package com.esb.server.entities.exercices;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Question {
	public static enum EExerciceType {
		QCM, TEXT
	}

	@Id
	public String id = ObjectId.get().toString();
	public EExerciceType type;
	public String statement;
	public List<String> qcmPropositions;

	public Question() {
		qcmPropositions = Lists.newArrayList();
	}
}
