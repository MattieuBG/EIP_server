package com.esb.server.services.media;

import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.User;
import com.esb.server.entities.media.Image;
import com.esb.server.helpers.ConvertHelper;
import com.esb.server.helpers.DAOHelper;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 04/04/16.
 * Project name : Eschoolbag
 */
public class ImageService extends AFileService {

    final static Logger logger = LoggerFactory.getLogger(ImageService.class);
    private final ConvertHelper convertHelper = new ConvertHelper();

    /**
     * Get image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First we get GridFSID and then get back image with this id
     * @param id is the id that i'll use to get back object
     */
    // http://stackoverflow.com/questions/20706783/put-byte-array-to-json-and-vice-versa
    public Image getImage(final String id) {
        GridFS gridFSImage = new GridFS(db, "Image");
        Image image = DAOHelper.imageDAO.createQuery().filter("id =", id).get();

        if (image == null) return (image);

        ObjectId idToGet = new ObjectId(image.getIdGridFs()); // Build obj to research via id

        /* Find GridFS Entity record with the field idGridFS saved in imageToGet */
        GridFSDBFile entryToGet = gridFSImage.findOne(idToGet);

        String stringConvertBase64 = convertHelper.takeGridFSDBFileReturnBase64String(entryToGet);

        image.setBinary(stringConvertBase64);
        return (image);
    }

    /**
     * Get image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First we get GridFSID and then get back image with this id
     */
    public List<Image> getImages() {
        List<Image> imgList = new ArrayList<Image>();

        imgList = DAOHelper.imageDAO.find().asList();
        System.out.println("imgList.Size = " + imgList.size());
        for (int i = 0 ; i < imgList.size() ;i++) {
            imgList.set(i, getImage(imgList.get(i).getId()));
            System.out.println("List["+i+"] = "+imgList.get(i));
        }
        return (imgList);
    }

    public List<Image> getImagesByIdAndModule(final User user, final Module module)
    {
        List<Image> imageList = DAOHelper.imageDAO.createQuery().filter("owner =", user).filter("module =", module).asList();

        for (int i = 0; i < imageList.size(); i++) {
            imageList.set(i, getImage(imageList.get(i).getId()));
            System.out.println("List["+i+"] = "+imageList.get(i));
        }
        return imageList;
    }
}

/**
 * Save an image in Image collection and in Image.files and Image.chunks (GridFS collection)
 * First, image will be saved via GridFS and then we get back the id and saved it Image collection
 * @param imageToSave is the object you want to save
 */
    /*public void saveImage(Image imageToSave){
        GridFS gridFSImage = new GridFS(this.db, "Image");
        GridFSInputFile gfsFile = null; // take the input stream coming the file we uploaded via our HTML page

        try {
             //create the file (the multiple chunks) from the binary of the image we wanted to save
            gfsFile = gridFSImage.createFile(imageToSave.getBinary());
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
         //Set the name of image in metadata Entity of GridFS named entityName.files
        gfsFile.setFilename(imageToSave.getName());
        gfsFile.save();
         //Set the gridFS's id in the Mongo's Entity attribute (idGridFs)
        imageToSave.setIdGridFs(gfsFile.get("_id").toString());
         //Same things but with the Mongo Entity
        DAOHelper.imageDAO.save(imageToSave);
    }*/


/**
 * Delete an image in Image collection and in Image.files and Image.chunks (GridFS collection)
 * First, we get GridFSID and delete in image.files and image.chunks through the API
 * @param imageToDelete is the object you want to delete
 */
    /*public void deleteImage(Image imageToDelete) {
        GridFS gridFSImage = new GridFS(this.db, "Image");
        ObjectId idToDelete = new ObjectId(imageToDelete.getIdGridFs()); // Build obj to research via id

        *//* Find record in GridFS table to delete *//*
        GridFSFile entryToRemove = gridFSImage.findOne(idToDelete);
        *//* Remove record *//*
        gridFSImage.remove(entryToRemove);
        *//* Same find but in mongo entity *//*
        DAOHelper.imageDAO.delete(imageToDelete);
    }*/

/**
 * Save an image in Image collection and in Image.files and Image.chunks (GridFS collection)
 * First, image will be updated via GridFS and then we update Image collection
 * @param imageToUpdate is the object you want to update
 */
  /*  public void updateImage(Image imageToUpdate) {
        GridFS gridFSImage = new GridFS(this.db, "Image");
        GridFSInputFile gfsFile = null; // take the input stream coming the file we uploaded via our HTML page
        ObjectId idToDelete = new ObjectId(imageToUpdate.getIdGridFs()); // Build obj to research via id

        *//* Find record in GridFS table to delete *//*
        GridFSFile entryToRemove = gridFSImage.findOne(idToDelete);
        *//* Remove record *//*
        gridFSImage.remove(entryToRemove);
        try {
            gfsFile = gridFSImage.createFile(imageToUpdate.getBinary());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        *//* As there is no update method with GridFS we delete and remake record
           So here we reset the previous name *//*
        gfsFile.setFilename(imageToUpdate.getName());
        gfsFile.save(); // save in gridFS
        *//* update modifiedDate to now *//*
        imageToUpdate.setModifiedDate(new Date());
        *//* update the ID of GridFS in Image collection *//*
        imageToUpdate.setIdGridFs(gfsFile.get("_id").toString());
        DAOHelper.imageDAO.save(imageToUpdate);
    }*/