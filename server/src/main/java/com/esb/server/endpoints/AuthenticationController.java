package com.esb.server.endpoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esb.server.entities.management.User;
import com.esb.server.helpers.TokenHelper;

@Path("authenticate")
public class AuthenticationController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user)
	{
		String token = TokenHelper.setUser(user);
		return Response.status(201).header("token", token).build();
	}
}
