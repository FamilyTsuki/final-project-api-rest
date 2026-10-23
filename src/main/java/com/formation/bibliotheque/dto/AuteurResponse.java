package com.formation.bibliotheque.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;

@JsonPropertyOrder({
        "id",
        "Nom",
        "Prenom",
        "Nationalite",
})
public class AuteurResponse {

    private final Long id;
    private final String nom;
    private final String prenom;
    private final String nationalite;

    public AuteurResponse(
            Long id,
            String nom,
            String prenom,
            String nationalite
    ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNationalite() {
        return nationalite;
    }

    public String getPrenom() {
        return prenom;
    }
}