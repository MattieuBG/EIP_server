package com.esb.server.endpoints.media;

import com.esb.server.entities.media.Audio;
import com.esb.server.entities.media.Course;
import com.esb.server.helpers.DAOHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by alex on 18/06/16.
 * Project name : Eschoolbag
 */
@Path("courses")
public class CourseController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> get() {
        return DAOHelper.courseDAO.find().asList();
    }

	@GET
	@Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}")
	public Course getById(@PathParam("id") final String id) {
        final Course course = DAOHelper.courseDAO.createQuery().filter("id =", id).get();
        if (course == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
        return course;
	}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Course entity) {
        DAOHelper.courseDAO.save(entity);
        return Response.status(201).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Course entity) {
        DAOHelper.courseDAO.save(entity);
        return Response.status(201).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Course entity) {
        DAOHelper.courseDAO.delete(entity);
        return Response.status(201).build();
    }
}
