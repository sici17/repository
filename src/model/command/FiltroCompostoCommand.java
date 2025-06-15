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
    
    public int getNumeroComandi() {
        return (int) comandi.stream().filter(FiltroCommand::isAttivo).count();
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
            if (comando.isAttivo()) {
                risultato = comando.esegui(risultato);
            }
        }
        
        return risultato;
    }
    
    @Override
    public String getDescrizione() {
        if (comandi.isEmpty()) {
            return "Nessun filtro applicato";
        }
        
        StringBuilder descrizione = new StringBuilder("Filtro composto: [");
        boolean primo = true;
        
        for (FiltroCommand comando : comandi) {
            if (comando.isAttivo()) {
                if (!primo) {
                    descrizione.append(", ");
                }
                descrizione.append(comando.getDescrizione());
                primo = false;
            }
        }
        
        descrizione.append("]");
        return descrizione.toString();
    }
    
    @Override
    public boolean isAttivo() {
        return comandi.stream().anyMatch(FiltroCommand::isAttivo);
    }
    
    @Override
    public void annulla() {
        // Annulla tutti i comandi in ordine inverso
        for (int i = comandi.size() - 1; i >= 0; i--) {
            comandi.get(i).annulla();
        }
    }
    
    public List<FiltroCommand> getComandi() {
        return new ArrayList<>(comandi);
    }
}

