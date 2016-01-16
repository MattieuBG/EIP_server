package jacksontest.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esb.server.entities.Image;

@Path("/images")
public class ImageService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Image get() {

		Image item = new Image();
		return item;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Image item) {
		
		// do CREATE on database
		
		return Response.status(201).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Image item)
	{
		// do UPDATE on database
		return Response.status(201).build();
	}

	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(Image item)
	{
		// do DELETE on database
		return Response.status(201).build();
	}

}
