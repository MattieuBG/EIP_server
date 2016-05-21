package com.esb.server.services.media;

import com.esb.server.dao.media.ImageDAO;
import com.esb.server.entities.media.Image;

import com.esb.server.helpers.DAOHelper;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.IOException;

/**
 * Created by alex on 04/04/16.
 * Project name : Eschoolbag
 */
public class ImageService {

    private ImageDAO dao    = new ImageDAO();
    private Mongo mongo     = new Mongo("localhost", 27017);
    private DB db           = mongo.getDB("eschoolbag");

    /**
     * Save an image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First, image will be saved via GridFS and then we get back the id and saved it Image collection
     * @param imageToSave is the object you want to save
     */
    public void saveImage(Image imageToSave){
        /*GridFS gfsPhoto = new GridFS((DB) MorphiaService.getDatastore(), "Image");*/
        GridFS gfsPhoto = new GridFS(this.db, "Image");
        GridFSInputFile gfsFile = null;
        try {
            gfsFile = gfsPhoto.createFile(imageToSave.getBinary());
        } catch (IOException e) {
            e.printStackTrace();
        }
        gfsFile.setFilename(imageToSave.getName());
        gfsFile.save();
        imageToSave.setIdGridFs(gfsFile.get("_id").toString());
      //  dao.save(imageToSave);
        DAOHelper.imageDAO.save(imageToSave);
    }

    /**
     * Delete an image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First, we get GridFSID and delete in image.files and image.chunks through the API
     * @param imageToDelete is the object you want to delete
     */
    public void deleteImage(Image imageToDelete) {
        GridFS gfsPhoto = new GridFS(this.db, "Image");
        gfsPhoto.remove(gfsPhoto.findOne(imageToDelete.getName()));
        // TODO Check delete with id
        // dao.delete(imageToDelete);
        DAOHelper.imageDAO.delete(imageToDelete);
    }

    /**
     * Save an image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First, image will be updated via GridFS and then we update Image collection
     * @param imageToUpdate is the object you want to update
     */
    public void updateImage(Image imageToUpdate) {
        GridFS gfsPhoto = new GridFS(this.db, "Image");
        GridFSInputFile gfsFile = null;

        gfsPhoto.remove(gfsPhoto.findOne(imageToUpdate.getName()));
        try {
            gfsFile = gfsPhoto.createFile(imageToUpdate.getBinary());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageToUpdate.setName(imageToUpdate.getName()+"_update");
        gfsFile.setFilename(imageToUpdate.getName());
        gfsFile.setId(imageToUpdate.getIdGridFs());
        gfsFile.save();
        imageToUpdate.setIdGridFs(gfsFile.get("_id").toString());
        // dao.save(imageToUpdate);
        DAOHelper.imageDAO.save(imageToUpdate);
    }

    /**
     * Get image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First we get GridFSID and then get back image with this id
     * @param imageToGet is the object you want to get
     */
    public void getImage(Image imageToGet) {
        GridFS gfsPhoto = new GridFS(db, "Image");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(imageToGet.getName());
        System.out.println(imageForOutput);
    }
}
