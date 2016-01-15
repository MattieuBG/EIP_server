package com.esb.server.entities;

import java.util.Collection;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Exercice 
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	public enum ExerciceStateEnum {
		NOTSTARTED, DOING
	}
	public enum ExerciceTypeEnum {
		QCM, NORMAL
	}
	@Id
	private String	id;
	private String	title;
	private String	description;
	@Reference
	private Module	module;
	private ExerciceStateEnum	state;
	@Embedded
	private Collection<Question>	questionList;
	@Embedded
	private Collection<Response>	responseList;
	private ExerciceTypeEnum	type;
	/*
	###############################
	#           Getter            #
	###############################
	*/
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public Module getModule() {
		return module;
	}
	public ExerciceStateEnum getState() {
		return state;
	}
	public Collection<Question> getQuestions() {
		return questionList;
	}
	public Collection<Response> getResponses() {
		return responseList;
	}
	public ExerciceTypeEnum getType() {
		return type;
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
	public void setDescription(String description) {
		this.description = description;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public void setState(ExerciceStateEnum state) {
		this.state = state;
	}
	public void setQuestions(Collection<Question> questions) {
		this.questionList = questions;
	}
	public void setResponses(Collection<Response> responses) {
		this.responseList = responses;
	}
	public void setType(ExerciceTypeEnum type) {
		this.type = type;
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
					", description : "+this.getDescription()+
					", state : "+this.getState()+
					", type : "+this.getType()+
					"]";
		return attributes;
	}
}
