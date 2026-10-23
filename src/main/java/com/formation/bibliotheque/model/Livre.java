package com.formation.bibliotheque.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank; 


@Entity 
@Table(name = "livres")
public class Livre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre du livre est obligatoire") 
    private String titre;

    @Column(nullable = false, unique = true)
    private String isbn;
    
    @NotBlank(message = "Le prix du livre est obligatoire") 
    private Double prix;

    @NotBlank(message = "L'année de publication du livre est obligatoire") 
    private Integer anneePublication;

    @NotBlank(message = "La quantité en stock du livre est obligatoire") 
    private Integer stock;

    @NotBlank(message = "L'auteur du livre est obligatoire") 
    private Long auteurId;
    
    public Livre() {
        // Classe vide requise par JPA 
    }

    public Livre(String titre, String isbn, Double prix, Integer anneePublication, Integer stock, Long auteurId) {
        this.titre = titre;
        this.isbn = isbn;
        this.prix = prix;
        this.anneePublication = anneePublication;
        this.stock = stock;
        this.auteurId = auteurId;
    }

    // Getters
    public Long getId(){ return id;}
    public String getTitre() { return titre;}
    public String getIsbn() { return isbn;}
    public Double getPrix() { return prix;}
    public Integer getAnneePublication() { return anneePublication;}
    public Integer getStock() { return stock;}
    public Long getAuteurId() { return auteurId;}

    // Setters
    public void setId(Long id) { this.id = id;}
    public void setTitre(String titre) { this.titre = titre;}
    public void setIsbn(String isbn) { this.isbn = isbn;}
    public void setPrix(Double prix) { this.prix = prix;}
    public void setAnneePublication(Integer anneePublication) { this.anneePublication = anneePublication;}
    public void setStock(Integer stock) { this.stock = stock;}
    public void setAuteurId(Long auteurId) { this.auteurId = auteurId;}

}
