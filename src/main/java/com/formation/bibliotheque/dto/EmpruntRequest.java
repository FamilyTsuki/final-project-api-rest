package com.formation.bibliotheque.dto;

public class EmpruntRequest {

    private Long livreId;

    private String emprunteur;
    private String emailEmprunteur;

    public EmpruntRequest() {}

    public Long getLivreId() {
        return livreId;
    }

    public void setLivreId(Long livreId) {
        this.livreId = livreId;
    }

    public String getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(String emprunteur) {
        this.emprunteur = emprunteur;
    }

    public String getEmailEmprunteur() {
        return emailEmprunteur;
    }

    public void setEmailEmprunteur(String emailEmprunteur) {
        this.emailEmprunteur = emailEmprunteur;
    }
}
