package com.esb.server.services.media;

import com.esb.server.entities.media.Audio;
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
public class AudioService extends AFileService {

    final static Logger logger = LoggerFactory.getLogger(AudioService.class);

    /**
     * Get audio in Audio collection and in Audio.files and Audio.chunks (GridFS collection)
     * First we get GridFSID and then get back audio with this id
     * @param audioToGet is the object you want to get
     */
    public Audio getAudio(Audio audioToGet) {
        GridFS gridFSAudio = new GridFS(db, "Audio");
        ObjectId idToGet = new ObjectId(audioToGet.getIdGridFs()); // Build obj to research via id
        File binaryOfAudio = new File("Audio");

        /* Find GridFS Entity record with the field idGridFS saved in audioToGet */
        GridFSDBFile entryToGet = gridFSAudio.findOne(idToGet);
        try {
            entryToGet.writeTo(binaryOfAudio);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        //audioToGet.setBinary(binaryOfAudio);
        return (audioToGet);
    }

    /**
     * Get audio in Audio collection and in Audio.files and Audio.chunks (GridFS collection)
     * First we get GridFSID and then get back audio with this id
     */
    public List<Audio> getAllAudio() {
        List<Audio> imgList = new ArrayList<Audio>();

        imgList = DAOHelper.audioDAO.find().asList();
        System.out.println("imgList.Size = " + imgList.size());
        for (int i = 0 ; i < imgList.size() ;i++) {
            imgList.set(i, getAudio(imgList.get(i)));
            System.out.println("List["+i+"] = "+imgList.get(i));
        }
        return (imgList);
    }
}
