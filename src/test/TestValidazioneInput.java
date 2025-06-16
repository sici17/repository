package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;

import controller.LibreriaController;
import model.enums.StatoLettura;
import model.enums.Valutazione;

public class TestValidazioneInput {
    
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
    void testValidazioneInput() {
        // Test con parametri null
        assertFalse(controller.aggiungiLibro(null, "Autore", "ISBN", "Genere", 
                Valutazione.BUONO, StatoLettura.LETTO));
        assertFalse(controller.aggiungiLibro("Titolo", null, "ISBN", "Genere", 
                Valutazione.BUONO, StatoLettura.LETTO));
        assertFalse(controller.aggiungiLibro("Titolo", "Autore", null, "Genere", 
                Valutazione.BUONO, StatoLettura.LETTO));
      
        
        // Test con stringhe vuote
        assertFalse(controller.aggiungiLibro("", "Autore", "ISBN", "Genere", 
                Valutazione.BUONO, StatoLettura.LETTO));
        assertFalse(controller.aggiungiLibro("Titolo", "", "ISBN", "Genere", 
                Valutazione.BUONO, StatoLettura.LETTO));
        assertFalse(controller.aggiungiLibro("Titolo", "Autore", "", "Genere", 
                Valutazione.BUONO, StatoLettura.LETTO));
        
        assertEquals(0, controller.getNumeroLibri());
    }
    
    
    
}