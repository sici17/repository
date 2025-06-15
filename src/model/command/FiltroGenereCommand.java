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
    
    @Override
    public String getDescrizione() {
        return "Filtro per genere: " + genere;
    }
    
    @Override
    public boolean isAttivo() {
        return genere != null && !genere.isEmpty() && !genere.equals("tutti");
    }
    
    @Override
    public void annulla() {
        // Implementazione undo se necessario
        // Potrebbe ripristinare lo stato precedente
    }
    
    public String getGenere() {
        return genere;
    }
    
    public List<Libro> getRisultatoPrecedente() {
        return risultatoPrecedente != null ? new ArrayList<>(risultatoPrecedente) : null;
    }
}
