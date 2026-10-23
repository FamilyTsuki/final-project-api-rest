package com.formation.bibliotheque.controller;

import com.formation.bibliotheque.dto.AuteurRequest;
import com.formation.bibliotheque.dto.AuteurResponse;
import com.formation.bibliotheque.dto.LivreResponse;
import com.formation.bibliotheque.mapper.AuteurMapper;
import com.formation.bibliotheque.mapper.LivreMapper;
import com.formation.bibliotheque.model.Auteur;
import com.formation.bibliotheque.service.AuteurService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/auteurs")
public class AuteurController {

    private final AuteurService auteurService;

    public AuteurController(AuteurService auteurService) {
        this.auteurService = auteurService;
    }

    @GetMapping
    public PagedModel<AuteurResponse> listerTous(Pageable pageable) {
        return AuteurMapper.versPage(auteurService.recupererTous(pageable));
    }

    @GetMapping("/{id}")
    public AuteurResponse obtenirParId(@PathVariable Long id) {
        return AuteurMapper.versResponse(trouver(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuteurResponse creer(@Valid @RequestBody AuteurRequest request) {
        Auteur cree = auteurService.creer(AuteurMapper.versEntite(request));
        return AuteurMapper.versResponse(cree);
    }

    @PutMapping("/{id}")
    public AuteurResponse mettreAJour(@PathVariable Long id, @Valid @RequestBody AuteurRequest request) {
        Auteur existant = trouver(id);
        existant.setNom(request.getNom());
        existant.setPrenom(request.getPrenom());
        existant.setNationalite(request.getNationalite());
        return AuteurMapper.versResponse(auteurService.mettreAJour(existant));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        Auteur auteur = trouver(id);
        if (auteurService.possedeDesLivres(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Auteur lié à des livres"); // RM3
        }
        auteurService.supprimer(auteur);
    }

    @GetMapping("/{id}/livres")
    public PagedModel<LivreResponse> listerLivres(@PathVariable Long id, Pageable pageable) {
        trouver(id);
        return LivreMapper.versPage(auteurService.recupererLivres(id, pageable));
    }

    private Auteur trouver(Long id) {
        return auteurService
                .recupererParId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auteur introuvable"));
    }
}