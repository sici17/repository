package model.command;

import java.util.ArrayList;
import java.util.List;

import model.Libro;

public class FiltroRicercaCommand implements FiltroCommand {
    
    private final FiltroReceiver receiver;
    private final String testo;
    private final String campo;
    public FiltroRicercaCommand(FiltroReceiver receiver, String testo, String campo) {
        this.receiver = receiver;
        this.testo = testo;
        this.campo = campo;
    }
    
    @Override
    public List<Libro> esegui(List<Libro> libri) {
        new ArrayList<>(libri);
        return receiver.ricercaTesto(libri, testo, campo);
    }
    
  
    
    public String getTesto() {
        return testo;
    }
    
    public String getCampo() {
        return campo;
    }
}

