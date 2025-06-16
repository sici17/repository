package controller;

import java.util.List;
import model.Libro;
import model.accesslogic.*;
import model.command.FiltroCommand;
import model.enums.StatoLettura;
import model.enums.Valutazione;
import model.observer.*;
import model.observer.LibreriaObserver.TipoEvento;


  //controller che gestisce la logica di business dell'applicazione Libreria.
 
public class LibreriaController extends LibreriaSubject {
    
 // tipi di dati supportati
    public enum TipoPersistenza {
        JSON,
        XML
    }
    
    private final LibroInt libro;
    private static LibreriaController instance=null;
    LibroFactory factory;
    
    public static synchronized LibreriaController getInstance(int capacitaMassima, String percorsoFile,TipoPersistenza tipoPersistenza) {
        if (instance == null) {
            instance = new LibreriaController(capacitaMassima, percorsoFile, tipoPersistenza);
        }
        return instance;
    }
    

    private  LibreriaController(int capacitaMassima, String percorsoFile, TipoPersistenza tipoPersistenza) {
        
        
     // crea la factory appropriata in base al tipo di persistenza
        
        switch (tipoPersistenza) {
            case JSON:
                factory = new JSONLibroFactory();
                break;
            case XML:
                factory = new XMLLibroFactory();
                break;
            default:
                throw new IllegalArgumentException("tipologia dato non supportata: " + tipoPersistenza);
        }
       
        this.libro = factory.creaLibro(capacitaMassima, percorsoFile); 
        caricaLibri();
    }
    
    // pattern singleton 
    
   
    
    
    
    
    // sezione dedicata al pattern observer
    @Override
    public void registraObserver(LibreriaObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    @Override
    public void rimuoviObserver(LibreriaObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notificaObserver(TipoEvento tipoEvento, Object dettagli) {
        for (LibreriaObserver observer : observers) {
            observer.aggiornamento(tipoEvento, dettagli);
        }
    }
    
    
    public boolean aggiungiLibro(String titolo, String autore, String isbn, 
                                String genere, Valutazione valutazione, StatoLettura statoLettura) {
        if (titolo == null || autore == null || isbn == null || genere == null || 
                valutazione == null || statoLettura == null) {
            return false;
        }
        
        if (titolo.isEmpty() || autore.isEmpty() || isbn.isEmpty() || genere.isEmpty()) {
            return false;
        }
        
        Libro libro1 = new Libro(titolo, autore, isbn, genere, valutazione, statoLettura);
        boolean ret= libro.aggiungiLibro(libro1);
        if(ret) {
            notificaObserver(TipoEvento.LIBRO_AGGIUNTO, libro1);
        }
        return ret;
    }
    
    
    
    
    public boolean aggiornaLibro(String titolo, String autore, String isbn, 
             String genere, Valutazione valutazione, StatoLettura statoLettura) {
        if (titolo == null || autore == null || isbn == null || genere == null || 
                valutazione == null || statoLettura == null) {
            return false;
        }
        
        if (titolo.isEmpty() || autore.isEmpty() || isbn.isEmpty() || genere.isEmpty()) {
            return false;
        }
        
        Libro libro1 = new Libro(titolo, autore, isbn, genere, valutazione, statoLettura);
        boolean result = libro.aggiornaLibro(libro1);
        
        if (result) {
            // notifica gli observer
            notificaObserver(TipoEvento.LIBRO_AGGIORNATO, libro1);
        }
        
        return result;
        
    }
   
    
    public boolean eliminaLibro(String isbn) {
        boolean result = libro.eliminaLibro(isbn);
        Libro libroDaEliminare = cercaLibroPerISBN(isbn);
        if (result && libroDaEliminare != null) {
            // notifica gli observer
            notificaObserver(TipoEvento.LIBRO_ELIMINATO, libroDaEliminare);
        } 
        return result;
    }
   
    public Libro cercaLibroPerISBN(String isbn) {
        return libro.cercaLibroPerISBN(isbn);
    }
    
    public List<Libro> getTuttiLibri() {
        return libro.getTuttiLibri();
    }
    
    public List<Libro> filtraLibri(FiltroCommand comando) {
        notificaObserver(TipoEvento.FILTRO_APPLICATO, comando);
        return libro.filtraLibri(comando); 
    }
    
    public List<Libro> filtraPerGenere(String genere) {
        return libro.filtraPerGenere(genere);
    }
    
    public List<Libro> filtraPerStatoLettura(StatoLettura statoLettura) {
        return libro.filtraPerStatoLettura(statoLettura);
    }
    
    public List<Libro> ricercaLibri(String testo,String campo) {
        return libro.ricercaLibri(testo, campo);
    }
    
    public boolean salvaLibri() {
        boolean result = libro.salvaLibri();
        if (result) {
            // notifica gli observer
            notificaObserver(TipoEvento.LIBRERIA_SALVATA, null);
        }
        return result;
    }
    
    public boolean caricaLibri() {
        boolean result = libro.caricaLibri();
        if (result) {
            // notifica gli observer
            notificaObserver(TipoEvento.LIBRERIA_CARICATA, getTuttiLibri());
        }
        return result;
    }
  
    public int getNumeroLibri() {
        return libro.getNumeroLibri();
    }
}
