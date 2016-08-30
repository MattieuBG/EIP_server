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

import com.esb.server.entities.management.User;
import com.esb.server.entities.media.Pdf;
import com.esb.server.helpers.DAOHelper;
import com.mongodb.util.JSON;

@Path("pdfs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PdfController {
	private final String baseUrl = "/pdfs/";

	@GET
	public List<Pdf> getPdfs() {
		return DAOHelper.pdfDAO.find().asList();
	}

	@GET
	@Path("{id}")
	public Pdf getPdfById(@PathParam("id") final String id) {
		final Pdf pdf = DAOHelper.pdfDAO.createQuery().filter("id =", id).get();
		if (pdf == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknown pdf " + id))
					.build());

		return pdf;
	}

	// @GET
	// @Path("user/{user_id}/module/{module_id} | module/{module_id}/user/{user_id}")
	// public List<Pdf> getPdfsByIdAndModule(@PathParam("user_id") final String
	// userId, @PathParam("module_id") final String moduleId) {
	// final User user = DAOHelper.userDAO.createQuery().filter("id =",
	// userId).get();
	// final Module module = DAOHelper.moduleDAO.createQuery().filter("id =",
	// moduleId).get();
	// final List<Pdf> pdf = servicePdf.getPdfsByIdAndModule(user, module);
	//
	// if (user == null)
	// throw new
	// WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow user "
	// + userId))
	// .build());
	// if (module == null)
	// throw new
	// WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
	// .entity(JSON.serialize("Unknow module " + moduleId)).build());
	//
	// return pdf;
	// }

	@GET
	@Path("user/{id}")
	public List<Pdf> getPdfsById(@PathParam("id") final String userId) {
		final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
		if (user == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow user " + userId))
					.build());

		final List<Pdf> pdfs = DAOHelper.pdfDAO.createQuery().filter("owner =", user).asList();
		final List<Pdf> shared = DAOHelper.pdfDAO.createQuery().filter("sharedUsers =", user).asList();

		pdfs.addAll(shared);
		return pdfs;
	}

	@POST
	public Response createPdf(final Pdf entity) {
		entity.ftpUrl = baseUrl + entity.name;
		DAOHelper.pdfDAO.save(entity);
		return Response.status(Response.Status.CREATED).build();
	}

	// @POST
	// @Path("{id}/module/set")
	// public Pdf setModuleForPdf(@PathParam("id") final String pdfId, final
	// String moduleId) {
	// final Pdf pdf = DAOHelper.pdfDAO.createQuery().filter("id =",
	// pdfId).get();
	// final Module module = DAOHelper.moduleDAO.createQuery().filter("id =",
	// moduleId).get();
	//
	// if (pdf == null)
	// throw new
	// WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow pdf "
	// + pdfId))
	// .build());
	// if (module == null)
	// throw new
	// WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
	// .entity(JSON.serialize("Unknow module " + moduleId)).build());
	//
	// if (pdf.getOwner() == null)
	// pdf.setOwner(module.user);
	// else if (!module.user.equals(pdf.getOwner()))
	// throw new
	// WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
	// .entity(JSON.serialize("Module does not belong to user " +
	// pdf.getOwner())).build());
	//
	// pdf.setModule(module);
	// serviceAFile.save(pdf, collectionName);
	// return pdf;
	// }
	//
	// @POST
	// @Path("{id}/module/unset")
	// public Pdf setModuleForPdf(@PathParam("id") final String pdfId) {
	// final Pdf pdf = DAOHelper.pdfDAO.createQuery().filter("id =",
	// pdfId).get();
	//
	// if (pdf == null)
	// throw new
	// WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow pdf "
	// + pdfId))
	// .build());
	//
	// pdf.setModule(null);
	// serviceAFile.save(pdf, collectionName);
	// return pdf;
	// }

	@POST
	@Path("{id}/owner/set")
	public Pdf setOwnerForPdf(@PathParam("id") final String pdfId, final String ownerId) {
		final Pdf pdf = DAOHelper.pdfDAO.createQuery().filter("id =", pdfId).get();
		if (pdf == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow pdf " + pdfId))
					.build());

		final User owner = DAOHelper.userDAO.createQuery().filter("id =", ownerId).get();
		if (ownerId == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity(JSON.serialize("Unknow owner " + ownerId)).build());

		pdf.owner = owner;
		DAOHelper.pdfDAO.save(pdf);
		return pdf;
	}

	@POST
	@Path("{id}/owner/unset")
	public Pdf setOwnerForPdf(@PathParam("id") final String pdfId) {
		final Pdf pdf = DAOHelper.pdfDAO.createQuery().filter("id =", pdfId).get();
		if (pdf == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize("Unknow pdf " + pdfId))
					.build());

		pdf.owner = null;
		DAOHelper.pdfDAO.save(pdf);
		return pdf;
	}

	@PUT
	public Response updatePdf(final Pdf entity) {
		entity.ftpUrl = baseUrl + entity.name;
		DAOHelper.pdfDAO.save(entity);
		return Response.status(200).build();
	}

	@DELETE
	public Response delete(final Pdf entity) {
		DAOHelper.pdfDAO.delete(entity);
		return Response.status(200).build();
	}
}
