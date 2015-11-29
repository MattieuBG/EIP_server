package com.esb.server.entities;

import java.util.Collection;
import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Module
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	@Id
	private String id;
	private Collection<User> teacherList;
	private String title;
	private Date creationDate;
	private Date modifiedDate;
	private Collection<User> studentList;
	private Collection<Homework> homeworkList;
	/*
	###############################
	#         Getter              #
	###############################
	*/
	public String getId() {
		return id;
	}
	public Collection<User> getTeacherList() {
		return teacherList;
	}
	public String getTitle() {
		return title;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Collection<User> getStudentList() {
		return studentList;
	}
	public Collection<Homework> getHomeworkList() {
		return homeworkList;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setId(String id) {
		this.id = id;
	}
	public void setTeacherList(Collection<User> teacherList) {
		this.teacherList = teacherList;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public void setStudentList(Collection<User> studentList) {
		this.studentList = studentList;
	}
	public void setHomeworkList(Collection<Homework> homeworkList) {
		this.homeworkList = homeworkList;
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
					"]";
		return attributes;
	}


	
}
