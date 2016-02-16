package nl.lorrain.tva.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
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
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(int id) {
		User user = userRepository.findOne(id);
		Hibernate.initialize(user.getBlogs());
		Hibernate.initialize(user.getRoles());
		return user;
	}

	public User findOneWithBlogs(int id) {
		User user = findOne(id);
		Hibernate.initialize(user.getRoles());
		List<Blog> blogs = blogRepository.findByUser(user);
		for (Blog blog : blogs) {
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}
	
	public User findOneWithBlogs(String name) {
		User user = userRepository.findByName(name);
		return findOneWithBlogs(user.getId());
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

	public void delete(int id) {
		userRepository.delete(id);		
	}

	public User findOne(String username) {
		return userRepository.findByName(username);
	}

	public User findOneByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void removeRoleFromUser(String roleName, User user) {
		User updateUser = userRepository.findOne(user.getId());
		List<Role> roles = updateUser.getRoles();
		for (Role role : roles) {
			if (role.getName().equals(roleName)){
				roles.remove(role);
				break;
			}
		}
		updateUser.setRoles(roles);
		userRepository.save(updateUser);
		
		Role updateRole = roleRepository.findOneByName(roleName);
		List<User> users = updateRole.getUsers();
		for(User user2 : users) {
			if (user2.getName().equals(roleName)){
				users.remove(user2);
			}
		}
		updateRole.setUsers(users);
		roleRepository.save(updateRole);
	}

	public void addRoleToUser(Role roleToAdd, int userId) {
		User user = userRepository.findOne(userId);
		Role newRole = roleRepository.findOneByName(roleToAdd.getName());
		List<Role> roles = user.getRoles();
		if (roles.contains(newRole)) {
			System.out.println("User already has the role");
		} else {
			roles.add(newRole);
			user.setRoles(roles);
			System.out.println("" + newRole + " added to " + user.getName());
		}
		
		
		Role role = roleRepository.findOneByName(roleToAdd.getName());
		User newUser = userRepository.findOne(userId);
		List<User> users = role.getUsers();
		if (users.contains(newUser)) {
			System.out.println("Role already knows this User");
		} else {
			users.add(newUser);
			role.setUsers(users);
			System.out.println("" + newUser + " added to " + role.getName());
		}
		userRepository.save(user);
		roleRepository.save(role);
	}
}
