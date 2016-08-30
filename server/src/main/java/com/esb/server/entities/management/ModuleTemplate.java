package com.esb.server.entities.management;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.esb.server.entities.exercices.ExerciceSetTemplate;
import com.esb.server.entities.media.ImageFTP;

@Entity
public class ModuleTemplate {
	@Id
	public String id = ObjectId.get().toString();
	public String name;
	@Reference
	public List<ExerciceSetTemplate> exerciceSetTemplates;
	@Reference
	public List<PlanningSession> sessions;
	@Reference
	public List<User> supervisors;
	@Reference
	public ImageFTP icon;

	public ModuleTemplate() {
		exerciceSetTemplates = Lists.newArrayList();
		sessions = Lists.newArrayList();
		supervisors = Lists.newArrayList();
	}
}
