package nl.lorrain.tva.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleService roleService;
	
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		return users;
	}

	public User findOneById(int id) {
		User user = userRepository.findOne(id);
		return user;
	}
	
	public User findOneByName(String name) {
		User user = userRepository.findByName(name);
		return user;
	}

	public User findOneByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Deprecated
	public User findOneWithBlogItems(int id) {
		User user = userRepository.findOne(id);
		List<Blog> blogs = blogRepository.findByUser(user);
		for (Blog blog : blogs) {
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findOneByName("ROLE_USER"));
		user.setRoles(roles);
		userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public void removeRoleFromUser(String roleName, String userName) {
		Role role = roleRepository.findOneByName(roleName);
		User user = userRepository.findByName(userName);
		List<Role> roles = user.getRoles();
		if (roles.contains(role)) {
			roles.remove(role);
			user.setRoles(roles);
			userRepository.save(user);
			roleService.addUserToRole(userName, roleName);
		} else {
			System.out.println("User " + user.getName() + " does not have role " + role.getName());
		}
	}

	public void addRoleToUser(String roleName, String userName) {
		Role role = roleRepository.findOneByName(roleName);
		User user = userRepository.findByName(userName);
		List<Role> roles = user.getRoles();
		if (roles.contains(role)) {
			System.out.println("User " + user.getName() + " already has role " + role.getName());
		} else {
			roles.add(role);
			user.setRoles(roles);
			userRepository.save(user);
			roleService.addUserToRole(userName, roleName);
		}
	}
}
