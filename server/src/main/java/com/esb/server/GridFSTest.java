package com.esb.server;

import com.esb.server.dao.media.ImageDAO;
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

    private static ImageDAO dao    = new ImageDAO();

    public static void main(String[] args) throws IOException {


        ImageService imageService = new ImageService();
        AFileService aFileservice = new AFileService();

        Image nef = createImage();

       /* service.saveImage(nef);h

        service.getAllImage();

        service.updateImage(nef);

        logger.debug("nef updated ...");

        service.deleteImage(nef);*/

        aFileservice.saveFile(nef, "Image");

        imageService.getAllImage();

        /*service.getAllImage();

        service.updateImage(nef);

        logger.debug("nef updated ...");

        service.deleteImage(nef);*/

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
        //File imageFile = new File("/home/alex/Project/EIP/CurrentVersion/Serveur/server/src/main/java/com/esb/server/DSC_0779.NEF");
        //nef.setBinary(imageFile);
        nef.setBinary("\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"fr\" lang=\"fr\" dir=\"ltr\">\n" +
                "  <head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "    <title>Développeur(se) full-stack H/F | Offre d'emploi | EuraTechnologies : Parc d'activités TIC et Incubateur - Lille</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<link rel=\"shortcut icon\" href=\"/sites/default/files/euratechnologies_theme_logo.ico\" type=\"image/x-icon\" />\n" +
                "<link rel=\"prev\" href=\"/node/368\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/content/financeurs-partenaires\" />\n" +
                "<link rel=\"prev\" href=\"/node/368\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/content/financeurs-partenaires\" />\n" +
                "<link rel=\"prev\" href=\"/euratechnologies-developpement\" />\n" +
                "<link rel=\"up\" href=\"/euratechnologies-developpement\" />\n" +
                "<link rel=\"next\" href=\"/euratechnologies-developpement/concept-iceee\" />\n" +
                "<link rel=\"prev\" href=\"/euratechnologies-developpement/missions\" />\n" +
                "<link rel=\"up\" href=\"/euratechnologies-developpement\" />\n" +
                "<link rel=\"next\" href=\"/node/373\" />\n" +
                "<link rel=\"prev\" href=\"/euratechnologies-developpement/concept-iceee\" />\n" +
                "<link rel=\"up\" href=\"/euratechnologies-developpement\" />\n" +
                "<link rel=\"next\" href=\"/parc-activites/localisation\" />\n" +
                "<link rel=\"prev\" href=\"/node/373\" />\n" +
                "<link rel=\"up\" href=\"/euratechnologies-developpement\" />\n" +
                "<link rel=\"next\" href=\"/node/1951\" />\n" +
                "<link rel=\"prev\" href=\"/parc-activites/localisation\" />\n" +
                "<link rel=\"up\" href=\"/euratechnologies-developpement\" />\n" +
                "<link rel=\"next\" href=\"/services\" />\n" +
                "<link rel=\"prev\" href=\"/content/financeurs-partenaires\" />\n" +
                "<link rel=\"up\" href=\"/content/financeurs-partenaires\" />\n" +
                "<link rel=\"next\" href=\"/content/partenaires-0\" />\n" +
                "<link rel=\"prev\" href=\"/content/financeurs-0\" />\n" +
                "<link rel=\"up\" href=\"/content/financeurs-partenaires\" />\n" +
                "<link rel=\"next\" href=\"/content/partenaires-incubateur-accelerateur-0\" />\n" +
                "<link rel=\"prev\" href=\"/content/partenaires-0\" />\n" +
                "<link rel=\"up\" href=\"/content/financeurs-partenaires\" />\n" +
                "<link rel=\"next\" href=\"/content/lille-french-tech-0\" />\n" +
                "<link rel=\"prev\" href=\"/content/partenaires-incubateur-accelerateur-0\" />\n" +
                "<link rel=\"up\" href=\"/content/financeurs-partenaires\" />\n" +
                "<link rel=\"next\" href=\"/euratechnologies-developpement\" />\n" +
                "<link rel=\"prev\" href=\"/node/1951\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/services/creer\" />\n" +
                "<link rel=\"prev\" href=\"/node/1951\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/services/creer\" />\n" +
                "<link rel=\"prev\" href=\"/content/bureau-espace-coworking-paris\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/entreprises/metiers\" />\n" +
                "<link rel=\"prev\" href=\"/content/bureau-espace-coworking-paris\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/entreprises/metiers\" />\n" +
                "<link rel=\"prev\" href=\"/entreprises/annuaire\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/node/388\" />\n" +
                "<link rel=\"prev\" href=\"/entreprises/annuaire\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/node/388\" />\n" +
                "<link rel=\"prev\" href=\"/international/accueil-international\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/euratechjob/offres\" />\n" +
                "<link rel=\"prev\" href=\"/international/accueil-international\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/euratechjob/offres\" />\n" +
                "<link rel=\"prev\" href=\"/euratechjob/formations\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/evenement/salles\" />\n" +
                "<link rel=\"prev\" href=\"/euratechjob/formations\" />\n" +
                "<link rel=\"up\" href=\"/node/368\" />\n" +
                "<link rel=\"next\" href=\"/evenement/salles\" />\n" +
                "<meta name=\"description\" content=\"A propos de Merchandising.IO : Merchandising.io est une startup proposant une application d’analyse web à destination des e-commerçants.\" />\n" +
                "<meta name=\"dcterms.description\" content=\"A propos de Merchandising.IO : Merchandising.io est une startup proposant une application d’analyse web à destination des e-commerçants.\" />\n" +
                "<link rel=\"canonical\" href=\"http://www.euratechnologies.com/euratechjob/offres/2016/06/developpeur-se-full-stack-h-f-10799\" />\n" +
                "<meta name=\"revisit-after\" content=\"1 day\" />\n" +
                "    <link type=\"text/css\" rel=\"stylesheet\" media=\"all\" href=\"/sites/default/files/css/css_c42dcbd2e2b90ca4b2a5a48a3fcd63dc.css\" />\n" +
                "    <script type=\"text/javascript\" src=\"/sites/default/files/js/js_14256a750f1ffaaa38765118ecadae04.js\"></script>\n" +
                "<script type=\"text/javascript\">\n" +
                "<!--//--><![CDATA[//><!--\n" +
                "jQuery.extend(Drupal.settings, { \"basePath\": \"/\", \"googleanalytics\": { \"trackOutbound\": 1, \"trackMailto\": 1, \"trackDownload\": 1, \"trackDownloadExtensions\": \"7z|aac|arc|arj|asf|asx|avi|bin|csv|doc|exe|flv|gif|gz|gzip|hqx|jar|jpe?g|js|mp(2|3|4|e?g)|mov(ie)?|msi|msp|pdf|phps|png|ppt|qtm?|ra(m|r)?|sea|sit|tar|tgz|torrent|txt|wav|wma|wmv|wpd|xls|xml|z|zip\" }, \"extlink\": { \"extTarget\": \"_blank\", \"extClass\": 0, \"extSubdomains\": 1, \"extExclude\": \"\", \"extInclude\": \"\", \"extAlert\": 0, \"extAlertText\": \"This link will take you to an external web site. We are not responsible for their content.\", \"mailtoClass\": \"mailto\" } });\n" +
                "//--><!]]>\n" +
                "</script>\n" +
                "  </head>\n" +
                "\n" +
                "  <body class=\" \">\n" +
                "    <div id=\"background\">\n" +
                "      <div id=\"page\">\n" +
                "        <div id=\"header\">\n" +
                "          <h1 id=\"logo\">\n" +
                "            <a href=\"/\" title=\"Accueil\" rel=\"home\">\n" +
                "              <img src=\"/sites/default/files/euratechnologies_theme_logo.gif\" title=\"Euratechnologies\" alt=\"Euratechnologies\" />\n" +
                "            </a>\n" +
                "          </h1>\n" +
                "          \n" +
                "                    \t<div id=\"extranetForm\">\n" +
                "\t          \t            <div id=\"topNav\" class=\"nav\">\n" +
                "\t                <ul>  <li class=\"menu-421 first\"><a href=\"/blog\" title=\"\">Blog</a></li>\n" +
                "  <li class=\"menu-222\"><a href=\"/actualites\" title=\"\">Actualités</a></li>\n" +
                "  <li class=\"menu-468\"><a href=\"/rss.xml\" title=\"\">RSS</a></li>\n" +
                "  <li class=\"menu-350\"><a href=\"/contact\" title=\"Contactez-nous\">Contactez-nous</a></li>\n" +
                "  <li class=\"menu-167\"><a href=\"http://www.euratechnologies.com/content/newsletter\" title=\"Les dernières news d&#039;EuraTechnologies.\">Newsletter</a></li>\n" +
                "  <li class=\"menu-1106 last\"><a href=\"/plan-d-acces\" title=\"Plan d&#039;accès\">Plan d&#039;accès</a></li>\n" +
                "</ul>\n" +
                "\t            </div>\n" +
                "\t                    \t  <div id=\"languageswitcher\">\n" +
                "  <ul>  <li class=\"en first last on language-link\"><a href=\"/en/euratechjob/offres/2016/06/developpeur-se-full-stack-h-f-10799\" class=\"language-link\"><img src=\"/sites/all/modules/languageicons/flags/en.png\" alt=\"English\" title=\"English\"  class=\"language-icon\" width=\"30\" height=\"15\" /></a></li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div class=\"clear-block-connexion\">\n" +
                "<form action=\"/euratechjob/offres/2016/06/developpeur-se-full-stack-h-f-10799?destination=node%2F10799\"  accept-charset=\"UTF-8\" method=\"post\" id=\"user-login-form\">\n" +
                "<div><div class=\"form-item form-item-textfield \"  id=\"edit-name-wrapper\">\n" +
                "<input type=\"text\" maxlength=\"60\" name=\"name\" id=\"edit-name\" size=\"32\" value=\"\" class=\"form-text required texte texte\" />\n" +
                "</div>\n" +
                "<div class=\"form-item form-item-password \"  id=\"edit-pass-wrapper\">\n" +
                "<input type=\"password\" name=\"pass\" id=\"edit-pass\"  maxlength=\"60\"  size=\"32\"  class=\"form-text required texte\" />\n" +
                "</div>\n" +
                "<div class=\"form-image-button\"><input type=\"image\" name=\"op\" value=\"OK\" id=\"edit-submit\"  class=\"form-image \" src=\"/sites/all/themes/euratechnologies_theme/img/header-search-arrow.gif\" src=\"/\" /></div>\n" +
                "<input type=\"hidden\" name=\"form_build_id\" id=\"form-d579c6c4db2aac76e466bb4f2a9e89d5\" value=\"form-d579c6c4db2aac76e466bb4f2a9e89d5\"  />\n" +
                "<input type=\"hidden\" name=\"form_id\" id=\"edit-user-login-block\" value=\"user_login_block\"  />\n" +
                "\n" +
                "</div></form>\n" +
                "</div>\n" +
                "          \t</div>\n" +
                "                    <div id=\"mainNav\" class=\"nav\">\n" +
                "<ul class='mainNav'><li class=\"euratechnologies\"><div class=\"mainNavTitle\"><a href=\"/\">Euratechnologies</a></div><div class=\"mainNavHover\" style=\"display: none;\">\n" +
                "<div class=\"field-menuimage\">\n" +
                "\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          <img  class=\"imagefield imagefield-field_menuimage\" width=\"296\" height=\"136\" alt=\"\" src=\"http://www.euratechnologies.com/sites/default/files/header_hover_euratech_0.gif?1278059883\" />\n" +
                "      \n" +
                "</div>\n" +
                "<ul>    <li><h2><a href=\"/euratechnologies-developpement\">S.P.L EuraTechnologies </a></h2></li>\n" +
                "        <li><a href=\"/euratechnologies-developpement/missions\">Les missions</a></li>    <li><a href=\"/euratechnologies-developpement/concept-iceee\">Le concept ICEEE</a></li>    <li><a href=\"/node/373\">L&#039;équipe</a></li>    <li><a href=\"/parc-activites/localisation\">Localisation</a></li>    <li><a href=\"/node/1951\">Marchés et consultations </a></li></ul><ul>    <li><h2><a href=\"/content/financeurs-partenaires\">Financeurs &amp; Partenaires</a></h2></li>\n" +
                "        <li><a href=\"/content/financeurs-0\">Les financeurs</a></li>    <li><a href=\"/content/partenaires-0\">Les partenaires</a></li>    <li><a href=\"/content/partenaires-incubateur-accelerateur-0\">Les partenaires de l&#039;incubateur et de l&#039;accélérateur</a></li>    <li><a href=\"/content/lille-french-tech-0\">Lille is French Tech </a></li></ul><div><ul><li><div class=\"fiche borderPink\">\n" +
                "              <p>\n" +
                "\t<b><br />\n" +
                "\t</b></p>\n" +
                "    </div>\n" +
                "</li></ul></div></div></li><li class=\"services\"><div class=\"mainNavTitle\"><a href=\"/\">Services</a></div><div class=\"mainNavHover\" style=\"display: none;\">\n" +
                "<div class=\"field-menuimage\">\n" +
                "\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          <img  class=\"imagefield imagefield-field_menuimage\" width=\"301\" height=\"169\" alt=\"\" src=\"http://www.euratechnologies.com/sites/default/files/header_hover_services_0.gif?1278335742\" />\n" +
                "      \n" +
                "</div>\n" +
                "<ul><li><h2><a href=\"/services/creer\">Créer</a></h2></li><li><h2><a href=\"/services/business-accelerateur\">Business Accelerateur</a></h2></li><li><h2><a href=\"/services/hotel-entreprises\">Hôtel d&#039;entreprises</a></h2></li><li><h2><a href=\"/content/clustercn-cs\">ClusterCN&amp;CS</a></h2></li><li><h2><a href=\"/services/technique-technologique\">Technique &amp; Technologique</a></h2></li></ul><ul><li><h2><a href=\"/services/formations\">Formations</a></h2></li><li><h2><a href=\"/services/recherche-developpement\">Recherche &amp; Développement</a></h2></li><li><h2><a href=\"/content/visioconference\">Visioconférence </a></h2></li><li><h2><a href=\"/content/bureau-espace-coworking-paris\">Bureau - Espace de coworking  Paris</a></h2></li></ul></div></li><li class=\"entreprises\"><div class=\"mainNavTitle\"><a href=\"/\">Les entreprises</a></div><div class=\"mainNavHover\" style=\"display: none;\">\n" +
                "<div class=\"field-menuimage\">\n" +
                "\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          <img  class=\"imagefield imagefield-field_menuimage\" width=\"302\" height=\"161\" alt=\"\" src=\"http://www.euratechnologies.com/sites/default/files/header_hover_entreprises_0.gif?1278335918\" />\n" +
                "      \n" +
                "</div>\n" +
                "<ul><li><h2><a href=\"/entreprises/metiers\">Les métiers</a></h2></li><li><h2><a href=\"/entreprises/annuaire\">Annuaire des entreprises</a></h2></li></ul><div><ul><li><div class=\"fiche borderPink\">\n" +
                "              <div id=\"entreprisesForm\">\n" +
                "\t<h2>\n" +
                "Rechercher une entreprise\t</h2>\n" +
                "<form action=\"/euratechjob/offres/2016/06/developpeur-se-full-stack-h-f-10799\"  accept-charset=\"UTF-8\" method=\"post\" id=\"entreprise-search-form-1\">\n" +
                "<div><div class=\"form-item form-item-select \"  id=\"edit-activite-1-wrapper\">\n" +
                " <label for=\"edit-activite-1\">Secteur d'activité : </label>\n" +
                "<select name=\"activite\" class=\"form-select select select\" id=\"edit-activite-1\" ><option value=\"all\">Tous</option><option value=\"E-commerce\">E-commerce\n" +
                "</option><option value=\"Pure Players\">Pure Players\n" +
                "</option><option value=\"SOLOMO\n" +
                "\"></option><option value=\"Editeurs de logiciel\">Editeurs de logiciel\n" +
                "</option><option value=\"Réseaux &amp; Télécoms\">Réseaux &amp; Télécoms\n" +
                "</option><option value=\"Industries de contenus\">Industries de contenus / 3D / RA\n" +
                "</option><option value=\"Accompagnement / Soutien à l&#039;entrepreneuriat\">Accompagnement / Soutien à l&#039;entrepreneuriat\n" +
                "</option><option value=\"Recherche/Formations\">Recherche/Formations\n" +
                "</option><option value=\"Intégrateur/Conseil\"></option></select>\n" +
                "</div>\n" +
                "<div class=\"form-item form-item-textfield \"  id=\"edit-raison-sociale-1-wrapper\">\n" +
                " <label for=\"edit-raison-sociale-1\">Raison sociale : </label>\n" +
                "<input type=\"text\" maxlength=\"150\" name=\"raison_sociale\" id=\"edit-raison-sociale-1\" size=\"60\" value=\"\" class=\"form-text texte texte\" />\n" +
                "</div>\n" +
                "<div class=\"form-image-button\"><input type=\"image\" name=\"entreprise_search\" id=\"edit-entreprise-search-1\"  class=\"form-image\" src=\"/sites/all/themes/euratechnologies_theme/img/encart_hover_arrow.gif\" /></div>\n" +
                "<input type=\"hidden\" name=\"form_build_id\" id=\"form-c9ebede84dbd37e8e8f59c60ed5a5758\" value=\"form-c9ebede84dbd37e8e8f59c60ed5a5758\"  />\n" +
                "<input type=\"hidden\" name=\"form_id\" id=\"edit-entreprise-search-form-1\" value=\"entreprise_search_form\"  />\n" +
                "\n" +
                "</div></form>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</li></ul></div></div></li><li class=\"international\"><div class=\"mainNavTitle\"><a href=\"/\">International</a></div><div class=\"mainNavHover\" style=\"display: none;\">\n" +
                "<div class=\"field-menuimage\">\n" +
                "\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          <img  class=\"imagefield imagefield-field_menuimage\" width=\"301\" height=\"169\" alt=\"\" src=\"http://www.euratechnologies.com/sites/default/files/header_hover_international_0.gif?1278336093\" />\n" +
                "      \n" +
                "</div>\n" +
                "<ul><li><h2><a href=\"/node/388\">Bureaux &amp; Partenariats</a></h2></li><li><h2><a href=\"/international/missions-etranger\">Missions à l&#039;étranger</a></h2></li><li><h2><a href=\"/international/accueil-international\">Accueil international</a></h2></li></ul></div></li><li class=\"euratechjob\"><div class=\"mainNavTitle\"><a href=\"/\">Euratech'Job</a></div><div class=\"mainNavHover\" style=\"display: none;\">\n" +
                "<div class=\"field-menuimage\">\n" +
                "\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          <img  class=\"imagefield imagefield-field_menuimage\" width=\"301\" height=\"169\" alt=\"\" src=\"http://www.euratechnologies.com/sites/default/files/header_hover_euratechjob_0.gif?1278335703\" />\n" +
                "      \n" +
                "</div>\n" +
                "<ul><li><h2><a href=\"/euratechjob/offres\">EuraTech&#039;Job</a></h2></li><li><h2><a href=\"/euratechjob/formations\">Les formations</a></h2></li></ul></div></li><li class=\"evenement\"><div class=\"mainNavTitle\"><a href=\"/\">Events</a></div><div class=\"mainNavHover\" style=\"display: none;\">\n" +
                "<div class=\"field-menuimage\">\n" +
                "\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          <img  class=\"imagefield imagefield-field_menuimage\" width=\"302\" height=\"161\" alt=\"\" src=\"http://www.euratechnologies.com/sites/default/files/header_hover_evenement_0.gif?1278336016\" />\n" +
                "      \n" +
                "</div>\n" +
                "<ul><li><h2><a href=\"/evenement/salles\">Organiser un événement </a></h2></li><li><h2><a href=\"/agenda\">L&#039;agenda</a></h2></li><li><h2><a href=\"/content/demande-devis\">Demande de devis</a></h2></li></ul></div></li></ul></div>        </div>\n" +
                "        <div id=\"content\" class=\"contentint\">\n" +
                "          \n" +
                "            <div id=\"leftColumn\" class=\"column\">\n" +
                "              \n" +
                "              \n" +
                "              \n" +
                "              \n" +
                "<div>\n" +
                "      <div id=\"articleTitle\">\n" +
                "      <div id='breadCrump' class='nav'><ul><li> <a href=\"/\">Accueil »</a></li><li> <a href=\"/offres_emplois\">Offres d&#039;emploi »</a></li><li> <a href=\"/euratechjob/offres/2016/06/developpeur-se-full-stack-h-f-10799\" class=\"active\">Développeur(se) full-stack H/F »</a></li></ul></div>      <h2>Développeur(se) full-stack H/F</h2>\n" +
                "    </div>\n" +
                "  \n" +
                "  <div class=\"meta\">\n" +
                "      </div>\n" +
                "\n" +
                "  <div class=\"fiche fiche-offre-emploi\">\n" +
                "    <img alt=\"\" src=\"http://www.euratechnologies.com/sites/default/files/logo-merchandisingio.png\" style=\"height:138px; width:200px\" /><br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<b>A propos de Merchandising.IO : </b><br />\n" +
                "<br />\n" +
                "Merchandising.io est une startup proposant une application d’analyse web à destination des e-commerçants. Au carrefour du merchandising et de l’analyse web, notre application permet aux e-commerçants de mieux connaitre la performance de leur offre produit pour les aider à mettre en place la stratégie merchandising qui fera croitre leur business en ligne.<br />\n" +
                "<br />\n" +
                "Nos bureaux sont au sein d’Euratechnologies, parc d’activité situé à Lille, regroupant les acteurs, projets et innovations de la filière TIC de la région.<br />\n" +
                "<br />\n" +
                "Pour le développement de nouvelles fonctionnalités de son application, Merchandising.io recherche un stagiaire lead developer qui aura comme mission principale le développement de nouvelles fonctionnalités de l’application et de l’API.<br />\n" +
                "<br />\n" +
                "Vous travaillerez au sein de notre start-up qui vous permettra de gagner en responsabilités rapidement et de booster vos compétences avec des missions polyvalentes.<br />\n" +
                "<br />\n" +
                "<b>Missions : Les responsabilités du poste sont les suivantes et sont amenées à évoluer selon le profil du candidat : </b><br />\n" +
                "<br />\n" +
                "• Participation au développement des nouvelles fonctionnalités de notre application.<br />\n" +
                "• Amélioration des fonctionnalités actuelles.<br />\n" +
                "• Développer, documenter et maintenir les API / Code sources.<br />\n" +
                "• Participation à l’écriture de la roadmap produit.<br />\n" +
                "<br />\n" +
                "<b>Profil recherché :</b><br />\n" +
                "<br />\n" +
                "• Vous avez le goût du challenge et vous êtes prêt(e) à vous lancer dans une expérience enrichissante afin de participer à la croissance d’une entreprise jeune, dynamique et créative.<br />\n" +
                "• Vous avez le sens des responsabilités, l’esprit d’initiative et d’entreprendre.<br />\n" +
                "• Vous aimez travailler en équipe et avez un intérêt fort pour le e-commerce.<br />\n" +
                "• Connaissances Javascript orienté objet.<br />\n" +
                "• Parfaite maitrise HTML, CSS ainsi que du framework bootstrap.<br />\n" +
                "• Framework angularJS.<br />\n" +
                "• Connaissance MySQL.<br />\n" +
                "• La maitrise de NodeJS est un plus.<br />\n" +
                "• Compréhension des API Rest.<br />\n" +
                "• Github recommandé.<br />\n" +
                "• Anglais écrit et oral courant.<br />\n" +
                "<br />\n" +
                "<b>Niveau(x) d’études :</b> Fin d’études\n" +
                "<p>\n" +
                "    <strong>Entreprise</strong><br />\n" +
                "                        <a href=\"/entreprises/merchandising-io\">Merchandising.io</a>      </p>\n" +
                "<br />\n" +
                "\n" +
                "\n" +
                "<div class=\"field-secteur-offreemploi\">\n" +
                "\n" +
                "      <span>Secteur activité:&nbsp;</span>\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "           Edition de logiciels\n" +
                "      \n" +
                "</div>\n" +
                "\n" +
                "<div class=\"field-fonction-offreemploi\">\n" +
                "\n" +
                "      <span>Fonction:&nbsp;</span>\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          Développeur(se) full-stack H/F\n" +
                "      \n" +
                "</div>\n" +
                "\n" +
                "<div class=\"field-exp-offreemploi\">\n" +
                "\n" +
                "      <span>Expériences:&nbsp;</span>\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "           Entre 1 an et 3 ans\n" +
                "      \n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<div class=\"field-typecontrat-offreemploi\">\n" +
                "\n" +
                "      <span>Type de contrat:&nbsp;</span>\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "           CDI\n" +
                "      \n" +
                "</div>\n" +
                "\n" +
                "<div class=\"field-salaire-offreemploi\">\n" +
                "\n" +
                "      <span>Salaire:&nbsp;</span>\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          Selon profil\n" +
                "      \n" +
                "</div>\n" +
                "\n" +
                "<div class=\"field-ville-offreemploi\">\n" +
                "\n" +
                "      <span>Ville:&nbsp;</span>\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          Lille\n" +
                "      \n" +
                "</div>\n" +
                "\n" +
                "<div class=\"field-region-offreemploi\">\n" +
                "\n" +
                "      <span>Région:&nbsp;</span>\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          Hauts de France\n" +
                "      \n" +
                "</div>\n" +
                "\n" +
                "<div class=\"field-pays-offreemploi\">\n" +
                "\n" +
                "      <span>Pays:&nbsp;</span>\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          France\n" +
                "      \n" +
                "</div>\n" +
                "\n" +
                "<div class=\"field-contacts-offreemploi\">\n" +
                "\n" +
                "  \n" +
                "    \n" +
                "          \n" +
                "          <a href=\"https://apply.talentdetection.com/Merchandising_IO/recherche_developpeur_full_stack_h_f__10298-zouYNh_adv\">Pour postulez cliquez sur ce lien !</a>\n" +
                "      \n" +
                "</div>\n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "                          </div>\n" +
                "\n" +
                "          \n" +
                "                      <div id=\"rightColumn\" class=\"column\">\n" +
                "              \n" +
                "<div id=\"summary\">  <ul class=\"summary offre_emploi\">  <li class=\"397 first\"><h2><a href=\"/euratechjob/offres\">EuraTech&#039;Job</a></h2></li>\n" +
                "  <li class=\"838 last\"><h2><a href=\"/euratechjob/formations\">Les formations</a></h2></li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div id=\"Start by Euratechnologies\" class=\"contentBloc\"><a href=\"http://etds16.euratechnologies.com/\">\n" +
                "<img alt=\"\" src=\"http://blog.euratechnologies.com/wp-content/uploads/2016/06/ETDS-BANDEAU.png\" style=\"height:103px; width:305px\" /></a>\n" +
                "</div>\n" +
                "<p>\n" +
                "\t<img alt=\"\" src=\"/sites/default/files/images/bloc-pole-ubiquitaire.jpg\" style=\"width: 305px; height: 103px; \" /><br />\n" +
                "\t<a href=\"http://www.pole-ubiquitaire.fr/\"></a></p>\n" +
                "\n" +
                "<div id=\"job\" class=\"contentBloc\">\n" +
                "<a href=\"/euratechjob/offres\"><img src=\"/sites/all/themes/euratechnologies_theme/img/content_rightcolumn_ad.gif\" /></a>\n" +
                "</div>            </div>\n" +
                "          \n" +
                "                  </div>\n" +
                "        <div id=\"footer\">\n" +
                "                      <div id=\"socialNav\" class=\"nav\">\n" +
                "              <div class=\"navLeft\">\n" +
                "  <!--menu-->  <ul>\n" +
                "    <li class=\"first\"><a href=\"http://www.facebook.com/EuraTechnologies\" title=\"\"><img src=\"/sites/all/themes/euratechnologies_theme/img/footer_liens_facebook.gif\" /></a></li>\n" +
                "    <li><a href=\"http://twitter.com/euratechnologie\" title=\"\"><img src=\"/sites/all/themes/euratechnologies_theme/img/footer_liens_twitter.gif\" /></a></li>\n" +
                "    <li class=\"last\"><a href=\"http://euratech.tv/\" title=\"\"><img src=\"/sites/all/themes/euratechnologies_theme/img/footer_liens_euratechtv.gif\" /></a></li>\n" +
                "\n" +
                "  </ul>\n" +
                "  </div>\n" +
                "<div class=\"navRight\">\n" +
                "  <!--menu-->  <ul>\n" +
                "    <li class=\"first\"><a href=\"/mentions-legales\" title=\"Mentions légales\">Mentions légales</a></li>\n" +
                "    <li class=\"last\"><a href=\"/contact\" title=\"Contacts\">Contacts</a></li>\n" +
                "\n" +
                "  </ul>\n" +
                "  </div>\n" +
                "            </div>\n" +
                "          \n" +
                "                      <div id=\"bottomNav\" class=\"nav\">\n" +
                "              \n" +
                "<ul>\n" +
                "  <li><h2><a href=\"/node/343\">Euratechnologies</a></h2></li>\n" +
                "  <li><a href=\"/euratechnologies-developpement\">S.P.L EuraTechnologies </a></li>\n" +
                "  <li><a href=\"/content/financeurs-partenaires\">Financeurs &amp; Partenaires</a></li>\n" +
                "</ul><ul>\n" +
                "  <li><h2><a href=\"/services\">Services</a></h2></li>\n" +
                "  <li><a href=\"/services/creer\">Créer</a></li>\n" +
                "  <li><a href=\"/services/business-accelerateur\">Business Accelerateur</a></li>\n" +
                "  <li><a href=\"/services/hotel-entreprises\">Hôtel d&#039;entreprises</a></li>\n" +
                "  <li><a href=\"/content/clustercn-cs\">ClusterCN&amp;CS</a></li>\n" +
                "  <li><a href=\"/services/technique-technologique\">Technique &amp; Technologique</a></li>\n" +
                "  <li><a href=\"/services/formations\">Formations</a></li>\n" +
                "  <li><a href=\"/services/recherche-developpement\">Recherche &amp; Développement</a></li>\n" +
                "  <li><a href=\"/content/visioconference\">Visioconférence </a></li>\n" +
                "  <li><a href=\"/content/bureau-espace-coworking-paris\">Bureau - Espace de coworking  Paris</a></li>\n" +
                "</ul><ul>\n" +
                "  <li><h2><a href=\"/entreprises\">Les entreprises</a></h2></li>\n" +
                "  <li><a href=\"/entreprises/metiers\">Les métiers</a></li>\n" +
                "  <li><a href=\"/entreprises/annuaire\">Annuaire des entreprises</a></li>\n" +
                "</ul><ul>\n" +
                "  <li><h2><a href=\"/international\">International</a></h2></li>\n" +
                "  <li><a href=\"/node/388\">Bureaux &amp; Partenariats</a></li>\n" +
                "  <li><a href=\"/international/missions-etranger\">Missions à l&#039;étranger</a></li>\n" +
                "  <li><a href=\"/international/accueil-international\">Accueil international</a></li>\n" +
                "</ul><ul>\n" +
                "  <li><h2><a href=\"/euratechjob\">Euratech&#039;Job</a></h2></li>\n" +
                "  <li><a href=\"/euratechjob/offres\">EuraTech&#039;Job</a></li>\n" +
                "  <li><a href=\"/euratechjob/formations\">Les formations</a></li>\n" +
                "</ul><ul>\n" +
                "  <li><h2><a href=\"/evenement\">Events</a></h2></li>\n" +
                "  <li><a href=\"/evenement/salles\">Organiser un événement </a></li>\n" +
                "  <li><a href=\"/agenda\">L&#039;agenda</a></li>\n" +
                "  <li><a href=\"/content/demande-devis\">Demande de devis</a></li>\n" +
                "</ul>            </div>\n" +
                "                  </div>\n" +
                "        <script type=\"text/javascript\">\n" +
                "<!--//--><![CDATA[//><!--\n" +
                "var _gaq = _gaq || [];_gaq.push([\"_setAccount\", \"UA-5481226-6\"]);_gaq.push([\"_trackPageview\"]);(function() {var ga = document.createElement(\"script\");ga.type = \"text/javascript\";ga.async = true;ga.src = (\"https:\" == document.location.protocol ? \"https://ssl\" : \"http://www\") + \".google-analytics.com/ga.js\";var s = document.getElementsByTagName(\"script\")[0];s.parentNode.insertBefore(ga, s);})();\n" +
                "//--><!]]>\n" +
                "</script>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n");
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
