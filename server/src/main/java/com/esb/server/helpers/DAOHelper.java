package com.esb.server.helpers;

import com.esb.server.dao.conversation.ConversationDAO;
import com.esb.server.dao.exercices.ExerciceDAO;
import com.esb.server.dao.exercices.ExerciceSetDAO;
import com.esb.server.dao.exercices.ExerciceSetTemplateDAO;
import com.esb.server.dao.management.AbsenceDAO;
import com.esb.server.dao.management.ClasseDAO;
import com.esb.server.dao.management.DelayDAO;
import com.esb.server.dao.management.ModuleDAO;
import com.esb.server.dao.management.ModuleTemplateDAO;
import com.esb.server.dao.management.PlanningSessionDAO;
import com.esb.server.dao.management.SanctionDAO;
import com.esb.server.dao.management.UserDAO;
import com.esb.server.dao.media.AFileDAO;
import com.esb.server.dao.media.AudioDAO;
import com.esb.server.dao.media.CourseDAO;
import com.esb.server.dao.media.DrawingDAO;
import com.esb.server.dao.media.ImageDAO;
import com.esb.server.dao.media.ImageFTPDAO;
import com.esb.server.dao.media.PdfDAO;
import com.esb.server.dao.media.VideoDAO;

public class DAOHelper {
	public static ConversationDAO conversationDAO = new ConversationDAO();

	public static ExerciceDAO exerciceDAO = new ExerciceDAO();
	public static ExerciceSetDAO exerciceSetDAO = new ExerciceSetDAO();
	public static ExerciceSetTemplateDAO exerciceSetTemplateDAO = new ExerciceSetTemplateDAO();

	public static ClasseDAO classeDAO = new ClasseDAO();
	public static ModuleDAO moduleDAO = new ModuleDAO();
	public static ModuleTemplateDAO moduleTemplateDAO = new ModuleTemplateDAO();
	public static PlanningSessionDAO planningSessionDAO = new PlanningSessionDAO();
	public static UserDAO userDAO = new UserDAO();

	public static AbsenceDAO absenceDAO = new AbsenceDAO();
	public static DelayDAO delayDAO = new DelayDAO();
	public static SanctionDAO sanctionDAO = new SanctionDAO();

	public static AFileDAO aFileDAO = new AFileDAO();
	public static ImageDAO imageDAO = new ImageDAO();
	public static VideoDAO videoDAO = new VideoDAO();
	public static AudioDAO audioDAO = new AudioDAO();
	public static DrawingDAO drawingDAO = new DrawingDAO();
	public static CourseDAO courseDAO = new CourseDAO();

	public static ImageFTPDAO imageFTPDAO = new ImageFTPDAO();
	public static PdfDAO pdfDAO = new PdfDAO();

	private DAOHelper() {

	}
}
