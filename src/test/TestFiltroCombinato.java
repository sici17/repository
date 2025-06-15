package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import model.Libro;
import model.enums.StatoLettura;
import model.enums.Valutazione;
import model.command.*; // CAMBIATO: era model.strategy.*

public class TestFiltroCombinato {
    
    private List<Libro> libri;
    private FiltroReceiver receiver; // AGGIUNTO
    
    @BeforeEach
    void setUp() {
        libri = new ArrayList<>();
        libri.add(new Libro("Harry Potter", "J.K. Rowling", "ISBN1", "Fantasy", 
                Valutazione.OTTIMO, StatoLettura.LETTO));
        libri.add(new Libro("Il Signore degli Anelli", "J.R.R. Tolkien", "ISBN2", "Fantasy", 
                Valutazione.PERFETTO, StatoLettura.LEGGERE));
        libri.add(new Libro("1984", "George Orwell", "ISBN3", "Fantascienza", 
                Valutazione.BUONO, StatoLettura.LETTO));
        libri.add(new Libro("Il Nome della Rosa", "Umberto Eco", "ISBN4", "Romanzo", 
                Valutazione.OTTIMO, StatoLettura.LETTURA));
        libri.add(new Libro("Dune", "Frank Herbert", "ISBN5", "Fantascienza", 
                Valutazione.PERFETTO, StatoLettura.LETTO));
        
        receiver = new FiltroReceiver(); // AGGIUNTO
    }
    
    @Test
    void testFiltroCombinato() {
        // Test combinazione Fantasy + LETTO
        FiltroCompostoCommand filtroCombinato = new FiltroCompostoCommand(); // CAMBIATO: era FiltroCombinato
        filtroCombinato.aggiungiComando(new FiltroGenereCommand(receiver, "Fantasy")); // CAMBIATO: aggiunto receiver
        filtroCombinato.aggiungiComando(new FiltroStatoLetturaCommand(receiver, StatoLettura.LETTO)); // CAMBIATO: aggiunto receiver
        
        List<Libro> risultato = filtroCombinato.esegui(libri); // CAMBIATO: era filtra()
        assertEquals(1, risultato.size());
        assertEquals("Harry Potter", risultato.get(0).getTitolo());
        
        // Test combinazione multipla
        FiltroCompostoCommand filtroComplesso = new FiltroCompostoCommand(); // CAMBIATO: era FiltroCombinato
        filtroComplesso.aggiungiComando(new FiltroGenereCommand(receiver, "Fantascienza")); // CAMBIATO: aggiunto receiver
        filtroComplesso.aggiungiComando(new FiltroStatoLetturaCommand(receiver, StatoLettura.LETTO)); // CAMBIATO: aggiunto receiver
        filtroComplesso.aggiungiComando(new FiltroRicercaCommand(receiver, "Dune", "titolo")); // CAMBIATO: aggiunto receiver
        
        risultato = filtroComplesso.esegui(libri); // CAMBIATO: era filtra()
        assertEquals(1, risultato.size());
        assertEquals("Dune", risultato.get(0).getTitolo());
        
        // Test combinazione che non produce risultati
        FiltroCompostoCommand filtroVuoto = new FiltroCompostoCommand(); // CAMBIATO: era FiltroCombinato
        filtroVuoto.aggiungiComando(new FiltroGenereCommand(receiver, "Fantasy")); // CAMBIATO: aggiunto receiver
        filtroVuoto.aggiungiComando(new FiltroStatoLetturaCommand(receiver, StatoLettura.LETTURA)); // CAMBIATO: aggiunto receiver
        
        risultato = filtroVuoto.esegui(libri); // CAMBIATO: era filtra()
        assertTrue(risultato.isEmpty());
        
        // Test filtro combinato vuoto
        FiltroCompostoCommand filtroSenzaFiltri = new FiltroCompostoCommand(); // CAMBIATO: era FiltroCombinato
        risultato = filtroSenzaFiltri.esegui(libri); // CAMBIATO: era filtra()
        assertEquals(5, risultato.size());
        
        // Test aggiunta filtro null
        filtroCombinato.aggiungiComando(null); // CAMBIATO: era aggiungiFiltro()
        risultato = filtroCombinato.esegui(libri); // CAMBIATO: era filtra()
        assertEquals(1, risultato.size()); // Non dovrebbe cambiare
        
        // Test clearFiltri
        filtroCombinato.clearComandi(); // CAMBIATO: era clearFiltri()
        risultato = filtroCombinato.esegui(libri); // CAMBIATO: era filtra()
        assertEquals(5, risultato.size());
    }
}