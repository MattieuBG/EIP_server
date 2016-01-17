package com.esb.server.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Course extends AFile
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	@Reference
	private Module module;
	@Reference
	private User creator;
	/*
	###############################
	#         Getter              #
	###############################
	*/
	public Module getModule() {
		return module;
	}
	public User getCreator() {
		return creator;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setModule(Module module) {
		this.module = module;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	/*
	###############################
	#        Other Methods        #
	###############################
	*/
	public String toString()
	{
		String attributes;
		
		attributes = "[creator : "+this.getCreator()+
					", module : "+this.getModule()+
					"]";
		return attributes;
	}
}
