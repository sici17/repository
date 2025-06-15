package model.accesslogic;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import model.Libro;
import model.command.FiltroCommand;
import model.command.FiltroReceiver;

import model.enums.StatoLettura;

import util.Constants;
import util.FileUtils;


 //implementazione dell'interfaccia  che utilizza json per la persistenza.
 
public class LibroImpl implements LibroInt {
    
    private final List<Libro> libri;
    private final int capacitaMassima;
    private final String percorsoFile;
    private final ObjectMapper mapper;
    private final FiltroReceiver filtroReceiver; // 
    
    
   
    public LibroImpl(int capacitaMassima, String percorsoFile) {
        this.libri = new ArrayList<>();
        this.capacitaMassima = capacitaMassima;
        this.percorsoFile = FileUtils.assicuraEstensioneJson(percorsoFile);      
        this.mapper = new ObjectMapper();
        this.filtroReceiver = new FiltroReceiver(); // NUOVO: Crea il receiver
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    
    @Override
    public boolean aggiungiLibro(Libro libro) {
        if (libro == null) {
            return false;
        }
        if (libri.size() >= capacitaMassima) {
            JOptionPane.showMessageDialog(null, 
                    "Capacità massima raggiunta. Impossibile aggiungere altri libri.", 
                    "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Verifica se esiste già un libro con lo stesso ISBN
        if (cercaLibroPerISBN(libro.getIsbn()) != null) {
            JOptionPane.showMessageDialog(null, 
                    "Esiste già un libro con ISBN: " + libro.getIsbn(), 
                    "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return libri.add(libro);
    }
    
    @Override
    public boolean aggiornaLibro(Libro libro) {
        if (libro == null) {
            return false;
        }
        
        for (int i = 0; i < libri.size(); i++) {
            if (libri.get(i).getIsbn().equals(libro.getIsbn())) {
                libri.set(i, libro);
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean eliminaLibro(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            return false;
        }
        
        return libri.removeIf(l -> l.getIsbn().equals(isbn));
    }
    
    @Override
    public Libro cercaLibroPerISBN(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            return null;
        }
        
        return libri.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Libro> getTuttiLibri() {
        return new ArrayList<>(libri);
    }
    @Override
    public List<Libro> filtraLibri(FiltroCommand comando) {
        if (comando == null) {
            return getTuttiLibri();
        }
        
        return comando.esegui(libri);
    }
    
    // Aggiorna questi metodi per usare il receiver direttamente:
    @Override
    public List<Libro> filtraPerGenere(String genere) {
        return filtroReceiver.filtraPerGenere(libri, genere);
    }
    
    @Override
    public List<Libro> filtraPerStatoLettura(StatoLettura statoLettura) {
        return filtroReceiver.filtraPerStatoLettura(libri, statoLettura);
    }
    
    @Override
    public List<Libro> ricercaLibri(String testo, String campo) {
        return filtroReceiver.ricercaTesto(libri, testo, campo);
    }
    @Override
    public boolean salvaLibri() {
        try {
            FileUtils.creaDirectories(percorsoFile);
            mapper.writeValue(new File(percorsoFile), libri);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    Constants.MSG_SAVE_ERROR + ": " + e.getMessage(), 
                    "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean caricaLibri() {
        File file = new File(percorsoFile);
        
        // Se il file non esiste, non fare nulla
        if (!file.exists()) {
            return false;
        }
        
        try {
            // tipo per la deserializazzione
            CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Libro.class);
            
            // l la lista di libri dal file JSON
            List<Libro> libriCaricati = mapper.readValue(file, type);
            
            // pulisci la lista corrente e aggiungi i libri caricati
            libri.clear();
            
            // verifica se si supera la capacità massima
            if (libriCaricati.size() > capacitaMassima) {
                JOptionPane.showMessageDialog(null, 
                        "Impossibile caricare tutti i libri. Capacità massima raggiunta.", 
                        "Avviso", JOptionPane.WARNING_MESSAGE);
                libri.addAll(libriCaricati.subList(0, capacitaMassima));
            } else {
                libri.addAll(libriCaricati);
            }
            
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    Constants.MSG_LOAD_ERROR + ": " + e.getMessage(), 
                    "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public int getNumeroLibri() {
        return libri.size();
    }
}