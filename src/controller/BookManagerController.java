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


  //controller che gestisce l'interazione tra la UI e il modello di business.
 
public class BookManagerController {
    
    private final LibreriaController libreriaController;
    private DefaultTableModel tableModel;
    private FiltroCombinato filtroAttuale;  
    
   
    public BookManagerController(LibreriaController libreriaController, DefaultTableModel tableModel) {
        this.libreriaController = libreriaController;
        this.tableModel = tableModel;
        this.filtroAttuale=new FiltroCombinato();
        
        // inizializza le colonne della tabella
        for (String columnName : Constants.TABLE_COLUMN_NAMES) {
            this.tableModel.addColumn("<html><b>" + columnName + "</b></html>");
        }
        
        // carica i dati iniziali
        aggiornaTabella();
    }
    
  
    public boolean aggiungiLibro(String titolo, String autore, String isbn, 
                               String genere, Valutazione valutazione, StatoLettura statoLettura) {
        // validazione dei dati
        if (titolo.isEmpty() || autore.isEmpty() || isbn.isEmpty() || genere.isEmpty()) {
            JOptionPane.showMessageDialog(null, Constants.MSG_EMPTY_FIELDS, 
                    "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // aggiungi il libro
        boolean result = libreriaController.aggiungiLibro(titolo, autore, isbn, genere, valutazione, statoLettura);
        
        if (result) {
            // aggiorna la tabella
            aggiornaTabella();
            JOptionPane.showMessageDialog(null, Constants.MSG_ADD_SUCCESS);
        } else {
            JOptionPane.showMessageDialog(null, Constants.MSG_ADD_ERROR, 
                    "Errore", JOptionPane.ERROR_MESSAGE);
        }
        
        return result;
    }
    
  
    public boolean aggiornaLibro(String titolo, String autore, String isbn, String genere, 
            Valutazione valutazione, StatoLettura statoLettura) {
        // validazione dei dati
        if (titolo.isEmpty() || autore.isEmpty() || isbn.isEmpty() || genere.isEmpty()) {
            JOptionPane.showMessageDialog(null, Constants.MSG_EMPTY_FIELDS, "Errore", 
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // aggiorna il libro
        boolean result = libreriaController.aggiornaLibro(titolo, autore, isbn, genere, valutazione, statoLettura);
        
        if (result) {
            // aggiorna la tabella
            aggiornaTabella();
            JOptionPane.showMessageDialog(null, Constants.MSG_UPDATE_SUCCESS);
        } else {
            JOptionPane.showMessageDialog(null, Constants.MSG_UPDATE_ERROR, "errore", JOptionPane.ERROR_MESSAGE);
        }
        
        return result;
    }
  
    public boolean eliminaLibro(String isbn) {
        // chiedi conferma prima dell'eliminazione
        int confirm = JOptionPane.showConfirmDialog(null, String.format(Constants.MSG_CONFIRM_DELETE, isbn), 
                "conferma eliminazione", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return false;
        }
        // elimina il libro
        boolean result = libreriaController.eliminaLibro(isbn);
        if (result) {
            // aggiorna la tabella
            aggiornaTabella();
            JOptionPane.showMessageDialog(null, Constants.MSG_DELETE_SUCCESS);
        } else {
            JOptionPane.showMessageDialog(null, Constants.MSG_DELETE_ERROR, 
                    "Errore", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }
    
   
    public boolean salvaLibri() {
        boolean result = libreriaController.salvaLibri();
        if (result) {
            JOptionPane.showMessageDialog(null, Constants.MSG_SAVE_SUCCESS);
        } else {
            JOptionPane.showMessageDialog(null, Constants.MSG_SAVE_ERROR, "Errore", JOptionPane.ERROR_MESSAGE);
        }
        
        return result;
    }
    
   
    public void aggiornaTabella() {
        // pulisci la tabella
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        // aggiungi tutti i libri
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
    
  
    public void setFiltroGenere(String genere) {
        // rimuovi eventuali filtri per genere precedenti
        filtroAttuale = new FiltroCombinato();
        if (genere != null && !genere.equals("tutti")) {
            filtroAttuale.aggiungiFiltro(new FiltroGenereStrategy(genere));
        }    
        aggiornaTabella();
    }
    
   
    public void setFiltroStatoLettura(StatoLettura statoLettura) {
        // rimuovi eventuali filtri per stato di lettura precedenti
        filtroAttuale = new FiltroCombinato();
        if (statoLettura != null) {
            filtroAttuale.aggiungiFiltro(new FiltroStatoLetturaStrategy(statoLettura));
        }
        aggiornaTabella();
    }
    
    
    public void setFiltroComposto(String genere, StatoLettura statoLettura) {
        filtroAttuale = new FiltroCombinato();
        
        // aggiungi filtro per genere se necessario
        if (genere != null && !genere.equals("tutti")) {
            filtroAttuale.aggiungiFiltro(new FiltroGenereStrategy(genere));
        }
        
        // aggiungi filtro per stato di lettura se necessario
        if (statoLettura != null) {
            filtroAttuale.aggiungiFiltro(new FiltroStatoLetturaStrategy(statoLettura));
        }
        
        aggiornaTabella();
    }
   
    public void setFiltroRicerca(String testo, String campo) {
        filtroAttuale = new FiltroCombinato();
        
        if (testo != null && !testo.isEmpty()) {
            filtroAttuale.aggiungiFiltro(new FiltroRicerca(testo, campo));
        }   
        aggiornaTabella();
    }
    
    public void resetFiltri() {
        filtroAttuale = new FiltroCombinato(); 
        aggiornaTabella();
    }
    
    
    
 
    public Libro getLibro(String isbn) {
        return libreriaController.cercaLibroPerISBN(isbn);
    }
    
 
   
}