package nl.lorrain.tva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nl.lorrain.tva.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findOneByName(String name);

}
