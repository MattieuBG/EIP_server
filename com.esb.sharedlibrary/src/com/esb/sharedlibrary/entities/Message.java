package com.esb.sharedlibrary.entities;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	@Id
	private String	id = ObjectId.get().toString();
	private String contenu;
	private Date creationDate;
	private Date deletedDate;
	@Reference
	private Conversation conversation;
	@Reference
	private User user;
	/*
	###############################
	#         Getter              #
	###############################
	*/
	public String getId() {
		return id;
	}
	public String getContenu() {
		return contenu;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public Date getDeletedDate() {
		return deletedDate;
	}
	public Conversation getIdConversation() {
		return conversation;
	}
	public User getIdUser() {
		return user;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setId(String id) {
		this.id = id;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	public void setIdConversation(Conversation idConversation) {
		this.conversation = idConversation;
	}
	public void setIdUser(User idUser) {
		this.user = idUser;
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
					", contenu : "+this.getContenu()+
					", craetionDate : "+this.getCreationDate()+
					", deletedDate : "+this.getDeletedDate()+
					"]";
		return attributes;
	}
}
