package com.formation.bibliotheque.controller;

import com.formation.bibliotheque.dto.AuteurRequest;
import com.formation.bibliotheque.dto.AuteurResponse;
import com.formation.bibliotheque.mapper.AuteurMapper;
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
    public AuteurResponse modifier(@PathVariable Long id, @Valid @RequestBody AuteurRequest request) {
        Auteur existant = trouver(id);
        AuteurMapper.mettreAJour(existant, request);
        return AuteurMapper.versResponse(auteurService.modifier(existant));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        Auteur auteur = trouver(id);
        if (auteurService.possedeDesLivres(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Auteur lié à des livres"); // à adapter selon tes RM
        }
        auteurService.supprimer(auteur);
    }

    private Auteur trouver(Long id) {
        return auteurService
                .recupererParId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auteur introuvable"));
    }
}