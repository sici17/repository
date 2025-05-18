package model.strategy;


import java.util.ArrayList;
import java.util.List;

import model.Libro;

/**
 * Strategia di filtro composto che combina più filtri.
 * Implementa anche il pattern Composite.
 */
public class FiltroCombinato implements FiltroStrategy {
    
    private final List<FiltroStrategy> filtri;
  
    public FiltroCombinato() {
        this.filtri = new ArrayList<>();
    }
    
    
    public void aggiungiFiltro(FiltroStrategy filtro) {
        if (filtro != null) {
            filtri.add(filtro);
        }
    }
   
    public void clearFiltri() {
        filtri.clear();
    }
    
    @Override
    public List<Libro> filtra(List<Libro> libri) {
        if (filtri.isEmpty()) {
            return new ArrayList<>(libri);
        }
        
        List<Libro> risultato = new ArrayList<>(libri);
        
        // applica sequenzialmente tutti i filtri
        for (FiltroStrategy filtro : filtri) {
            risultato = filtro.filtra(risultato);
        }
        
        return risultato;
    }
    
    
}