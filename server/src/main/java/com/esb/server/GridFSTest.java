package com.esb.server;

import com.esb.server.dao.media.ImageDAO;
import com.esb.server.entities.media.Image;

import com.esb.server.helpers.DAOHelper;
import com.esb.server.services.media.AFileService;
import com.esb.server.services.media.ImageService;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by alex on 04/04/16.
 * Project name : Eschoolbag
 */
public class GridFSTest {

    final static Logger logger = LoggerFactory.getLogger(ImageService.class);

    private static ImageDAO dao    = new ImageDAO();

    public static void main(String[] args) throws IOException {


        ImageService imageService = new ImageService();
        AFileService aFileservice = new AFileService();

        Image nef = createImage();

       /* service.saveImage(nef);h

        service.getAllImage();

        service.updateImage(nef);

        logger.debug("nef updated ...");

        service.deleteImage(nef);*/

        aFileservice.saveFile(nef, "Image");

        imageService.getAllImage();

        /*service.getAllImage();

        service.updateImage(nef);

        logger.debug("nef updated ...");

        service.deleteImage(nef);*/

    }

    /**
     *  Test if GridFS works
     */
   /* private static void FillMongoWithBigFile() throws IOException {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("eschoolbag");

        String newFileName = "mkyong-java-image";
        File imageFile = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");
        GridFS gfsPhoto = new GridFS(db, "Image");
        GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
        gfsFile.setFilename(newFileName);
        gfsFile.save();
        ObjectId id = (ObjectId)gfsFile.get("_id");
        System.out.println("id = ["+id+"]");
        mongo.close();
    }*/


    private static Image createImage() {
        Image nef = new Image();
        nef.setName("nef_img");
        nef.setCreationDate(new Date());
        nef.setModifiedDate(new Date());
        File imageFile = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");
        nef.setBinary(imageFile);

        return nef;
    }

/*    *
     * Test of ImageService
     * Save Big image through ImageService

    private static void saveImageServiceTest() {

        //service.saveImage(nef);
    }*/
  /*  private static void getImageServiceTest(){
        ImageService service = new ImageService();
        Image nef = new Image();

        nef.setName("nef_img");
        nef.setCreationDate(new Date());
        nef.setModifiedDate(new Date());
        File imageFile = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");
        nef.setBinary(imageFile);
        service.getImage(nef);
    }

    private static void deleteImageServiceTest(){
        ImageService service = new ImageService();
        Image nef = DAOHelper.imageDAO.findOne("name", "nef_img");

        service.deleteImage(nef);
    }

    private static void updateImageServiceTest(){
        ImageService service = new ImageService();
        Image nef = dao.findOne("name", "nef_img");

        File imageFile = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");
        nef.setBinary(imageFile);
        service.updateImage(nef);
        service.getImage(nef);
    }*/
}
