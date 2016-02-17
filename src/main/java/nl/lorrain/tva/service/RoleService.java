package nl.lorrain.tva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.lorrain.tva.entity.Role;
import nl.lorrain.tva.entity.User;
import nl.lorrain.tva.repository.RoleRepository;
import nl.lorrain.tva.repository.UserRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	public Role findRoleByName(String name) {
		Role role = roleRepository.findOneByName(name);
		return role;
	}
	
	public void addUserToRole(String userName, String roleName) {
		User user = userRepository.findByName(userName);
		Role role = roleRepository.findOneByName(roleName);
		List<User> users = role.getUsers();
		if (users.contains(user)) {
			System.out.println("Role " + role.getName() + " already knows user " + user.getName());
		} else {
			users.add(user);
			role.setUsers(users);
			roleRepository.save(role);
			userService.addRoleToUser(roleName, userName);
		}
	}
	
	public void removeUserFromRole(String userName, String roleName) {
		User user = userRepository.findByName(userName);
		Role role = roleRepository.findOneByName(roleName);
		List<User> users = role.getUsers();
		if (users.contains(user)) {
			users.remove(user);
			role.setUsers(users);
			roleRepository.save(role);
			userService.addRoleToUser(roleName, userName);
		} else {
			System.out.println("User " + user.getName() + " does not exist in role " + role.getName());		
		}
	}
}
