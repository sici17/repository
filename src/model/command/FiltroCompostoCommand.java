package model.command;

import java.util.ArrayList;
import java.util.List;

import model.Libro;

public class FiltroCompostoCommand implements FiltroCommand {
    
    private final List<FiltroCommand> comandi;
    public FiltroCompostoCommand() {
        this.comandi = new ArrayList<>();
    }
    
    public void aggiungiComando(FiltroCommand comando) {
        if (comando != null) {
            comandi.add(comando);
        }
    }
    
    public void clearComandi() {
        comandi.clear();
    }
    
   
    
    @Override
    public List<Libro> esegui(List<Libro> libri) {
        new ArrayList<>(libri);
        
        if (comandi.isEmpty()) {
            return new ArrayList<>(libri);
        }
        
        List<Libro> risultato = new ArrayList<>(libri);
        
        // Applica sequenzialmente tutti i comandi attivi
        for (FiltroCommand comando : comandi) {
                risultato = comando.esegui(risultato);
            
        }
        
        return risultato;
    }
    
    
   
    
    public List<FiltroCommand> getComandi(
) {
        return new ArrayList<>(comandi);
    }
}

