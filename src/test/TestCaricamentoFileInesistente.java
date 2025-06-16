package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;


import model.accesslogic.LibroImpl;


public class TestCaricamentoFileInesistente {
    
    @TempDir
    Path tempDir;
    
    private String percorsoFile;
    
    @BeforeEach
    void setUp() {
        percorsoFile = tempDir.resolve("test_libreria.json").toString();
        new LibroImpl(10, percorsoFile);
    }
    
    
    
    @Test
    void testCaricamentoFileInesistente() {
        // Test caricamento quando il file non esiste
        String percorsoInesistente = tempDir.resolve("file_inesistente.json").toString();
        LibroImpl libroImplInesistente = new LibroImpl(5, percorsoInesistente);
        
        assertFalse(libroImplInesistente.caricaLibri());
        assertEquals(0, libroImplInesistente.getNumeroLibri());
    }
    
   
}