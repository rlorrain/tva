package nl.lorrain.tva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nl.lorrain.tva.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

	User findByEmail(String email);

}
