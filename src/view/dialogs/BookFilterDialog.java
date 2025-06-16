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


 // Dialog per filtrare i libri per genere e stato di lettura.
 
public class BookFilterDialog extends JDialog {
    
    private static final long serialVersionUID = 1L;
    
    private JComboBox<String> genereComboBox;
    private JComboBox<String> statoLetturaComboBox;
    private final BookManagerController controller;
    
   
    public BookFilterDialog(Component parent, BookManagerController controller) {
        super(javax.swing.SwingUtilities.getWindowAncestor(parent), Constants.DIALOG_FILTER_TITLE);
        this.controller = controller;
        
        // configurazione della dialog
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(new BorderLayout());
        
        // creazione del pannello per il filtro
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(3, 2, 10, 10));
        
        // etichetta e ComboBox per il genere
        JLabel genereLabel = new JLabel("Genere:");
        genereLabel.setFont(new Font("Arial", Font.BOLD, 14));
        filterPanel.add(genereLabel);
        
        // ottieni tutti i generi unici
        String[] generi = ottieniGeneriUnici();
        genereComboBox = new JComboBox<>(generi);
        filterPanel.add(genereComboBox);
        
        // etichetta e ComboBox per lo stato di lettura
        JLabel statoLabel = new JLabel("Stato lettura:");
        statoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        filterPanel.add(statoLabel);
        
        // crea un array con "Tutti" come prima opzione seguito dai valori dell'enumerazione
        String[] statoOptions = ottieniStatiLetturaOpzioni();
        statoLetturaComboBox = new JComboBox<>(statoOptions);
        filterPanel.add(statoLetturaComboBox);
        
        // pannello per i pulsanti
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        
        // pulsante Applica
        JButton applyButton = createStyledButton("Applica");
        buttonPanel.add(applyButton);
        
        // pulsante Annulla
        JButton cancelButton = createStyledButton("Annulla");
        buttonPanel.add(cancelButton);
        
        // aggiunta dei pannelli alla dialog
        add(filterPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // configurazione degli action listener
        
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
  
    private String[] ottieniGeneriUnici() {
        // Utilizziamo i generi predefiniti invece dei generi trovati nei libri
        String[] generiPredefiniti = Constants.GENERI_PREDEFINITI;
        
        // Creazione di un nuovo array che include "Tutti" all'inizio
        String[] generiConTutti = new String[generiPredefiniti.length + 1];
        generiConTutti[0] = "tutti";
        
        System.arraycopy(generiPredefiniti, 0, generiConTutti, 1, generiPredefiniti.length);
        
        return generiConTutti;
    }
    
  
    private String[] ottieniStatiLetturaOpzioni() {
        StatoLettura[] stati = StatoLettura.values();
        
        // Creazione di un nuovo array che include "Tutti" all'inizio
        String[] opzioni = new String[stati.length + 1];
        opzioni[0] = "tutti";
        
        for (int i = 0; i < stati.length; i++) {
            opzioni[i + 1] = stati[i].toString();
        }
        
        return opzioni;
    }
    
    
    private void applicaFiltri() {
        String genereSelezionato = (String) genereComboBox.getSelectedItem();
        String statoSelezionato = (String) statoLetturaComboBox.getSelectedItem();
        
        StatoLettura statoLettura = null;
        if (!statoSelezionato.equals("tutti")) {
            for (StatoLettura stato : StatoLettura.values()) {
                if (stato.toString().equals(statoSelezionato)) {
                    statoLettura = stato;
                    break;
                }
            }
        }
        
        // usa il nuovo metodo per impostare il filtro composto
        controller.setFiltroComposto(genereSelezionato, statoLettura);
        
        dispose();
    }
}