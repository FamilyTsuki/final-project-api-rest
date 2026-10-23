package com.formation.apirestdemo.mapper;

import com.formation.apirestdemo.dto.ProduitRequest;
import com.formation.apirestdemo.dto.ProduitResponse;
import com.formation.apirestdemo.model.Produit;

public class EmpruntMapper {

    public static Emprunt versEntite(ProduitRequest request) {
        Emprunt emprunt = new Emprunt();
        emprunt.setLivreId(request.getNom());
        emprunt.setEmprunteur(request.getDescription());
        emprunt.setEmailEmprunteur(request.getPrix());
        return emprunt;
    }

    public static EmpruntResponse versResponse(Emprunt emprunt) {
        return new EmpruntResponse(
            emprunt.getId(),
            emprunt.getLivreId(),
            emprunt.getEmprunteur(),
            emprunt.getDateEmprunt(),
            emprunt.getDateRetourPrevue(),
            emprunt.getDateRetourEffective(),
            emprunt.getRendu(),
            emprunt.getEnRetard()
        );
    }
}
