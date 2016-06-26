package com.esb.server.endpoints.media;

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

import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.User;
import com.esb.server.entities.media.Drawing;
import com.esb.server.entities.media.Drawing;
import com.esb.server.entities.media.Drawing;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esb.server.entities.media.Drawing;
import com.esb.server.helpers.DAOHelper;
import com.esb.server.services.media.DrawingService;

/**
 * Created by alex on 12/06/16. Project name : Eschoolbag
 */
@Path("drawings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DrawingController {
	final static Logger logger = LoggerFactory.getLogger(DrawingController.class);
	final static String collectionName = "Drawing";

	private final DrawingService service = new DrawingService();

	/**
	 * The method will return you all drawings store in database
	 *
	 * @return List of Drawing (List<Drawing>)
	 */
	@GET
	public List<Drawing> getDrawings() {
		return DAOHelper.drawingDAO.find().asList();
	}

	/**
	 * This method will retrun you the drawing corresponding to the id given
	 *
	 * @return One Drawing
	 */
	@GET
	@Path("{id}")
	public Drawing getDrawingById(@PathParam("id") final String id) {
		final Drawing drawing = DAOHelper.drawingDAO.createQuery().filter("id =", id).get();
		if (drawing == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknown id " + id)).build());
		return drawing;
	}

    @GET
    @Path("user/{user_id}/module/{module_id} | module/{module_id}/user/{user_id}")
    public List<Drawing> getDrawingsByIdAndModule(@PathParam("user_id") final String userId, @PathParam("module_id") final String moduleId) {
        final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
        final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();

        if (user == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow user " + userId)).build());
        if (module == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow module " + moduleId)).build());

        return DAOHelper.drawingDAO.createQuery().filter("owner =", user).filter("module =", module).asList();
    }

    @POST
    @Path("{id}/module/set")
    public Drawing setModuleForDrawing(@PathParam("id") final String drawingId, final String moduleId) {
        final Drawing drawing = DAOHelper.drawingDAO.createQuery().filter("id =", drawingId).get();
        final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();

        if (drawing == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow drawing " + drawingId)).build());
        if (module == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow module " + moduleId)).build());

        if (drawing.getOwner() == null)
            drawing.setOwner(module.user);
        else if (!module.user.equals(drawing.getOwner()))
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Module does not belong to user " + drawing.getOwner())).build());

        drawing.setModule(module);
        DAOHelper.drawingDAO.save(drawing);
        return drawing;
    }

    @POST
    @Path("{id}/module/unset")
    public Drawing setModuleForDrawing(@PathParam("id") final String drawingId) {
        final Drawing drawing = DAOHelper.drawingDAO.createQuery().filter("id =", drawingId).get();

        if (drawing == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow drawing " + drawingId)).build());

        drawing.setModule(null);
        DAOHelper.drawingDAO.save(drawing);
        return drawing;
    }

    @POST
    @Path("{id}/owner/set")
    public Drawing setOwnerForDrawing(@PathParam("id") final String drawingId, final String ownerId) {
        final Drawing drawing = DAOHelper.drawingDAO.createQuery().filter("id =", drawingId).get();
        final User owner = DAOHelper.userDAO.createQuery().filter("id =", ownerId).get();

        if (drawing == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow drawing " + drawingId)).build());
        if (ownerId == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow owner " + ownerId)).build());

        drawing.setOwner(owner);
        DAOHelper.drawingDAO.save(drawing);
        return drawing;
    }

    @POST
    @Path("{id}/owner/unset")
    public Drawing setOwnerForDrawing(@PathParam("id") final String drawingId) {
        final Drawing drawing = DAOHelper.drawingDAO.createQuery().filter("id =", drawingId).get();

        if (drawing == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow drawing " + drawingId)).build());

        drawing.setOwner(null);
        DAOHelper.drawingDAO.save(drawing);
        return drawing;
    }
    
	/**
	 * This method will create entry in Imqge collection and multiples entries
	 * in Drawing.chunks and Drawing.files. Both are linked by and id (GidFS id
	 * is store in Drawing Collection)
	 */
	@POST
	public Response createDrawing(final Drawing entity) {
		logger.debug("Drawing = " + entity);
		service.saveFile(entity, collectionName);
		return Response.status(201).build();
	}

	/**
	 * This method will upade entry in Imqge collection and remove entries in
	 * Drawing.chunks and Drawing.files to recreate them GridFS API haven't
	 * manage this kind of function.
	 */
	@PUT
	public Response updateDrawing(final Drawing entity) {
		service.updateFile(entity, collectionName);
		return Response.status(201).build();
	}

	/**
	 * This method will delete entry in Imqge collection and entries in
	 * Drawing.chunks and Drawing.files. In this case GridFS does the work by
	 * herself.
	 */
	@DELETE
	public Response deleteDrawing(final Drawing entity) {
		service.deleteFile(entity, collectionName);
		return Response.status(201).build();
	}
}
