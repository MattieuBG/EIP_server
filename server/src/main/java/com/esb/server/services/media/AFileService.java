package com.esb.server.services.media;

import com.esb.server.entities.media.AFile;
import com.esb.server.helpers.DAOHelper;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;


/**
 * Created by alex on 11/06/16.
 * Project name : Eschoolbag
 */
public class AFileService {

    final static Logger logger = LoggerFactory.getLogger(AFileService.class);

    final protected Mongo mongo     = new Mongo("localhost", 27017);
    final protected DB db           = mongo.getDB("eschoolbag");

    /**
     * Save an image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First, image will be saved via GridFS and then we get back the id and saved it Image collection
     * @param fileToSave is the object you want to save
     */
    public void saveFile(AFile fileToSave, String fileType){
        GridFS gridFSImage = new GridFS(this.db, fileType);
        GridFSInputFile gfsFile = null; // take the input stream coming the file we uploaded via our HTML page

        try {
            /* create the file (the multiple chunks) from the binary of the image we wanted to save */
            //gfsFile = gridFSImage.createFile(fileToSave.getBinary());
            gfsFile = gridFSImage.createFile(fileToSave.getBinary());
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        /* Set the name of image in metadata Entity of GridFS named entityName.files */
        gfsFile.setFilename(fileToSave.getName());
        gfsFile.save();
        /* Set the gridFS's id in the Mongo's Entity attribute (idGridFs) */
        fileToSave.setIdGridFs(gfsFile.get("_id").toString());
        /* Same things but with the Mongo Entity */
        DAOHelper.aFileDAO.save(fileToSave);
    }

    /**
     * Delete an image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First, we get GridFSID and delete in image.files and image.chunks through the API
     * @param fileToDelete is the object you want to delete
     */
    public void deleteFile(AFile fileToDelete, String fileType) {
        GridFS gridFSImage = new GridFS(this.db, fileType);
        ObjectId idToDelete = new ObjectId(fileToDelete.getIdGridFs()); // Build obj to research via id

        /* Find record in GridFS table to delete */
        GridFSFile entryToRemove = gridFSImage.findOne(idToDelete);
        /* Remove record */
        gridFSImage.remove(entryToRemove);
        /* Same find but in mongo entity */
        DAOHelper.aFileDAO.delete(fileToDelete);
    }

    /**
     * Save an image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First, image will be updated via GridFS and then we update Image collection
     * @param fileToUpdate is the object you want to update
     */
    public void updateFile(AFile fileToUpdate, String fileType) {
        GridFS gridFSImage = new GridFS(this.db, fileType);
        GridFSInputFile gfsFile = null; // take the input stream coming the file we uploaded via our HTML page
        ObjectId idToDelete = new ObjectId(fileToUpdate.getIdGridFs()); // Build obj to research via id

        /* Find record in GridFS table to delete */
        GridFSFile entryToRemove = gridFSImage.findOne(idToDelete);
        /* Remove record */
        gridFSImage.remove(entryToRemove);
        try {
            gfsFile = gridFSImage.createFile(fileToUpdate.getBinary());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        /* As there is no update method with GridFS we delete and remake record
           So here we reset the previous name */
        gfsFile.setFilename(fileToUpdate.getName());
        gfsFile.save(); // save in gridFS
        /* update modifiedDate to now */
        fileToUpdate.setModifiedDate(new Date());
        /* update the ID of GridFS in Image collection */
        fileToUpdate.setIdGridFs(gfsFile.get("_id").toString());
        DAOHelper.aFileDAO.save(fileToUpdate);
    }


}
/**
 * Get image in Image collection and in Image.files and Image.chunks (GridFS collection)
 * First we get GridFSID and then get back image with this id
 * @param fileToGet is the object you want to get
 */
/*    public AFile getFile(AFile fileToGet, String fileType) {
        GridFS gridFSImage = new GridFS(db, fileType);
        ObjectId idToGet = new ObjectId(fileToGet.getIdGridFs()); // Build obj to research via id
        File binaryOfImage = new File(fileType);

        *//* Find GridFS Entity record with the field idGridFS saved in imageToGet *//*
        GridFSDBFile entryToGet = gridFSImage.findOne(idToGet);
        try {
            entryToGet.writeTo(binaryOfImage);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        fileToGet.setBinary(binaryOfImage);
        return (fileToGet);
    }*/

/**
 * Get image in Image collection and in Image.files and Image.chunks (GridFS collection)
 * First we get GridFSID and then get back image with this id
 */
    /*public List<> getFile(String fileType) {
        List<fileType> imgList = new ArrayList<fileType>();

        imgList = DAOHelper.imageDAO.find().asList();
        System.out.println("imgList.Size = " + imgList.size());
        for (int i = 0 ; i < imgList.size() ;i++) {
            imgList.set(i, getImage(imgList.get(i)));
            System.out.println("List["+i+"] = "+imgList.get(i));
        }
        return (imgList);
    }*/