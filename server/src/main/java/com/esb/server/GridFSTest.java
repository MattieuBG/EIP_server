package com.esb.server;

import com.esb.server.dao.media.CourseDAO;
import com.esb.server.dao.media.ImageDAO;
import com.esb.server.entities.management.ModuleTemplate;
import com.esb.server.entities.management.User;
import com.esb.server.entities.media.Course;
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
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * Created by alex on 04/04/16.
 * Project name : Eschoolbag
 */
public class GridFSTest {

    final static Logger logger = LoggerFactory.getLogger(ImageService.class);

/*    private static ImageDAO dao    = new ImageDAO();*/

    public static void main(String[] args) throws IOException {
        ImageService imageService = new ImageService();
        AFileService aFileservice = new AFileService();

        ModuleTemplate moduleTemplate = new ModuleTemplate();
        Image image = createImage();
        moduleTemplate.name = "French";
        moduleTemplate.icon = image;

        DAOHelper.moduleTemplateDAO.save(moduleTemplate);

/*        User user = new User();
        user.firstName = "alex";
        user.lastName = "test";
        user.birthDate = new Date();
        DAOHelper.userDAO.save(user);*/
/*
        Image image = createImage();
        System.out.println("Save");
        aFileservice.save(image, "Image");*/
    }

    private static Image createImage() {
        Image image = new Image();
        //image.setName("TEST update create 1");
        image.setId("57792f4b4da3132a3a8e988c");
        image.setIdGridFs("577933f04da3132ec2358abe");
        //image.setCreationDate(new Date());

        File binaryOfImage = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");


        byte[] bFile = new byte[(int) binaryOfImage.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(binaryOfImage);
            fileInputStream.read(bFile);
            fileInputStream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        image.setBinary(DatatypeConverter.printBase64Binary(bFile));

        return image;
    }
}
