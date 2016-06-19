package com.esb.server.endpoints.exercices;

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

import com.esb.server.entities.exercices.ExerciceSet;
import com.esb.server.entities.exercices.ExerciceSetTemplate;
import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.ModuleTemplate;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;

@Path("exercicesets")
public class ExerciceSetController {

	// all exercices sets
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExerciceSet> get() {
		return DAOHelper.exerciceSetDAO.find().asList();
	}

	// by id
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ExerciceSet getById(@PathParam("id") String id) {
		ExerciceSet set = DAOHelper.exerciceSetDAO.createQuery().filter("id =", id).get();
		if (set == null)
			throw new WebApplicationException(Response.status(
					Response.Status.BAD_REQUEST).entity(id).build());
		return set;
	}

	// by user
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user/{id}")
	public List<ExerciceSet> getByUser(@PathParam("id") String id) {
		User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		if (user == null)
			throw new WebApplicationException(Response.status(
					Response.Status.BAD_REQUEST).entity("Unknown user " + id).build());
		return DAOHelper.exerciceSetDAO.createQuery().filter("user =", user)
				.asList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(ExerciceSet entity) {
		DAOHelper.exerciceSetDAO.save(entity);
		return Response.status(Status.CREATED).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(ExerciceSet entity) {
		DAOHelper.exerciceSetDAO.save(entity);
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(ExerciceSet entity) {
		DAOHelper.exerciceSetDAO.delete(entity);
		return Response.status(Status.OK).build();
	}
}
