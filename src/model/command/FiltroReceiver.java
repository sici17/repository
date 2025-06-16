package model.command;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Libro;
import model.enums.StatoLettura;
import model.enums.Valutazione;
import util.Constants;



public class FiltroReceiver {
    
   
    public List<Libro> filtraPerGenere(List<Libro> libri, String genere) {
        if (genere == null || genere.isEmpty() || genere.equals("tutti")) {
            return new ArrayList<>(libri);
        }
        
        return libri.stream()
                .filter(libro -> libro.getGenere().equalsIgnoreCase(genere))
                .collect(Collectors.toList());
    }
    
   
    public List<Libro> filtraPerStatoLettura(List<Libro> libri, StatoLettura statoLettura) {
        if (statoLettura == null) {
            return new ArrayList<>(libri);
        }
        
        return libri.stream()
                .filter(libro -> libro.getStatoLettura() == statoLettura)
                .collect(Collectors.toList());
    }
    
    
    public List<Libro> ricercaTesto(List<Libro> libri, String testo, String campo) {
        if (testo == null || testo.trim().isEmpty()) {
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
    
   
    public List<Libro> filtraPerValutazione(List<Libro> libri, Valutazione valutazioneMinima) {
        if (valutazioneMinima == null) {
            return new ArrayList<>(libri);
        }
        
        return libri.stream()
                .filter(libro -> libro.getValutazione().ordinal() >= valutazioneMinima.ordinal())
                .collect(Collectors.toList());
    }
}
