package com.esb.sharedlibrary.entities;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Homework
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	@Id
	private String	id = ObjectId.get().toString();
	
	// TODO : type (EXAM ou HOMEWORK)
	
	
	private Date	toDoDate;
	private Date	finishedDate;
	@Reference
	private User	student;
	@Reference
	private Module	module;
	@Embedded
	private List<Exercice> exerciceList;
	/*
	###############################
	#           Getter            #
	###############################
	*/
	public String getId() {
		return id;
	}
	public Date getToDoDate() {
		return toDoDate;
	}
	public Date getFinishedDate() {
		return finishedDate;
	}
	public User getStudent() {
		return student;
	}
	public Module getModule() {
		return module;
	}
	public List<Exercice> getExercices() {
		return exerciceList;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setId(String id) {
		this.id = id;
	}
	public void setToDoDate(Date toDoDate) {
		this.toDoDate = toDoDate;
	}
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}
	public void setStudent(User student) {
		this.student = student;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public void setExercices(List<Exercice> exercices) {
		this.exerciceList = exercices;
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
				", toDoDate : "+this.getToDoDate()+
				", finishedDate : "+this.getFinishedDate()+
				", student : "+this.getStudent()+
				", module : "+this.getModule()+
				"]";
		return attributes;
	}
}
