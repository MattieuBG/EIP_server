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

import com.esb.server.entities.management.Classe;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;

@Path("classes")
public class ClasseController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Classe> get() {
		return DAOHelper.classeDAO.find().asList();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("id/{id}")
	public Classe getById(@PathParam("id") String id) {
		return DAOHelper.classeDAO.createQuery().filter("id =", id).get();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user/{id}")
	public List<Classe> getBySupervisor(@PathParam("id") String id) {
		User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		if (user == null) throw new WebApplicationException(404);
		return DAOHelper.classeDAO.createQuery().filter("supervisor =", user).asList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Classe entity) {
		DAOHelper.classeDAO.save(entity);
		return Response.status(201).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Classe entity) {
		DAOHelper.classeDAO.save(entity);
		return Response.status(201).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(Classe entity) {
		DAOHelper.classeDAO.delete(entity);
		return Response.status(201).build();
	}
}
