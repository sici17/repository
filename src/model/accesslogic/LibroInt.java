package model.accesslogic;

import java.util.List;

import model.Libro;
import model.enums.StatoLettura;
import model.strategy.FiltroStrategy;


public interface LibroInt {
   
    
    
    boolean aggiungiLibro(Libro libro);
    
    
    boolean aggiornaLibro(Libro libro);
    
    
    
    
    
    
    boolean eliminaLibro(String isbn);
    
    
    
    
    Libro cercaLibroPerISBN(String isbn);
    
   
    List<Libro> getTuttiLibri();
    
    
    List<Libro> filtraLibri(FiltroStrategy filtro);
    
   
    
    List<Libro> filtraPerGenere(String genere);
    
   
    
    
    List<Libro> filtraPerStatoLettura(StatoLettura statoLettura);
    
   
    
    
    List<Libro> ricercaLibri(String testo, String campo);
    
   
    
    
    
    
    
    boolean salvaLibri();
    
    
    boolean caricaLibri();
 
    
    
    
    
    int getNumeroLibri();
}