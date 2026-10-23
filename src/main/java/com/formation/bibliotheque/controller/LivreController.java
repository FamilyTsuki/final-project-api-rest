package com.formation.bibliotheque.controller;

import com.formation.bibliotheque.dto.LivreRequest;
import com.formation.bibliotheque.dto.LivreResponse;
import com.formation.bibliotheque.mapper.LivreMapper;
import com.formation.bibliotheque.model.Livre;
import com.formation.bibliotheque.service.LivreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/livres")
public class LivreController {

    private final LivreService livreService;
    private final LivreMapper livreMapper;

    public LivreController(LivreService livreService, LivreMapper livreMapper) {
        this.livreService = livreService;
        this.livreMapper = livreMapper;
    }

    @GetMapping
    public ResponseEntity<PagedModel<LivreResponse>> listerTous(
            @PageableDefault(size = 10, sort = "nom") Pageable pageable) {
        Page<Livre> page = livreService.recupererTous(pageable);
        return ResponseEntity.ok(livreMapper.versPage(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivreResponse> obtenirParId(@PathVariable Long id) {
        return ResponseEntity.ok(livreMapper.versResponse(livreService.recupererParId(id)));
    }

    @PostMapping
    public ResponseEntity<LivreResponse> creer(@RequestBody LivreRequest request) {
        Livre livre = livreService.creer(livreMapper.versEntite(request));
        return ResponseEntity.status(201).body(livreMapper.versResponse(livre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivreResponse> remplacer(
            @PathVariable Long id, @RequestBody LivreRequest request) {
        Livre livre = livreService.remplacer(id, livreMapper.versEntite(request));
        return ResponseEntity.ok(livreMapper.versResponse(livre));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LivreResponse> modifierPartiellement(
            @PathVariable Long id, @RequestBody LivreRequest request) {
        Livre livre = livreService.modifierPartiellement(id, request);
        return ResponseEntity.ok(livreMapper.versResponse(livre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        livreService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
