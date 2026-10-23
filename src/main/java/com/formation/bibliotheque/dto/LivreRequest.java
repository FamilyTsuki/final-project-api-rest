package com.formation.bibliotheque.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class LivreRequest {
     
    @NotBlank(message = "Le titre est obligatoire") 
    private String titre;

    @Column(nullable = false, unique = true)
    private String isbn; 
 
    @NotBlank(message = "Le prix du livre est obligatoire") 
    private Double prix; 
 
    private Integer anneePublication;

    @NotBlank(message = "La quantité en stock du livre est obligatoire") 
    private Integer stock;

    @NotBlank(message = "L'auteur du livre est obligatoire") 
    private Long auteurId;
 
    // Getters et setters 
 
    public String getTitre() { return titre; } 
    public void setTitre(String titre) { this.titre = titre; } 
 
    public String getIsbn() { return isbn; } 
    public void setIsbn(String isbn) { this.isbn = isbn; } 
 
    public Double getPrix() { return prix; } 
    public void setPrix(Double prix) { this.prix = prix; }

    public Integer getAnneePublication() { return anneePublication;}
    public void setAnneePublication(Integer anneePublication) { this.anneePublication = anneePublication; }


    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
 
    public Long getAuteurId() { return auteurId; }
    public void setAuteurId(Long auteurId) { this.auteurId = auteurId; }
}
