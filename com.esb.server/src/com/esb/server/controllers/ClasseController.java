package com.esb.server.controllers;

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

import com.esb.server.dao.ClasseDAO;
import com.esb.sharedlibrary.entities.Classe;

@Path("/classes")
public class ClasseController {

	private ClasseDAO dao = new ClasseDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Classe> get() {
		return dao.find().asList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Classe item) {
		dao.save(item);
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Classe item)
	{
		// do UPDATE on database
		return Response.status(201).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(Classe item)
	{
		// do DELETE on database
		return Response.status(201).build();
	}
}