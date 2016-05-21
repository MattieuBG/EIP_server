package com.esb.server.entities.conversation;

import java.util.Date;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.esb.server.entities.management.User;

@Entity
public class Conversation
{
	@Id
	public String	id = ObjectId.get().toString();
	public String name;
	@Reference
	public List<User> users;
	public Date creationDate;
	@Embedded
	public List<Message> messages;
	
	public Conversation()
	{
		users = Lists.newArrayList();
		messages = Lists.newArrayList();
		creationDate = new Date();
	}
}
