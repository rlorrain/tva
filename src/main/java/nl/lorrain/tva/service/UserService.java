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
		return userRepository.findOne(id);
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
		roles.add(roleRepository.findByName("ROLE_USER"));
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
		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			if (role.getName().equals(roleName)){
				roles.remove(role);
				break;
			}
		}
		user.setRoles(roles);
		userRepository.save(user);
	}
}
