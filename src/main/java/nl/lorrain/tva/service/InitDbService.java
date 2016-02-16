package nl.lorrain.tva.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.lorrain.tva.entity.Blog;
import nl.lorrain.tva.entity.Item;
import nl.lorrain.tva.entity.Role;
import nl.lorrain.tva.entity.User;
import nl.lorrain.tva.repository.BlogRepository;
import nl.lorrain.tva.repository.ItemRepository;
import nl.lorrain.tva.repository.RoleRepository;
import nl.lorrain.tva.repository.UserRepository;
import nl.lorrain.tva.type.RoleType;

@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Transactional
	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName(RoleType.ROLE_USER);
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName(RoleType.ROLE_ADMIN);
		roleRepository.save(roleAdmin);
		
		// Create admin user
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
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
		
		Blog blogJavavids = new Blog();
		blogJavavids.setName("JavaVids");
		blogJavavids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		blogJavavids.setUser(userTest);
		blogRepository.save(blogJavavids);
		
		Item item1 = new Item();
		item1.setBlog(blogJavavids);
		item1.setTitle("first");
		item1.setLink("http://www.javavids.com");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setBlog(blogJavavids);
		item2.setTitle("second");
		item2.setLink("http://www.javavids.com");
		item2.setPublishedDate(new Date());
		itemRepository.save(item2);
	}
}
