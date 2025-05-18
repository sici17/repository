package model.strategy;

import java.util.List;
import model.Libro;


 // interfaccia Strategy per definire algoritmi di filtro sui libri.
 
public interface FiltroStrategy {
  
    List<Libro> filtra(List<Libro> libri);
    
    
}