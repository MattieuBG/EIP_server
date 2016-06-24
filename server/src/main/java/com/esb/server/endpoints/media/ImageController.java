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
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(id).build());
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

/*  *//**
 * The method will return you all images store in database
 *
 * @return List of Image (List<Image>)
 */
/*
 * @GET
 * 
 * @Produces(MediaType.APPLICATION_JSON) public List<Image> get() { return
 * DAOHelper.imageDAO.find().asList(); }
 *//**
 * This method will retrun you the image corresponding to the id given
 *
 * @return One Image
 */
/*
 * @GET
 * 
 * @Produces(MediaType.APPLICATION_JSON)
 * 
 * @Path("id/{id}") public Image getById(@PathParam("id") String id) { return
 * DAOHelper.imageDAO.createQuery().filter("id =", id).get(); }
 *//**
 * This method will create entry in Imqge collection and multiples entries in
 * Image.chunks and Image.files. Both are linked by and id (GidFS id is store in
 * Image Collection)
 */
/*
 * @POST
 * 
 * @Consumes(MediaType.APPLICATION_JSON) public Response create(Image entity) {
 * logger.debug("Image = "+entity); //service.saveImage(entity);
 * //DAOHelper.imageDAO.save(entity); return Response.status(201).build(); }
 *//**
 * This method will upade entry in Imqge collection and remove entries in
 * Image.chunks and Image.files to recreate them GridFS API haven't manage this
 * kind of function.
 */
/*
 * @PUT
 * 
 * @Consumes(MediaType.APPLICATION_JSON) public Response update(Image entity) {
 * // service.updateImage(entity); return Response.status(201).build(); }
 *//**
 * This method will delete entry in Imqge collection and entries in
 * Image.chunks and Image.files. In this case GridFS does the work by herself.
 */
/*
 * @DELETE
 * 
 * @Consumes(MediaType.APPLICATION_JSON) public Response delete(Image entity) {
 * //service.deleteImage(entity); return Response.status(201).build(); }
 */