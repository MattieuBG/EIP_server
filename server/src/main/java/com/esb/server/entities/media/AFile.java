package com.esb.server.entities.media;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;

import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.User;
import com.google.common.collect.Lists;

@Entity
public abstract class AFile {
	/*
	 * ############################### # Attributes #
	 * ###############################
	 */
	@Id
	private String id = ObjectId.get().toString();
	private String idGridFs = ObjectId.get().toString();
	private String name;
	@Transient
	private String binary;
	private String description;
	private Date creationDate;
	private Date modifiedDate;
	private Date deletedDate;

	/*
	 * ############################### # Relation #
	 * ###############################
	 */
	@Reference
	private User owner;
	@Reference
	private Module module;

	@Reference
	public List<User> sharedUsers;

	public AFile() {
		sharedUsers = Lists.newArrayList();
	}

	/*
	 * ############################### # Getter #
	 * ###############################
	 */
	public String getId() {
		return id;
	}
	public String getIdGridFs() {
		return idGridFs;
	}
	public String getName() {
		return name;
	}
	public String getBinary() {
		return binary;
	}
	public String getDescription() {
		return description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Date getDeletedDate() {
		return deletedDate;
	}
	public Module getModule() {
		return module;
	}
	public User getOwner() {
		return owner;
	}
	/*
	 * ############################### # Setter #
	 * ###############################
	 */
	public void setId(final String id) {
		this.id = id;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public void setBinary(final String binary) {
		this.binary = binary;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setModifiedDate(final Date lastModified) {
		this.modifiedDate = lastModified;
	}
	public void setDeletedDate(final Date deleted) {
		this.deletedDate = deleted;
	}
	public void setIdGridFs(final String idGridFs) {
		this.idGridFs = idGridFs;
	}
	public void setModule(final Module module) {
		this.module = module;
	}
	public void setOwner(final User owner) {
		this.owner = owner;
	}
	/*
	 * ############################### # Other Methods #
	 * ###############################
	 */
	public String toString() {
		String attributes;

		attributes = "[id : " + this.getId() + ", IdGridFs : " + this.getIdGridFs() + ", name : " + this.getName() + ", binary : "
				+ this.getBinary() + ", description : " + this.getDescription() + ", create : " + this.getCreationDate()
				+ ", lastUpdate : " + this.getModifiedDate() + ", deleted : " + this.getDeletedDate() + "]";
		return attributes;
	}
}
