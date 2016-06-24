package com.esb.server.entities.media;

import org.mongodb.morphia.annotations.Reference;

import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.User;

/**
 * Created by alex on 18/06/16. Project name : Eschoolbag
 */
public class Course extends AFile {
	/*
	 * ############################### # Attributes #
	 * ###############################
	 */
	private String html;
	@Reference
	public User owner;
	@Reference
	public Module module;
	/*
	 * ############################### # Getter #
	 * ###############################
	 */
	public String getHtml() {
		return html;
	}
	/*
	 * ############################### # Setter #
	 * ###############################
	 */
	public void setHtml(final String html) {
		this.html = html;
	}
	/*
	 * ############################### # Other Methods #
	 * ###############################
	 */
	public String toString() {
		String attributes;

		attributes = "[id : " + this.getId() + ", name : " + this.getName() + ", html : " + this.getHtml() + ", description : "
				+ this.getDescription() + ", create : " + this.getCreationDate() + ", lastUpdate : " + this.getModifiedDate()
				+ ", deleted : " + this.getDeletedDate() + "]";
		return attributes;
	}
}
