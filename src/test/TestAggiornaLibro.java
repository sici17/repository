package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;


import model.accesslogic.LibroImpl;
import model.Libro;
import model.enums.StatoLettura;
import model.enums.Valutazione;

public class TestAggiornaLibro {
    
    @TempDir
    Path tempDir;
    
    private LibroImpl libroImpl;
    private String percorsoFile;
    
    @BeforeEach
    void setUp() {
        percorsoFile = tempDir.resolve("test_libreria.json").toString();
        libroImpl = new LibroImpl(10, percorsoFile);
    }
    
    @Test
    void testAggiornaLibro() {
        // Aggiungi libro originale
        Libro libroOriginale = new Libro("Titolo Originale", "Autore Originale", "ISBN_UPDATE", 
                "Genere Originale", Valutazione.MEDIOCRE, StatoLettura.LEGGERE);
        assertTrue(libroImpl.aggiungiLibro(libroOriginale));
        
        // Aggiorna libro
        Libro libroAggiornato = new Libro("Titolo Aggiornato", "Autore Aggiornato", "ISBN_UPDATE", 
                "Genere Aggiornato", Valutazione.PERFETTO, StatoLettura.LETTO);
        assertTrue(libroImpl.aggiornaLibro(libroAggiornato));
        
        // Verifica aggiornamento
        Libro libroVerifica = libroImpl.cercaLibroPerISBN("ISBN_UPDATE");
        assertEquals("Titolo Aggiornato", libroVerifica.getTitolo());
        assertEquals("Autore Aggiornato", libroVerifica.getAutore());
        assertEquals("Genere Aggiornato", libroVerifica.getGenere());
        assertEquals(Valutazione.PERFETTO, libroVerifica.getValutazione());
        assertEquals(StatoLettura.LETTO, libroVerifica.getStatoLettura());
        
        // Test aggiornamento libro inesistente
        Libro libroInesistente = new Libro("Libro Inesistente", "Autore Inesistente", 
                "ISBN_INESISTENTE", "Genere", Valutazione.BUONO, StatoLettura.LETTO);
        assertFalse(libroImpl.aggiornaLibro(libroInesistente));
    }
}