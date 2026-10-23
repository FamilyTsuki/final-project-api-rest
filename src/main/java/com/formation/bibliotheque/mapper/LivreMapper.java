package com.formation.bibliotheque.mapper;
import com.formation.bibliotheque.model.Livre;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

import com.formation.bibliotheque.dto.LivreRequest;
import com.formation.bibliotheque.dto.LivreResponse;

public class LivreMapper {
    
        /** 
     * Convertit un DTO de requête en entité JPA. 
     */ 
    public static Livre versEntite(LivreRequest request) { 
        Livre livre = new Livre(); 
        livre.setTitre(request.getTitre()); 
        livre.setIsbn(request.getIsbn());
        livre.setPrix(request.getPrix());
        livre.setAnneePublication(request.getAnneePublication());
        livre.setStock(request.getStock());
        livre.setAuteurId(request.getAuteurId());
        return livre;
    } 
 

    public static LivreResponse versResponse(Livre livre) { 
        return new LivreResponse(  
                livre.getId(),
                livre.getTitre(), 
                livre.getIsbn(), 
                livre.getPrix(),
                livre.getAnneePublication(), 
                livre.getStock(),
                livre.getAuteurId()
        ); }



    public static PagedModel<LivreResponse> versPage(Page<Livre> page) {
        Page<LivreResponse> pageDeResponses = page.map(livre -> versResponse(livre));

        return new PagedModel<>(pageDeResponses);
    }
}
