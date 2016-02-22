package nl.lorrain.tva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matchmaker")
public class MatchmakerController {

	@RequestMapping
	public String matchmaker() {
		return "matchmaker";
	}	
}
