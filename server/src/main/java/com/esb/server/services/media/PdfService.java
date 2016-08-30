//package com.esb.server.services.media;
//
//import com.esb.server.entities.management.Module;
//import com.esb.server.entities.management.User;
//import com.esb.server.entities.media.Pdf;
//import com.esb.server.helpers.ConvertHelper;
//import com.esb.server.helpers.DAOHelper;
//import com.mongodb.gridfs.GridFS;
//import com.mongodb.gridfs.GridFSDBFile;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by alex on 03/07/16.
// * Project name : Eschoolbag
// */
//public class PdfService  extends AFileService {
//
//    final static Logger logger = LoggerFactory.getLogger(PdfService.class);
//    private final ConvertHelper convertHelper = new ConvertHelper();
//
//    /**
//     * Get pdf in Pdf collection and in Pdf.files and Pdf.chunks (GridFS collection)
//     * First we get GridFSID and then get back pdf with this id
//     * @param id is the id that i'll use to get back object
//     */
//    // http://stackoverflow.com/questions/20706783/put-byte-array-to-json-and-vice-versa
//    public Pdf getPdf(final String id) {
//        GridFS gridFSPdf = new GridFS(db, "Pdf");
//        Pdf pdf = DAOHelper.pdfDAO.createQuery().filter("id =", id).get();
//
//        if (pdf == null) return (pdf);
//
//        ObjectId idToGet = new ObjectId(pdf.getIdGridFs()); // Build obj to research via id
//
//        /* Find GridFS Entity record with the field idGridFS saved in pdfToGet */
//        GridFSDBFile entryToGet = gridFSPdf.findOne(idToGet);
//
//        String stringConvertBase64 = convertHelper.takeGridFSDBFileReturnBase64String(entryToGet);
//
//        pdf.setBinary(stringConvertBase64);
//        return (pdf);
//    }
//
//    /**
//     * Get pdf in Pdf collection and in Pdf.files and Pdf.chunks (GridFS collection)
//     * First we get GridFSID and then get back pdf with this id
//     */
//    public List<Pdf> getPdfs() {
//        List<Pdf> imgList = new ArrayList<Pdf>();
//
//        imgList = DAOHelper.pdfDAO.find().asList();
//        System.out.println("imgList.Size = " + imgList.size());
//        for (int i = 0 ; i < imgList.size() ;i++) {
//            imgList.set(i, getPdf(imgList.get(i).getId()));
//            System.out.println("List["+i+"] = "+imgList.get(i));
//        }
//        return (imgList);
//    }
//
//    public List<Pdf> getPdfsByIdAndModule(final User user, final Module module)
//    {
//        List<Pdf> pdfList = DAOHelper.pdfDAO.createQuery().filter("owner =", user).filter("module =", module).asList();
//
//        for (int i = 0; i < pdfList.size(); i++) {
//            pdfList.set(i, getPdf(pdfList.get(i).getId()));
//            System.out.println("List["+i+"] = "+pdfList.get(i));
//        }
//        return pdfList;
//    }
// }
