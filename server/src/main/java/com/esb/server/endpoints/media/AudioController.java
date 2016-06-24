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

import com.esb.server.entities.media.Audio;
import com.esb.server.helpers.DAOHelper;
import com.esb.server.services.media.AudioService;

/**
 * Created by alex on 12/06/16. Project name : Eschoolbag
 */
@Path("audios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AudioController {

	final static Logger logger = LoggerFactory.getLogger(ImageController.class);
	final static String collectionName = "Audio";

	private final AudioService service = new AudioService();

	/**
	 * The method will return you all images store in database
	 *
	 * @return List of Audio (List<Audio>)
	 */
	@GET
	public List<Audio> getAudios() {
		return DAOHelper.audioDAO.find().asList();
	}

	/**
	 * This method will retrun you the image corresponding to the id given
	 *
	 * @return One Audio
	 */
	@GET
	@Path("{id}")
	public Audio getAudioById(@PathParam("id") final String id) {
		final Audio audio = DAOHelper.audioDAO.createQuery().filter("id =", id).get();
		if (audio == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
		return audio;
	}

	/**
	 * This method will create entry in Imqge collection and multiples entries
	 * in Audio.chunks and Audio.files. Both are linked by and id (GidFS id is
	 * store in Audio Collection)
	 */
	@POST
	public Response createAudio(final Audio entity) {
		logger.debug("Audio = " + entity);
		service.saveFile(entity, collectionName);
		return Response.status(201).build();
	}

	/**
	 * This method will upade entry in Imqge collection and remove entries in
	 * Audio.chunks and Audio.files to recreate them GridFS API haven't manage
	 * this kind of function.
	 */
	@PUT
	public Response updateAudio(final Audio entity) {
		service.updateFile(entity, collectionName);
		return Response.status(201).build();
	}

	/**
	 * This method will delete entry in Imqge collection and entries in
	 * Audio.chunks and Audio.files. In this case GridFS does the work by
	 * herself.
	 */
	@DELETE
	public Response deleteAudio(final Audio entity) {
		service.deleteFile(entity, collectionName);
		return Response.status(201).build();
	}
}
