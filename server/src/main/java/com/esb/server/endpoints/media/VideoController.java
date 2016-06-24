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
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
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
