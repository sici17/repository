package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BookManagerController;
import model.Libro;
import model.enums.StatoLettura;
import model.enums.Valutazione;
import util.Constants;


public class BookUpdateDialog extends JDialog {
    
    private static final long serialVersionUID = 1L;
    
    private final JTextField titleTextField;
    private final JTextField authorTextField;
    private final JTextField isbnTextField;
    private final JTextField genreTextField;
    private final JComboBox<Valutazione> ratingComboBox;
    private final JComboBox<StatoLettura> readingStatusComboBox;
    private final BookManagerController controller;
    
    
    
    public BookUpdateDialog(Component parent, BookManagerController controller, Libro libro) {
        super(javax.swing.SwingUtilities.getWindowAncestor(parent), Constants.DIALOG_UPDATE_TITLE);
        
        this.controller = controller;
        // configurazione della dialog
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(new BorderLayout());
        
        // creazione del pannello per i campi di input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        
        // titolo
        JLabel titleLabel = new JLabel("Titolo:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(titleLabel);
        
        titleTextField = new JTextField(libro.getTitolo());
        inputPanel.add(titleTextField);
        
        // autore
        JLabel authorLabel = new JLabel("Autore:");
        authorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(authorLabel);
        
        authorTextField = new JTextField(libro.getAutore());
        inputPanel.add(authorTextField);
        
        // isbn (non modificabile)
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnLabel.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(isbnLabel);
        
        isbnTextField = new JTextField(libro.getIsbn());
        isbnTextField.setEditable(false); 
        inputPanel.add(isbnTextField);
        
        // genere
        JLabel genreLabel = new JLabel("Genere:");
        genreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(genreLabel);
        
        // utilizziamo una ComboBox invece di un TextField per il genere
        genreTextField = new JTextField(libro.getGenere());
        genreTextField.setVisible(false); // Nascondiamo il campo di testo
        
        JComboBox<String> genreComboBox = new JComboBox<>(Constants.GENERI_PREDEFINITI);
        
        // seleziona il genere attuale se presente tra quelli predefiniti
        String genereAttuale = libro.getGenere();
        boolean generePresente = false;
        
        for (int i = 0; i < Constants.GENERI_PREDEFINITI.length; i++) {
            if (Constants.GENERI_PREDEFINITI[i].equals(genereAttuale)) {
                genreComboBox.setSelectedIndex(i);
                generePresente = true;
                break;
            }
        }
        
        // se il genere non è presente, aggiungerlo alla ComboBox
        if (!generePresente) {
            genreComboBox.addItem(genereAttuale);
            genreComboBox.setSelectedItem(genereAttuale);
        }
        
        genreComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genreTextField.setText((String) genreComboBox.getSelectedItem());
            }
        });
        
        // imposta il valore iniziale
        genreTextField.setText((String) genreComboBox.getSelectedItem());
        
        inputPanel.add(genreComboBox);
        
        // valutazione
        JLabel ratingLabel = new JLabel("Valutazione:");
        ratingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(ratingLabel);
        
        ratingComboBox = new JComboBox<>(Valutazione.values());
        ratingComboBox.setSelectedItem(libro.getValutazione());
        inputPanel.add(ratingComboBox);
        
        // stato di lettura
        JLabel readingStatusLabel = new JLabel("Stato Lettura:");
        readingStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(readingStatusLabel);
        
        readingStatusComboBox = new JComboBox<>(StatoLettura.values());
        readingStatusComboBox.setSelectedItem(libro.getStatoLettura());
        inputPanel.add(readingStatusComboBox);
        
        // pannello per i pulsanti
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        
        // pulsante Salva
        JButton saveButton = createStyledButton("Salva");
        buttonPanel.add(saveButton);
        
        // pulsante Annulla
        JButton cancelButton = createStyledButton("Annulla");
        buttonPanel.add(cancelButton);
        
        // aggiunta dei pannelli alla dialog
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // configurazione degli action listener
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModifiche();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
    
    private void salvaModifiche() {
        String titolo = titleTextField.getText();
        String autore = authorTextField.getText();
        String isbn = isbnTextField.getText();
        String genere = genreTextField.getText();
        Valutazione valutazione = (Valutazione) ratingComboBox.getSelectedItem();
        StatoLettura statoLettura = (StatoLettura) readingStatusComboBox.getSelectedItem();
        
        // validazione dei dati
        if (titolo.isEmpty() || autore.isEmpty() || genere.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, Constants.MSG_EMPTY_FIELDS, 
                    "Errore", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // aggiorna il libro
        boolean result = controller.aggiornaLibro(titolo, autore, isbn, genere, valutazione, statoLettura);
        
        if (result) {
            dispose();
        }
    }
}