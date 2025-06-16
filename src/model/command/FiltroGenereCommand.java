package model.command;

import java.util.ArrayList;
import java.util.List;

import model.Libro;


public class FiltroGenereCommand implements FiltroCommand {
    
    private final FiltroReceiver receiver;
    private final String genere;
    private List<Libro> risultatoPrecedente; // Per undo
    
    public FiltroGenereCommand(FiltroReceiver receiver, String genere) {
        this.receiver = receiver;
        this.genere = genere;
    }
    
    @Override
    public List<Libro> esegui(List<Libro> libri) {
        this.risultatoPrecedente = new ArrayList<>(libri); // Salva per undo
        return receiver.filtraPerGenere(libri, genere);
    }
    
  
    
    public String getGenere() {
        return genere;
    }
    
    public List<Libro> getRisultatoPrecedente() {
        return risultatoPrecedente != null ? new ArrayList<>(risultatoPrecedente) : null;
    }
}
