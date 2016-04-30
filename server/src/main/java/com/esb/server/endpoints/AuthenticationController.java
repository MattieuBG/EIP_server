package com.esb.server.endpoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class AuthenticationController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login()
	{
		return Response.status(201).build();
	}
}
