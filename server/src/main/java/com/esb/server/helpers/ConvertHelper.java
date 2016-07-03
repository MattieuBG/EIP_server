package com.esb.server.helpers;

import com.mongodb.gridfs.GridFSDBFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by alex on 03/07/16.
 * Project name : Eschoolbag
 */
public class ConvertHelper {

    final static Logger logger = LoggerFactory.getLogger(ConvertHelper.class);

    public String takeFileReturnBase64String(File fileToConvert){
        byte[] bFile = new byte[(int) fileToConvert.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(fileToConvert);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String stringConvertBase64 = DatatypeConverter.printBase64Binary(bFile);
        return stringConvertBase64;
    }

    public String takeGridFSDBFileReturnBase64String(GridFSDBFile gridFSDBFile){
        File binary = new File("file");

        try {
            gridFSDBFile.writeTo(binary);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        String stringConvertBase64 = takeFileReturnBase64String(binary);
       return stringConvertBase64;
    }

    public ConvertHelper(){

    }
}
