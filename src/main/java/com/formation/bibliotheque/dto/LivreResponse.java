package com.formation.bibliotheque.dto;

public class LivreResponse {

    private Long id; 
    private String titre; 
    private String isbn; 
    private Double prix; 
    private Integer stock; 
    private Integer anneePublication;
    private Long auteurId;

    public LivreResponse() {
        // Constructeur vide requis par Jackson
    }
    public LivreResponse(Long id, String titre, String isbn, Double prix, Integer anneePublication, Integer stock, Long auteurId) { 
        this.id = id; 
        this.titre = titre; 
        this.isbn = isbn;
        this.prix = prix; 
        this.anneePublication = anneePublication;
        this.stock = stock;
        this.auteurId = auteurId;

    }
    public Long getId() { return id; } 
    public String getTitre() { return titre; } 
    public String getIsbn() { return isbn; } 
    public Double getPrix() { return prix; } 
    public Integer getStock() { return stock; } 
    public Integer getAnneePublication() { return anneePublication; }
    public Long getAuteurId() { return auteurId; }
}
