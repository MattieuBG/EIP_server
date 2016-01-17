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

import com.esb.server.entities.User;

@Path("/users")
public class UserController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User get() {

		User user = new User();

		return user;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(User user) {
		
		// do CREATE on database
		
		return Response.status(201).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(User user)
	{
		// do UPDATE on database
		return Response.status(201).build();
	}

	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(User user)
	{
		// do DELETE on database
		return Response.status(201).build();
	}

}
