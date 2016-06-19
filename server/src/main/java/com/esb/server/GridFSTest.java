package com.esb.server;

import com.esb.server.dao.media.CourseDAO;
import com.esb.server.dao.media.ImageDAO;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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


        CourseDAO courseDAO = new CourseDAO();
/*        ImageService imageService = new ImageService();
        AFileService aFileservice = new AFileService();

        Image nef = createImage();*/

       /* service.saveImage(nef);h

        service.getAllImage();

        service.updateImage(nef);

        logger.debug("nef updated ...");

        service.deleteImage(nef);*/

        Course course = testInsertHTML();

        courseDAO.save(course);

       /* aFileservice.saveFile(nef, "Image");

        imageService.getAllImage();*/

        /*service.getAllImage();

        service.updateImage(nef);

        logger.debug("nef updated ...");

        service.deleteImage(nef);*/

    }

    private static Course testInsertHTML(){
        Course course = new Course();

        course.setHtml("\n" +
                "<!DOCTYPE HTML>\n" +
                "<html lang=\"fr\">\n" +
                "<!-- language de la page doit etre insere dans la balise html - en/fr -->\t\n" +
                "\t<head>\n" +
                "\t\t<title>Vid&eacute;otron : Test de vitesse de connexion Internet</title>\n" +
                "        <meta name=\"description\" content=\"Testez la vitesse de votre connexion Internet Vid&eacute;otron.\">\n" +
                "\t\t<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\" />\n" +
                "<!-- V.COM external -->\n" +
                "\t\t<link rel=\"shortcut icon\" type=\"image/x-icon\"href=\"http://www.videotron.com/resources/external/skin/http://www.videotron.com/resources/external/skin/img/icons/favicon.ico\" />\n" +
                "\t\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" title=\"css\" href=\"http://www.videotron.com/resources/external/skin/css/combo.css\" />\t\n" +
                "\t\t<link rel=\"stylesheet\" type=\"text/css\" media=\"print\" title=\"css\" href=\"http://www.videotron.com/resources/external/skin/css/print/print.css\" />\n" +
                "\t\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" title=\"css\" href=\"http://www.videotron.com/resources/external/speed-test/css/micro-site-vitesse.css\" />\n" +
                "\t<!-- IE conditionals -->\n" +
                "\t\t<!--[if IE]>\n" +
                "\t\t\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" title=\"css\" href=\"http://www.videotron.com/resources/external/skin/css/ie/ieall.css\" />\n" +
                "\t\t<![endif]-->\n" +
                "\t\t<!--[if lt IE 9]>\n" +
                "\t\t\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" title=\"css\" href=\"http://www.videotron.com/resources/external/skin/css/ie78/ie78.css\" />\n" +
                "\t\t<![endif]-->\n" +
                "\t\t<!--[if lte IE 9]>\n" +
                "\t\t\t<script type=\"text/javascript\" src=\"http://www.videotron.com/resources/external/skin/js/ie789lib/PIE.js\" ></script>\n" +
                "\t\t<![endif]-->\n" +
                "\t\t<!--[if lt IE 9]>\n" +
                "\t\t\t<script type=\"text/javascript\" src=\"http://www.videotron.com/resources/external/skin/js/ie78lib/excanvas.compiled.js\" ></script>\n" +
                "\t\t\t<script type=\"text/javascript\" src=\"http://www.videotron.com/resources/external/skin/js/ie78lib/html5.js\" ></script>\n" +
                "\t\t<![endif]-->\n" +
                "\t\t\n" +
                "\t\t<script type=\"text/javascript\">\n" +
                "\t\t\t// allows js specific css styles\n" +
                "\t\t\tdocument.documentElement.setAttribute(\"data-javascript\",\"\");\n" +
                "\t\t</script>\t\n" +
                "<!-- /V.COM external -->\t\t\t\n" +
                "\t\t<script type=\"text/javascript\" src=\"swfobject.js\"></script>\n" +
                "\t\t<script type=\"text/javascript\" src=\"browserdetect.js\"></script>\n" +
                "\t\t<script type=\"text/javascript\" src=\"functions_v3.js\"></script>\n" +
                "\t\t<script type=\"text/javascript\">\n" +
                "\t\tvar loadedApplet = \"\";\n" +
                "\t\tfunction loadJava(applet) {\n" +
                "\t\t        if(loadedApplet == applet) {\n" +
                "\t\t                return;\n" +
                "\t\t        } else {\n" +
                "\t\t                loadedApplet = applet;\n" +
                "\t\t        }\n" +
                "\t\t        var e = document.getElementById('javadiv');\n" +
                "\t\t        if(e != null) {\n" +
                "\t\t                if ((BrowserDetect.browser == \"Safari\") || (BrowserDetect.browser == \"Opera\")) {\n" +
                "\t\t                    applet = \"VoipApplet.jar\";\n" +
                "\t\t                }\n" +
                "\t\t                e.innerHTML = \"<applet code=\\\"VoipApplet.class\\\" archive=\\\"\" + applet + \"?blah=\" + Math.random() + \"\\\" width=\\\"1\\\" height=\\\"1\\\" mayscript id=\\\"VoipApplet\\\" name=\\\"VoipApplet\\\"><param name=\\\"debug\\\" value=\\\"false\\\"></applet>\";\n" +
                "\t\t        }\n" +
                "\t\t}\n" +
                "\t\t</script>\t\t\n" +
                "\t</head>\n" +
                "\t\n" +
                "\t<body class=\"white\">\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t\t<div class=\"page-wrapper page-test-speed\">\n" +
                "\t\t\n" +
                "\t\t\t<!-- header qui va etre remplace -->\n" +
                "\t\t\t<header id=\"header\">\n" +
                "\t\t\t\t<div class=\"toolbar-wrapper\"><div class=\"wrapper\"><div class=\"content\"><nav><ul id=\"toolbar\" class=\"fix-height\"><li class=\"inline-block main-section selected\"><a href=\"http://www.videotron.com/resources/external/skin/http://www.videotron.com/\">Résidentiel</a></li></ul></nav></div></div></div>\n" +
                "\t\t\t\t<div class=\"wrapper\"><div class=\"content\">\n" +
                "\t\t\t\t\t<div class=\"wrapper\"><div class=\"fix-height\"><a href=\"http://www.videotron.com/\" id=\"logo\"><img src=\"http://www.videotron.com/resources/external/skin/img/logo/head-videotron-logo-black-fr.png\" alt=\"Résidentiel\"></a><nav id=\"main-menu\"><ul class=\"inline-block\"><li class=\"home\"><a href=\"http://www.videotron.com/\">Résidentiel</a></li></ul></nav></div></div>\n" +
                "\t\t\t\t\t<!-- sub-menu optionnnel - cette section est conserve telle-quelle -->\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t<!-- no sub menu in white page -->\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t<!-- /sub-menu optionnnel -->\n" +
                "\t\t\t\t</div></div>\n" +
                "\t\t\t</header>\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t<section class=\"page-section\">\n" +
                "\t\t\t\t<div class=\"wrapper\">\n" +
                "\t\t\t\t\t<div class=\"content\">\n" +
                "\t\t\t\t\t\t<div class=\"inner-content\">\n" +
                "\t\t\t\t\t\t<!-- CONTENU DE LA PAGE - cette section est conserve telle-quelle -->\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t<hgroup>\n" +
                "\t\t\t\t\t\t\t\t<h1>Test de vitesse</h1>\n" +
                "\t\t\t\t\t\t\t</hgroup>\n" +
                "\t\t\t\t\t\t\t<div class=\"container-pp\">\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t<div id=\"introduction\" class=\"media\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"text\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<p><strong>Mesurez la vitesse de votre acc&egrave;s Internet</strong></p>\n" +
                "\t\t\t\t\t\t\t\t\t\t<p>Ce service gratuit vous permet d'&eacute;valuer la performance de votre acc&egrave;s Internet en mesurant le d&eacute;bit d'envoi et de r&eacute;ception de donn&eacute;es.</p>\n" +
                "\t\t\t\t\t\t\t\t\t\t<p><strong>Pour vous assurer de la validit&eacute; du test de vitesse, vous devez :</strong></p>\n" +
                "\t\t\t\t\t\t\t\t\t\t<ol class=\"list\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<li>brancher l'ordinateur directement au modem c&acirc;ble;</li>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<li>v&eacute;rifier que l'ordinateur ne traite pas d&rsquo;autres t&acirc;ches;</li>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<li>v&eacute;rifier que seul le test de vitesse utilise la connexion Internet.</li>\n" +
                "\t\t\t\t\t\t\t\t\t\t</ol>\n" +
                "\t\t\t\t\t\t\t\t\t\t<ul class=\"list\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<li><a href=\"faq-fr.html\" title=\"Pour en savoir plus, visitez notre foire aux questions\">Pour en savoir plus, visitez notre foire aux questions</a></li>\n" +
                "\t\t\t\t\t\t\t\t\t\t</ul>\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"player-wrapper wide-list fix-height\" align=\"center\">\t\n" +
                "\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\t<!-- New code test speed - fournisseur externe -->\n" +
                "\t\t\t\t\t\t\t\t\t\t \n" +
                "\t\t\t\t\t\t\t\t\t\t<div id=\"abovebefore\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!-- ANYTHING PLACED IN THIS DIV WILL SHOW UP ABOVE THE TEST BUT DISAPPEAR AFTER IT COMPLETES -->\n" +
                "\t\t\t\t\t\t\t\t\t\t<!-- Editer index-fr.html pour changer ou enlever ce contenu exemple qui <strong>disparaitra apres</strong> que le test ait ete execute une fois. --> \n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\t<div id=\"aboveafter\" style=\"display: none;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!-- ANYTHING PLACED IN THIS DIV WILL SHOW UP ABOVE THE TEST AFTER IT COMPLETES -->\n" +
                "\t\t\t\t\t\t\t\t\t\t<!-- Ce contenu <strong>n'apparaitra qu'apres</strong> avoir execute le test une fois. Modifier index-fr.html pour le changer ou l'enlever. -->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\t<div id=\"flashcontainer\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div id=\"flashcontent\">\n" +
                "\t\t\t\t\t\t\t\t\t\t \t \t<div id=\"speedtest\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"flash-alt-txt\" align=\"left\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<p class=\"first\"><strong>Pour acc&eacute;dez &agrave; ce contenu vous devez activer JavaScript et &ecirc;tre &eacute;quip&eacute; du plugiciel Adobe Flash. \n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a class=\"external\" target=\"adobe\" href=\"http://www.adobe.com/go/getflashplayer_fr\" title=\"T&eacute;l&eacute;chargez la derni&egrave;re version du plugiciel Adobe Flash (gratuit)\" rel=\"nofollow\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<br />\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\tCliquez ici pour le t&eacute;l&eacute;charger</a> en toute s&eacute;curit&eacute;, en quelques minutes seulement.</strong></p>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<p><a class=\"external\" target=\"adobe\" href=\"http://www.adobe.com/go/getflashplayer_fr\" title=\"T&eacute;l&eacute;chargez la derni&egrave;re version du plugiciel Adobe Flash (gratuit)\" rel=\"nofollow\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<img alt=\"Get Adobe Flash Player\" src=\"http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif\" border=\"0\" /></a><br /><br />\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span>L'installation se fera automatiquement. Ce plugiciel vous servira pour la visite de nombreux sites anim&eacute;s.</span></p>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</div> <!-- /flash-alt-txt -->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\t\t\t\t\t\t\t\t\t \t \n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\t<script type=\"text/javascript\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\tvar flashvars = {\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tconfigExtension: \"php\"\n" +
                "\t\t\t\t\t\t\t\t\t            \n" +
                "\t\t\t\t\t\t\t\t\t\t\t};\n" +
                "\t\t\t\t\t\t\t\t\t\t\tvar params = {\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tquality: \"high\",\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tbgcolor: \"#ffffff\",\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tallowScriptAccess: \"always\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t};\n" +
                "\t\t\t\t\t\t\t\t\t\t\tvar attributes = {\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tid: \"flashtest\",\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tname: \"flashtest\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t};\n" +
                "\t\t\t\t\t\t\t\t\t\t\tswfobject.embedSWF(\"netgauge.swf?v=3.0&lang=fr\", \"flashcontent\", \"640\", \"400\", \"10.0.0\", false, flashvars, params, attributes);\n" +
                "\t\t\t\t\t\t\t\t\t\t</script>\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\t<div id=\"speed\"></div>\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\t<div id=\"javadiv\"></div>\t \n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\t<div id=\"belowbefore\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!-- ANYTHING PLACED IN THIS DIV WILL SHOW UP BELOW THE TEST BUT DISAPPEAR AFTER IT COMPLETES -->\n" +
                "\t\t\t\t\t\t\t\t\t\t<!-- Ce contenu <strong>n'apparaitra qu'apres</strong> avoir execute le test une fois. Modifier index-fr.html pour le changer ou l'enlever. -->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\t<div id=\"belowafter\" style=\"display: none;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!-- ANYTHING PLACED IN THIS DIV WILL SHOW UP BELOW THE TEST AFTER IT COMPLETES -->\n" +
                "\t\t\t\t\t\t\t\t\t\t<!-- Editer index-fr.html pour changer ou enlever ce contenu exemple qui <strong>disparaitra apres</strong> que le test ait ete execute une fois. -->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\t<!-- /New code test speed - fournisseur externe -->\t\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t</div>\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t</div><!-- /introduction -->\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t<div id=\"technologie-hd\" class=\"clear\">\n" +
                "\t\t\t\t\t\t\t\t\t<img src=\"http://www.videotron.com/resources/external/speed-test/img/icone-vitesse.gif\" width=\"126\" height=\"90\" style=\"float:left;\"/>\n" +
                "\t\t\t\t\t\t\t\t\t<p><strong>Besoin de vitesse ?</strong></p>\n" +
                "\t\t\t\t\t\t\t\t\t<p>Choisissez l'acc&egrave;s qui r&eacute;pondra le mieux &agrave; vos besoins.<br /><br />\n" +
                "\t\t\t\t\t\t\t\t\t<a href=\"http://www.videotron.com/residentiel/internet/internet-residentiel\" title=\"Visitez nos acc&egrave;s Internet\">Visitez nos acc&egrave;s Internet</a></p>\n" +
                "\t\t\t\t\t\t\t\t</div><!-- /technologie-hd -->\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t</div>\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<!-- /CONTENU DE LA PAGE -->\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</section>\t\t\t\n" +
                "\t\t\t\t\n" +
                "\t\t\t<div class=\"footer-section\">\n" +
                "\t\t\t\t<footer class=\"fix-height\" id=\"footer\">\n" +
                "\t\t\t\t\t<div class=\"wrapper\">\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<!-- big footer -->\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<!-- no big footer in white page --->\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<!-- /big footer -->\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<!-- footer dynamic inseré ici -->\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</footer>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t\t\n" +
                "\t\t<script>\n" +
                "\t\t\t/* check for jquery before using it, download it if missing */ \n" +
                "\t\t\twindow.jQuery || document.write('<script src=\"' + document.location.protocol + '//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js\"><\\/script>');\n" +
                "\t\t</script>\n" +
                "\t\t<script type=\"text/javascript\" src=\"http://www.videotron.com/resources/external/skin/js/ext_menu.js\" charset=\"utf-8\"></script>\n" +
                "\t\t<script>\n" +
                "\t\t\t// important de mettre ce bloc apres ext_menu.js\n" +
                "\t\t\t$(function(){\n" +
                "\t\t\t\t// ajoute le bouton qui gere la langue au menu - d'autres solutions sont aussi possible\n" +
                "\t\t\t\t$(\"#lang-sub-menu\").append(\"<li class='toolbar-button-lang'><a href='index-en.html'>EN</a></li>\");\n" +
                "\t\t\t});\n" +
                "\t\t</script>\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t</body>\n" +
                "</html>");
        course.setName("Cours de Math");
        course. setCreationDate(new Date());
        return course;
    }

    /**
     *  Test if GridFS works
     */
   /* private static void FillMongoWithBigFile() throws IOException {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("eschoolbag");

        String newFileName = "mkyong-java-image";
        File imageFile = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");
        GridFS gfsPhoto = new GridFS(db, "Image");
        GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
        gfsFile.setFilename(newFileName);
        gfsFile.save();
        ObjectId id = (ObjectId)gfsFile.get("_id");
        System.out.println("id = ["+id+"]");
        mongo.close();
    }*/


    private static Image createImage() {
        Image nef = new Image();
        nef.setName("nef_img");
        nef.setCreationDate(new Date());
        nef.setModifiedDate(new Date());
        File imageFile = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");
        nef.setBinary(imageFile);

        return nef;
    }

/*    *
     * Test of ImageService
     * Save Big image through ImageService

    private static void saveImageServiceTest() {

        //service.saveImage(nef);
    }*/
  /*  private static void getImageServiceTest(){
        ImageService service = new ImageService();
        Image nef = new Image();

        nef.setName("nef_img");
        nef.setCreationDate(new Date());
        nef.setModifiedDate(new Date());
        File imageFile = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");
        nef.setBinary(imageFile);
        service.getImage(nef);
    }

    private static void deleteImageServiceTest(){
        ImageService service = new ImageService();
        Image nef = DAOHelper.imageDAO.findOne("name", "nef_img");

        service.deleteImage(nef);
    }

    private static void updateImageServiceTest(){
        ImageService service = new ImageService();
        Image nef = dao.findOne("name", "nef_img");

        File imageFile = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");
        nef.setBinary(imageFile);
        service.updateImage(nef);
        service.getImage(nef);
    }*/
}
