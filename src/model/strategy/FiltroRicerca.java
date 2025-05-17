package model.strategy;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Libro;
import util.Constants;

/**
 * Strategia di filtro basata su una ricerca testuale.
 */
public class FiltroRicerca implements FiltroStrategy {
    
    private final String testo;
    private final String campo;
    
    /**
     * Costruttore per inizializzare il filtro per ricerca testuale.
     * 
     * @param testo Il testo da cercare
     * @param campo Il campo su cui effettuare la ricerca (titolo, autore, isbn)
     */
    public FiltroRicerca(String testo, String campo) {
        this.testo = testo;
        this.campo = campo;
    }
    
    @Override
    public List<Libro> filtra(List<Libro> libri) {
        if (testo == null || testo.isEmpty()) {
            return new ArrayList<>(libri);
        }
        
        String testoLowerCase = testo.toLowerCase();
        
        switch (campo) {
            case Constants.SEARCH_FIELD_TITLE:
                return libri.stream()
                        .filter(libro -> libro.getTitolo().toLowerCase().contains(testoLowerCase))
                        .collect(Collectors.toList());
                
            case Constants.SEARCH_FIELD_AUTHOR:
                return libri.stream()
                        .filter(libro -> libro.getAutore().toLowerCase().contains(testoLowerCase))
                        .collect(Collectors.toList());
                
            case Constants.SEARCH_FIELD_ISBN:
                return libri.stream()
                        .filter(libro -> libro.getIsbn().toLowerCase().contains(testoLowerCase))
                        .collect(Collectors.toList());
                
            default:
                return new ArrayList<>(libri);
        }
    }
    
    @Override
    public String getDescrizioneFiltro() {
        if (testo == null || testo.isEmpty()) {
            return "Nessuna ricerca";
        }
        
        String campoDescrizione;
        switch (campo) {
            case Constants.SEARCH_FIELD_TITLE:
                campoDescrizione = "titolo";
                break;
            case Constants.SEARCH_FIELD_AUTHOR:
                campoDescrizione = "autore";
                break;
            case Constants.SEARCH_FIELD_ISBN:
                campoDescrizione = "ISBN";
                break;
            default:
                campoDescrizione = "tutti i campi";
        }
        
        return "Ricerca di \"" + testo + "\" in " + campoDescrizione;
    }
}
