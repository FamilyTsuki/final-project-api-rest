package com.formation.bibliotheque.controller;

import com.formation.bibliotheque.dto.AuteurRequest;
import com.formation.bibliotheque.dto.AuteurResponse;
import com.formation.bibliotheque.mapper.AuteurMapper;
import com.formation.bibliotheque.model.Auteur;
import com.formation.bibliotheque.service.AuteurService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auteurs")
public class AuteurController {

    private final AuteurService auteurService;

    public AuteurController(AuteurService auteurService) {
        this.auteurService = auteurService;
    }

    @GetMapping
    public ResponseEntity<PagedModel<AuteurResponse>> listerTous(
            @PageableDefault(size = 10, sort = "nom") Pageable pageable) {
        Page<Auteur> page = auteurService.recupererTous(pageable);
        return ResponseEntity.ok(AuteurMapper.versPage(page));                       // 200
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuteurResponse> obtenirParId(@PathVariable Long id) {
        Optional<Auteur> resultat = auteurService.recupererParId(id);
        if (resultat.isEmpty()) {
            return ResponseEntity.notFound().build();                                // 404
        }
        return ResponseEntity.ok(AuteurMapper.versResponse(resultat.get()));         // 200
    }
    @PostMapping
    public ResponseEntity<AuteurResponse> creer(@Valid @RequestBody AuteurRequest request) {
        Auteur cree = auteurService.creer(AuteurMapper.versEntite(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuteurMapper.versResponse(cree));                              // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuteurResponse> mettreAJour(
            @PathVariable Long id, @Valid @RequestBody AuteurRequest request) {
        Optional<Auteur> resultat = auteurService.recupererParId(id);
        if (resultat.isEmpty()) {
            return ResponseEntity.notFound().build();                                // 404
        }
        Auteur misAJour = auteurService.mettreAJour(resultat.get(), AuteurMapper.versEntite(request));
        return ResponseEntity.ok(AuteurMapper.versResponse(misAJour));               // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!auteurService.existe(id)) {
            return ResponseEntity.notFound().build();                                // 404
        }
        if (auteurService.aDesLivres(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();               // 409 (RM3)
        }
        auteurService.supprimer(id);
        return ResponseEntity.noContent().build();                                   // 204
    }
}