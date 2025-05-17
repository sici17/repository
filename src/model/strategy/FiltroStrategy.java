package model.strategy;

import java.util.List;
import model.Libro;

/**
 * Interfaccia Strategy per definire algoritmi di filtro sui libri.
 */
public interface FiltroStrategy {
    /**
     * Filtra una lista di libri secondo una strategia specifica.
     * 
     * @param libri La lista di libri da filtrare
     * @return La lista di libri filtrati
     */
    List<Libro> filtra(List<Libro> libri);
    
    /**
     * Restituisce una descrizione testuale del filtro.
     * 
     * @return Descrizione del filtro
     */
    String getDescrizioneFiltro();
}