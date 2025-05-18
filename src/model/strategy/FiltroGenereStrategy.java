package model.strategy;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Libro;

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
        if (genere == null || genere.isEmpty() || genere.equals("tutti")) {
            return new ArrayList<>(libri);
        }
        
        return libri.stream()
                .filter(libro -> libro.getGenere().equalsIgnoreCase(genere))
                .collect(Collectors.toList());
    }
    
    
}