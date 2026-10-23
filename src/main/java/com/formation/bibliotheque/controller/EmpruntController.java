package com.formation.bibliotheque.controller;

import com.formation.bibliotheque.dto.EmpruntRequest;
import com.formation.bibliotheque.dto.EmpruntResponse;
import com.formation.bibliotheque.mapper.EmpruntMapper;
import com.formation.bibliotheque.model.Emprunt;
import com.formation.bibliotheque.service.EmpruntService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/emprunts")
public class EmpruntController {

    private final EmpruntService empruntService;

    public EmpruntController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    @GetMapping
    public PagedModel<EmpruntResponse> listerTous(Pageable pageable) {
        return EmpruntMapper.versPage(empruntService.recupererTous(pageable));
    }

    @GetMapping("/en-cours")
    public PagedModel<EmpruntResponse> listerEnCours(Pageable pageable) {
        return EmpruntMapper.versPage(empruntService.recupererEnCours(pageable));
    }

    @GetMapping("/en-retard")
    public PagedModel<EmpruntResponse> listerEnRetard(Pageable pageable) {
        return EmpruntMapper.versPage(empruntService.recupererEnRetard(pageable));
    }

    @GetMapping("/{id}")
    public EmpruntResponse obtenirParId(@PathVariable Long id) {
        return EmpruntMapper.versResponse(trouver(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpruntResponse creer(@Valid @RequestBody EmpruntRequest request) {
        if (!empruntService.livreExiste(request.getLivreId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livre introuvable");
        }
        if (!empruntService.livreDisponible(request.getLivreId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Livre indisponible"); // RM4
        }
        Emprunt cree = empruntService.creer(EmpruntMapper.versEntite(request));
        return EmpruntMapper.versResponse(cree);
    }

    @PatchMapping("/{id}/retour")
    public EmpruntResponse retourner(@PathVariable Long id) {
        Emprunt emprunt = trouver(id);
        if (emprunt.getRendu()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Emprunt déjà rendu"); // RM6
        }
        return EmpruntMapper.versResponse(empruntService.retourner(emprunt));
    }

    private Emprunt trouver(Long id) {
        return empruntService
            .recupererParId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Emprunt introuvable"));
    }
}
