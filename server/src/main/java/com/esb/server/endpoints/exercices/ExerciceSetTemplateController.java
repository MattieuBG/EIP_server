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

import com.esb.server.entities.exercices.Exercice;
import com.esb.server.entities.exercices.ExerciceSet;
import com.esb.server.entities.exercices.ExerciceSetTemplate;
import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.ModuleTemplate;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;
import com.mongodb.util.JSON;

@Path("exercicesets/templates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExerciceSetTemplateController {

	// all exercice set templates
	@GET
	public List<ExerciceSetTemplate> getExerciceSetTemplate() {
		return DAOHelper.exerciceSetTemplateDAO.find().asList();
	}

	// by id
	@GET
	@Path("{id}")
	public ExerciceSetTemplate getExerciceSetTemplateById(@PathParam("id") final String id) {
		final ExerciceSetTemplate template = DAOHelper.exerciceSetTemplateDAO.createQuery().filter("id =", id).get();
		if (template == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize(id)).build());
		return template;
	}

	// register
	@POST
	@Path("{id}/register")
	public Response registerToExerciceSetTemplate(@PathParam("id") final String templateId, final String userId) {
		final ExerciceSetTemplate template = DAOHelper.exerciceSetTemplateDAO.createQuery().filter("id =", templateId).get();
		if (template == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("Unknown exercice set template " + templateId).build());

		final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unknown user " + userId).build());

		final ModuleTemplate moduleTemplate = DAOHelper.moduleTemplateDAO.createQuery().filter("exerciceSetTemplates =", template).get();
		if (moduleTemplate == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("Unknown module template for exercice set template " + templateId).build());

		final Module module = DAOHelper.moduleDAO.createQuery().filter("user =", user).filter("template =", moduleTemplate).get();
		if (module == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("Not registered to module " + moduleTemplate.id).build());

		final ExerciceSet set = new ExerciceSet();
		set.template = template;
		module.exerciceSets.add(set);
		DAOHelper.exerciceSetDAO.save(set);
		DAOHelper.moduleDAO.save(module);
		return Response.status(Status.CREATED).build();
	}

	// unregister
	@POST
	@Path("{id}/unregister")
	public Response unregisterFromExerciceSetTemplate(@PathParam("id") final String templateId, final String userId) {
		final ExerciceSetTemplate exerciceSetTemplate = DAOHelper.exerciceSetTemplateDAO.createQuery().filter("id =", templateId).get();
		if (exerciceSetTemplate == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("Unknown exercice set template " + templateId).build());

		final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unknown user " + userId).build());

		final ModuleTemplate moduleTemplate = DAOHelper.moduleTemplateDAO.createQuery()
				.filter("exerciceSetTemplates =", exerciceSetTemplate).get();
		if (moduleTemplate == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("Unknown module template for exercice set " + templateId).build());

		final Module module = DAOHelper.moduleDAO.createQuery().filter("user =", user).filter("template =", moduleTemplate).get();
		if (module == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("Not registered to module " + moduleTemplate.id).build());

		final ExerciceSet set = DAOHelper.exerciceSetDAO.createQuery().filter("user =", user).filter("template =", exerciceSetTemplate)
				.get();
		if (set == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("Not registered to exercice set template " + exerciceSetTemplate.id).build());

		DAOHelper.exerciceSetDAO.delete(set);
		return Response.status(Status.OK).build();
	}

	// add exercice
	@POST
	@Path("{id}/exercices/add")
	public ExerciceSetTemplate addExerciceToExerciceSetTemplate(@PathParam("id") final String templateId, final String exerciceId) {
		final ExerciceSetTemplate exerciceSetTemplate = DAOHelper.exerciceSetTemplateDAO.createQuery().filter("id =", templateId).get();
		if (exerciceSetTemplate == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("Unknown exercice set template " + templateId).build());

		Exercice exercice = DAOHelper.exerciceDAO.createQuery().filter("id =", exerciceId).get();
		if (exercice == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknown exercice " + exerciceId)).build());

		final List<ExerciceSetTemplate> templates = DAOHelper.exerciceSetTemplateDAO.createQuery().filter("exercices =", exerciceId)
				.asList();
		if (templates.size() != 0) {
			exercice = new Exercice(exercice);
			DAOHelper.exerciceDAO.save(exercice);
		}
		exerciceSetTemplate.exercices.add(exercice);
		DAOHelper.exerciceSetTemplateDAO.save(exerciceSetTemplate);
		return exerciceSetTemplate;
	}

	// remove exercice
	@POST
	@Path("{id}/exercices/remove")
	public ExerciceSetTemplate removeExerciceFromExerciceSetTemplate(@PathParam("id") final String templateId, final String exerciceId) {
		final ExerciceSetTemplate exerciceSetTemplate = DAOHelper.exerciceSetTemplateDAO.createQuery().filter("id =", templateId).get();
		if (exerciceSetTemplate == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknown exercice set template " + templateId)).build());

		final Exercice exercice = DAOHelper.exerciceDAO.createQuery().filter("id =", exerciceId).get();
		if (exercice == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknown exercice " + exerciceId)).build());

		exerciceSetTemplate.exercices.remove(exercice);
		DAOHelper.exerciceSetTemplateDAO.save(exerciceSetTemplate);
		return exerciceSetTemplate;
	}

	@POST
	public Response createExerciceSetTemplate(final ExerciceSetTemplate entity) {
		DAOHelper.exerciceSetTemplateDAO.save(entity);
		return Response.status(Status.CREATED).build();
	}

	@PUT
	public Response updateExerciceSetTemplate(final ExerciceSetTemplate entity) {
		DAOHelper.exerciceSetTemplateDAO.save(entity);
		return Response.status(Status.OK).build();
	}

	@DELETE
	public Response deleteExerciceSetTemplate(final ExerciceSetTemplate entity) {
		if (DAOHelper.exerciceSetDAO.createQuery().filter("template =", entity).asList().size() != 0)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("Users are registered to this, unregister them all before deleting exercice set.").build());
		DAOHelper.exerciceSetTemplateDAO.delete(entity);
		return Response.status(Status.OK).build();
	}
}
