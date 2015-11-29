package com.esb.server.entities;

import java.util.Date;

import org.mongodb.morphia.annotations.Id;

public abstract class MediaGeneric
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	@Id
	private Long id;
	private String name;
	private String path;
	private String description;
	private Date creationDate;
	private Date lastModified;
	private Date deleted;
	/*
	###############################
	#         Getter              #
	###############################
	*/
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPath() {
		return path;
	}
	public String getDescription() {
		return description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public Date getDeleted() {
		return deleted;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public void setDeleted(Date deleted) {
		this.deleted = deleted;
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
					", name : "+this.getName()+
					", path : "+this.getPath()+
					", description : "+this.getDescription()+
					", path : "+this.getPath()+
					", create : "+this.getCreationDate()+
					", lastUpdate : "+this.getLastModified()+
					", deleted : "+this.getDeleted()+
					"]";
		return attributes;
	}
}
