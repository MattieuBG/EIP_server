package com.esb.server.entities;

import java.util.Date;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Embedded;

@Entity
public class User {

	public enum RoleEnum {
		PARENT, STUDENT, PROF, ADMIN
	}

	@Id
	private String id;
	private Classe niveauScolaire;
	private RoleEnum role;
	private String email;
	private String password;
	private String login;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String adresse;
	@Embedded
	private Module[] modules;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Classe getNiveauScolaire() {
		return niveauScolaire;
	}
	public void setNiveauScolaire(Classe niveauScolaire) {
		this.niveauScolaire = niveauScolaire;
	}
	public RoleEnum getRole() {
		return role;
	}
	public void setRole(RoleEnum role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Module[] getModules() {
		return modules;
	}
	public void setModules(Module[] modules) {
		this.modules = modules;
	}

	

}
