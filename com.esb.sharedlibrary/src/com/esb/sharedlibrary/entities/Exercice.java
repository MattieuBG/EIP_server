package com.esb.sharedlibrary.entities;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
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
	private String	id = ObjectId.get().toString();
	private String	title;
	private String	description;
	@Reference
	private Module	module;
	private ExerciceStateEnum	state;
	@Embedded
	private Collection<Question>	questions;
	@Embedded
	private Collection<Answer>	answers;
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
		return questions;
	}
	public Collection<Answer> getAnswers() {
		return answers;
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
		this.questions = questions;
	}
	public void setAnswers(Collection<Answer> answers) {
		this.answers = answers;
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
