package libreria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
package libreria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
        this.path = path;
    }
    
    public Libro[] getLibri() {
        Libro[] listalibri= new Libro[numlibri];
        System.arraycopy(libri, 0, listalibri, 0, numlibri);
        return listalibri;
        
    }
    
    public void salvalibriinfile() {
        try (PrintWriter writer=new PrintWriter(new FileWriter(path))){
            for(int i=0;i<numlibri;i++) {
                writer.println(libri[i].getAutore());
                writer.println(libri[i].getGenere());
                writer.println(libri[i].getISBN());
                writer.println(libri[i].getTitolo());
                writer.println(libri[i].getL());
                writer.println(libri[i].getV());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
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
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            numlibri = 0;
            
            while ((line = reader.readLine()) != null && numlibri < libri.length) {
                String autore = line;
                String genere = reader.readLine();
                String isbn = reader.readLine();
                String titolo = reader.readLine();
                
                // Gestione enumerazioni
                statolettura stato;
                try {
                    stato = statolettura.valueOf(reader.readLine());
                } catch (IllegalArgumentException e) {
                    stato = statolettura.LEGGERE;  // Valore predefinito
                }
                
                valutazione val;
                try {
                    val = valutazione.valueOf(reader.readLine());
                } catch (IllegalArgumentException e) {
                    val = valutazione.BUONO;  // Valore predefinito
                }
                
                libri[numlibri] = new Libro(titolo, autore, isbn, genere, val, stato);
                numlibri++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Errore nel caricamento dei libri: " + e.getMessage(), 
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
            JOptionPane.showMessageDialog(null, "Libro eliminato con successo!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Libro non trovato!");
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
