package nl.lorrain.tva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.lorrain.tva.service.TournamentService;

@Controller
@RequestMapping("/tournaments")
public class TournamentsController {
	
	@Autowired
	TournamentService tournamentService;

	@RequestMapping
	public String tournaments(Model model) {
		model.addAttribute("tournaments", tournamentService.findAll());
		return "tournaments";
	}
	
	@RequestMapping("/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("tournament", tournamentService.findOneById(id));
		return "redirect:/tournaments.html";
	}	
}
