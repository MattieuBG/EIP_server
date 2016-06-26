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
import com.esb.server.entities.media.Audio;
import com.esb.server.entities.media.Audio;
import com.esb.server.entities.media.Audio;
import com.mongodb.util.JSON;
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

	final static Logger logger = LoggerFactory.getLogger(AudioController.class);
	final static String collectionName = "Audio";

	private final AudioService service = new AudioService();

	/**
	 * The method will return you all audios store in database
	 *
	 * @return List of Audio (List<Audio>)
	 */
	@GET
	public List<Audio> getAudios() {
		return DAOHelper.audioDAO.find().asList();
	}

	/**
	 * This method will retrun you the audio corresponding to the id given
	 *
	 * @return One Audio
	 */
	@GET
	@Path("{id}")
	public Audio getAudioById(@PathParam("id") final String id) {
		final Audio audio = DAOHelper.audioDAO.createQuery().filter("id =", id).get();
		if (audio == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknown id " + id)).build());
		return audio;
	}

    @GET
    @Path("user/{user_id}/module/{module_id} | module/{module_id}/user/{user_id}")
    public List<Audio> getAudiosByIdAndModule(@PathParam("user_id") final String userId, @PathParam("module_id") final String moduleId) {
        final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
        final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();

        if (user == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow user " + userId)).build());
        if (module == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow module " + moduleId)).build());

        return DAOHelper.audioDAO.createQuery().filter("owner =", user).filter("module =", module).asList();
    }

    @POST
    @Path("{id}/module/set")
    public Audio setModuleForAudio(@PathParam("id") final String audioId, final String moduleId) {
        final Audio audio = DAOHelper.audioDAO.createQuery().filter("id =", audioId).get();
        final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();

        if (audio == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow audio " + audioId)).build());
        if (module == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow module " + moduleId)).build());

        if (audio.getOwner() == null)
            audio.setOwner(module.user);
        else if (!module.user.equals(audio.getOwner()))
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Module does not belong to user " + audio.getOwner())).build());

        audio.setModule(module);
        DAOHelper.audioDAO.save(audio);
        return audio;
    }

    @POST
    @Path("{id}/module/unset")
    public Audio setModuleForAudio(@PathParam("id") final String audioId) {
        final Audio audio = DAOHelper.audioDAO.createQuery().filter("id =", audioId).get();

        if (audio == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow audio " + audioId)).build());

        audio.setModule(null);
        DAOHelper.audioDAO.save(audio);
        return audio;
    }

    @POST
    @Path("{id}/owner/set")
    public Audio setOwnerForAudio(@PathParam("id") final String audioId, final String ownerId) {
        final Audio audio = DAOHelper.audioDAO.createQuery().filter("id =", audioId).get();
        final User owner = DAOHelper.userDAO.createQuery().filter("id =", ownerId).get();

        if (audio == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow audio " + audioId)).build());
        if (ownerId == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow owner " + ownerId)).build());

        audio.setOwner(owner);
        DAOHelper.audioDAO.save(audio);
        return audio;
    }

    @POST
    @Path("{id}/owner/unset")
    public Audio setOwnerForAudio(@PathParam("id") final String audioId) {
        final Audio audio = DAOHelper.audioDAO.createQuery().filter("id =", audioId).get();

        if (audio == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow audio " + audioId)).build());

        audio.setOwner(null);
        DAOHelper.audioDAO.save(audio);
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
