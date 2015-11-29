package com.esb.server.entities;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Homework {
	@Id
	private Long	id;
	private Date	toDoDate;
	private Date	finishedDate;
	@Reference
	private User	Student;
	@Reference
	private Module	Module;
	private List<Exercice> exercices;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getToDoDate() {
		return toDoDate;
	}
	public void setToDoDate(Date toDoDate) {
		this.toDoDate = toDoDate;
	}
	public Date getFinishedDate() {
		return finishedDate;
	}
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}
	public User getStudent() {
		return Student;
	}
	public void setStudent(User student) {
		Student = student;
	}
	public Module getModule() {
		return Module;
	}
	public void setModule(Module module) {
		Module = module;
	}
	public List<Exercice> getExercices() {
		return exercices;
	}
	public void setExercices(List<Exercice> exercices) {
		this.exercices = exercices;
	}

	
}
