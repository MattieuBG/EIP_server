package com.esb.server.entities;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class Cours extends MediaGeneric
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	Long idModule; /* ça serait pas plus private Module idModule */
	Long idCreator; /* ça serait pas plus private User idCreator */
	/*
	###############################
	#         Getter              #
	###############################
	*/
	public Long getIdModule() {
		return idModule;
	}
	public Long getIdCreator() {
		return idCreator;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setIdModule(Long idModule) {
		this.idModule = idModule;
	}
	public void setIdCreator(Long idCreator) {
		this.idCreator = idCreator;
	}
	/*
	###############################
	#        Other Methods        #
	###############################
	*/
	public String toString()
	{
		String attributes;
		
		attributes = "[idCreator : "+this.getIdCreator()+
					"idModule : "+this.getIdModule()+
					"]";
		return attributes;
	}
}
