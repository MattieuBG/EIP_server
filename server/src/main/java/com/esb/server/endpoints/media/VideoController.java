package com.esb.server.endpoints.media;

import com.esb.server.entities.media.Image;
import com.esb.server.entities.media.Video;
import com.esb.server.helpers.DAOHelper;
import com.esb.server.services.media.VideoService;
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
public class VideoController {
    final static Logger logger = LoggerFactory.getLogger(VideoController.class);
    final static String collectionName = "Video";

    private VideoService service = new VideoService();

    /**
     * The method will return you all videos store in database
     * @return List of Video (List<Video>)
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Video> get() {
        return DAOHelper.videoDAO.find().asList();
    }

    /**
     * This method will retrun you the video corresponding to the id given
     * @return One Video
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}")
    public Video getById(@PathParam("id") final String id) {
        final Video video = DAOHelper.videoDAO.createQuery().filter("id =", id).get();
        if (video == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
        return video;
    }

    /**
     * This method will create entry in Imqge collection and multiples entries
     * in Video.chunks and Video.files.
     * Both are linked by and id (GidFS id is store in Video Collection)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Video entity) {
        logger.debug("Video = "+entity);
        service.saveFile(entity, collectionName);
        return Response.status(201).build();
    }

    /**
     * This method will upade entry in Imqge collection and remove entries
     * in Video.chunks and Video.files to recreate them GridFS API haven't
     * manage this kind of function.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Video entity) {
        service.updateFile(entity, collectionName);
        return Response.status(201).build();
    }

    /**
     * This method will delete entry in Imqge collection and entries
     * in Video.chunks and Video.files. In this case GridFS does the work
     * by herself.
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Video entity) {
        service.deleteFile(entity, collectionName);
        return Response.status(201).build();
    }
}
