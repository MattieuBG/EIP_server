package com.esb.server.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public abstract class AQuestionAndAnswer
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	@Id
	private ObjectId	id;
	private String	description;
	private User user;
	/*
	###############################
	#           Getter            #
	###############################
	*/
	public ObjectId getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public User getUser() {
		return user;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setId(ObjectId id) {
		this.id = id;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/*
	###############################
	#         Other Methods       #
	###############################
	*/
	public String toString()
	{
		String attributes;
		
		attributes = "[id : "+this.getId()+
					", description : "+this.getDescription()+
					"]";
		return attributes;
	}
}
