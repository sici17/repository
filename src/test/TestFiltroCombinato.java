package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import model.Libro;
import model.enums.StatoLettura;
import model.enums.Valutazione;
import model.strategy.*;

public class TestFiltroCombinato {
    
    private List<Libro> libri;
    
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
    }
    
    
    
    @Test
    void testFiltroCombinato() {
        // Test combinazione Fantasy + LETTO
        FiltroCombinato filtroCombinato = new FiltroCombinato();
        filtroCombinato.aggiungiFiltro(new FiltroGenereStrategy("Fantasy"));
        filtroCombinato.aggiungiFiltro(new FiltroStatoLetturaStrategy(StatoLettura.LETTO));
        
        List<Libro> risultato = filtroCombinato.filtra(libri);
        assertEquals(1, risultato.size());
        assertEquals("Harry Potter", risultato.get(0).getTitolo());
        
        // Test combinazione multipla
        FiltroCombinato filtroComplesso = new FiltroCombinato();
        filtroComplesso.aggiungiFiltro(new FiltroGenereStrategy("Fantascienza"));
        filtroComplesso.aggiungiFiltro(new FiltroStatoLetturaStrategy(StatoLettura.LETTO));
        filtroComplesso.aggiungiFiltro(new FiltroRicerca("Dune", "titolo"));
        
        risultato = filtroComplesso.filtra(libri);
        assertEquals(1, risultato.size());
        assertEquals("Dune", risultato.get(0).getTitolo());
        
        // Test combinazione che non produce risultati
        FiltroCombinato filtroVuoto = new FiltroCombinato();
        filtroVuoto.aggiungiFiltro(new FiltroGenereStrategy("Fantasy"));
        filtroVuoto.aggiungiFiltro(new FiltroStatoLetturaStrategy(StatoLettura.LETTURA));
        
        risultato = filtroVuoto.filtra(libri);
        assertTrue(risultato.isEmpty());
        
        // Test filtro combinato vuoto
        FiltroCombinato filtroSenzaFiltri = new FiltroCombinato();
        risultato = filtroSenzaFiltri.filtra(libri);
        assertEquals(5, risultato.size());
        
        // Test aggiunta filtro null
        filtroCombinato.aggiungiFiltro(null);
        risultato = filtroCombinato.filtra(libri);
        assertEquals(1, risultato.size()); // Non dovrebbe cambiare
        
        // Test clearFiltri
        filtroCombinato.clearFiltri();
        risultato = filtroCombinato.filtra(libri);
        assertEquals(5, risultato.size());
    }
}