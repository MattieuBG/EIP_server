package com.esb.server.controllers;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esb.sharedlibrary.entities.Exercice;

@Path("/exercices")
public class ExerciceController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Exercice get() {
		Exercice item = new Exercice();
		return item;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Exercice item) {
		// do CREATE on database
		return Response.status(201).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Exercice item)
	{
		// do UPDATE on database
		return Response.status(201).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(Exercice item)
	{
		// do DELETE on database
		return Response.status(201).build();
	}
}