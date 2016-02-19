package nl.lorrain.tva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nl.lorrain.tva.entity.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

	Tournament findOneById(int id);

}
