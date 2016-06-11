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

import com.esb.server.entities.management.ModuleTemplate;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;

@Path("modules/templates")
public class ModuleTemplateController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ModuleTemplate> all() {
		return DAOHelper.moduleTemplateDAO.find().asList();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ModuleTemplate getById(@PathParam("id") String id) {
		ModuleTemplate template = DAOHelper.moduleTemplateDAO.createQuery()
				.filter("id =", id).get();
		if (template == null)
			throw new WebApplicationException(Response.status(
					Response.Status.BAD_REQUEST).build());
		return template;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("supervisors/{id}")
	public List<ModuleTemplate> getByUser(@PathParam("id") String id) {
		User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		if (user == null)
			throw new WebApplicationException(Response
					.status(Response.Status.BAD_REQUEST)
					.entity("Unknown supervisor.").build());
		return DAOHelper.moduleTemplateDAO.createQuery()
				.filter("supervisors in", user).asList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(ModuleTemplate entity) {
		DAOHelper.moduleTemplateDAO.save(entity);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(ModuleTemplate entity) {
		DAOHelper.moduleTemplateDAO.save(entity);
		return Response.status(Response.Status.OK).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(ModuleTemplate entity) {
		DAOHelper.moduleTemplateDAO.delete(entity);
		return Response.status(Response.Status.OK).build();
	}
}
