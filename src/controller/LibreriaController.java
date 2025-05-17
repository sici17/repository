package controller;

import java.util.List;

import model.Libro;
import model.accesslogic.JSONLibroFactory;
import model.accesslogic.LibroFactory;
import model.accesslogic.LibroInt;
import model.accesslogic.XMLLibroFactory;
import model.enums.StatoLettura;
import model.enums.Valutazione;
import model.observer.LibreriaObserver;
import model.observer.LibreriaObserver.TipoEvento;
import model.observer.LibreriaSubject;
import model.strategy.FiltroStrategy;

/**
 * Controller che gestisce la logica di business dell'applicazione Libreria.
 */
public class LibreriaController extends LibreriaSubject {
    
 // Tipi di persistenza supportati
    public enum TipoPersistenza {
        JSON,
        XML
    }
    
    private final LibroInt libro;
    private static LibreriaController instance=null;
    
    
    
    /**
     * Costruttore che inizializza il controller.
     * 
     * @param capacitaMassima Capacità massima della libreria
     * @param percorsoFile Percorso del file per la persistenza
     * @param tipoPersistenza 
     */
    private  LibreriaController(int capacitaMassima, String percorsoFile, TipoPersistenza tipoPersistenza) {
        
        
     // Crea la factory appropriata in base al tipo di persistenza
        LibroFactory factory;
        switch (tipoPersistenza) {
            case JSON:
                factory = new JSONLibroFactory();
                break;
            case XML:
                factory = new XMLLibroFactory();
                break;
            default:
                throw new IllegalArgumentException("Tipo di persistenza non supportato: " + tipoPersistenza);
        }
        
        
        this.libro = factory.creaLibro(capacitaMassima, percorsoFile);
        
        caricaLibri();
    }
    
    public static synchronized LibreriaController getInstance(int capacitaMassima, String percorsoFile,TipoPersistenza tipoPersistenza) {
        if (instance == null) {
            instance = new LibreriaController(capacitaMassima, percorsoFile, tipoPersistenza);
        }
        return instance;
    }
    
    public static synchronized LibreriaController getInstance(int capacitaMassima, String percorsoFile) {
        return getInstance(capacitaMassima, percorsoFile, TipoPersistenza.JSON);
    }
    
    
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
    
    
    
    
    
    /**
     * Aggiunge un nuovo libro alla libreria.
     * 
     * @param titolo Titolo del libro
     * @param autore Autore del libro
     * @param isbn ISBN del libro
     * @param genere Genere del libro
     * @param valutazione Valutazione del libro
     * @param statoLettura Stato di lettura del libro
     * @return true se l'operazione ha successo, false altrimenti
     */
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
            // Notifica gli observer
            notificaObserver(TipoEvento.LIBRO_AGGIORNATO, libro1);
        }
        
        return result;
        
    }
    
    /**
     * Elimina un libro dalla libreria.
     * 
     * @param isbn ISBN del libro da eliminare
     * @return true se l'operazione ha successo, false altrimenti
     */
    public boolean eliminaLibro(String isbn) {
        boolean result = libro.eliminaLibro(isbn);
        Libro libroDaEliminare = cercaLibroPerISBN(isbn);
        if (result && libroDaEliminare != null) {
            // Notifica gli observer
            notificaObserver(TipoEvento.LIBRO_ELIMINATO, libroDaEliminare);
        }
        
        return result;
    }
    
    /**
     * Ricerca un libro per ISBN.
     * 
     * @param isbn ISBN del libro da cercare
     * @return Il libro trovato o null se non esiste
     */
    public Libro cercaLibroPerISBN(String isbn) {
        return libro.cercaLibroPerISBN(isbn);
    }
    
    /**
     * Ottiene tutti i libri nella libreria.
     * 
     * @return La lista di tutti i libri
     */
    public List<Libro> getTuttiLibri() {
        return libro.getTuttiLibri();
    }
    
    /**
     * Filtra i libri per genere.
     * 
     * @param genere Il genere da filtrare
     * @return La lista dei libri filtrati
     */
    
    
    public List<Libro> filtraLibri(FiltroStrategy filtro) {
        notificaObserver(TipoEvento.FILTRO_APPLICATO, filtro);
        return libro.filtraLibri(filtro);
        
    }
    
    public List<Libro> filtraPerGenere(String genere) {
        return libro.filtraPerGenere(genere);
    }
    
    public List<Libro> filtraPerStatoLettura(StatoLettura statoLettura) {
        return libro.filtraPerStatoLettura(statoLettura);
    }
    
    public List<Libro> ricercaLibri(String testo, String campo) {
        return libro.ricercaLibri(testo, campo);
    }
    
    /**
     * Salva i libri su file.
     * 
     * @return true se l'operazione ha successo, false altrimenti
     */
    public boolean salvaLibri() {
        boolean result = libro.salvaLibri();
        
        if (result) {
            // Notifica gli observer
            notificaObserver(TipoEvento.LIBRERIA_SALVATA, null);
        }
        
        return result;
    }
    
    /**
     * Carica i libri da file.
     * 
     * @return true se l'operazione ha successo, false altrimenti
     */
    public boolean caricaLibri() {
        boolean result = libro.caricaLibri();
        
        if (result) {
            // Notifica gli observer
            notificaObserver(TipoEvento.LIBRERIA_CARICATA, getTuttiLibri());
        }
        
        return result;
    }
    
    /**
     * Ottiene il numero di libri nella libreria.
     * 
     * @return Il numero di libri
     */
    public int getNumeroLibri() {
        return libro.getNumeroLibri();
    }
}
