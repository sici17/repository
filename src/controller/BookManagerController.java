package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Libro;
import model.enums.StatoLettura;
import model.enums.Valutazione;
import model.strategy.FiltroCombinato;
import model.strategy.FiltroGenereStrategy;
import model.strategy.FiltroRicerca;
import model.strategy.FiltroStatoLetturaStrategy;
import util.Constants;

/**
 * Controller che gestisce l'interazione tra la UI e il modello di business.
 */
public class BookManagerController {
    
    private final LibreriaController libreriaController;
    private DefaultTableModel tableModel;
    private FiltroCombinato filtroAttuale;
    
    /**
     * Costruttore che inizializza il controller con un controller di business.
     * 
     * @param libreriaController Il controller di business
     * @param tableModel Il modello della tabella
     */
    public BookManagerController(LibreriaController libreriaController, DefaultTableModel tableModel) {
        this.libreriaController = libreriaController;
        this.tableModel = tableModel;
        this.filtroAttuale=new FiltroCombinato();
        
        // Inizializza le colonne della tabella
        for (String columnName : Constants.TABLE_COLUMN_NAMES) {
            this.tableModel.addColumn("<html><b>" + columnName + "</b></html>");
        }
        
        // Carica i dati iniziali
        aggiornaTabella();
    }
    
    /**
     * Aggiunge un nuovo libro e aggiorna la tabella.
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
        // Validazione dei dati
        if (titolo.isEmpty() || autore.isEmpty() || isbn.isEmpty() || genere.isEmpty()) {
            JOptionPane.showMessageDialog(null, Constants.MSG_EMPTY_FIELDS, 
                    "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Aggiungi il libro
        boolean result = libreriaController.aggiungiLibro(titolo, autore, isbn, genere, valutazione, statoLettura);
        
        if (result) {
            // Aggiorna la tabella
            aggiornaTabella();
            JOptionPane.showMessageDialog(null, Constants.MSG_ADD_SUCCESS);
        } else {
            JOptionPane.showMessageDialog(null, Constants.MSG_ADD_ERROR, 
                    "Errore", JOptionPane.ERROR_MESSAGE);
        }
        
        return result;
    }
    
    /**
     * Aggiorna un libro esistente e aggiorna la tabella.
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
        // Validazione dei dati
        if (titolo.isEmpty() || autore.isEmpty() || isbn.isEmpty() || genere.isEmpty()) {
            JOptionPane.showMessageDialog(null, Constants.MSG_EMPTY_FIELDS, 
                    "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Aggiorna il libro
        boolean result = libreriaController.aggiornaLibro(titolo, autore, isbn, genere, valutazione, statoLettura);
        
        if (result) {
            // Aggiorna la tabella
            aggiornaTabella();
            JOptionPane.showMessageDialog(null, Constants.MSG_UPDATE_SUCCESS);
        } else {
            JOptionPane.showMessageDialog(null, Constants.MSG_UPDATE_ERROR, 
                    "Errore", JOptionPane.ERROR_MESSAGE);
        }
        
        return result;
    }
    
    /**
     * Elimina un libro e aggiorna la tabella.
     * 
     * @param isbn ISBN del libro da eliminare
     * @return true se l'operazione ha successo, false altrimenti
     */
    public boolean eliminaLibro(String isbn) {
        // Chiedi conferma prima dell'eliminazione
        int confirm = JOptionPane.showConfirmDialog(null, 
                String.format(Constants.MSG_CONFIRM_DELETE, isbn), 
                "Conferma eliminazione", 
                JOptionPane.YES_NO_OPTION);
                
        if (confirm != JOptionPane.YES_OPTION) {
            return false;
        }
        
        // Elimina il libro
        boolean result = libreriaController.eliminaLibro(isbn);
        
        if (result) {
            // Aggiorna la tabella
            aggiornaTabella();
            JOptionPane.showMessageDialog(null, Constants.MSG_DELETE_SUCCESS);
        } else {
            JOptionPane.showMessageDialog(null, Constants.MSG_DELETE_ERROR, 
                    "Errore", JOptionPane.ERROR_MESSAGE);
        }
        
        return result;
    }
    
