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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mongodb.morphia.Key;

import jersey.repackaged.com.google.common.collect.Lists;

import com.esb.server.entities.exercices.ExerciceSetTemplate;
import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.ModuleTemplate;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;

@Path("users")
public class UserController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> get() {
		return DAOHelper.userDAO.find().asList();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("id/{id}")
	public User getById(@PathParam("id") String id) {
		return DAOHelper.userDAO.createQuery().filter("id =", id).get();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("module/{id}")
	public List<User> getByModuleId(@PathParam("id") String id) {
		ModuleTemplate template = DAOHelper.moduleTemplateDAO.createQuery()
				.filter("id =", id).get();
		if (template == null)
			return null;
		List<Module> mods = DAOHelper.moduleDAO.createQuery()
				.filter("template =", template).asList();
		List<User> users = Lists.newArrayList();
		for (Module mod : mods) {
			users.add(mod.user);
		}
		return users;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("exerciceset/{id} | examen/{id} | homeworks/{id}")
	public List<User> getBySetId(@PathParam("id") String id) {
		ExerciceSetTemplate template = DAOHelper.exerciceSetTemplateDAO
				.createQuery().filter("id =", id).get();
		if (template == null)
			return null;

		List<Key<ModuleTemplate>> modTemplates = DAOHelper.moduleTemplateDAO
				.createQuery().filter("exerciceSets in", template).asKeyList();

		List<Module> mods = DAOHelper.moduleDAO.createQuery().filter("template in", modTemplates).asList();
		List<User> users = Lists.newArrayList();
		for (Module mod : mods) {
			users.add(mod.user);
		}
		return users;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(User entity) {
		DAOHelper.userDAO.save(entity);
		return Response.status(201).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(User entity) {
		DAOHelper.userDAO.save(entity);
		return Response.status(201).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(User entity) {
		DAOHelper.userDAO.delete(entity);
		return Response.status(201).build();
	}

}
