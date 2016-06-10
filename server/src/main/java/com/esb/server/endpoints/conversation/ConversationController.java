package com.esb.server.endpoints.conversation;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esb.server.entities.conversation.Conversation;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;

@Path("conversations")
public class ConversationController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Conversation> get() {
		return DAOHelper.conversationDAO.find().asList();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Conversation getById(@PathParam("id") String id) {
		return DAOHelper.conversationDAO.createQuery().filter("id =", id).get();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user/{id}")
	public List<Conversation> getByUser(@PathParam("id") String id)
	{
		User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		if (user == null) return null;
		return DAOHelper.conversationDAO.createQuery().filter("users in", user).asList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Conversation entity) {
		DAOHelper.conversationDAO.save(entity);
		return Response.status(201).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Conversation entity) {
		DAOHelper.conversationDAO.save(entity);
		return Response.status(201).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(Conversation entity) {
		DAOHelper.conversationDAO.delete(entity);
		return Response.status(201).build();
	}
}
