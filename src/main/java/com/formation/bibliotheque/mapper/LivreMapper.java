package com.formation.bibliotheque.mapper;
import com.formation.bibliotheque.model.Livre;

import com.formation.bibliotheque.dto.LivreRequest;

public class LivreMapper {
    
        /** 
     * Convertit un DTO de requête en entité JPA. 
     */ 
    public static Livre versEntite(LivreRequest request) { 
        Livre livre = new Livre(); 
        livre.setNom(request.getNom()); 
        livre.setDescription(request.getDescription());
        livre.setPrix(request.getPrix()); 
        livre.setStock(request.getStock()); 
        return livre;
    } 
 
    /** 
     * Convertit une entité JPA en DTO de réponse. 
     */ 
    public static LivreResponse versResponse(Livre livre) { 
        return new LivreResponse( 
                livre.getId(), 
                livre.getNom(), 
                livre.getDescription(), 
                livre.getPrix(), 
                livre.getStock() 
        ); 
}
