package com.esb.server.entities.media;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.esb.server.entities.management.User;
import com.google.common.collect.Lists;

@Entity
public class ImageFTP {

	@Id
	public String id = ObjectId.get().toString();
	public String name;
	public String description;
	public Date creationDate;
	public Date modifiedDate;
	public Date deletedDate;
	@Reference
	public User owner;

	@Reference
	public List<User> sharedUsers;

	public ImageFTP() {
		sharedUsers = Lists.newArrayList();
	}
	public String ftpUrl;

}
