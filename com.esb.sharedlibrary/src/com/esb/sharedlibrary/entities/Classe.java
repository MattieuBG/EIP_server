package com.esb.sharedlibrary.entities;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.esb.sharedlibrary.json.LocalDateTimeDeserializer;
import com.esb.sharedlibrary.json.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Classe
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	@Id
	private String	id = ObjectId.get().toString();
	private String title;
	
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime creationDate;
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime modifiedDate;
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
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public LocalDateTime getModifiedDate() {
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
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
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
