package libreria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import libreria.Libro.statolettura;
import libreria.Libro.valutazione;

public class Libreria {
    
    private Libro[] libri;
    private int numlibri;
    private String path;
    
    public Libreria(int maxLibri, String path) {
        libri = new Libro[maxLibri];
        this.numlibri = 0;
        
        // Assicurati che il path termini con .json
        if (!path.toLowerCase().endsWith(".json")) {
            path = path.replaceAll("\\.[^.]*$", "") + ".json";
        }
        this.path = path;
    }
    
    public Libro[] getLibri() {
        Libro[] listalibri= new Libro[numlibri];
        System.arraycopy(libri, 0, listalibri, 0, numlibri);
        return listalibri;
        
    }
    
    public void salvalibriinfile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            
            // Crea una lista dei libri esistenti per evitare valori null
            List<Libro> libriList = Arrays.asList(getLibri());
            
            // Salva la lista nel file JSON
            mapper.writeValue(new File(path), libriList);
            
            JOptionPane.showMessageDialog(null, "Libri salvati con successo nel file JSON!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Errore durante il salvataggio dei libri: " + e.getMessage(), 
                "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public boolean updateBook(String autore, String newTitle, String genere, valutazione v, statolettura s,
            String isbn) {
        for (int i = 0; i < numlibri; i++) {
            if (libri[i].getISBN().equals(isbn)) {  // Corretta la comparazione da == a equals()
                libri[i].setAutore(autore);
                libri[i].setGenere(genere);  // Corretta l'assegnazione, ora usa genere invece di isbn
                libri[i].setL(s);
                libri[i].setV(v);
                libri[i].setTitolo(newTitle);
                JOptionPane.showMessageDialog(null, "Libro aggiornato con successo!");
                return true;
            }
        }
        return false;
    }

    public void addBook(String autore, String newTitle, String genere, valutazione v, statolettura s,
            String isbn) {
        if (numlibri < libri.length) {
            Libro book = new Libro(newTitle, autore ,isbn,genere, v, s);
            libri[numlibri] = book;
            numlibri++;
            JOptionPane.showMessageDialog(null, "Book added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "BookShop is full. Cannot add more books.");
        }
    }

    public Libro findBook(String isbn) {
        for (int i = 0; i < numlibri; i++) {
            if (libri[i].getISBN().equals(isbn)) {  // Corretta la comparazione da == a equals()
                return libri[i];
            }
        }
        return null;
    }
    
    public Libro[] filtraPerGenere(String genere) {
        List<Libro> risultati = new ArrayList<>();
        for (int i = 0; i < numlibri; i++) {
            if (libri[i].getGenere().equalsIgnoreCase(genere)) {
                risultati.add(libri[i]);
            }
        }
        return risultati.toArray(new Libro[0]);
    }

    public Libro[] filtraPerStatoLettura(statolettura stato) {
        List<Libro> risultati = new ArrayList<>();
        for (int i = 0; i < numlibri; i++) {
            if (libri[i].getL() == stato) {
                risultati.add(libri[i]);
            }
        }
        return risultati.toArray(new Libro[0]);
    }
    
    public void caricaLibriDaFile() {
        File file = new File(path);
        
        // Se il file non esiste, non fare nulla
        if (!file.exists()) {
            return;
        }
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            
            // Definisci il tipo di collezione per la deserializzazione
            CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Libro.class);
            
            // Leggi la lista di libri dal file JSON
            List<Libro> libriList = mapper.readValue(file, type);
            
            // Popola l'array di libri
            numlibri = 0;
            for (Libro libro : libriList) {
                if (numlibri < libri.length) {
                    libri[numlibri++] = libro;
                } else {
                    JOptionPane.showMessageDialog(null, "Impossibile caricare tutti i libri. Capacità massima raggiunta.", 
                        "Avviso", JOptionPane.WARNING_MESSAGE);
                    break;
                }
            }
            
            JOptionPane.showMessageDialog(null, "Caricati " + numlibri + " libri dal file JSON!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Errore durante il caricamento dei libri: " + e.getMessage(), 
                "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    

    public boolean deleteBook(String isbn) {
        int index = -1;
        for (int i = 0; i < numlibri; i++) {
            if (libri[i].getISBN().equals(isbn)) {  // Corretta la comparazione da == a equals()
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < numlibri - 1; i++) {
                libri[i] = libri[i + 1];
            }
            numlibri--;
            return true;
        } else {
            
            return false;
        }
    }
 
    public void displayBooksss() {
        if (numlibri == 0) {
            JOptionPane.showMessageDialog(null, "No books available!");
        } else {
            StringBuilder bookList = new StringBuilder();
            for (int i = 0; i < numlibri; i++) {
                bookList.append(libri[i].toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, bookList.toString());
        }
    }

    public void close() {
        salvalibriinfile();
    }
    

}
