package com.esb.server;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esb.server.entities.media.Image;
import com.esb.server.entities.media.Pdf;
import com.esb.server.helpers.ConvertHelper;
import com.esb.server.services.media.AFileService;
import com.esb.server.services.media.ImageService;

/**
 * Created by alex on 04/04/16. Project name : Eschoolbag
 */
public class GridFSTest {

	final static Logger logger = LoggerFactory.getLogger(ImageService.class);
	private static final ConvertHelper convertHelper = new ConvertHelper();

	public static void main(final String[] args) throws IOException {
		final ImageService imageService = new ImageService();
		final AFileService aFileservice = new AFileService();

		/*
		 * ModuleTemplate moduleTemplate = new ModuleTemplate(); Image image =
		 * createImage(); moduleTemplate.name = "French"; moduleTemplate.icon =
		 * image; DAOHelper.moduleTemplateDAO.save(moduleTemplate);
		 */

		/*
		 * User user = new User(); user.firstName = "alex"; user.lastName =
		 * "test"; user.birthDate = new Date(); DAOHelper.userDAO.save(user);
		 */

		/*
		 * Image image = createImage(); System.out.println("Save Image");
		 * aFileservice.save(image, "Image");
		 */

		final Pdf pdf = createPdf();
		System.out.println("Save PDF");
		// aFileservice.save(pdf, "Pdf");
	}

	private static Pdf createPdf() {
		final Pdf pdf = new Pdf();
		// pdf.setName("PDF hehe");

		final File binary = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/S23-PLANNING.pdf");
		// pdf.setBinary(convertHelper.takeFileReturnBase64String(binary));
		return pdf;
	}

	private static Image createImage() {
		final Image image = new Image();
		// image.setName("TEST update create 1");
		image.setId("57792f4b4da3132a3a8e988c");
		image.setIdGridFs("577933f04da3132ec2358abe");
		// image.setCreationDate(new Date());

		final File binaryOfImage = new File(
				"/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");

		image.setBinary(convertHelper.takeFileReturnBase64String(binaryOfImage));
		return image;
	}
}
