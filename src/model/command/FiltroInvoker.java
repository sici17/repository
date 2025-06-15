package model.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.Libro;
import model.enums.StatoLettura;

public class FiltroInvoker {
    
    private final Stack<FiltroCommand> storicoComandi;
    private final List<String> logComandi;
    private final FiltroReceiver receiver; 
    private static final int MAX_STORICO = 10;
    
    public FiltroInvoker() {
        this.storicoComandi = new Stack<>();
        this.logComandi = new ArrayList<>();
        this.receiver = new FiltroReceiver(); 
    }
    
   
    public FiltroGenereCommand creaFiltroGenere(String genere) {
        return new FiltroGenereCommand(receiver, genere);
    }
    
    public FiltroStatoLetturaCommand creaFiltroStatoLettura(StatoLettura statoLettura) {
        return new FiltroStatoLetturaCommand(receiver, statoLettura);
    }
    
    public FiltroRicercaCommand creaFiltroRicerca(String testo, String campo) {
        return new FiltroRicercaCommand(receiver, testo, campo);
    }
    
    public List<Libro> eseguiComando(FiltroCommand comando, List<Libro> libri) {
        if (comando == null) {
            return new ArrayList<>(libri);
        }
        
        List<Libro> risultato = comando.esegui(libri);
        
        if (comando.isAttivo()) {
            aggiungiAlloStorico(comando);
            aggiungiAlLog(comando);
        }
        
        return risultato;
    }
    
    public FiltroCommand getUltimoComando() {
        if (storicoComandi.isEmpty()) {
            return null;
        }
        return storicoComandi.peek();
    }
    
    public FiltroCommand annullaUltimoComando() {
        if (storicoComandi.isEmpty()) {
            return null;
        }
        
        FiltroCommand comando = storicoComandi.pop();
        comando.annulla(); // Chiama annulla sul comando
        aggiungiAlLog("ANNULLATO: " + comando.getDescrizione());
        return comando;
    }
    
    public void pulisciStorico() {
        storicoComandi.clear();
        aggiungiAlLog("Storico comandi pulito");
    }
    
    public List<FiltroCommand> getStorico() {
        return new ArrayList<>(storicoComandi);
    }
    
    public List<String> getLog() {
        return new ArrayList<>(logComandi);
    }
    
    public int getDimensioneStorico() {
        return storicoComandi.size();
    }
    
    public boolean isStoricoVuoto() {
        return storicoComandi.isEmpty();
    }
    
    public FiltroReceiver getReceiver() {
        return receiver;
    }
    
    private void aggiungiAlloStorico(FiltroCommand comando) {
        if (storicoComandi.size() >= MAX_STORICO) {
            storicoComandi.removeElementAt(0);
        }
        storicoComandi.push(comando);
    }
    
    private void aggiungiAlLog(FiltroCommand comando) {
        aggiungiAlLog(comando.getDescrizione());
    }
    
    private void aggiungiAlLog(String messaggio) {
        String timestamp = java.time.LocalDateTime.now().toString();
        logComandi.add("[" + timestamp + "] " + messaggio);
        
        if (logComandi.size() > 50) {
            logComandi.remove(0);
        }
    }
}
