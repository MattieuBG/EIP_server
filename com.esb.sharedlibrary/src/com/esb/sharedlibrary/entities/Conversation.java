package com.esb.sharedlibrary.entities;

import java.util.Collection;
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Conversation
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	public enum ConversationStateEnum {
		OPEN, CLOSE
	}
	
	@Id
	private String	id = ObjectId.get().toString();
	private String title;
	@Reference
	private Collection<User> userList;
	private Date dateCreation;
	private ConversationStateEnum state;
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
	public Collection<User> getUserList() {
		return userList;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public ConversationStateEnum getState() {
		return state;
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
	public void setUserList(Collection<User> userList) {
		this.userList = userList;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public void setState(ConversationStateEnum state) {
		this.state = state;
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
					", Title : "+this.getTitle()+
					", creation : "+this.getDateCreation()+
					", state : "+this.getState()+
					"]";
		return attributes;
	}
}
