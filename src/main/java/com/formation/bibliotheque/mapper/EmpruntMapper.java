package com.formation.bibliotheque.mapper;

import com.formation.bibliotheque.dto.EmpruntRequest;
import com.formation.bibliotheque.dto.EmpruntResponse;
import com.formation.bibliotheque.model.Emprunt;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

public class EmpruntMapper {

    public static Emprunt versEntite(EmpruntRequest request) {
        Emprunt emprunt = new Emprunt();
        emprunt.setLivreId(request.getLivreId());
        emprunt.setEmprunteur(request.getEmprunteur());
        emprunt.setEmailEmprunteur(request.getEmailEmprunteur());
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
            estEnRetard(emprunt)
        );
    }

    public static PagedModel<EmpruntResponse> versPage(Page<Emprunt> page) {
        Page<EmpruntResponse> pageDeResponses = page.map(emprunt -> versResponse(emprunt));

        return new PagedModel<>(pageDeResponses);
    }

    private static boolean estEnRetard(Emprunt emprunt) {
        if (
            !Boolean.TRUE.equals(emprunt.getRendu()) &&
            emprunt.getDateRetourPrevue() != null &&
            LocalDate.now().isAfter(emprunt.getDateRetourPrevue())
        ) {
            return true;
        }
        return false;
    }
}
