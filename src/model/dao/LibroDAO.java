package model.dao;

import java.util.List;

import model.Libro;
import model.enums.StatoLettura;
import model.strategy.FiltroStrategy;

/**
 * Interfaccia per le operazioni CRUD sui libri.
 */
public interface LibroDAO {
    
    /**
     * Aggiunge un libro alla collezione.
     * 
     * @param libro Il libro da aggiungere
     * @return true se l'operazione ha successo, false altrimenti
     */
    boolean aggiungiLibro(Libro libro);
    
    /**
     * Aggiorna i dati di un libro esistente.
     * 
     * @param libro Il libro con i dati aggiornati
     * @return true se l'operazione ha successo, false altrimenti
     */
    boolean aggiornaLibro(Libro libro);
    
    /**
     * Elimina un libro dalla collezione.
     * 
     * @param isbn L'ISBN del libro da eliminare
     * @return true se l'operazione ha successo, false altrimenti
     */
    boolean eliminaLibro(String isbn);
    
    /**
     * Ricerca un libro per ISBN.
     * 
     * @param isbn L'ISBN del libro da cercare
     * @return Il libro trovato o null se non esiste
     */
    Libro cercaLibroPerISBN(String isbn);
    
    /**
     * Restituisce tutti i libri nella collezione.
     * 
     * @return La lista di tutti i libri
     */
    List<Libro> getTuttiLibri();
    
    
    List<Libro> filtraLibri(FiltroStrategy filtro);
    
    /**
     * Filtra i libri per genere.
     * 
     * @param genere Il genere da filtrare
     * @return La lista dei libri filtrati
     */
    List<Libro> filtraPerGenere(String genere);
    
    /**
     * Filtra i libri per stato di lettura.
     * 
     * @param statoLettura Lo stato di lettura da filtrare
     * @return La lista dei libri filtrati
     */
    List<Libro> filtraPerStatoLettura(StatoLettura statoLettura);
    
    /**
     * Filtra i libri per titolo, autore o ISBN.
     * 
     * @param testo Il testo da cercare
     * @param campo Il campo su cui effettuare la ricerca (titolo, autore, isbn)
     * @return La lista dei libri che corrispondono alla ricerca
     */
    List<Libro> ricercaLibri(String testo, String campo);
    
    /**
     * Salva tutti i libri su file.
     * 
     * @return true se l'operazione ha successo, false altrimenti
     */
    boolean salvaLibri();
    
    /**
     * Carica i libri da file.
     * 
     * @return true se l'operazione ha successo, false altrimenti
     */
    boolean caricaLibri();
    
    /**
     * Restituisce il numero di libri nella collezione.
     * 
     * @return Il numero di libri
     */
    int getNumeroLibri();
}