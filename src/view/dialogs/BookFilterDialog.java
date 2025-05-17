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

import controller.BookManagerController;
import model.enums.StatoLettura;
import util.Constants;

/**
 * Dialog per filtrare i libri per genere e stato di lettura.
 */
public class BookFilterDialog extends JDialog {
    
    private static final long serialVersionUID = 1L;
    
    private JComboBox<String> genereComboBox;
    private JComboBox<String> statoLetturaComboBox;
    private final BookManagerController controller;
    
    /**
     * Costruttore che inizializza la dialog.
     * 
     * @param parent Il componente padre
     * @param controller Il controller per la gestione dei libri
     */
    public BookFilterDialog(Component parent, BookManagerController controller) {
        super(javax.swing.SwingUtilities.getWindowAncestor(parent), Constants.DIALOG_FILTER_TITLE);
        this.controller = controller;
        
        // Configurazione della dialog
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(new BorderLayout());
        
        // Creazione del pannello per il filtro
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(3, 2, 10, 10));
        
        // Etichetta e ComboBox per il genere
        JLabel genereLabel = new JLabel("Genere:");
        genereLabel.setFont(new Font("Arial", Font.BOLD, 14));
        filterPanel.add(genereLabel);
        
        // Ottieni tutti i generi unici
        String[] generi = ottieniGeneriUnici();
        genereComboBox = new JComboBox<>(generi);
        filterPanel.add(genereComboBox);
        
        // Etichetta e ComboBox per lo stato di lettura
        JLabel statoLabel = new JLabel("Stato lettura:");
        statoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        filterPanel.add(statoLabel);
        
        // Crea un array con "Tutti" come prima opzione seguito dai valori dell'enumerazione
        String[] statoOptions = ottieniStatiLetturaOpzioni();
        statoLetturaComboBox = new JComboBox<>(statoOptions);
        filterPanel.add(statoLetturaComboBox);
        
        // Pannello per i pulsanti
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        
        // Pulsante Applica
        JButton applyButton = createStyledButton("Applica");
        buttonPanel.add(applyButton);
        
        // Pulsante Annulla
        JButton cancelButton = createStyledButton("Annulla");
        buttonPanel.add(cancelButton);
        
        // Aggiunta dei pannelli alla dialog
        add(filterPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Configurazione degli action listener
        
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicaFiltri();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
     * Ottiene un array di stringhe con tutti i generi unici.
     * 
     * @return Array di stringhe con i generi
     */
    private String[] ottieniGeneriUnici() {
        // Utilizziamo i generi predefiniti invece dei generi trovati nei libri
        String[] generiPredefiniti = Constants.GENERI_PREDEFINITI;
        
        // Creazione di un nuovo array che include "Tutti" all'inizio
        String[] generiConTutti = new String[generiPredefiniti.length + 1];
        generiConTutti[0] = Constants.FILTER_ALL;
        
        System.arraycopy(generiPredefiniti, 0, generiConTutti, 1, generiPredefiniti.length);
        
        return generiConTutti;
    }
    
    /**
     * Ottiene un array di stringhe con tutte le opzioni per lo stato di lettura.
     * 
     * @return Array di stringhe con le opzioni
     */
    private String[] ottieniStatiLetturaOpzioni() {
        StatoLettura[] stati = StatoLettura.values();
        
        // Creazione di un nuovo array che include "Tutti" all'inizio
        String[] opzioni = new String[stati.length + 1];
        opzioni[0] = Constants.FILTER_ALL;
        
        for (int i = 0; i < stati.length; i++) {
            opzioni[i + 1] = stati[i].toString();
        }
        
        return opzioni;
    }
    
    /**
     * Applica i filtri selezionati.
     */
    private void applicaFiltri() {
        String genereSelezionato = (String) genereComboBox.getSelectedItem();
        String statoSelezionato = (String) statoLetturaComboBox.getSelectedItem();
        
        StatoLettura statoLettura = null;
        if (!statoSelezionato.equals(Constants.FILTER_ALL)) {
            for (StatoLettura stato : StatoLettura.values()) {
                if (stato.toString().equals(statoSelezionato)) {
                    statoLettura = stato;
                    break;
                }
            }
        }
        
        // Usa il nuovo metodo per impostare il filtro composto
        controller.setFiltroComposto(genereSelezionato, statoLettura);
        
        dispose();
    }
}