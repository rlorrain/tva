package nl.lorrain.tva.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.lorrain.tva.entity.Categorie;
import nl.lorrain.tva.entity.Role;
import nl.lorrain.tva.entity.Tournament;
import nl.lorrain.tva.entity.User;
import nl.lorrain.tva.repository.CategorieRepository;
import nl.lorrain.tva.repository.RoleRepository;
import nl.lorrain.tva.repository.TournamentRepository;
import nl.lorrain.tva.repository.UserRepository;
import nl.lorrain.tva.type.AgeCategorieType;
import nl.lorrain.tva.type.RoleType;
import nl.lorrain.tva.type.StrengthCategorieType;
import nl.lorrain.tva.type.MatchType;

@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Transactional
	@PostConstruct
	public void init() {
		
		// Create role admin
		Role roleAdmin = new Role();
		roleAdmin.setName(RoleType.ROLE_ADMIN);
		roleRepository.save(roleAdmin);
		
		// Create role superuser
		Role roleSuperUser = new Role();
		roleSuperUser.setName(RoleType.ROLE_SUPERUSER);
		roleRepository.save(roleSuperUser);
		
		// Create role user
		Role roleUser = new Role();
		roleUser.setName(RoleType.ROLE_USER);
		roleRepository.save(roleUser);
		
		// Create admin user
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleSuperUser);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		// Create test user
		User userTest = new User();
		userTest.setEnabled(true);
		userTest.setName("test");
		BCryptPasswordEncoder encoderTest = new BCryptPasswordEncoder();
		userTest.setPassword(encoderTest.encode("test"));
		List<Role> rolesTest = new ArrayList<Role>();
		rolesTest.add(roleUser);
		userTest.setRoles(rolesTest);
		userRepository.save(userTest);
		
		// Create test tournament
		Tournament testTournament = new Tournament();
		Categorie testCategorie = new Categorie();
		testTournament.setName("Open dubbeltoernooi 2016");
		testTournament.setStartDate(new Date());
		testTournament.setEndDate(new Date());
		testTournament.setInfoText("Dit is infotext.");
		List<Categorie> categories = new ArrayList<Categorie>();
		categories.add(testCategorie);
		testTournament.setCategories(categories);
		tournamentRepository.save(testTournament);
		
		// Create test categorie
		testCategorie.setTournament(testTournament);
		testCategorie.setMatchType(MatchType.HE);
		testCategorie.setStrengthCategorieType(StrengthCategorieType.SIX);
		testCategorie.setAgeCategorieType(AgeCategorieType.OPEN);
		categorieRepository.save(testCategorie);
		
		// Create test tournament 2
		Tournament testTournament2 = new Tournament();
		Categorie testCategorie2 = new Categorie();
		testTournament2.setName("Open zomertoernooi 2016");
		testTournament2.setStartDate(new Date());
		testTournament2.setEndDate(new Date());
		testTournament2.setInfoText("Dit is infotext.");
		List<Categorie> categories2 = new ArrayList<Categorie>();
		categories2.add(testCategorie2);
		testTournament2.setCategories(categories2);
		tournamentRepository.save(testTournament2);
				
		// Create test categorie 2
		testCategorie2.setTournament(testTournament2);
		testCategorie2.setMatchType(MatchType.DD);
		testCategorie2.setStrengthCategorieType(StrengthCategorieType.SEVEN);
		testCategorie2.setAgeCategorieType(AgeCategorieType.TWENTYFIVE);
		categorieRepository.save(testCategorie2);
	}
}
