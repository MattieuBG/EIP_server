package com.esb.server.endpoints.management;

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

import com.esb.server.entities.management.Delay;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;

@Path("delays")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DelayController {

	// all delays
	@GET
	public List<Delay> getDelays() {
		return DAOHelper.delayDAO.find().asList();
	}

	// by id
	@GET
	@Path("{id}")
	public Delay getDelayById(@PathParam("id") final String id) {
		final Delay mod = DAOHelper.delayDAO.createQuery().filter("id =", id).get();
		if (mod == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
		return mod;
	}

	// by user
	@GET
	@Path("user/{id}")
	public List<Delay> getDelaysByUser(@PathParam("id") final String id) {
		final User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unknown user " + id).build());
		return DAOHelper.delayDAO.createQuery().filter("user =", user).asList();
	}

	@POST
	public Delay createDelay(final Delay entity) {
		DAOHelper.delayDAO.save(entity);
		return entity;
	}

	@PUT
	public Delay updateDelay(final Delay entity) {
		DAOHelper.delayDAO.save(entity);
		return entity;
	}

	@DELETE
	public Response deleteDelay(final Delay entity) {
		DAOHelper.delayDAO.delete(entity);
		return Response.status(Status.OK).build();
	}
}
