package com.esb.server.entities.media;

import java.util.Date;

import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
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
    private String binary;
	private String description;
	private Date creationDate;
	private Date modifiedDate;
	private Date deletedDate;

	/*
	###############################
	#         Relation            #
	###############################
	*/
	@Reference
	private User owner;
	@Reference
	private Module module;

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
	public String getBinary() {
		return binary;
	}
	public String getDescription() {
		return description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Date getDeletedDate() {
		return deletedDate;
	}
	public Module getModule() {
	return module;
}
	public User getOwner() {
		return owner;
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
	public void setBinary(String binary) {
		this.binary = binary;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setModifiedDate(Date lastModified) {
		this.modifiedDate = lastModified;
	}
	public void setDeletedDate(Date deleted) {
		this.deletedDate = deleted;
	}
	public void setIdGridFs(String idGridFs) {
		this.idGridFs = idGridFs;
	}
	public void setModule(Module module) {
	this.module = module;
}
	public void setOwner(User owner) {
		this.owner = owner;
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
					", create : "+this.getCreationDate()+
					", lastUpdate : "+this.getModifiedDate()+
					", deleted : "+this.getDeletedDate()+
					"]";
		return attributes;
	}
}
