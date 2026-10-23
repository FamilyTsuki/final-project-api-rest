package com.formation.bibliotheque.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.formation.bibliotheque.model.Livre;
import com.formation.bibliotheque.repository.LivreRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; 
import java.util.Optional; 

@Service 
public class LivreService { 
  
    private final LivreRepository livreRepository; 
    private final AuteurRepository auteurRepository; 
  
    public LivreService(LivreRepository livreRepository, AuteurRepository auteurRepository) { 
        this.livreRepository = livreRepository; 
        this.auteurRepository = auteurRepository; 
    } 

    Page<Livre> recupererTous(Pageable pageable) {
        return livreRepository.findAll(pageable);
    }
    
    Optional<Livre> recupererParId(Long id) {
        return livreRepository.findById(id);
    }

    boolean existe(Long id) {
        return livreRepository.existsById(id);
    }

    boolean auteurExiste(Long auteurId) {
        return auteurRepository.existsById(auteurId);
    }

    boolean isbnExiste(String isbn) {
        return livreRepository.existsByIsbn(isbn);
    }

    boolean isbnUtiliseParUnAutreLivre(String isbn, Long idLivre) {
        return livreRepository.existsByIsbnAndIdNot(isbn, idLivre);
    }

    Livre creer(Livre livre) {
        return livreRepository.save(livre);
    }

    Livre mettreAJour(Livre livreExistant, Livre nouvellesValeurs) {
        // Implémenter la mise à jour du livre
    }

    void supprimer(Long id) {
        livreRepository.deleteById(id);
    }

    Page<Livre> rechercherParTitre(String titre, Pageable pageable) {
        return livreRepository.findByTitreContaining(titre, pageable);
    }

    Page<Livre> recupererDisponibles(Pageable pageable) {
        return livreRepository.findByStockGreaterThan(0, pageable);
    }

    Livre ajouterStock(Livre livre, Integer quantite) {
        livre.setStock(livre.getStock() + quantite);
        return livreRepository.save(livre);
    }
    // Écrivez ici les méthodes du tableau 
} 
