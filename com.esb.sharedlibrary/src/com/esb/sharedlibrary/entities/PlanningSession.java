package com.esb.sharedlibrary.entities;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import com.esb.sharedlibrary.json.LocalDateTimeDeserializer;
import com.esb.sharedlibrary.json.LocalDateTimeSerializer;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanningSession {

	@Id
	private String	id = ObjectId.get().toString();

	private String name;
	@Reference
	private User prof;
	private String salle;

	@Reference
	private Classe classe;
	
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime debut;
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime fin;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public User getProf() {
		return prof;
	}

	public String getSalle() {
		return salle;
	}

	public Classe getClasse() {
		return classe;
	}

	public LocalDateTime getDebut() {
		return debut;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProf(User prof) {
		this.prof = prof;
	}

	public void setSalle(String salle) {
		this.salle = salle;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public void setDebut(LocalDateTime debut) {
		this.debut = debut;
	}

	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}

}
