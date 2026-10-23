package com.formation.bibliotheque.service;

import com.formation.bibliotheque.model.Emprunt;
import com.formation.bibliotheque.model.Livre;
import com.formation.bibliotheque.repository.EmpruntRepository;
import com.formation.bibliotheque.repository.LivreRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpruntService {

    private final EmpruntRepository empruntRepository;
    private final LivreRepository livreRepository;

    public EmpruntService(EmpruntRepository empruntRepository, LivreRepository livreRepository) {
        this.empruntRepository = empruntRepository;
        this.livreRepository = livreRepository;
    }

    public Page<Emprunt> recupererTous(Pageable pageable) {
        return empruntRepository.findAll(pageable);
    }

    public Optional<Emprunt> recupererParId(Long id) {
        return empruntRepository.findById(id);
    }

    public Emprunt creer(Emprunt emprunt) {
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetourPrevue(LocalDate.now().plusDays(14));
        emprunt.setRendu(false);
        //modifierStock(emprunt.getLivreId(), -1);
        return empruntRepository.save(emprunt);
    }

    public Emprunt retourner(Emprunt emprunt) {
        emprunt.setRendu(true);
        emprunt.setDateRetourEffective(LocalDate.now());
        //modifierStock(emprunt.getLivreId(), 1);
        return empruntRepository.save(emprunt);
    }

    //    private void modifierStock(Long livreId, int number) {
    //        Livre livre = livreRepository.findById(livreId).orElseThrow();
    //        livre.setStock(livre.getStock() + number);
    //        livreRepository.save(livre);
    //    }
}
