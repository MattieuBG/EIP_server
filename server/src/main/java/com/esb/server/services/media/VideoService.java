package com.esb.server.services.media;

import com.esb.server.entities.media.Video;
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
public class VideoService extends AFileService {

    final static Logger logger = LoggerFactory.getLogger(VideoService.class);

    /**
     * Get image in Video collection and in Video.files and Video.chunks (GridFS collection)
     * First we get GridFSID and then get back Video with this id
     * @param videoToGet is the object you want to get
     */
    public Video getVideo(Video videoToGet) {
        GridFS gridFSVideo = new GridFS(db, "Video");
        ObjectId idToGet = new ObjectId(videoToGet.getIdGridFs()); // Build obj to research via id
        File binaryOfVideo = new File("Video");

        /* Find GridFS Entity record with the field idGridFS saved in videoToGet */
        GridFSDBFile entryToGet = gridFSVideo.findOne(idToGet);
        try {
            entryToGet.writeTo(binaryOfVideo);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
       // videoToGet.setBinary(binaryOfVideo);
        return (videoToGet);
    }

    /**
     * Get image in Image collection and in Image.files and Image.chunks (GridFS collection)
     * First we get GridFSID and then get back image with this id
     */
    public List<Video> getAllVideo() {
        List<Video> imgList = new ArrayList<Video>();

        imgList = DAOHelper.videoDAO.find().asList();
        System.out.println("imgList.Size = " + imgList.size());
        for (int i = 0 ; i < imgList.size() ;i++) {
            imgList.set(i, getVideo(imgList.get(i)));
            System.out.println("List["+i+"] = "+imgList.get(i));
        }
        return (imgList);
    }
}
