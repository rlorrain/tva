package nl.lorrain.tva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class EventsController {

	@RequestMapping
	public String events() {
		return "events";
	}	
}
