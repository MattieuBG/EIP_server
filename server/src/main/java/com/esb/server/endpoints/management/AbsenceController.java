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

import com.esb.server.entities.management.Absence;
import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.DAOHelper;

@Path("absences")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AbsenceController {

	// all absences
	@GET
	public List<Absence> getAbsences() {
		return DAOHelper.absenceDAO.find().asList();
	}

	// by id
	@GET
	@Path("{id}")
	public Module getAbsenceById(@PathParam("id") final String id) {
		final Module mod = DAOHelper.moduleDAO.createQuery().filter("id =", id).get();
		if (mod == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
		return mod;
	}

	// by user
	@GET
	@Path("user/{id}")
	public List<Absence> getAbsencesByUser(@PathParam("id") final String id) {
		final User user = DAOHelper.userDAO.createQuery().filter("id =", id).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unknown user " + id).build());
		return DAOHelper.absenceDAO.createQuery().filter("user =", user).asList();
	}

	@POST
	public Absence createAbsence(final Absence entity) {
		DAOHelper.absenceDAO.save(entity);
		return entity;
	}

	@PUT
	public Absence updateModule(final Absence entity) {
		DAOHelper.absenceDAO.save(entity);
		return entity;
	}

	@DELETE
	public Response deleteAbsence(final Absence entity) {
		DAOHelper.absenceDAO.delete(entity);
		return Response.status(Status.OK).build();
	}
}
