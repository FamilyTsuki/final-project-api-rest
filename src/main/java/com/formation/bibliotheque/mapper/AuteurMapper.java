package com.formation.bibliotheque.mapper;

import com.formation.bibliotheque.dto.AuteurRequest;
import com.formation.bibliotheque.dto.AuteurResponse;
import com.formation.bibliotheque.model.Auteur;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

/**
 * Convertit les objets entre les couches DTO et Entity.
 * Centralise la logique de transformation.
 */
public final class AuteurMapper {

    private AuteurMapper() {}

    /**
     * Convertit un DTO de requête en entité JPA (l'id reste null).
     */
    public static Auteur versEntite(AuteurRequest request) {
        Auteur auteur = new Auteur();
        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());
        return auteur;
    }

    /**
     * Convertit une entité JPA en DTO de réponse.
     */
    public static AuteurResponse versResponse(Auteur auteur) {
        return new AuteurResponse(
                auteur.getId(),
                auteur.getNom(),
                auteur.getPrenom(),
                auteur.getNationalite()
        );
    }

    /**
     * Convertit une liste d'entités en liste de DTOs de réponse.
     */
    public static List<AuteurResponse> versResponses(List<Auteur> auteurs) {
        List<AuteurResponse> reponses = new ArrayList<>();
        for (Auteur auteur : auteurs) {
            reponses.add(versResponse(auteur));
        }
        return reponses;
    }

    /**
     * Convertit une page d'entités en page de DTOs.
     */
    public static PagedModel<AuteurResponse> versPage(Page<Auteur> page) {
        // 1. On convertit le contenu de la page en DTOs
        List<AuteurResponse> contenu = versResponses(page.getContent());
        // 2. On reconstruit une page de DTOs avec les mêmes informations de pagination
        Page<AuteurResponse> pageDto = new PageImpl<>(contenu, page.getPageable(), page.getTotalElements());
        // 3. PagedModel produit un JSON stable : { "content": [...], "page": {...} }
        return new PagedModel<>(pageDto);
    }
}