package com.esb.server.entities;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Classe
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	@Id
	private String id;
	private String title;
	private Date creationDate;
	private Date modifiedDate;
	@Reference
	private User supervisor;
	/*
	###############################
	#         Getter              #
	###############################
	*/
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public User getSupervisor() {
		return supervisor;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}
	/*
	###############################
	#        Other Methods        #
	###############################
	*/
	public String toString()
	{
		String attributes;
		
		attributes = "[id : "+this.getId()+
					", title : "+this.getTitle()+
					", creation : "+this.getCreationDate()+
					", modified : "+this.getModifiedDate()+
					", supervisor : "+this.getSupervisor()+
					"]";
		return attributes;
	}
}
