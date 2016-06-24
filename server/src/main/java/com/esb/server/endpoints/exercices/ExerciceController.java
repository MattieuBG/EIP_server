package com.esb.server.endpoints.exercices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esb.server.entities.exercices.Exercice;
import com.esb.server.helpers.DAOHelper;

@Path("exercices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExerciceController {

	@GET
	public List<Exercice> getExercices() {
		return DAOHelper.exerciceDAO.find().asList();
	}

	@POST
	public Response createExercice(final Exercice entity) {
		DAOHelper.exerciceDAO.save(entity);
		return Response.status(201).build();
	}

	@PUT
	public Response updateExercice(final Exercice entity) {
		DAOHelper.exerciceDAO.save(entity);
		return Response.status(201).build();
	}

	@DELETE
	public Response deleteExercice(final Exercice entity) {
		DAOHelper.exerciceDAO.delete(entity);
		return Response.status(201).build();
	}
}
