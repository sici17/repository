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

public class TestCapacitaMassima {
    
    @TempDir
    Path tempDir;
    
    private String percorsoFile;
    
    @BeforeEach
    void setUp() {
        percorsoFile = tempDir.resolve("test_libreria.json").toString();
        new LibroImpl(10, percorsoFile);
    }
    
 
    
    @Test
    void testCapacitaMassima() {
        // Test con capacità massima ridotta
        LibroImpl libroImplRidotto = new LibroImpl(2, percorsoFile);
        
        Libro libro1 = new Libro("Libro 1", "Autore 1", "ISBN1", "Genere1", 
                Valutazione.BUONO, StatoLettura.LETTO);
        Libro libro2 = new Libro("Libro 2", "Autore 2", "ISBN2", "Genere2", 
                Valutazione.OTTIMO, StatoLettura.LEGGERE);
        Libro libro3 = new Libro("Libro 3", "Autore 3", "ISBN3", "Genere3", 
                Valutazione.PERFETTO, StatoLettura.LETTURA);
        
        assertTrue(libroImplRidotto.aggiungiLibro(libro1));
        assertTrue(libroImplRidotto.aggiungiLibro(libro2));
        assertFalse(libroImplRidotto.aggiungiLibro(libro3)); // Dovrebbe fallire
        
        assertEquals(2, libroImplRidotto.getNumeroLibri());
    }
    
    
}