package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;

import model.accesslogic.LibroImpl;
import model.Libro;
import model.enums.StatoLettura;
import model.enums.Valutazione;

public class TestSalvataggioCaricamento {
    
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
    void testSalvataggioECaricamento() throws Exception {
        // Aggiungi libri
        Libro libro1 = new Libro("Il Nome della Rosa", "Umberto Eco", "ISBN1", "Romanzo", 
                Valutazione.OTTIMO, StatoLettura.LETTO);
        Libro libro2 = new Libro("1984", "George Orwell", "ISBN2", "Fantascienza", 
                Valutazione.PERFETTO, StatoLettura.LEGGERE);
        Libro libro3 = new Libro("Harry Potter", "J.K. Rowling", "ISBN3", "Fantasy", 
                Valutazione.BUONO, StatoLettura.LETTURA);
        
        assertTrue(libroImpl.aggiungiLibro(libro1));
        assertTrue(libroImpl.aggiungiLibro(libro2));
        assertTrue(libroImpl.aggiungiLibro(libro3));
        assertEquals(3, libroImpl.getNumeroLibri());
        
        // Salva
        assertTrue(libroImpl.salvaLibri());
        assertTrue(Files.exists(tempDir.resolve("test_libreria.json")));
        
        // Verifica contenuto file
        String contenutoFile = Files.readString(tempDir.resolve("test_libreria.json"));
        assertTrue(contenutoFile.contains("Il Nome della Rosa"));
        assertTrue(contenutoFile.contains("Umberto Eco"));
        assertTrue(contenutoFile.contains("ISBN1"));
        assertTrue(contenutoFile.contains("OTTIMO"));
        assertTrue(contenutoFile.contains("LETTO"));
        
        // Crea una nuova istanza e carica
        LibroImpl nuovoLibroImpl = new LibroImpl(10, percorsoFile);
        assertTrue(nuovoLibroImpl.caricaLibri());
        assertEquals(3, nuovoLibroImpl.getNumeroLibri());
        
        // Verifica che i libri siano stati caricati correttamente
        List<Libro> libriCaricati = nuovoLibroImpl.getTuttiLibri();
        assertEquals(3, libriCaricati.size());
        
        Libro libroCaricato1 = nuovoLibroImpl.cercaLibroPerISBN("ISBN1");
        assertNotNull(libroCaricato1);
        assertEquals("Il Nome della Rosa", libroCaricato1.getTitolo());
        assertEquals("Umberto Eco", libroCaricato1.getAutore());
        assertEquals(Valutazione.OTTIMO, libroCaricato1.getValutazione());
        assertEquals(StatoLettura.LETTO, libroCaricato1.getStatoLettura());
        
        Libro libroCaricato2 = nuovoLibroImpl.cercaLibroPerISBN("ISBN2");
        assertNotNull(libroCaricato2);
        assertEquals("1984", libroCaricato2.getTitolo());
        assertEquals("George Orwell", libroCaricato2.getAutore());
        
        Libro libroCaricato3 = nuovoLibroImpl.cercaLibroPerISBN("ISBN3");
        assertNotNull(libroCaricato3);
        assertEquals("Harry Potter", libroCaricato3.getTitolo());
        assertEquals("J.K. Rowling", libroCaricato3.getAutore());
    }
    
    
}