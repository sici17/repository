package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.enums.StatoLettura;
import model.enums.Valutazione;

/**
 * Classe che rappresenta un libro nella libreria personale.
 */
public class Libro {
    
    private String titolo;
    private String autore;
    private String isbn;
    private String genere;
    private Valutazione valutazione;
    private StatoLettura statoLettura;
    
    /**
     * Costruttore per creare un nuovo libro.
     * 
     * @param titolo Titolo del libro
     * @param autore Autore del libro
     * @param isbn ISBN del libro
     * @param genere Genere del libro
     * @param valutazione Valutazione del libro
     * @param statoLettura Stato di lettura del libro
     */
    @JsonCreator
    public Libro(
            @JsonProperty("titolo") String titolo, 
            @JsonProperty("autore") String autore, 
            @JsonProperty("isbn") String isbn, 
            @JsonProperty("genere") String genere, 
            @JsonProperty("valutazione") Valutazione valutazione, 
            @JsonProperty("statoLettura") StatoLettura statoLettura) {
        
        this.titolo = titolo;
        this.autore = autore;
        this.isbn = isbn;
        this.genere = genere;
        this.valutazione = valutazione;
        this.statoLettura = statoLettura;
    }
    
    // Metodi getter e setter
    
    public String getTitolo() {
        return titolo;
    }
    
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    
    public String getAutore() {
        return autore;
    }
    
    public void setAutore(String autore) {
        this.autore = autore;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getGenere() {
        return genere;
    }
    
    public void setGenere(String genere) {
        this.genere = genere;
    }
    
    public Valutazione getValutazione() {
        return valutazione;
    }
    
    public void setValutazione(Valutazione valutazione) {
        this.valutazione = valutazione;
    }
    
    public StatoLettura getStatoLettura() {
        return statoLettura;
    }
    
    public void setStatoLettura(StatoLettura statoLettura) {
        this.statoLettura = statoLettura;
    }
    
    @Override
    public String toString() {
        return "Libro [titolo=" + titolo + 
               ", autore=" + autore + 
               ", ISBN=" + isbn + 
               ", genere=" + genere + 
               ", valutazione=" + valutazione + 
               ", statoLettura=" + statoLettura + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Libro libro = (Libro) obj;
        return isbn != null && isbn.equals(libro.isbn);
    }
    
    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }
}