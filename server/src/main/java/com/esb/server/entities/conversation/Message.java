package com.esb.server.entities.conversation;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.esb.server.entities.management.User;

@Entity
public class Message {
	@Id
	public String id = ObjectId.get().toString();
	@Reference
	public User author;
	public Date creationDate;
	public String content;

	public Message() {
		creationDate = new Date();
	}

}
