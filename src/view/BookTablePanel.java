package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.BookManagerController;
import model.Libro;
import util.Constants;
import view.dialogs.BookFilterDialog;
import view.dialogs.BookUpdateDialog;


 // pannello che contiene la tabella dei libri e i relativi pulsanti di azione.
 
public class BookTablePanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private final JTable table;
    private final BookManagerController controller;
    private String selectedISBN = null;
    
    
    
    public BookTablePanel(JTable table, BookManagerController controller) {
        this.table = table;
        this.controller = controller;
        
        // configurazione del pannello
        setLayout(new BorderLayout());
        
        // aggiunta della tabella con scrollbar
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // creazione del pannello per i pulsanti
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        // creazione dei pulsanti
        JButton updateButton = createStyledButton("Modifica");
        JButton deleteButton = createStyledButton("Elimina");
        JButton filterButton = createStyledButton("Filtra");
        JButton sortButton = createStyledButton("Ordina");
        JButton saveButton = createStyledButton("Salva");
        
        // aggiunta dei pulsanti al pannello
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(filterButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(saveButton);
        
        // aggiunta del pannello dei pulsanti nella parte inferiore
        add(buttonPanel, BorderLayout.SOUTH);
        
        // configurazione del listener per la selezione nella tabella
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        selectedISBN = (String) table.getValueAt(selectedRow, 0);
                    } else {
                        selectedISBN = null;
                    }
                }
            }
        });
        
        // configurazione degli action listener per i pulsanti
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaLibroSelezionato();
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaLibroSelezionato();
            }
        });
        
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriDialogFiltro();
            }
        });
        
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordinaTabella();
            }
        });
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.salvaLibri();
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
    
    
    private void aggiornaLibroSelezionato() {
        if (selectedISBN == null) {
            javax.swing.JOptionPane.showMessageDialog(this, Constants.MSG_SELECT_BOOK);
            return;
        }
        
        Libro libro = controller.getLibro(selectedISBN);
        if (libro != null) {
            BookUpdateDialog dialog = new BookUpdateDialog(this, controller, libro);
            dialog.setVisible(true);
        }
    }
    
    
    private void eliminaLibroSelezionato() {
        if (selectedISBN == null) {
            javax.swing.JOptionPane.showMessageDialog(this, Constants.MSG_SELECT_BOOK);
            return;
        }
        
        controller.eliminaLibro(selectedISBN);
    }
   
    private void apriDialogFiltro() {
        BookFilterDialog dialog = new BookFilterDialog(this, controller);
        dialog.setVisible(true);
    }
    
    
    private void ordinaTabella() {
        String[] opzioni = {"Titolo (A-Z)", "Autore (A-Z)"};
        
        Object scelta = javax.swing.JOptionPane.showInputDialog(
                this,
                "Seleziona il criterio di ordinamento:",
                "Ordina libri",
                javax.swing.JOptionPane.QUESTION_MESSAGE,
                null,
                opzioni,
                opzioni[0]);
        
        if (scelta != null) {
            int columnIndex;
            
            if (scelta.equals(opzioni[0])) {
                // ordina per titolo (colonna 1)
                columnIndex = 1;
            } else {
                // ordina per autore (colonna 2)
                columnIndex = 2;
            }
            
            // ordina la tabella
            javax.swing.table.TableRowSorter<javax.swing.table.TableModel> sorter = 
                    new javax.swing.table.TableRowSorter<>(table.getModel());
            
            sorter.setComparator(columnIndex, String.CASE_INSENSITIVE_ORDER);
            
            table.setRowSorter(sorter);
            
            java.util.ArrayList<javax.swing.RowSorter.SortKey> sortKeys = new java.util.ArrayList<>();
            sortKeys.add(new javax.swing.RowSorter.SortKey(columnIndex, javax.swing.SortOrder.ASCENDING));
            
            sorter.setSortKeys(sortKeys);
            sorter.sort();
            
            // mostra messaggio di conferma
            String criterio = (columnIndex == 1) ? "titolo" : "autore";
            javax.swing.JOptionPane.showMessageDialog(this, 
                    "Libri ordinati per " + criterio + " in ordine alfabetico.");
        }
    }
}