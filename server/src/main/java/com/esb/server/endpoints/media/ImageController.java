package com.esb.server.endpoints.media;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esb.server.dao.media.ImageDAO;
import com.esb.server.services.media.ImageService;
import com.esb.server.entities.media.Image;
import com.esb.server.helpers.DAOHelper;

import java.util.List;

@Path("images")
public class ImageController {

	private ImageDAO dao = new ImageDAO();
	private ImageService service = new ImageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Image> get() {
		return DAOHelper.imageDAO.find().asList();
	}

    // TODO Get une image en particulier via son ID
   /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}")
    public Image getById(@PathParam("id") String id) {
        return DAOHelper.imageDAO.createQuery().filter("id =", id).get();
    }*/

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Image item) {
        service.saveImage(item);
		return Response.status(201).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Image item)
	{
		service.updateImage(item);
		return Response.status(201).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(Image item)
	{
		service.deleteImage(item);
		return Response.status(201).build();
	}
}
