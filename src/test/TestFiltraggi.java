package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.util.List;

import controller.LibreriaController;
import model.Libro;
import model.enums.StatoLettura;
import model.enums.Valutazione;
import model.strategy.FiltroGenereStrategy;

public class TestFiltraggi {
    
    @TempDir
    Path tempDir;
    
    private LibreriaController controller;
    private String percorsoFile;
    
    @BeforeEach
    void setUp() {
        percorsoFile = tempDir.resolve("test_libreria.json").toString();
        controller = LibreriaController.getInstance(10, percorsoFile, 
                LibreriaController.TipoPersistenza.JSON);
        
    }
    
    @Test
    void testFiltraggi() {
        // Aggiungi libri di test
        controller.aggiungiLibro("Libro Fantasy 1", "Autore A", "ISBN1", "Fantasy", 
                Valutazione.BUONO, StatoLettura.LETTO);
        controller.aggiungiLibro("Libro Fantasy 2", "Autore B", "ISBN2", "Fantasy", 
                Valutazione.OTTIMO, StatoLettura.LEGGERE);
        controller.aggiungiLibro("Libro Romanzo", "Autore C", "ISBN3", "Romanzo", 
                Valutazione.PERFETTO, StatoLettura.LETTO);
        controller.aggiungiLibro("Altro Fantasy", "Autore D", "ISBN4", "Fantasy", 
                Valutazione.MEDIOCRE, StatoLettura.LETTURA);
        
        // Test filtro per genere
        List<Libro> libriFantasy = controller.filtraPerGenere("Fantasy");
        assertEquals(3, libriFantasy.size());
        assertTrue(libriFantasy.stream().allMatch(l -> l.getGenere().equals("Fantasy")));
        
        // Test filtro per stato lettura
        List<Libro> libriLetti = controller.filtraPerStatoLettura(StatoLettura.LETTO);
        assertEquals(2, libriLetti.size());
        assertTrue(libriLetti.stream().allMatch(l -> l.getStatoLettura() == StatoLettura.LETTO));
        
        // Test ricerca per titolo
        List<Libro> risultatiRicerca = controller.ricercaLibri("Fantasy", "titolo");
   
        assertEquals(3, risultatiRicerca.size()); // "Libro Fantasy 1" e "Libro Fantasy 2"
        
        // Test ricerca per autore
        risultatiRicerca = controller.ricercaLibri("Autore A", "autore");
        assertEquals(1, risultatiRicerca.size());
        assertEquals("Libro Fantasy 1", risultatiRicerca.get(0).getTitolo());
        
        // Test filtro strategy
        FiltroGenereStrategy filtroStrategy = new FiltroGenereStrategy("Romanzo");
        List<Libro> libriRomanzo = controller.filtraLibri(filtroStrategy);
        assertEquals(1, libriRomanzo.size());
        assertEquals("Libro Romanzo", libriRomanzo.get(0).getTitolo());
        
        
    }
    
   
}