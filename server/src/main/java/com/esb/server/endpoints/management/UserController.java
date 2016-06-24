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
import com.esb.server.entities.management.Classe;
import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.ModuleTemplate;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;
import com.mongodb.util.JSON;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// @Api(value = "users")
public class UserController {

	// @Authenticated
	@GET
	public List<User> getUsers() {
		return DAOHelper.userDAO.find().asList();
	}

	@GET
	@Path("/{id}")
	public User getUserById(@PathParam("id") final String id) {
		final User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
		return user;
	}

	@GET
	@Path("module/{id}")
	public List<User> getUsersByModuleTemplateId(@PathParam("id") final String id) {
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
	@Path("exerciceset/{id} | examen/{id} | homeworks/{id}")
	public List<User> getUsersByExerciceSetTemplateId(@PathParam("id") final String id) {
		final ExerciceSetTemplate template = DAOHelper.exerciceSetTemplateDAO.createQuery().filter("id =", id).get();
		if (template == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknown exercice set template " + id)).build());

		final List<Key<ModuleTemplate>> modTemplates = DAOHelper.moduleTemplateDAO.createQuery().filter("exerciceSets in", template)
				.asKeyList();

		final List<Module> mods = DAOHelper.moduleDAO.createQuery().filter("template in", modTemplates).asList();
		final List<User> users = Lists.newArrayList();
		for (final Module mod : mods) {
			users.add(mod.user);
		}
		return users;
	}

	// by classe
	@GET
	@Path("classes/{id}")
	public List<User> getUsersByClasseId(@PathParam("id") final String classeId) {
		final Classe classe = DAOHelper.classeDAO.createQuery().filter("id =", classeId).get();
		if (classe == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknown classe id " + classeId)).build());
		return DAOHelper.userDAO.createQuery().filter("classe = ", classe).asList();
	}

	@POST
	public Response createUser(final User entity) {
		DAOHelper.userDAO.save(entity);
		return Response.status(Status.CREATED).build();
	}

	@PUT
	public Response updateUser(final User entity) {
		DAOHelper.userDAO.save(entity);
		return Response.status(Status.OK).build();
	}

	@DELETE
	public Response deleteUser(final User entity) {
		DAOHelper.userDAO.delete(entity);
		return Response.status(Status.OK).build();
	}

}
