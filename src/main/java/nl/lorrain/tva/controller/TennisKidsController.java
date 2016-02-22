package nl.lorrain.tva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tennis-kids")
public class TennisKidsController {

	@RequestMapping
	public String events() {
		return "tennis-kids";
	}	
}
