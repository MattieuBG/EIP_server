package jacksontest.client;

import java.time.LocalDateTime;

import com.esb.client.ESBClient;
import com.esb.sharedlibrary.entities.Classe;
import com.esb.sharedlibrary.entities.PlanningSession;
import com.esb.sharedlibrary.entities.User;
import com.esb.sharedlibrary.entities.User.RoleEnum;

public class JacksonClientPost {
	public static void main(String[] args) {

		PlanningSession planning = new PlanningSession();
		Classe classe = new Classe();
		User prof = new User();

		prof.setEmail("gustave.latouche@prof.com");
		prof.setLogin("latouc_g");
		prof.setRole(RoleEnum.PROF);
		prof.setPassword("password");
		prof.setFirstName("Gustave");
		prof.setLastName("Latouche");
		ESBClient.sendObject("users", prof);

		classe.setSupervisor(prof);
		classe.setTitle("Francais");
		ESBClient.sendObject("classes", classe);

		planning.setClasse(classe);
		planning.setProf(prof);
		planning.setDebut(LocalDateTime.now());
		planning.setFin(LocalDateTime.now());
		planning.setName("session de test");
		planning.setSalle("SM 42");
		ESBClient.sendObject("plannings", planning);
	}
}
