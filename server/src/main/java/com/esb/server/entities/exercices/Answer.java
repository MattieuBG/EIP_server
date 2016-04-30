package com.esb.server.entities.exercices;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Answer {
	@Id
	public  String	id = ObjectId.get().toString();
	
	@Embedded
	public Question question;
	public String txtValue;
	public int[] qcmValues;
}
