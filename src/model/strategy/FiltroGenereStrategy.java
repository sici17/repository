package model.strategy;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Libro;
import util.Constants;

/**
 * Strategia di filtro basata sul genere del libro.
 */
public class FiltroGenereStrategy implements FiltroStrategy {
    
    private final String genere;
    
    /**
     * Costruttore per inizializzare il filtro per genere.
     * 
     * @param genere Il genere da filtrare
     */
    public FiltroGenereStrategy(String genere) {
        this.genere = genere;
    }
    
    @Override
    public List<Libro> filtra(List<Libro> libri) {
        if (genere == null || genere.isEmpty() || genere.equals(Constants.FILTER_ALL)) {
            return new ArrayList<>(libri);
        }
        
        return libri.stream()
                .filter(libro -> libro.getGenere().equalsIgnoreCase(genere))
                .collect(Collectors.toList());
    }
    
    @Override
    public String getDescrizioneFiltro() {
        if (genere == null || genere.isEmpty() || genere.equals(Constants.FILTER_ALL)) {
            return "Tutti i generi";
        }
        return "Genere: " + genere;
    }
}