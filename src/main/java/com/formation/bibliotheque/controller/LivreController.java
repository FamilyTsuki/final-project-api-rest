package com.formation.bibliotheque.controller;

import com.formation.bibliotheque.dto.LivreRequest;
import com.formation.bibliotheque.dto.LivreResponse;
import com.formation.bibliotheque.mapper.LivreMapper;
import com.formation.bibliotheque.model.Livre;
import com.formation.bibliotheque.service.LivreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/livres")
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }
    
    @GetMapping
    public PagedModel<LivreResponse> listerTous(Pageable pageable) {
        return LivreMapper.versPage(livreService.recupererTous(pageable));
    }

    @GetMapping("/{id}")
    public LivreResponse obtenirParId(@PathVariable Long id) {
        return LivreMapper.versResponse(livreService.recupererParId(id)
        .orElseThrow(() -> new RuntimeException("Livre introuvable")));
    }

    @PostMapping
    public ResponseEntity<LivreResponse> creer(@RequestBody LivreRequest request) {
        Livre livre = livreService.creer(LivreMapper.versEntite(request));
        return ResponseEntity
            .status(201)
            .body(LivreMapper
            .versResponse(livre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivreResponse> remplacer(
            @PathVariable Long id, @RequestBody LivreRequest request) {
        Livre livre = livreService.remplacer(id, LivreMapper.versEntite(request));
        return ResponseEntity.ok(LivreMapper.versResponse(livre));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LivreResponse> modifierPartiellement(
            @PathVariable Long id, @RequestBody LivreRequest request) {
        Livre livre = livreService.modifierPartiellement(id, request);
        return ResponseEntity.ok(LivreMapper.versResponse(livre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        livreService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
