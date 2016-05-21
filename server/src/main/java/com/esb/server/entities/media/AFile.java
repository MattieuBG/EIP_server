package com.esb.server.entities.media;

import java.io.File;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

@Entity
public abstract class AFile
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	@Id
	private String id = ObjectId.get().toString();
	private String idGridFs = ObjectId.get().toString();
	private String name;
	@Transient
	private File binary;
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
	public String getIdGridFs() {
		return idGridFs;
	}
	public String getName() {
		return name;
	}
	public File getBinary() {
		return binary;
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
	public void setBinary(File binary) {
		this.binary = binary;
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
	public void setIdGridFs(String idGridFs) {
		this.idGridFs = idGridFs;
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
					", IdGridFs : "+this.getIdGridFs()+
					", name : "+this.getName()+
					", binary : "+this.getBinary()+
					", description : "+this.getDescription()+
					", binary : "+this.getBinary()+
					", create : "+this.getCreationDate()+
					", lastUpdate : "+this.getModifiedDate()+
					", deleted : "+this.getDeletedDate()+
					"]";
		return attributes;
	}
}
