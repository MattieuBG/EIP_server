package com.esb.server.entities.exercices;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.esb.server.entities.management.User;

@Entity
public class Notation {

	@Id
	public String id = ObjectId.get().toString();
	public int note;
	public String comment;
	@Reference
	public User corrector;
}
