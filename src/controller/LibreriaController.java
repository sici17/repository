package controller;

import java.util.List;

import model.Libro;
import model.dao.JSONLibroDAOFactory;
import model.dao.LibroDAO;
import model.dao.LibroDAOFactory;
import model.dao.XMLLibroDAOFactory;
import model.enums.StatoLettura;
import model.enums.Valutazione;

/**
 * Controller che gestisce la logica di business dell'applicazione Libreria.
 */
public class LibreriaController {
    
 // Tipi di persistenza supportati
    public enum TipoPersistenza {
        JSON,
        XML
    }
    
    private final LibroDAO libroDAO;
    private static LibreriaController instance=null;
    
    
    
    /**
     * Costruttore che inizializza il controller con un DAO.
     * 
     * @param capacitaMassima Capacità massima della libreria
     * @param percorsoFile Percorso del file per la persistenza
     * @param tipoPersistenza 
     */
    private  LibreriaController(int capacitaMassima, String percorsoFile, TipoPersistenza tipoPersistenza) {
        
        
     // Crea la factory appropriata in base al tipo di persistenza
        LibroDAOFactory factory;
        switch (tipoPersistenza) {
            case JSON:
                factory = new JSONLibroDAOFactory();
                break;
            case XML:
                factory = new XMLLibroDAOFactory();
                break;
            default:
                throw new IllegalArgumentException("Tipo di persistenza non supportato: " + tipoPersistenza);
        }
        
        // Usa la factory per creare il DAO
        this.libroDAO = factory.creaLibroDAO(capacitaMassima, percorsoFile);
        
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
        
        Libro libro = new Libro(titolo, autore, isbn, genere, valutazione, statoLettura);
        return libroDAO.aggiungiLibro(libro);
    }
    
    /**
     * Aggiorna i dati di un libro esistente.
     * 
     * @param titolo Nuovo titolo del libro
     * @param autore Nuovo autore del libro
     * @param isbn ISBN del libro (non modificabile)
     * @param genere Nuovo genere del libro
     * @param valutazione Nuova valutazione del libro
     * @param statoLettura Nuovo stato di lettura del libro
     * @return true se l'operazione ha successo, false altrimenti
     */
    public boolean aggiornaLibro(String titolo, String autore, String isbn, 
                               String genere, Valutazione valutazione, StatoLettura statoLettura) {
        if (titolo == null || autore == null || isbn == null || genere == null || 
                valutazione == null || statoLettura == null) {
            return false;
        }
        
        if (titolo.isEmpty() || autore.isEmpty() || isbn.isEmpty() || genere.isEmpty()) {
            return false;
        }
        
        Libro libro = new Libro(titolo, autore, isbn, genere, valutazione, statoLettura);
        return libroDAO.aggiornaLibro(libro);
    }
    
    /**
     * Elimina un libro dalla libreria.
     * 
     * @param isbn ISBN del libro da eliminare
     * @return true se l'operazione ha successo, false altrimenti
     */
    public boolean eliminaLibro(String isbn) {
        return libroDAO.eliminaLibro(isbn);
    }
    
    /**
     * Ricerca un libro per ISBN.
     * 
     * @param isbn ISBN del libro da cercare
     * @return Il libro trovato o null se non esiste
     */
    public Libro cercaLibroPerISBN(String isbn) {
        return libroDAO.cercaLibroPerISBN(isbn);
    }
    
    /**
     * Ottiene tutti i libri nella libreria.
     * 
     * @return La lista di tutti i libri
     */
    public List<Libro> getTuttiLibri() {
        return libroDAO.getTuttiLibri();
    }
    
    /**
     * Filtra i libri per genere.
     * 
     * @param genere Il genere da filtrare
     * @return La lista dei libri filtrati
     */
    public List<Libro> filtraPerGenere(String genere) {
        return libroDAO.filtraPerGenere(genere);
    }
    
    /**
     * Filtra i libri per stato di lettura.
     * 
     * @param statoLettura Lo stato di lettura da filtrare
     * @return La lista dei libri filtrati
     */
    public List<Libro> filtraPerStatoLettura(StatoLettura statoLettura) {
        return libroDAO.filtraPerStatoLettura(statoLettura);
    }
    
    /**
     * Ricerca libri per titolo, autore o ISBN.
     * 
     * @param testo Il testo da cercare
     * @param campo Il campo su cui effettuare la ricerca (titolo, autore, isbn)
     * @return La lista dei libri che corrispondono alla ricerca
     */
    public List<Libro> ricercaLibri(String testo, String campo) {
        return libroDAO.ricercaLibri(testo, campo);
    }
    
    /**
     * Salva i libri su file.
     * 
     * @return true se l'operazione ha successo, false altrimenti
     */
    public boolean salvaLibri() {
        return libroDAO.salvaLibri();
    }
    
    /**
     * Carica i libri da file.
     * 
     * @return true se l'operazione ha successo, false altrimenti
     */
    public boolean caricaLibri() {
        return libroDAO.caricaLibri();
    }
    
    /**
     * Ottiene il numero di libri nella libreria.
     * 
     * @return Il numero di libri
     */
    public int getNumeroLibri() {
        return libroDAO.getNumeroLibri();
    }
}
