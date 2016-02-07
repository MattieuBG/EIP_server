package com.esb.sharedlibrary.entities;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Embedded;

import com.esb.sharedlibrary.json.LocalDateTimeDeserializer;
import com.esb.sharedlibrary.json.LocalDateTimeSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User
{
	/*
	###############################
	#         Attributes          #
	###############################
	*/
	public enum RoleEnum {
		PARENT, STUDENT, PROF, ADMIN
	}

	@Id
	private String	id = ObjectId.get().toString();
	private Classe academicLevel;
	private RoleEnum role;
	private String email;
	private String password;
	private String login;
	private String firstName;
	private String lastName;
	
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime birthDate;
    
	private String adresse;
	@Embedded
	private Module[] modules;
	/*
	###############################
	#         Getter              #
	###############################
	*/
	public String getId() {
		return id;
	}
	public Classe getNiveauScolaire() {
		return academicLevel;
	}
	public RoleEnum getRole() {
		return role;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getLogin() {
		return login;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public LocalDateTime getBirthDate() {
		return birthDate;
	}
	public String getAdresse() {
		return adresse;
	}
	public Module[] getModules() {
		return modules;
	}
	/*
	###############################
	#         Setter              #
	###############################
	*/
	public void setId(String id) {
		this.id = id;
	}
	public void setNiveauScolaire(Classe niveauScolaire) {
		this.academicLevel = niveauScolaire;
	}
	public void setRole(RoleEnum role) {
		this.role = role;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public void setModules(Module[] modules) {
		this.modules = modules;
	}	
	/*
	###############################
	#         Other Methods       #
	###############################
	*/
	public String toString()
	{
		String attributes;
		
		attributes = "[id : "+this.getId()+
					", role : "+this.getRole()+
					", email : "+this.getEmail()+
					", password : "+this.getPassword()+
					", login : "+this.getLogin()+
					", firstName : "+this.getFirstName()+
					", lastName : "+this.getLastName()+
					", brithDate : "+this.getBirthDate()+
					", adresse : "+this.getAdresse()+
					"]";
		return attributes;
	}
}
