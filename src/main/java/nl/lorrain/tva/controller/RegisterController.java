package nl.lorrain.tva.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.lorrain.tva.entity.User;
import nl.lorrain.tva.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@ModelAttribute("user")
	public User construct() {
		return new User();
	}
	
	@RequestMapping
	public String showRegistrer() {
		return "user-register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result) {
		if(result.hasErrors()) {
			return "user-register";
		}
		userService.save(user);
		return "redirect:/register.html?success=true";
	}
	
	@RequestMapping("/validateUsername")
	@ResponseBody
	public String validateUsername(@RequestParam String username) {
		Boolean available = userService.findOne(username) == null;
		return available.toString();
	}
	
	@RequestMapping("/validateEmail")
	@ResponseBody
	public String validateEmail(@RequestParam String email) {
		Boolean available = userService.findOneByEmail(email) == null;
		return available.toString();
	}
}
