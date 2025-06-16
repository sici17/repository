package view;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BookManagerController;
import model.enums.StatoLettura;
import model.enums.Valutazione;
import util.Constants;


 // pannello per l'input dei dati di un libro.
 
public class BookFormPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private final JTextField titleTextField;
    private final JTextField authorTextField;
    private final JTextField isbnTextField;
    private final JTextField genreTextField;
    private final JComboBox<Valutazione> ratingComboBox;
    private final JComboBox<StatoLettura> readingStatusComboBox;
    private final JComboBox<String> genreComboBox = new JComboBox<>(Constants.GENERI_PREDEFINITI);
    private final BookManagerController controller;
    
    
    
    
    public BookFormPanel(BookManagerController controller) {
        this.controller = controller;
        
        // configurazione del pannello
        setLayout(new GridBagLayout());
        setBorder(javax.swing.BorderFactory.createTitledBorder("Dati Libro"));
        
        // inizializzazione delle constraints per il GridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        
        // titolo
        JLabel titleLabel = new JLabel("Titolo:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        c.gridx = 0;
        c.gridy = 0;
        add(titleLabel, c);       
        titleTextField = new JTextField();
        titleTextField.setPreferredSize(new Dimension(Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        c.gridx = 1;
        c.gridy = 0;
        add(titleTextField, c);
        
        // autore
        JLabel authorLabel = new JLabel("Autore:");
        authorLabel.setFont(new Font("Arial", Font.BOLD, 15));
        c.gridx = 0;
        c.gridy = 1;
        add(authorLabel, c);       
        authorTextField = new JTextField();
        authorTextField.setPreferredSize(new Dimension(Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        c.gridx = 1;
        c.gridy = 1;
        add(authorTextField, c);
        
        // ISBN
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnLabel.setFont(new Font("Arial", Font.BOLD, 15));
        c.gridx = 0;
        c.gridy = 2;
        add(isbnLabel, c);    
        isbnTextField = new JTextField();
        isbnTextField.setPreferredSize(new Dimension(Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        c.gridx = 1;
        c.gridy = 2;
        add(isbnTextField, c);
        
        // genere
        JLabel genreLabel = new JLabel("Genere:");
        genreLabel.setFont(new Font("Arial", Font.BOLD, 15));
        c.gridx = 0;
        c.gridy = 3;
        add(genreLabel, c);
    
        // utilizziamo una ComboBox invece di un TextField per il genere
        genreTextField = new JTextField();
        genreTextField.setVisible(false); // Nascondiamo il campo di testo
        
        JComboBox<String> genreComboBox = new JComboBox<>(Constants.GENERI_PREDEFINITI);
        // impostiamo il primo genere come default
        genreComboBox.setSelectedIndex(0);
        genreTextField.setText((String) genreComboBox.getSelectedItem());
        
        genreComboBox.setPreferredSize(new Dimension(Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        genreComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genreTextField.setText((String) genreComboBox.getSelectedItem());
            }
        });
        
        c.gridx = 1;
        c.gridy = 3;
        add(genreComboBox, c);
        
        // valutazione
        JLabel ratingLabel = new JLabel("Valutazione:");
        ratingLabel.setFont(new Font("Arial", Font.BOLD, 15));
        c.gridx = 0;
        c.gridy = 4;
        add(ratingLabel, c);       
        ratingComboBox = new JComboBox<>(Valutazione.values());
        ratingComboBox.setPreferredSize(new Dimension(Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        c.gridx = 1;
        c.gridy = 4;
        add(ratingComboBox, c);
        
        // stato di lettura
        JLabel readingStatusLabel = new JLabel("Stato Lettura:");
        readingStatusLabel.setFont(new Font("Arial", Font.BOLD, 15));
        c.gridx = 0;
        c.gridy = 5;
        add(readingStatusLabel, c);   
        readingStatusComboBox = new JComboBox<>(StatoLettura.values());
        readingStatusComboBox.setPreferredSize(new Dimension(Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        c.gridx = 1;
        c.gridy = 5;
        add(readingStatusComboBox, c);
        
        // pulsante Aggiungi
        JButton addButton = createStyledButton("Aggiungi");
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        add(addButton, c);
        
        // pulsante Pulisci
        JButton clearButton = createStyledButton("Pulisci");
        c.gridx = 0;
        c.gridy = 7;
        add(clearButton, c);
        
        // aggiunta degli action listener
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiLibro();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pulisciCampi();
            }
        });
    }
    
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        
        button.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        button.setBackground(Constants.COLOR_BUTTON_BG);
        button.setForeground(Constants.COLOR_BUTTON_FG);
        button.setFocusPainted(false);
        button.setBorder(
                javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createLineBorder(Constants.COLOR_BUTTON_BORDER, 2),
                        javax.swing.BorderFactory.createEmptyBorder(8, 16, 8, 16)));
        button.setFont(button.getFont().deriveFont(Font.BOLD, 12f));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Constants.COLOR_BUTTON_HOVER);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Constants.COLOR_BUTTON_BG);
            }
        });
        
        return button;
    }
    
   
    private void aggiungiLibro() {
        String titolo = titleTextField.getText();
        String autore = authorTextField.getText();
        String isbn = isbnTextField.getText();
        String genere = genreTextField.getText();
        Valutazione valutazione = (Valutazione) ratingComboBox.getSelectedItem();
        StatoLettura statoLettura = (StatoLettura) readingStatusComboBox.getSelectedItem();
        
        controller.aggiungiLibro(titolo, autore, isbn, genere, valutazione, statoLettura);
        
        // pulisci i campi dopo l'aggiunta
        pulisciCampi();
    }
    
   
    private void pulisciCampi() {
        titleTextField.setText("");
        authorTextField.setText("");
        isbnTextField.setText("");
        genreTextField.setText("");
        ratingComboBox.setSelectedIndex(0);
        readingStatusComboBox.setSelectedIndex(0);
    }
  
    public void impostaLibro(String titolo, String autore, String isbn, 
                           String genere, Valutazione valutazione, StatoLettura statoLettura) {
        titleTextField.setText(titolo);
        authorTextField.setText(autore);
        isbnTextField.setText(isbn);
        genreComboBox.setSelectedIndex(0);
        ratingComboBox.setSelectedItem(valutazione);
        readingStatusComboBox.setSelectedItem(statoLettura);
    }
}