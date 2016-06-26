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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.esb.server.entities.conversation.Conversation;
import com.esb.server.entities.conversation.Message;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;
import com.mongodb.util.JSON;

@Path("conversations")
// @Api(value = "conversations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConversationController {

	@GET
	public List<Conversation> getConversations() {
		return DAOHelper.conversationDAO.find().asList();
	}

	@GET
	@Path("{id}")
	public Conversation getConversationById(@PathParam("id") final String id) {
		final Conversation conv = DAOHelper.conversationDAO.createQuery().filter("id =", id).get();
		if (conv == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
		return conv;
	}

	@GET
	@Path("user/{id}")
	public List<Conversation> getConversationByUser(@PathParam("id") final String id) {
		final User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unknown user " + id).build());
		return DAOHelper.conversationDAO.createQuery().filter("users in", user).asList();
	}

	@POST
	@Path("{id}/messages/add")
	public Conversation addMessageToConversation(@PathParam("id") final String conversationId, final Message message) {
		final Conversation conversation = DAOHelper.conversationDAO.createQuery().filter("id =", conversationId).get();
		if (conversation == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknown conversation " + conversationId)).build());

		User user;
		if (message.author == null || (user = DAOHelper.userDAO.createQuery().filter("id =", message.author.id).get()) == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Invalid message" + message.id)).build());
		for (final Message m : conversation.messages)
			if (m.id.equals(message.id))
				throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
						.entity(JSON.serialize("Invalid message id " + message.id)).build());

		message.author = user;
		conversation.messages.add(message);
		DAOHelper.conversationDAO.save(conversation);
		return conversation;
	}
	@POST
	public Response createConversation(final Conversation entity) {
		DAOHelper.conversationDAO.save(entity);
		return Response.status(Status.CREATED).build();
	}

	@PUT
	public Response updateConversation(final Conversation entity) {
		DAOHelper.conversationDAO.save(entity);
		return Response.status(Status.OK).build();
	}

	@DELETE
	public Response deleteConversation(final Conversation entity) {
		DAOHelper.conversationDAO.delete(entity);
		return Response.status(Status.OK).build();
	}
}
