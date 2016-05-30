package com.esb.server;

import com.esb.server.dao.media.ImageDAO;
import com.esb.server.entities.media.Image;

import com.esb.server.helpers.DAOHelper;
import com.esb.server.services.media.ImageService;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by alex on 04/04/16.
 * Project name : Eschoolbag
 */
public class GridFSTest {

    private static ImageDAO dao    = new ImageDAO();

    public static void main(String[] args) throws IOException {
/*        System.out.println("Fill :");
        FillMongoWithBigFile();

        System.out.println("Get image :");
        getImage();*/

        ImageService service = new ImageService();
        Image nef = new Image();
        nef.setName("nef_img");
        nef.setCreationDate(new Date());
        nef.setModifiedDate(new Date());
        File imageFile = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");
        nef.setBinary(imageFile); // Generation du binaire en plusieurs chunks via GridFS

        service.saveImage(nef);

        /*service.getAllImage();*/

       /* nef.setName("nef_img");
        nef.setCreationDate(new Date());
        nef.setModifiedDate(new Date());
        File imageFile = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");
        nef.setBinary(imageFile); // Generation du binaire en plusieurs chunks via GridFS

        System.out.println("Save :");
        service.saveImage(nef);

        System.out.println("Get image :");
        service.getImage(nef);

        System.out.println("update image :");
        service.updateImage(nef);

        System.out.println("Get image :");
        service.getImage(nef);*/
/*
        System.out.println("Get :");
        getImageServiceTest();

       System.out.println("Delete :");
        deleteImageServiceTest();

        System.out.println("Get :");
        getImageServiceTest();

        System.out.println("Update :");
        updateImageServiceTest();*/
    }

    /**
     *  Test if GridFS works
     */
    private static void FillMongoWithBigFile() throws IOException {
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
    }

    /**
     * Get data from GridFS collection
     */
    private static void getImage() {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("eschoolbag");

        String newFileName = "mkyong-java-image";
        GridFS gfsPhoto = new GridFS(db, "Image");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(newFileName);
        System.out.println(imageForOutput);
        mongo.close();
    }

    /**
     * Test of ImageService
     * Save Big image through ImageService
     */
    private static void saveImageServiceTest() {

        //service.saveImage(nef);
    }
    private static void getImageServiceTest(){
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
    }
}
