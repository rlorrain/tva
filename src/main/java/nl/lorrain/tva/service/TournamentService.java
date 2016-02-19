package nl.lorrain.tva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.lorrain.tva.entity.Tournament;
import nl.lorrain.tva.repository.TournamentRepository;

@Service
public class TournamentService {
	
	@Autowired
	TournamentRepository tournamentRepository;

	public List<Tournament> findAll() {
		List<Tournament> tournaments = tournamentRepository.findAll();
		return tournaments;
	}

	public Tournament findOneById(int id) {
		return tournamentRepository.findOneById(id);
	}
}
