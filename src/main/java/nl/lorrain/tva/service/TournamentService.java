package nl.lorrain.tva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.lorrain.tva.entity.Categorie;
import nl.lorrain.tva.entity.Tournament;
import nl.lorrain.tva.repository.CategorieRepository;
import nl.lorrain.tva.repository.TournamentRepository;
import nl.lorrain.tva.type.AgeCategorieType;
import nl.lorrain.tva.type.MatchType;
import nl.lorrain.tva.type.StrengthCategorieType;

@Service
public class TournamentService {
	
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Autowired
	CategorieRepository categorieRepository;

	public List<Tournament> findAll() {
		List<Tournament> tournaments = tournamentRepository.findAll();
		return tournaments;
	}

	public Tournament findOneById(int id) {
		return tournamentRepository.findOneById(id);
	}
	
	public void addCategorie(AgeCategorieType ageCategorieType, MatchType matchType, StrengthCategorieType strengthCategorieType, Tournament tournament) {
		Categorie categorie = new Categorie();
		categorie.setAgeCategorieType(ageCategorieType);
		categorie.setMatchType(matchType);
		categorie.setStrengthCategorieType(strengthCategorieType);
		categorie.setTournament(tournament);
		categorieRepository.save(categorie);
	}
	
	public void removeCategorie(int id) {
		categorieRepository.delete(id);
	}
}
