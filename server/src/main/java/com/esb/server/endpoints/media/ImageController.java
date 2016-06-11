package com.esb.server.endpoints.media;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esb.server.entities.media.Image;
import com.esb.server.helpers.DAOHelper;
import com.esb.server.services.media.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("images")
public class ImageController {

	final static Logger logger = LoggerFactory.getLogger(ImageController.class);
	private ImageService service = new ImageService();

	/**
	 * The method will return you all images store in database
	 * @return List of Image (List<Image>)
     */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Image> get() {
		return DAOHelper.imageDAO.find().asList();
	}

	/**
	 * This method will retrun you the image corresponding to the id given
	 * @return One Image
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}")
    public Image getById(@PathParam("id") String id) {
        return DAOHelper.imageDAO.createQuery().filter("id =", id).get();
    }

	/**
	 * This method will create entry in Imqge collection and multiples entries
	 * in Image.chunks and Image.files.
	 * Both are linked by and id (GidFS id is store in Image Collection)
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Image entity) {
        logger.debug("Image = "+entity);
        service.saveImage(entity);
        //DAOHelper.imageDAO.save(entity);
		return Response.status(201).build();
	}

	/**
	 * This method will upade entry in Imqge collection and remove entries
	 * in Image.chunks and Image.files to recreate them GridFS API haven't
	 * manage this kind of function.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Image entity) {
		service.updateImage(entity);
		return Response.status(201).build();
	}

	/**
	 * This method will delete entry in Imqge collection and entries
	 * in Image.chunks and Image.files. In this case GridFS does the work
	 * by herself.
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(Image entity) {
		service.deleteImage(entity);
		return Response.status(201).build();
	}
}
