package com.esb.sharedlibrary.entities;


import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public abstract class AFile
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	@Id
	private String id = ObjectId.get().toString();
	private String name;
	private String path;
	private String description;
	private LocalDateTime creationDate;
	private LocalDateTime modifiedDate;
	private LocalDateTime deletedDate;
	/*
	###############################
	#         Getter              #
	###############################
	*/
	public String getId() {
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
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	public LocalDateTime getDeletedDate() {
		return deletedDate;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setId(String id) {
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
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public void setModifiedDate(LocalDateTime lastModified) {
		this.modifiedDate = lastModified;
	}
	public void setDeletedDate(LocalDateTime deleted) {
		this.deletedDate = deleted;
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
					", lastUpdate : "+this.getModifiedDate()+
					", deleted : "+this.getDeletedDate()+
					"]";
		return attributes;
	}
}
