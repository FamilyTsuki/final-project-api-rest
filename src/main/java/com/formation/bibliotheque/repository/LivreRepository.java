package com.formation.bibliotheque.repository;
import org.springframework.data.domain.Page;
import com.formation.bibliotheque.model.Livre;
import org.springframework.data.domain.Pageable; 
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LivreRepository extends JpaRepository<Livre, Long> { 
  
    Optional<Livre> findByIsbn(String isbn);                                    // le livre qui a cet ISBN un livre 
  
    Page<Livre> findByAuteurId(Long auteurId, Pageable pageable);               // les livres d'un auteur 
  
    Page<Livre> findByTitreContainingIgnoreCase(String titre, Pageable pageable); // titre contenant le texte 
  
    Page<Livre> findByStockGreaterThan(Integer stock, Pageable pageable);       // stock strictement supérieur

    Page<Livre> findByStockLessThanEqual(Integer stock, Pageable pageable);      // stock inférieur ou égal

    Page<Livre> findByStockEquals(Integer stock, Pageable pageable);              // stock égal à 0

    boolean existsByIsbnAndIdNot(String isbn, Long idLivre); // vérifier si un autre livre a le même ISBN

    boolean existsByIsbn(String isbn); // vérifier si un livre avec cet ISBN existe
} 
