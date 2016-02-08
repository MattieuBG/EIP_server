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

import com.esb.server.dao.PlanningSessionDAO;
import com.esb.sharedlibrary.entities.PlanningSession;


@Path("/plannings")
public class PlanningSessionController {

	private PlanningSessionDAO dao = new PlanningSessionDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlanningSession> get() {
		PlanningSession  item = dao.find();
		
		if (item){
			List<PlanningSession> itemList = item.asList();
			return itemList;
		}
		return null;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(PlanningSession item) {
		dao.save(item);
		return Response.ok().build();	
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(PlanningSession item)
	{
		// do UPDATE on database
		return Response.status(201).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(PlanningSession item)
	{
		// do DELETE on database
		return Response.status(201).build();
	}
}