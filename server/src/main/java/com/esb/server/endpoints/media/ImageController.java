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
import com.esb.server.entities.media.Image;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esb.server.entities.media.Image;
import com.esb.server.helpers.DAOHelper;
import com.esb.server.services.media.ImageService;

@Path("images")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImageController {

	final static Logger logger = LoggerFactory.getLogger(ImageController.class);
	final static String collectionName = "Image";

	private final ImageService service = new ImageService();

	/**
	 * The method will return you all images store in database
	 *
	 * @return List of Image (List<Image>)
	 */
	@GET
	public List<Image> getImages() {
		return DAOHelper.imageDAO.find().asList();
	}

	/**
	 * This method will retrun you the image corresponding to the id given
	 *
	 * @return One Image
	 */
	@GET
	@Path("{id}")
	public Image getImageById(@PathParam("id") final String id) {
		final Image image = DAOHelper.imageDAO.createQuery().filter("id =", id).get();

        if (image == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknown id " + id)).build());
        return image;
	}

    @GET
    @Path("user/{user_id}/module/{module_id} | module/{module_id}/user/{user_id}")
    public List<Image> getImagesByIdAndModule(@PathParam("user_id") final String userId, @PathParam("module_id") final String moduleId) {
        final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
        final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();

        if (user == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow user " + userId)).build());
        if (module == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow module " + moduleId)).build());

        return DAOHelper.imageDAO.createQuery().filter("owner =", user).filter("module =", module).asList();
    }

    @POST
    @Path("{id}/module/set")
    public Image setModuleForImage(@PathParam("id") final String imageId, final String moduleId) {
        final Image image = DAOHelper.imageDAO.createQuery().filter("id =", imageId).get();
        final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();

        if (image == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow image " + imageId)).build());
        if (module == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow module " + moduleId)).build());

        if (image.getOwner() == null)
            image.setOwner(module.user);
        else if (!module.user.equals(image.getOwner()))
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Module does not belong to user " + image.getOwner())).build());

        image.setModule(module);
        DAOHelper.imageDAO.save(image);
        return image;
    }

    @POST
    @Path("{id}/module/unset")
    public Image setModuleForImage(@PathParam("id") final String imageId) {
        final Image image = DAOHelper.imageDAO.createQuery().filter("id =", imageId).get();

        if (image == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow image " + imageId)).build());

        image.setModule(null);
        DAOHelper.imageDAO.save(image);
        return image;
    }

    @POST
    @Path("{id}/owner/set")
    public Image setOwnerForImage(@PathParam("id") final String imageId, final String ownerId) {
        final Image image = DAOHelper.imageDAO.createQuery().filter("id =", imageId).get();
        final User owner = DAOHelper.userDAO.createQuery().filter("id =", ownerId).get();

        if (image == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow image " + imageId)).build());
        if (ownerId == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow owner " + ownerId)).build());

        image.setOwner(owner);
        DAOHelper.imageDAO.save(image);
        return image;
    }

    @POST
    @Path("{id}/owner/unset")
    public Image setOwnerForImage(@PathParam("id") final String imageId) {
        final Image image = DAOHelper.imageDAO.createQuery().filter("id =", imageId).get();

        if (image == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow image " + imageId)).build());

        image.setOwner(null);
        DAOHelper.imageDAO.save(image);
        return image;
    }

	/**
	 * This method will create entry in Imqge collection and multiples entries
	 * in Image.chunks and Image.files. Both are linked by and id (GidFS id is
	 * store in Image Collection)
	 */
	@POST
	public Response createImage(final Image entity) {
		logger.debug("Image = " + entity);
		service.saveFile(entity, collectionName);
		return Response.status(201).build();
	}

	/**
	 * This method will upade entry in Imqge collection and remove entries in
	 * Image.chunks and Image.files to recreate them GridFS API haven't manage
	 * this kind of function.
	 */
	@PUT
	public Response updateImage(final Image entity) {
		service.updateFile(entity, collectionName);
		return Response.status(201).build();
	}

	/**
	 * This method will delete entry in Imqge collection and entries in
	 * Image.chunks and Image.files. In this case GridFS does the work by
	 * herself.
	 */
	@DELETE
	public Response delete(final Image entity) {
		service.deleteFile(entity, collectionName);
		return Response.status(201).build();
	}
}
