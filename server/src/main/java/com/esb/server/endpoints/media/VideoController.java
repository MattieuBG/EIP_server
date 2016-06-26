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
import com.esb.server.entities.media.Video;
import com.esb.server.entities.media.Video;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esb.server.entities.media.Video;
import com.esb.server.helpers.DAOHelper;
import com.esb.server.services.media.VideoService;

/**
 * Created by alex on 12/06/16. Project name : Eschoolbag
 */
@Path("videos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VideoController {
	final static Logger logger = LoggerFactory.getLogger(VideoController.class);
	final static String collectionName = "Video";

	private final VideoService service = new VideoService();

	/**
	 * The method will return you all videos store in database
	 *
	 * @return List of Video (List<Video>)
	 */
	@GET
	public List<Video> getVideos() {
		return DAOHelper.videoDAO.find().asList();
	}

	/**
	 * This method will retrun you the video corresponding to the id given
	 *
	 * @return One Video
	 */
	@GET
	@Path("{id}")
	public Video getVideoById(@PathParam("id") final String id) {
		final Video video = DAOHelper.videoDAO.createQuery().filter("id =", id).get();
		if (video == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknown id " + id)).build());
		return video;
	}

    @GET
    @Path("user/{user_id}/module/{module_id} | module/{module_id}/user/{user_id}")
    public List<Video> getVideosByIdAndModule(@PathParam("user_id") final String userId, @PathParam("module_id") final String moduleId) {
        final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
        final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();

        if (user == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow user " + userId)).build());
        if (module == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow module " + moduleId)).build());

        return DAOHelper.videoDAO.createQuery().filter("owner =", user).filter("module =", module).asList();
    }

    @POST
    @Path("{id}/module/set")
    public Video setModuleForVideo(@PathParam("id") final String videoId, final String moduleId) {
        final Video video = DAOHelper.videoDAO.createQuery().filter("id =", videoId).get();
        final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();

        if (video == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow video " + videoId)).build());
        if (module == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow module " + moduleId)).build());

        if (video.getOwner() == null)
            video.setOwner(module.user);
        else if (!module.user.equals(video.getOwner()))
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Module does not belong to user " + video.getOwner())).build());

        video.setModule(module);
        DAOHelper.videoDAO.save(video);
        return video;
    }

    @POST
    @Path("{id}/module/unset")
    public Video setModuleForVideo(@PathParam("id") final String videoId) {
        final Video video = DAOHelper.videoDAO.createQuery().filter("id =", videoId).get();

        if (video == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow video " + videoId)).build());

        video.setModule(null);
        DAOHelper.videoDAO.save(video);
        return video;
    }

    @POST
    @Path("{id}/owner/set")
    public Video setOwnerForVideo(@PathParam("id") final String videoId, final String ownerId) {
        final Video video = DAOHelper.videoDAO.createQuery().filter("id =", videoId).get();
        final User owner = DAOHelper.userDAO.createQuery().filter("id =", ownerId).get();

        if (video == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow video " + videoId)).build());
        if (ownerId == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow owner " + ownerId)).build());

        video.setOwner(owner);
        DAOHelper.videoDAO.save(video);
        return video;
    }

    @POST
    @Path("{id}/owner/unset")
    public Video setOwnerForVideo(@PathParam("id") final String videoId) {
        final Video video = DAOHelper.videoDAO.createQuery().filter("id =", videoId).get();

        if (video == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow video " + videoId)).build());

        video.setOwner(null);
        DAOHelper.videoDAO.save(video);
        return video;
    }
    
	/**
	 * This method will create entry in Imqge collection and multiples entries
	 * in Video.chunks and Video.files. Both are linked by and id (GidFS id is
	 * store in Video Collection)
	 */
	@POST
	public Response createVideo(final Video entity) {
		logger.debug("Video = " + entity);
		service.saveFile(entity, collectionName);
		return Response.status(201).build();
	}

	/**
	 * This method will upade entry in Imqge collection and remove entries in
	 * Video.chunks and Video.files to recreate them GridFS API haven't manage
	 * this kind of function.
	 */
	@PUT
	public Response updateVideo(final Video entity) {
		service.updateFile(entity, collectionName);
		return Response.status(201).build();
	}

	/**
	 * This method will delete entry in Imqge collection and entries in
	 * Video.chunks and Video.files. In this case GridFS does the work by
	 * herself.
	 */
	@DELETE
	public Response deleteVideo(final Video entity) {
		service.deleteFile(entity, collectionName);
		return Response.status(201).build();
	}
}
