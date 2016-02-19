package nl.lorrain.tva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.lorrain.tva.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
