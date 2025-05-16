package model.dao;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import model.Libro;
import model.enums.StatoLettura;
import util.Constants;
import util.FileUtils;

/**
 * Implementazione dell'interfaccia LibroDAO che utilizza JSON per la persistenza.
 */
public class LibroDAOImpl implements LibroDAO {
    
    private final List<Libro> libri;
    private final int capacitaMassima;
    private final String percorsoFile;
    private final ObjectMapper mapper;
    
    /**
     * Costruttore che inizializza il DAO con una capacità massima e un percorso file.
     * 
     * @param capacitaMassima Numero massimo di libri
     * @param percorsoFile Percorso del file JSON
     */
    public LibroDAOImpl(int capacitaMassima, String percorsoFile) {
        this.libri = new ArrayList<>();
        this.capacitaMassima = capacitaMassima;
        this.percorsoFile = FileUtils.assicuraEstensioneJson(percorsoFile);
        
        this.mapper = new ObjectMapper();
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
    public List<Libro> filtraPerGenere(String genere) {
        if (genere == null || genere.isEmpty() || genere.equals(Constants.FILTER_ALL)) {
            return getTuttiLibri();
        }
        
        return libri.stream()
                .filter(l -> l.getGenere().equalsIgnoreCase(genere))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Libro> filtraPerStatoLettura(StatoLettura statoLettura) {
        if (statoLettura == null) {
            return getTuttiLibri();
        }
        
        return libri.stream()
                .filter(l -> l.getStatoLettura() == statoLettura)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Libro> ricercaLibri(String testo, String campo) {
        if (testo == null || testo.isEmpty()) {
            return getTuttiLibri();
        }
        
        String testoLowerCase = testo.toLowerCase();
        
        switch (campo) {
            case Constants.SEARCH_FIELD_TITLE:
                return libri.stream()
                        .filter(l -> l.getTitolo().toLowerCase().contains(testoLowerCase))
                        .collect(Collectors.toList());
                
            case Constants.SEARCH_FIELD_AUTHOR:
                return libri.stream()
                        .filter(l -> l.getAutore().toLowerCase().contains(testoLowerCase))
                        .collect(Collectors.toList());
                
            case Constants.SEARCH_FIELD_ISBN:
                return libri.stream()
                        .filter(l -> l.getIsbn().toLowerCase().contains(testoLowerCase))
                        .collect(Collectors.toList());
                
            default:
                return getTuttiLibri();
        }
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
            // Definisci il tipo di collezione per la deserializzazione
            CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Libro.class);
            
            // Leggi la lista di libri dal file JSON
            List<Libro> libriCaricati = mapper.readValue(file, type);
            
            // Pulisci la lista corrente e aggiungi i libri caricati
            libri.clear();
            
            // Verifica se si supera la capacità massima
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