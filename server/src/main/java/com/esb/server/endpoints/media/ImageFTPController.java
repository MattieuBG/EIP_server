package com.esb.server.endpoints.media;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esb.server.entities.management.User;
import com.esb.server.entities.media.ImageFTP;
import com.esb.server.helpers.DAOHelper;
import com.mongodb.util.JSON;

@Path("images/ftp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImageFTPController {
	private final String baseUrl = "/images/";

	@GET
	public List<ImageFTP> getImages() {
		return DAOHelper.imageFTPDAO.find().asList();
	}

	@GET
	@Path("{id}")
	public ImageFTP getImageById(@PathParam("id") final String id) {
		final ImageFTP image = DAOHelper.imageFTPDAO.createQuery().filter("id =", id).get();
		if (image == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknown image " + id))
					.build());
		return image;
	}

	@GET
	@Path("user/{id}")
	public List<ImageFTP> getImagesByIdAndModule(@PathParam("id") final String userId) {
		final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow user " + userId))
					.build());

		final List<ImageFTP> images = DAOHelper.imageFTPDAO.createQuery().filter("owner =", userId).asList();
		final List<ImageFTP> shared = DAOHelper.imageFTPDAO.createQuery().filter("sharedUsers =", userId).asList();

		images.addAll(shared);
		return images;
	}

	@POST
	public Response createImage(final ImageFTP entity) {
		entity.ftpUrl = baseUrl + entity.name;
		DAOHelper.imageFTPDAO.save(entity);
		return Response.status(Response.Status.CREATED).build();
	}

	@POST
	@Path("{id}/owner/set")
	public ImageFTP setOwnerForImage(@PathParam("id") final String imageId, final String ownerId) {
		final ImageFTP image = DAOHelper.imageFTPDAO.createQuery().filter("id =", imageId).get();
		final User owner = DAOHelper.userDAO.createQuery().filter("id =", ownerId).get();

		if (image == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknow image " + imageId)).build());
		if (ownerId == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknow owner " + ownerId)).build());

		image.owner = owner;
		DAOHelper.imageFTPDAO.save(image);
		return image;
	}

	@POST
	@Path("{id}/owner/unset")
	public ImageFTP setOwnerForImage(@PathParam("id") final String imageId) {
		final ImageFTP image = DAOHelper.imageFTPDAO.createQuery().filter("id =", imageId).get();
		if (image == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknow image " + imageId)).build());

		image.owner = null;
		DAOHelper.imageFTPDAO.save(image);
		return image;
	}

	@DELETE
	public Response delete(final ImageFTP entity) {
		return Response.status(200).build();
	}
}
