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
import com.esb.server.entities.management.Module;
import com.esb.server.helpers.DAOHelper;

@Path("exercices")
public class ExerciceController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Exercice> get() {
		return DAOHelper.exerciceDAO.find().asList();
	}

//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("{id}")
//	public List<Exercice> getById() {
//		Exercice ex = DAOHelper.exerciceDAO.find().asList();
//	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Exercice entity) {
		DAOHelper.exerciceDAO.save(entity);
		return Response.status(201).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Exercice entity) {
		DAOHelper.exerciceDAO.save(entity);
		return Response.status(201).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(Exercice entity) {
		DAOHelper.exerciceDAO.delete(entity);
		return Response.status(201).build();
	}
}
