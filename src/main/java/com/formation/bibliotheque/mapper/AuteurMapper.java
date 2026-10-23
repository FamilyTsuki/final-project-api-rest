package com.formation.apirestdemo.mapper;

import com.formation.bibliotheque.dto.AuteurRequest;
import com.formation.bibliotheque.dto.AuteurResponse;
import com.formation.bibliotheque.model.Auteur;

/**
 * Convertit les objets entre les couches DTO et Entity.
 * Centralise la logique de transformation.
 */
public class AuteurMapper {

    /**
     * Convertit un DTO de requête en entité JPA.
     */
    public static Auteur versEntite(AuteurRequest request) {
        auteur auteur = new Produit();
        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());
        return auteur;
    }

    /**
     * Convertit une entité JPA en DTO de réponse.
     */
    public static AuteurResponse versResponse(Auteur produit) {
        return new AuteurResponse(
                auteur.getId(),
                auteur.getNom(),
                auteur.getPrenom(),
                auteur.getNationalite(),
        );
    }
}