    /**
     * Salva i libri su file.
     * 
     * @return true se l'operazione ha successo, false altrimenti
     */
    public boolean salvaLibri() {
        boolean result = libreriaController.salvaLibri();
        
        if (result) {
            JOptionPane.showMessageDialog(null, Constants.MSG_SAVE_SUCCESS);
        } else {
            JOptionPane.showMessageDialog(null, Constants.MSG_SAVE_ERROR, 
                    "Errore", JOptionPane.ERROR_MESSAGE);
        }
        
        return result;
    }
    
    /**
     * Aggiorna la tabella con tutti i libri.
     */
    public void aggiornaTabella() {
        // Pulisci la tabella
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        
        // Aggiungi tutti i libri
        List<Libro> libri = libreriaController.filtraLibri(filtroAttuale);
        
        for (Libro libro : libri) {
            Object[] rowData = {
                libro.getIsbn(),
                libro.getTitolo(),
                libro.getAutore(),
                libro.getGenere(),
                libro.getValutazione(),
                libro.getStatoLettura()
            };
            
            tableModel.addRow(rowData);
        }
    }
    
    /**
     * Aggiorna la tabella con i libri filtrati per genere.
     * 
     * @param genere Il genere da filtrare
     */
    public void setFiltroGenere(String genere) {
        // Rimuovi eventuali filtri per genere precedenti
        filtroAttuale = new FiltroCombinato();
        
        if (genere != null && !genere.equals(Constants.FILTER_ALL)) {
            filtroAttuale.aggiungiFiltro(new FiltroGenereStrategy(genere));
        }
        
        aggiornaTabella();
    }
    
    /**
     * Imposta un filtro per stato di lettura.
     * 
     * @param statoLettura Lo stato di lettura da filtrare
     */
    public void setFiltroStatoLettura(StatoLettura statoLettura) {
        // Rimuovi eventuali filtri per stato di lettura precedenti
        filtroAttuale = new FiltroCombinato();
        
        if (statoLettura != null) {
            filtroAttuale.aggiungiFiltro(new FiltroStatoLetturaStrategy(statoLettura));
        }
        
        aggiornaTabella();
    }
    
    
    public void setFiltroComposto(String genere, StatoLettura statoLettura) {
        filtroAttuale = new FiltroCombinato();
        
        // Aggiungi filtro per genere se necessario
        if (genere != null && !genere.equals(Constants.FILTER_ALL)) {
            filtroAttuale.aggiungiFiltro(new FiltroGenereStrategy(genere));
        }
        
        // Aggiungi filtro per stato di lettura se necessario
        if (statoLettura != null) {
            filtroAttuale.aggiungiFiltro(new FiltroStatoLetturaStrategy(statoLettura));
        }
        
        aggiornaTabella();
    }
    
    /**
     * Aggiorna la tabella con i libri che corrispondono alla ricerca.
     * 
     * @param testo Il testo da cercare
     * @param campo Il campo su cui effettuare la ricerca (titolo, autore, isbn)
     */
    public void setFiltroRicerca(String testo, String campo) {
        // Rimuovi eventuali filtri di ricerca precedenti
        filtroAttuale = new FiltroCombinato();
        
        if (testo != null && !testo.isEmpty()) {
            filtroAttuale.aggiungiFiltro(new FiltroRicerca(testo, campo));
        }
        
        aggiornaTabella();
    }
    
    public void resetFiltri() {
        filtroAttuale = new FiltroCombinato(); // Filtro vuoto mostra tutti i libri
        aggiornaTabella();
    }
    
    /**
     * Ottiene il libro con l'ISBN specificato.
     * 
     * @param isbn ISBN del libro da cercare
     * @return Il libro trovato o null se non esiste
     */
    public Libro getLibro(String isbn) {
        return libreriaController.cercaLibroPerISBN(isbn);
    }
    
    /**
     * Ottiene tutti i generi unici presenti nella libreria.
     * 
     * @return Array di stringhe con i generi
     */
    public String[] getGeneriUnici() {
        List<Libro> libri = libreriaController.getTuttiLibri();
        
        return libri.stream()
                .map(Libro::getGenere)
                .distinct()
                .toArray(String[]::new);
    }
}