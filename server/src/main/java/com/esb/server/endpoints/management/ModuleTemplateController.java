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

import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.ModuleTemplate;
import com.esb.server.entities.management.PlanningSession;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;
import com.mongodb.util.JSON;

@Path("modules/templates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModuleTemplateController {

	// all modules templates
	@GET
	public List<ModuleTemplate> getModulesTemplates() {
		return DAOHelper.moduleTemplateDAO.find().asList();
	}

	// by id
	@GET
	@Path("{id}")
	public ModuleTemplate getModuleTemplateById(@PathParam("id") final String id) {
		final ModuleTemplate template = DAOHelper.moduleTemplateDAO.createQuery().filter("id =", id).get();
		if (template == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
		return template;
	}

	// by supervisor
	@GET
	@Path("supervisor/{id}")
	public List<ModuleTemplate> getModuleTemplatesBySupervisor(@PathParam("id") final String id) {
		final User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unknown supervisor " + id).build());
		return DAOHelper.moduleTemplateDAO.createQuery().filter("supervisors in", user).asList();
	}

	// register
	@POST
	@Path("{id}/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerToModuleTemplate(@PathParam("id") final String moduleId, final String userId) {
		final ModuleTemplate template = DAOHelper.moduleTemplateDAO.createQuery().filter("id =", moduleId).get();
		if (template == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unknown module " + moduleId).build());

		final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unknown user " + userId).build());

		Module module = DAOHelper.moduleDAO.createQuery().filter("user =", user).filter("template =", template).get();
		if (module != null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("User already registered to module template " + userId).build());

		module = new Module();
		module.user = user;
		module.template = template;
		DAOHelper.moduleDAO.save(module);
		return Response.status(Status.CREATED).build();
	}

	// unregister
	@POST
	@Path("{id}/unregister")
	public Response unregisterFromModuleTemplate(@PathParam("id") final String moduleId, final String userId) {
		final ModuleTemplate template = DAOHelper.moduleTemplateDAO.createQuery().filter("id =", moduleId).get();
		if (template == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(moduleId).build());

		final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(moduleId).build());
		final Module module = DAOHelper.moduleDAO.createQuery().filter("user =", user).get();
		if (module == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(moduleId).build());
		DAOHelper.moduleDAO.delete(module);
		return Response.status(Status.OK).build();
	}

	// add planning session
	@POST
	@Path("{id}/sessions/add")
	public ModuleTemplate addPlanningSessionToModuleTemplate(@PathParam("id") final String moduleId, final PlanningSession session) {
		final ModuleTemplate template = DAOHelper.moduleTemplateDAO.createQuery().filter("id =", moduleId).get();
		if (template == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(moduleId).build());
		for (final PlanningSession s : template.sessions)
			if (s.id.equals(session.id))
				throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
						.entity(JSON.serialize("Session already exists " + session.id)).build());
		final User user;
		if (session.endDate.before(session.startDate) || session.speaker == null
				|| (user = DAOHelper.userDAO.createQuery().filter("id =", session.speaker.id).get()) == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Invalid session " + session)).build());
		template.sessions.add(session);
		DAOHelper.planningSessionDAO.save(session);
		DAOHelper.moduleTemplateDAO.save(template);
		return template;
	}

	// rm session
	@POST
	@Path("{id}/sessions/remove")
	public ModuleTemplate removePlanningSessionFromModuleTemplate(@PathParam("id") final String moduleId, final PlanningSession session) {
		final ModuleTemplate template = DAOHelper.moduleTemplateDAO.createQuery().filter("id =", moduleId).get();
		if (template == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(moduleId).build());
		for (final PlanningSession s : template.sessions)
			if (s.id.equals(session.id)) {
				template.sessions.remove(s);
				DAOHelper.planningSessionDAO.delete(s);
				DAOHelper.moduleTemplateDAO.save(template);
				return template;
			}
		throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknown session " + session))
				.build());
	}

	// create
	@POST
	public Response createModuleTemplate(final ModuleTemplate entity) {
		DAOHelper.moduleTemplateDAO.save(entity);
		return Response.status(Status.CREATED).build();
	}

	// update
	@PUT
	public Response updateModuleTemplate(final ModuleTemplate entity) {
		DAOHelper.moduleTemplateDAO.save(entity);
		return Response.status(Status.OK).build();
	}

	// delete
	@DELETE
	public Response deleteModuleTemplate(final ModuleTemplate entity) {
		if (DAOHelper.moduleDAO.createQuery().filter("template =", entity).asList().size() != 0)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("Users are registered to this, unregister them all before deleting module.").build());
		DAOHelper.moduleTemplateDAO.delete(entity);
		return Response.status(Status.OK).build();
	}
}
