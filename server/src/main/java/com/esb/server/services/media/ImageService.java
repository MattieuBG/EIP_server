package com.esb.server.services.media;

import com.esb.server.entities.media.Image;
import com.esb.server.helpers.DAOHelper;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alex on 04/04/16.
 * Project name : Eschoolbag
 */
public class ImageService {

    final static Logger logger = LoggerFactory.getLogger(ImageService.class);

    final private Mongo mongo     = new Mongo("localhost", 27017);
    final private DB db           = mongo.getDB("eschoolbag");

    /**
     * Save an image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First, image will be saved via GridFS and then we get back the id and saved it Image collection
     * @param imageToSave is the object you want to save
     */
    public void saveImage(Image imageToSave){
        GridFS gridFSImage = new GridFS(this.db, "Image");
        GridFSInputFile gfsFile = null; // take the input stream coming the file we uploaded via our HTML page

        logger.debug("TEST");

        try {
            /* create the file (the multiple chunks) from the binary of the image we wanted to save */
            gfsFile = gridFSImage.createFile(imageToSave.getBinary());
        } catch (IOException e) {
            e.printStackTrace();
            // TODO Create log
        }
        /* Set the name of image in metadata Entity of GridFS named entityName.files */
        gfsFile.setFilename(imageToSave.getName());
        gfsFile.save();
        /* Set the gridFS's id in the Mongo's Entity attribute (idGridFs) */
        imageToSave.setIdGridFs(gfsFile.get("_id").toString());
        /* Same things but with the Mongo Entity */
        DAOHelper.imageDAO.save(imageToSave);
    }

    /**
     * Delete an image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First, we get GridFSID and delete in image.files and image.chunks through the API
     * @param imageToDelete is the object you want to delete
     */
    public void deleteImage(Image imageToDelete) {
        GridFS gridFSImage = new GridFS(this.db, "Image");
        ObjectId idToDelete = new ObjectId(imageToDelete.getIdGridFs()); // Build obj to research via id

        /* Find record in GridFS table to delete */
        GridFSFile entryToRemove = gridFSImage.findOne(idToDelete);
        /* Remove record */
        gridFSImage.remove(entryToRemove);
        /* Same find but in mongo entity */
        DAOHelper.imageDAO.delete(imageToDelete);
    }

    /**
     * Save an image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First, image will be updated via GridFS and then we update Image collection
     * @param imageToUpdate is the object you want to update
     */
    public void updateImage(Image imageToUpdate) {
        GridFS gridFSImage = new GridFS(this.db, "Image");
        GridFSInputFile gfsFile = null; // take the input stream coming the file we uploaded via our HTML page
        ObjectId idToDelete = new ObjectId(imageToUpdate.getIdGridFs()); // Build obj to research via id

        /* Find record in GridFS table to delete */
        GridFSFile entryToRemove = gridFSImage.findOne(idToDelete);
        /* Remove record */
        gridFSImage.remove(entryToRemove);
        try {
            gfsFile = gridFSImage.createFile(imageToUpdate.getBinary());
        } catch (IOException e) {
            e.printStackTrace();
            // TODO Logs
        }
        /* As there is no update method with GridFS we delete and remake record
           So here we reset the previous name */
        gfsFile.setFilename(imageToUpdate.getName());
        gfsFile.save(); // save in gridFS
        /* update modifiedDate to now */
        imageToUpdate.setModifiedDate(new Date());
        /* update the ID of GridFS in Image collection */
        imageToUpdate.setIdGridFs(gfsFile.get("_id").toString());
        DAOHelper.imageDAO.save(imageToUpdate);
    }

    /**
     * Get image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First we get GridFSID and then get back image with this id
     * @param imageToGet is the object you want to get
     */
    public Image getImage(Image imageToGet) {
        GridFS gridFSImage = new GridFS(db, "Image");
        ObjectId idToGet = new ObjectId(imageToGet.getIdGridFs()); // Build obj to research via id
        File binaryOfImage = new File("Image");

        /* Find GridFS Entity record with the field idGridFS saved in imageToGet */
        GridFSDBFile entryToGet = gridFSImage.findOne(idToGet);
        try {
            entryToGet.writeTo(binaryOfImage);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO Logs
        }
        imageToGet.setBinary(binaryOfImage);
        return (imageToGet);
    }

    /**
     * Get image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First we get GridFSID and then get back image with this id
     */
    public List<Image> getAllImage() {
        List<Image> imgList = new ArrayList<Image>();

        imgList = DAOHelper.imageDAO.find().asList();
        System.out.println("imgList.Size = " + imgList.size());
        for (int i = 0 ; i < imgList.size() ;i++) {
            imgList.set(i, getImage(imgList.get(i)));
            System.out.println("List["+i+"] = "+imgList.get(i));
        }
        return (imgList);
    }
}
