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
    
    /**
     * Costruttore per inizializzare il filtro composto.
     */
    public FiltroCombinato() {
        this.filtri = new ArrayList<>();
    }
    
    /**
     * Aggiunge un filtro alla composizione.
     * 
     * @param filtro Il filtro da aggiungere
     */
    public void aggiungiFiltro(FiltroStrategy filtro) {
        if (filtro != null) {
            filtri.add(filtro);
        }
    }
    
    /**
     * Rimuove tutti i filtri dalla composizione.
     */
    public void clearFiltri() {
        filtri.clear();
    }
    
    @Override
    public List<Libro> filtra(List<Libro> libri) {
        if (filtri.isEmpty()) {
            return new ArrayList<>(libri);
        }
        
        List<Libro> risultato = new ArrayList<>(libri);
        
        // Applica sequenzialmente tutti i filtri
        for (FiltroStrategy filtro : filtri) {
            risultato = filtro.filtra(risultato);
        }
        
        return risultato;
    }
    
    @Override
    public String getDescrizioneFiltro() {
        if (filtri.isEmpty()) {
            return "Nessun filtro applicato";
        }
        
        StringBuilder descrizione = new StringBuilder("Filtri: ");
        for (int i = 0; i < filtri.size(); i++) {
            if (i > 0) {
                descrizione.append(", ");
            }
            descrizione.append(filtri.get(i).getDescrizioneFiltro());
        }
        
        return descrizione.toString();
    }
}