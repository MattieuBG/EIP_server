package com.esb.server.services.media;

import com.esb.server.entities.media.Drawing;
import com.esb.server.helpers.DAOHelper;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/06/16.
 * Project name : Eschoolbag
 */
public class DrawingService extends AFileService {

    final static Logger logger = LoggerFactory.getLogger(DrawingService.class);

    /**
     * Get drawing in Drawing collection and in Drawing.files and Drawing.chunks (GridFS collection)
     * First we get GridFSID and then get back drawing with this id
     * @param drawingToGet is the object you want to get
     */
    public Drawing getDrawing(Drawing drawingToGet) {
        GridFS gridFSDrawing = new GridFS(db, "Drawing");
        ObjectId idToGet = new ObjectId(drawingToGet.getIdGridFs()); // Build obj to research via id
        File binaryOfDrawing = new File("Drawing");

        /* Find GridFS Entity record with the field idGridFS saved in drawingToGet */
        GridFSDBFile entryToGet = gridFSDrawing.findOne(idToGet);
        try {
            entryToGet.writeTo(binaryOfDrawing);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
       // drawingToGet.setBinary(binaryOfDrawing);
        return (drawingToGet);
    }

    /**
     * Get drawing in Drawing collection and in Drawing.files and Drawing.chunks (GridFS collection)
     * First we get GridFSID and then get back drawing with this id
     */
    public List<Drawing> getAllDrawing() {
        List<Drawing> imgList = new ArrayList<Drawing>();

        imgList = DAOHelper.drawingDAO.find().asList();
        System.out.println("imgList.Size = " + imgList.size());
        for (int i = 0 ; i < imgList.size() ;i++) {
            imgList.set(i, getDrawing(imgList.get(i)));
            System.out.println("List["+i+"] = "+imgList.get(i));
        }
        return (imgList);
    }
}
