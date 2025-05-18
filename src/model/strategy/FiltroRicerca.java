package model.strategy;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Libro;
import util.Constants;


public class FiltroRicerca implements FiltroStrategy {
    
    private final String testo;
    private final String campo;
    
   
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
                        .filter(libro -> libro.getTitolo().toLowerCase().contains(testoLowerCase)).collect(Collectors.toList());
            case Constants.SEARCH_FIELD_AUTHOR:
                return libri.stream()
                        .filter(libro -> libro.getAutore().toLowerCase().contains(testoLowerCase)).collect(Collectors.toList());
            case Constants.SEARCH_FIELD_ISBN:
                return libri.stream().filter(libro -> libro.getIsbn().toLowerCase().contains(testoLowerCase)).collect(Collectors.toList());
            default:
                return new ArrayList<>(libri);
        }
    }
    
  
}
