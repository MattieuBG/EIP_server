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

import com.esb.server.entities.management.Classe;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;

@Path("classes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClasseController {

	// all classes
	@GET
	public List<Classe> getClasses() {
		return DAOHelper.classeDAO.find().asList();
	}

	// by id
	@GET
	@Path("{id}")
	public Classe getClasseById(@PathParam("id") final String id) {
		final Classe classe = DAOHelper.classeDAO.createQuery().filter("id =", id).get();
		if (classe == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
		return classe;
	}

	// by user
	@GET
	@Path("supervisor/{id}")
	public List<Classe> getClasseBySupervisor(@PathParam("id") final String supervisorId) {
		final User user = DAOHelper.userDAO.createQuery().filter("id =", supervisorId).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unknown user " + supervisorId).build());
		return DAOHelper.classeDAO.createQuery().filter("supervisor =", user).asList();
	}

	@POST
	public Response createClasse(final Classe entity) {
		DAOHelper.classeDAO.save(entity);
		return Response.status(Status.CREATED).build();
	}

	@PUT
	public Response updateClasse(final Classe entity) {
		DAOHelper.classeDAO.save(entity);
		return Response.status(Status.OK).build();
	}

	@DELETE
	public Response deleteClasse(final Classe entity) {
		DAOHelper.classeDAO.delete(entity);
		return Response.status(Status.OK).build();
	}

	// /**
	// * Register student in Classe
	// *
	// * @param id
	// * @param entity
	// * @return
	// */
	// @POST
	// @Path("/{id}/register")
	// @Consumes(MediaType.APPLICATION_JSON)
	// public Response registerToClasse(@PathParam("id") final String id, final
	// Classe entity) {
	// final User user = DAOHelper.userDAO.createQuery().filter("id =",
	// id).get();
	// if (user == null)
	// throw new
	// WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
	// user.classe = entity;
	// DAOHelper.userDAO.save(user);
	// return Response.status(Status.OK).build();
	// }
	//
	// /**
	// * Unregister student in Classe
	// *
	// * @param id
	// * @return
	// */
	// @POST
	// @Path("/{id}/unregister")
	// @Consumes(MediaType.APPLICATION_JSON)
	// public Response unregister(@PathParam("id") final String id) {
	// final User user = DAOHelper.userDAO.createQuery().filter("id =",
	// id).get();
	// if (user == null)
	// throw new
	// WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
	// user.classe = null;
	// DAOHelper.userDAO.save(user);
	// return Response.status(Status.OK).build();
	// }
}
