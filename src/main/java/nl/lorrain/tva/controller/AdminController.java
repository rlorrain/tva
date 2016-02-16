package nl.lorrain.tva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.lorrain.tva.entity.User;
import nl.lorrain.tva.service.UserService;

@Controller
@RequestMapping("/users")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	
	@RequestMapping("/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.findOneWithBlogs(id));
		return "user-detail";
	}
	
	@RequestMapping("/{id}/{roleName}")
	public String removeRole(Model model, @PathVariable int id, @PathVariable String roleName) {
		User user = userService.findOne(id);
		userService.removeRoleFromUser(roleName, user);
		model.addAttribute("user", userService.findOne(id));
		return "user-detail";
	}
	
	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/users.html";
	}
}
