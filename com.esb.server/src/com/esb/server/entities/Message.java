package com.esb.server.entities;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Message
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	@Id
	private Long id;
	private String contenu;
	private Date creationDate;
	private Date deletedDate;
	private Conversation idConversation;
	private User idUser;
	/*
	###############################
	#         Getter              #
	###############################
	*/
	public Long getId() {
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
		return idConversation;
	}
	public User getIdUser() {
		return idUser;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setId(Long id) {
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
		this.idConversation = idConversation;
	}
	public void setIdUser(User idUser) {
		this.idUser = idUser;
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
