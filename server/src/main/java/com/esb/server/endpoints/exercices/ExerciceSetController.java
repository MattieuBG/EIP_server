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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esb.server.entities.exercices.ExerciceSet;
import com.esb.server.entities.exercices.ExerciceSetTemplate;
import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.ModuleTemplate;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;

@Path("exercicesets")
public class ExerciceSetController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExerciceSet> get() {
		return DAOHelper.exerciceSetDAO.find().asList();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ExerciceSet getById(@PathParam("id") String id) {
		return DAOHelper.exerciceSetDAO.createQuery().filter("id =", id).get();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user/{id}")
	public List<ExerciceSet> getByUser(@PathParam("id") String id) {
		User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		return DAOHelper.exerciceSetDAO.createQuery().filter("user =", user)
				.asList();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("module/{id}")
	public List<ExerciceSetTemplate> getByModule(@PathParam("id") String id) {
		return DAOHelper.moduleTemplateDAO.createQuery().filter("id =", id)
				.get().exerciceSetTemplates;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("modules/{mid}/user/{uid} | user/{uid}/module/{mid}")
	public List<ExerciceSet> getByModAndUser(@PathParam("uid") String uid,
			@PathParam("mid") String mid) {
		User user = DAOHelper.userDAO.createQuery().filter("id =", uid).get();
		ModuleTemplate template = DAOHelper.moduleTemplateDAO.createQuery()
				.filter("id =", mid).get();
		if (user == null || template == null)
			return null;
		return DAOHelper.moduleDAO.createQuery().filter("user =", user)
				.filter("template =", template).get().exerciceSets;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(ExerciceSet entity) {
		DAOHelper.exerciceSetDAO.save(entity);
		return Response.status(201).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(ExerciceSet entity) {
		DAOHelper.exerciceSetDAO.save(entity);
		return Response.status(201).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(ExerciceSet entity) {
		DAOHelper.exerciceSetDAO.delete(entity);
		return Response.status(201).build();
	}
}
