package nl.lorrain.tva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import nl.lorrain.tva.entity.Blog;
import nl.lorrain.tva.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
	List<Blog> findByUser(User user);

}
