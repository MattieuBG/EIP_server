package com.esb.server.endpoints.media;

import com.esb.server.entities.media.Drawing;
import com.esb.server.helpers.DAOHelper;
import com.esb.server.services.media.DrawingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by alex on 12/06/16.
 * Project name : Eschoolbag
 */
public class DrawingController {
    final static Logger logger = LoggerFactory.getLogger(DrawingController.class);
    final static String collectionName = "Drawing";

    private DrawingService service = new DrawingService();

    /**
     * The method will return you all drawings store in database
     * @return List of Drawing (List<Drawing>)
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Drawing> get() {
        return DAOHelper.drawingDAO.find().asList();
    }

    /**
     * This method will retrun you the drawing corresponding to the id given
     * @return One Drawing
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}")
    public Drawing getById(@PathParam("id") String id) {
        return DAOHelper.drawingDAO.createQuery().filter("id =", id).get();
    }

    /**
     * This method will create entry in Imqge collection and multiples entries
     * in Drawing.chunks and Drawing.files.
     * Both are linked by and id (GidFS id is store in Drawing Collection)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Drawing entity) {
        logger.debug("Drawing = "+entity);
        service.saveFile(entity, collectionName);
        //service.saveDrawing(entity);
        //DAOHelper.drawingDAO.save(entity);
        return Response.status(201).build();
    }

    /**
     * This method will upade entry in Imqge collection and remove entries
     * in Drawing.chunks and Drawing.files to recreate them GridFS API haven't
     * manage this kind of function.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Drawing entity) {
        service.updateFile(entity, collectionName);
        //	service.updateDrawing(entity);
        return Response.status(201).build();
    }

    /**
     * This method will delete entry in Imqge collection and entries
     * in Drawing.chunks and Drawing.files. In this case GridFS does the work
     * by herself.
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Drawing entity) {
        service.deleteFile(entity, collectionName);
        //service.deleteDrawing(entity);
        return Response.status(201).build();
    }
}
