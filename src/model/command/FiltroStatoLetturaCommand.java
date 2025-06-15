package model.command;

import java.util.ArrayList;
import java.util.List;

import model.Libro;
import model.enums.StatoLettura;

public class FiltroStatoLetturaCommand implements FiltroCommand {
    
    private final FiltroReceiver receiver;
    private final StatoLettura statoLettura;
    public FiltroStatoLetturaCommand(FiltroReceiver receiver, StatoLettura statoLettura) {
        this.receiver = receiver;
        this.statoLettura = statoLettura;
    }
    
    @Override
    public List<Libro> esegui(List<Libro> libri) {
        new ArrayList<>(libri);
        return receiver.filtraPerStatoLettura(libri, statoLettura);
    }
    
    @Override
    public String getDescrizione() {
        return "Filtro per stato lettura: " + (statoLettura != null ? statoLettura.getDescrizione() : "null");
    }
    
    @Override
    public boolean isAttivo() {
        return statoLettura != null;
    }
    
    @Override
    public void annulla() {
        // implementazione undo
    }
    
    public StatoLettura getStatoLettura() {
        return statoLettura;
    }
}
