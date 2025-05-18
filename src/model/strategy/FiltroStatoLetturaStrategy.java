package model.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Libro;
import model.enums.StatoLettura;


  //Strategia di filtro basata sullo stato di lettura del libro.
 
public class FiltroStatoLetturaStrategy implements FiltroStrategy {
    
    private final StatoLettura statoLettura;
    public FiltroStatoLetturaStrategy(StatoLettura statoLettura) {
        this.statoLettura = statoLettura;
    }
    
    @Override
    public List<Libro> filtra(List<Libro> libri) {
        if (statoLettura == null) {
            return new ArrayList<>(libri);
        }
        
        return libri.stream()
                .filter(libro -> libro.getStatoLettura() == statoLettura)
                .collect(Collectors.toList());
    }
    
   
}