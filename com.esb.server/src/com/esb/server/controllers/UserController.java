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

import com.esb.server.dao.UserDAO;
import com.esb.sharedlibrary.entities.User;

@Path("/users")
public class UserController {
	private UserDAO dao = new UserDAO();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> get() {
		return dao.find().asList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(User item) {
		dao.save(item);
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(User item)
	{
		// do UPDATE on database
		return Response.status(201).build();
	}

	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(User item)
	{
		// do DELETE on database
		return Response.status(201).build();
	}

}
