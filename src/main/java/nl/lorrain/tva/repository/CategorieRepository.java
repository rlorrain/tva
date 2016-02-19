package nl.lorrain.tva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.lorrain.tva.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

}
