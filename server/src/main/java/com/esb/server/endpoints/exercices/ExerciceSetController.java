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
import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;
import com.google.common.collect.Lists;

@Path("exercicesets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExerciceSetController {

	// all exercices sets
	@GET
	public List<ExerciceSet> getExerciceSets() {
		return DAOHelper.exerciceSetDAO.find().asList();
	}

	// by id
	@GET
	@Path("{id}")
	public ExerciceSet getExerciceSetById(@PathParam("id") final String id) {
		final ExerciceSet set = DAOHelper.exerciceSetDAO.createQuery().filter("id =", id).get();
		if (set == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
		return set;
	}

	// by user
	@GET
	@Path("user/{id}")
	public List<ExerciceSet> getExerciceSetByUser(@PathParam("id") final String id) {
		final User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unknown user " + id).build());

		final List<Module> mods = DAOHelper.moduleDAO.createQuery().filter("user =", user).asList();
		if (mods == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("No modules for user " + id).build());

		final List<ExerciceSet> sets = Lists.newArrayList();
		for (final Module mod : mods)
			sets.addAll(mod.exerciceSets);
		return sets;
	}

	@POST
	public Response createExerciceSet(final ExerciceSet entity) {
		DAOHelper.exerciceSetDAO.save(entity);
		return Response.status(Status.CREATED).build();
	}

	@PUT
	public Response updateExerciceSet(final ExerciceSet entity) {
		DAOHelper.exerciceSetDAO.save(entity);
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteExerciceSet(final ExerciceSet entity) {
		DAOHelper.exerciceSetDAO.delete(entity);
		return Response.status(Status.OK).build();
	}
}
