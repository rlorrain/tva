package nl.lorrain.tva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.lorrain.tva.entity.Role;
import nl.lorrain.tva.entity.User;
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
	
	@RequestMapping("/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.findOne(id));
		return "user-detail";
	}
	
	@RequestMapping(value="/{userId}", method=RequestMethod.POST)
	public String doAddRole(Model model, @ModelAttribute("role") Role role, @PathVariable int userId) {
		userService.addRoleToUser(role, userId);
		return "redirect:/users/" + userId + ".html";
	}
	
	@RequestMapping("/{userId}/{roleName}")
	public String removeRole(Model model, @PathVariable int userId, @PathVariable String roleName) {
		User user = userService.findOne(userId);
		userService.removeRoleFromUser(roleName, user);
		model.addAttribute("user", userService.findOne(userId));
		return "redirect:/users/" + userId + ".html";
	}
	
	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/users.html";
	}
}
