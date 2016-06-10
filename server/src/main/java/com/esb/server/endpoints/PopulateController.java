package com.esb.server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jersey.repackaged.com.google.common.collect.Lists;

import com.esb.server.entities.conversation.Conversation;
import com.esb.server.entities.exercices.Exercice;
import com.esb.server.entities.exercices.Question;
import com.esb.server.entities.management.Classe;
import com.esb.server.entities.management.Module;
import com.esb.server.entities.management.ModuleTemplate;
import com.esb.server.entities.management.User;
import com.esb.server.entities.management.User.EUserRole;
import com.esb.server.helpers.DAOHelper;

@Path("populate")
public class PopulateController {

	@GET
	@Path("generate")
	public Response runTests() {
		createProfs();
		createStudents();
		createModuleTemplates();
		assignModulesToStudents();
		return Response.status(201).build();
	}

	private void createProfs() {
		User user = new User();
		user.firstName = "Amina";
		user.lastName = "Burana";
		user.role = EUserRole.PROF;
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Maurice";
		user.lastName = "Platon";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Antoine";
		user.lastName = "Polochon";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Dolores";
		user.lastName = "Ombrage";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Eric";
		user.lastName = "Ramzy";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Gladys";
		user.lastName = "Scottcow";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Paul";
		user.lastName = "Auchon";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Albert";
		user.lastName = "Boom";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Talbin";
		user.lastName = "Bourse";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Marie";
		user.lastName = "Lallemand";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Camille";
		user.lastName = "Lescaut";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Stephanie";
		user.lastName = "Thales";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.PROF;
		user.firstName = "Justin";
		user.lastName = "Bridou";
		DAOHelper.userDAO.save(user);

	}

	private void createStudents() {
		User user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Thierry";
		user.lastName = "Boulard";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Dimitri";
		user.lastName = "Nitchinsky";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Bill";
		user.lastName = "Boudini";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Antoine";
		user.lastName = "Tessier";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Sandrine";
		user.lastName = "Bourgueil";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Thomas";
		user.lastName = "Brochand";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Nicolas";
		user.lastName = "Lebrec";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Romain";
		user.lastName = "Treille";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Laetitia";
		user.lastName = "Malinina";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Ludivine";
		user.lastName = "Enfant";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Jennifer";
		user.lastName = "Lopez";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Sandra";
		user.lastName = "Sanchocola";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Vanessa";
		user.lastName = "Kouglof";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Laetitia";
		user.lastName = "Bourguignon";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Philip";
		user.lastName = "Martinez";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Vincent";
		user.lastName = "Larbier";
		DAOHelper.userDAO.save(user);

		user = new User();
		user.role = EUserRole.STUDENT;
		user.firstName = "Wilfrid";
		user.lastName = "Akafe";
		DAOHelper.userDAO.save(user);

	}

	private void createModuleTemplates() {
		ModuleTemplate template = new ModuleTemplate();
		template.name = "Français";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Burana").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Philosophie";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Platon").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Histoire";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Polochon").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Espagnol";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Ombrage").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Sport";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Ramzy").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Anglais";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Scottcow").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Geographie";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Auchon").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Physiques-Chimie";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Boom").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Economie";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Bourse").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Allemand";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Lallemand").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Geographie";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Lescaut").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Mathematiques";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Thales").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
		template = new ModuleTemplate();
		template.name = "Comptabilité";
		template.supervisors.add(DAOHelper.userDAO.createQuery()
				.filter("lastName =", "Bridou").get());
		DAOHelper.moduleTemplateDAO.save(template);
		
	}

	private void assignModulesToStudents() {
		
	}
}
