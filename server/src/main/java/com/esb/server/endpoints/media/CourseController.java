package com.esb.server.endpoints.media;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.User;
import com.esb.server.entities.media.Course;
import com.esb.server.helpers.DAOHelper;
import com.mongodb.util.JSON;

/**
 * Created by alex on 18/06/16. Project name : Eschoolbag
 */
@Path("courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseController {

	final static Logger logger = LoggerFactory.getLogger(ImageController.class);

	@GET
	public List<Course> getCourses() {
		return DAOHelper.courseDAO.find().asList();
	}

	@GET
	@Path("{id}")
	public Course getCourseById(@PathParam("id") final String id) {
		final Course course = DAOHelper.courseDAO.createQuery().filter("id =", id).get();

		if (course == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknown id " + id))
					.build());
		return course;
	}

	@GET
	@Path("user/{user_id}/module/{module_id} | module/{module_id}/user/{user_id}")
	public List<Course> getCoursesByIdAndModule(@PathParam("user_id") final String userId, @PathParam("module_id") final String moduleId) {
		final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
		final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();

		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow user " + userId))
					.build());
		if (module == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknow module " + moduleId)).build());

		return DAOHelper.courseDAO.createQuery().filter("owner =", user).filter("module =", module).asList();
	}

	@POST
	@Path("{id}/module/set")
	public Course setModuleForCourse(@PathParam("id") final String courseId, final String moduleId) {
		final Course course = DAOHelper.courseDAO.createQuery().filter("id =", courseId).get();

		if (course == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknow course " + courseId)).build());

		final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();

		if (module == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknow module " + moduleId)).build());

		if (course.getOwner() == null)
			course.setOwner(module.user);
		else if (!module.user.equals(course.getOwner()))
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Module does not belong to user " + course.getOwner())).build());

		course.setModule(module);
		DAOHelper.courseDAO.save(course);
		return course;
	}

	@POST
	@Path("{id}/module/unset")
	public Course setModuleForCourse(@PathParam("id") final String courseId) {
		final Course course = DAOHelper.courseDAO.createQuery().filter("id =", courseId).get();
		if (course == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknow course " + courseId)).build());

		course.setModule(null);
		DAOHelper.courseDAO.save(course);
		return course;
	}

	@POST
	public Course createCourse(final Course entity) {
		entity.setCreationDate(new Date());
		DAOHelper.courseDAO.save(entity);
		return entity;
	}

	@PUT
	public Course updateCourse(final Course entity) {
		entity.setModifiedDate(new Date());
		DAOHelper.courseDAO.save(entity);
		return entity;
	}

	@DELETE
	public Response deleteCourse(final Course entity) {
		DAOHelper.courseDAO.delete(entity);
		return Response.status(Status.OK).build();
	}
}
