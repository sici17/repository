package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;

import controller.LibreriaController;
import model.Libro;
import model.enums.StatoLettura;
import model.enums.Valutazione;

public class LibreriaControllerTest {
    
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
    void testGestioneCompletaLibri() {
        // Test aggiunta libro
        assertTrue(controller.aggiungiLibro("Il Nome della Rosa", "Umberto Eco", 
                "978-88-17-12345-6", "Romanzo", Valutazione.OTTIMO, StatoLettura.LETTO));
        assertEquals(1, controller.getNumeroLibri());
        
        // Test aggiunta libro duplicato (dovrebbe fallire)
        assertFalse(controller.aggiungiLibro("Altro Titolo", "Altro Autore", 
                "978-88-17-12345-6", "Fantasy", Valutazione.BUONO, StatoLettura.LEGGERE));
        assertEquals(1, controller.getNumeroLibri());
        
        // Test aggiunta secondo libro
        assertTrue(controller.aggiungiLibro("1984", "George Orwell", 
                "978-88-17-67890-1", "Fantascienza", Valutazione.PERFETTO, StatoLettura.LETTURA));
        assertEquals(2, controller.getNumeroLibri());
        
        // Test ricerca per ISBN
        Libro libro = controller.cercaLibroPerISBN("978-88-17-12345-6");
        assertNotNull(libro);
        assertEquals("Il Nome della Rosa", libro.getTitolo());
        assertEquals("Umberto Eco", libro.getAutore());
        
        // Test aggiornamento libro
        assertTrue(controller.aggiornaLibro("Il Nome della Rosa (Edizione Speciale)", 
                "Umberto Eco", "978-88-17-12345-6", "Romanzo", 
                Valutazione.PERFETTO, StatoLettura.LETTO));
        
        libro = controller.cercaLibroPerISBN("978-88-17-12345-6");
        assertEquals("Il Nome della Rosa (Edizione Speciale)", libro.getTitolo());
        assertEquals(Valutazione.PERFETTO, libro.getValutazione());
        
        // Test eliminazione libro
        assertTrue(controller.eliminaLibro("978-88-17-67890-1"));
        assertEquals(1, controller.getNumeroLibri());
        assertNull(controller.cercaLibroPerISBN("978-88-17-67890-1"));
        
        
    }
    
   
    
   
}