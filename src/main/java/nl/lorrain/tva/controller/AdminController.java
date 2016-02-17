package nl.lorrain.tva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.lorrain.tva.entity.Role;
import nl.lorrain.tva.service.UserService;

@Controller
@RequestMapping("/users")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public Role constructRole() {
		return new Role();
	}
	
	@RequestMapping
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	
	@RequestMapping("/{userId}")
	public String detail(Model model, @PathVariable int userId) {
		model.addAttribute("user", userService.findOneById(userId));
		return "user-detail";
	}
	
	@RequestMapping(value="/{userId}", method=RequestMethod.POST)
	public String addRole(Model model, @ModelAttribute("role") Role role, @PathVariable int userId) {
		userService.addRoleToUser(role.getName(), userService.findOneById(userId).getName());
		return "redirect:/users/" + userId + ".html";
	}
	
	@RequestMapping("/{userId}/{roleName}")
	public String removeRole(Model model, @PathVariable int userId, @PathVariable String roleName) {
		userService.removeRoleFromUser(roleName, userService.findOneById(userId).getName());
		model.addAttribute("user", userService.findOneById(userId));
		return "redirect:/users/" + userId + ".html";
	}
	
	@RequestMapping("/remove/{userId}")
	public String removeUser(@PathVariable int userId) {
		userService.delete(userService.findOneById(userId));
		return "redirect:/users.html";
	}
}
