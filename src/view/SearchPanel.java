package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BookManagerController;
import util.Constants;

/**
 * Pannello di ricerca per la libreria.
 */
public class SearchPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private final JTextField searchTextField;
    private final JComboBox<String> searchTypeComboBox;
    private final BookManagerController controller;
    
    /**
     * Costruttore che inizializza il pannello di ricerca.
     * 
     * @param controller Il controller per la gestione dei libri
     */
    public SearchPanel(BookManagerController controller) {
        this.controller = controller;
        
        // Configurazione del pannello
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        // Etichetta per la ricerca
        JLabel searchLabel = new JLabel("Cerca:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(searchLabel);
        
        // Campo di testo per la ricerca
        searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension(200, 25));
        add(searchTextField);
        
        // Etichetta per il tipo di ricerca
        JLabel searchTypeLabel = new JLabel("Per:");
        searchTypeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(searchTypeLabel);
        
        // ComboBox per il tipo di ricerca
        searchTypeComboBox = new JComboBox<>(new String[]{"Titolo", "Autore", "ISBN"});
        add(searchTypeComboBox);
        
        // Pulsante di ricerca
        JButton searchButton = createStyledButton("Cerca");
        add(searchButton);
        
        // Pulsante di reset
        JButton resetButton = createStyledButton("Reset");
        add(resetButton);
        
        // Configurazione degli action listener
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cercaLibri();
            }
        });
        
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetRicerca();
            }
        });
    }
    
    /**
     * Crea un pulsante con stile personalizzato.
     * 
     * @param text Il testo del pulsante
     * @return Il pulsante creato
     */
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
    
    /**
     * Cerca i libri in base al testo e tipo di ricerca selezionati.
     */
    private void cercaLibri() {
        String testo = searchTextField.getText().trim();
        
        if (testo.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Inserisci un testo da cercare");
            return;
        }
        
        String tipo = (String) searchTypeComboBox.getSelectedItem();
        String campo;
        
        // Converti il tipo di ricerca nel campo corrispondente
        switch (tipo) {
            case "Titolo":
                campo = Constants.SEARCH_FIELD_TITLE;
                break;
            case "Autore":
                campo = Constants.SEARCH_FIELD_AUTHOR;
                break;
            case "ISBN":
                campo = Constants.SEARCH_FIELD_ISBN;
                break;
            default:
                campo = Constants.SEARCH_FIELD_TITLE;
        }
        
        // Esegui la ricerca
        controller.ricercaLibri(testo, campo);
    }
    
    /**
     * Resetta la ricerca e mostra tutti i libri.
     */
    private void resetRicerca() {
        searchTextField.setText("");
        controller.aggiornaTabella();
    }
}
