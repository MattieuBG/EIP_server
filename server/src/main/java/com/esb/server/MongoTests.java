package com.esb.server;

import com.esb.server.dao.media.ImageDAO;
import com.esb.server.services.media.ImageService;
import com.esb.server.entities.media.Image;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.io.File;
import java.rmi.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by alex on 04/04/16.
 * Project name : Eschoolbag
 */
public class MongoTests {

    public static void main(String[] args) throws UnknownHostException {

        System.out.println("List all mongo's database in localhost");
        ConnectionTest();
        System.out.println("Test Input in eschoolbag database");
        InputTest();
        System.out.println("Test Input, via service, in eschoolbag database");
        InputTestService();
    }

    /**
     * Test you can reach mongo AND eschoolbag is present in localhost
     */
    private static void ConnectionTest()
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        List<String> databases = mongoClient.getDatabaseNames();

        for (String dbName : databases) {
            System.out.println("- Database: " + dbName);

            DB db = mongoClient.getDB(dbName);

            Set<String> collections = db.getCollectionNames();
            for (String colName : collections) {
                System.out.println("\t + Collection: " + colName);
            }
        }
        mongoClient.close();
    }

    /**
     * Test if you can create an entry in database with DAO
     */
    private static void InputTest()
    {
        Image image = new Image();
        ImageDAO imageDAO = new ImageDAO();

        image.setName("Foobar");
        image.setCreationDate(LocalDateTime.now());
        image.setModifiedDate(LocalDateTime.now());

        imageDAO.save(image);
    }

    /**
     * Test if you can create an entry in database with Service
     */
    private static void InputTestService()
    {
        Image image = new Image();
        ImageService imgService = new ImageService();
        File imageFile = new File("/home/alex/Project/EIP/newarchi/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");

        image.setName("Foobar");
        image.setBinary(imageFile);
        image.setDescription("Awesome Picture !");
        image.setCreationDate(LocalDateTime.now());
        image.setModifiedDate(LocalDateTime.now());

        imgService.saveImage(image);
    }
}

