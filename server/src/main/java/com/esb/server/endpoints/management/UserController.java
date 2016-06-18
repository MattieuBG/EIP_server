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

import jersey.repackaged.com.google.common.collect.Lists;

import org.mongodb.morphia.Key;

import com.esb.server.entities.exercices.ExerciceSetTemplate;
import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.ModuleTemplate;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;

@Path("users")
public class UserController {

	// @Authenticated
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> get() {
		return DAOHelper.userDAO.find().asList();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public User getById(@PathParam("id") final String id) {
		final User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
		return user;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("module/{id}")
	public List<User> getByModuleId(@PathParam("id") final String id) {
		final ModuleTemplate template = DAOHelper.moduleTemplateDAO.createQuery().filter("id =", id).get();
		if (template == null)
			return null;
		final List<Module> mods = DAOHelper.moduleDAO.createQuery().filter("template =", template).asList();
		final List<User> users = Lists.newArrayList();
		for (final Module mod : mods) {
			users.add(mod.user);
		}
		return users;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("exerciceset/{id} | examen/{id} | homeworks/{id}")
	public List<User> getBySetId(@PathParam("id") final String id) {
		final ExerciceSetTemplate template = DAOHelper.exerciceSetTemplateDAO.createQuery().filter("id =", id).get();
		if (template == null)
			return null;

		final List<Key<ModuleTemplate>> modTemplates = DAOHelper.moduleTemplateDAO.createQuery().filter("exerciceSets in", template)
				.asKeyList();

		final List<Module> mods = DAOHelper.moduleDAO.createQuery().filter("template in", modTemplates).asList();
		final List<User> users = Lists.newArrayList();
		for (final Module mod : mods) {
			users.add(mod.user);
		}
		return users;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(final User entity) {
		DAOHelper.userDAO.save(entity);
		return Response.status(Status.CREATED).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(final User entity) {
		DAOHelper.userDAO.save(entity);
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(final User entity) {
		DAOHelper.userDAO.delete(entity);
		return Response.status(Status.OK).build();
	}

}
