package com.esb.server.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.esb.server.annotations.Authenticated;
import com.esb.server.entities.management.User;
import com.esb.server.helpers.TokenHelper;

@Authenticated
@Provider
public class AuthenticatedFilter implements ContainerRequestFilter {
	@Context
	HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		String token = request.getParameter("token");
		if (token == null)
			token = "";
		String path = request.getRequestURI().substring(
				request.getContextPath().length());
		User user = TokenHelper.getUser(token);

		if (user != null || (user == null && path.endsWith("/authenticate"))){
		} else {
			requestContext
					.abortWith(Response
							.status(Response.Status.UNAUTHORIZED)
							.entity("Invalid token. If session existed, it has been deleted.")
							.build());
		}
	}
}