package com.esb.server.entities.management;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class User {
	public static enum EUserRole {
		PARENT, STUDENT, PROF, ADMIN
	}

	@Id
	public String id = ObjectId.get().toString();
	public String firstName;
	public String lastName;
	public Date birthDate;
	public String address;
	public String login;
	public String password;
	public String email;
	public EUserRole role;
	@Reference
	public Classe classe;

	// @Reference
	// public List<Module> modules;

	public User() {
		role = EUserRole.STUDENT;
	}

}
