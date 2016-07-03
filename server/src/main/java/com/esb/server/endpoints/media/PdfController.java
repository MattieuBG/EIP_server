package com.esb.server.endpoints.media;

import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.User;
import com.esb.server.entities.media.Pdf;
import com.esb.server.entities.media.Pdf;
import com.esb.server.helpers.DAOHelper;
import com.esb.server.services.media.AFileService;
import com.esb.server.services.media.PdfService;
import com.esb.server.services.media.PdfService;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by alex on 03/07/16.
 * Project name : Eschoolbag
 */
@Path("pdfs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PdfController {

    final static Logger logger = LoggerFactory.getLogger(PdfController.class);
    final static String collectionName = "Pdf";

    private final PdfService servicePdf = new PdfService();
    private final AFileService serviceAFile = new PdfService();

    /**
     * The method will return you all pdfs store in database
     * @return List of Pdf (List<Pdf>)
     */
    @GET
    public List<Pdf> getPdfs(){
        return servicePdf.getPdfs();
    }

    /**
     * This method will retrun you the pdf corresponding to the id given
     * @return One Pdf
     */
    @GET
    @Path("{id}")
    public Pdf getPdfById(@PathParam("id") final String id) {
        Pdf pdf = servicePdf.getPdf(id);

        if (pdf == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknown id " + id)).build());

        return pdf;
    }

    @GET
    @Path("user/{user_id}/module/{module_id} | module/{module_id}/user/{user_id}")
    public List<Pdf> getPdfsByIdAndModule(@PathParam("user_id") final String userId, @PathParam("module_id") final String moduleId){
        final User user = DAOHelper.userDAO.createQuery().filter("id =", userId).get();
        final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();
        final List<Pdf> pdf = servicePdf.getPdfsByIdAndModule(user, module);

        if (user == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow user " + userId)).build());
        if (module == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow module " + moduleId)).build());

        return pdf;
    }


    /**
     * This method will create entry in Imqge collection and multiples entries
     * in Pdf.chunks and Pdf.files. Both are linked by and id (GidFS id is
     * store in Pdf Collection)
     */
    @POST
    public Response createPdf(final Pdf entity) {
        serviceAFile.save(entity, collectionName);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("{id}/module/set")
    public Pdf setModuleForPdf(@PathParam("id") final String pdfId, final String moduleId) {
        final Pdf pdf = DAOHelper.pdfDAO.createQuery().filter("id =", pdfId).get();
        final Module module = DAOHelper.moduleDAO.createQuery().filter("id =", moduleId).get();

        if (pdf == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow pdf " + pdfId)).build());
        if (module == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow module " + moduleId)).build());

        if (pdf.getOwner() == null)
            pdf.setOwner(module.user);
        else if (!module.user.equals(pdf.getOwner()))
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Module does not belong to user " + pdf.getOwner())).build());

        pdf.setModule(module);
        serviceAFile.save(pdf, collectionName);
        return pdf;
    }

    @POST
    @Path("{id}/module/unset")
    public Pdf setModuleForPdf(@PathParam("id") final String pdfId) {
        final Pdf pdf = DAOHelper.pdfDAO.createQuery().filter("id =", pdfId).get();

        if (pdf == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow pdf " + pdfId)).build());

        pdf.setModule(null);
        serviceAFile.save(pdf, collectionName);
        return pdf;
    }

    @POST
    @Path("{id}/owner/set")
    public Pdf setOwnerForPdf(@PathParam("id") final String pdfId, final String ownerId) {
        final Pdf pdf = DAOHelper.pdfDAO.createQuery().filter("id =", pdfId).get();
        final User owner = DAOHelper.userDAO.createQuery().filter("id =", ownerId).get();

        if (pdf == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow pdf " + pdfId)).build());
        if (ownerId == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow owner " + ownerId)).build());

        pdf.setOwner(owner);
        serviceAFile.save(pdf, collectionName);
        return pdf;
    }

    @POST
    @Path("{id}/owner/unset")
    public Pdf setOwnerForPdf(@PathParam("id") final String pdfId) {
        final Pdf pdf = DAOHelper.pdfDAO.createQuery().filter("id =", pdfId).get();

        if (pdf == null)
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(JSON.serialize("Unknow pdf " + pdfId)).build());

        pdf.setOwner(null);
        serviceAFile.save(pdf, collectionName);
        return pdf;
    }


    /**
     * This method will upade entry in Imqge collection and remove entries in
     * Pdf.chunks and Pdf.files to recreate them GridFS API haven't manage
     * this kind of function.
     */
    @PUT
    public Response updatePdf(final Pdf entity) {
        serviceAFile.save(entity, collectionName);
        return Response.status(201).build();
    }

    /**
     * This method will delete entry in Imqge collection and entries in
     * Pdf.chunks and Pdf.files. In this case GridFS does the work by
     * herself.
     */
    @DELETE
    public Response delete(final Pdf entity) {
        serviceAFile.deleteFile(entity, collectionName);
        return Response.status(201).build();
    }
}
