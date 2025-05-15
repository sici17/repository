package libreria;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
import java.awt.*;


public class LibreriaSystem extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField titleTextField;
    private JTextField authorTextField;
    private JTextField isbnTextField;
    private JTextField genereTextField;
    private JComboBox<Libro.valutazione> valutazioneComboBox;
    private JComboBox<Libro.statolettura> statoLetturaComboBox;
    
    private JTextField searchTextField;
    private JComboBox<String> searchTypeComboBox;
    private JComboBox<String> filterGenereComboBox;
    private JComboBox<Libro.statolettura> filterStatoComboBox;

    private Libreria bookShop;
    private DefaultTableModel tableModel;

    public LibreriaSystem() {
        this.bookShop = new Libreria(100, "C:\\Users\\franc\\Desktop\\libri.json");
        this.bookShop.caricaLibriDaFile();
        int borderSize = 20;
        getRootPane().setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));

        setTitle("Book Management System");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel1 = new JLabel("Personal Book Manager");
        titleLabel1.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel1.setHorizontalAlignment(JLabel.CENTER);

        titleLabel1.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0)); // Add padding to the bottom of the title

        // Panel for the title label
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel1, BorderLayout.CENTER);

        // Add the title panel to the main frame
        add(titlePanel, BorderLayout.NORTH);
        
        
        
        

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints formConstraints = new GridBagConstraints();
        formConstraints.fill = GridBagConstraints.HORIZONTAL;
        formConstraints.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));

        formConstraints.gridx = 0;
        formConstraints.gridy = 0;
        formPanel.add(titleLabel, formConstraints);

        titleTextField = new JTextField();
        titleTextField.setPreferredSize(new Dimension(200, 25)); // Set preferred size for the text field
        formConstraints.gridx = 1;
        formConstraints.gridy = 0;
        formPanel.add(titleTextField, formConstraints);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setFont(new Font("Arial", Font.BOLD, 15));

        formConstraints.gridx = 0;
        formConstraints.gridy = 1;
        formPanel.add(authorLabel, formConstraints);

        authorTextField = new JTextField();
        authorTextField.setPreferredSize(new Dimension(200, 25)); // Set preferred size for the text field
        formConstraints.gridx = 1;
        formConstraints.gridy = 1;
        formPanel.add(authorTextField, formConstraints);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 15));

        formConstraints.gridx = 0;
        formConstraints.gridy = 2;
        formPanel.add(priceLabel, formConstraints);

        isbnTextField = new JTextField();
        isbnTextField.setPreferredSize(new Dimension(200, 25)); // Set preferred size for the text field
        formConstraints.gridx = 1;
        formConstraints.gridy = 2;
        formPanel.add(isbnTextField, formConstraints);

       

        genereTextField = new JTextField();
        genereTextField.setPreferredSize(new Dimension(200, 25)); // Set preferred size for the text field
        formConstraints.gridx = 1;
        formConstraints.gridy = 3;
        formPanel.add(genereTextField, formConstraints);// Creazione del pannello di input
        JPanel formPanel1 = new JPanel();
        formPanel1.setLayout(new GridBagLayout());
        GridBagConstraints formConstraints1 = new GridBagConstraints();
        formConstraints1.fill = GridBagConstraints.HORIZONTAL;
        formConstraints1.insets = new Insets(5, 5, 5, 5);

        // Titolo
        JLabel titleLabel11 = new JLabel("Titolo:");
        titleLabel11.setFont(new Font("Arial", Font.BOLD, 15));
        formConstraints1.gridx = 0;
        formConstraints1.gridy = 0;
        formPanel1.add(titleLabel11, formConstraints1);

        titleTextField = new JTextField();
        titleTextField.setPreferredSize(new Dimension(200, 25));
        formConstraints1.gridx = 1;
        formConstraints1.gridy = 0;
        formPanel1.add(titleTextField, formConstraints1);

        // Autore
        JLabel authorLabel1 = new JLabel("Autore:");
        authorLabel1.setFont(new Font("Arial", Font.BOLD, 15));
        formConstraints1.gridx = 0;
        formConstraints1.gridy = 1;
        formPanel1.add(authorLabel1, formConstraints1);

        authorTextField = new JTextField();
        authorTextField.setPreferredSize(new Dimension(200, 25));
        formConstraints1.gridx = 1;
        formConstraints1.gridy = 1;
        formPanel1.add(authorTextField, formConstraints1);

        // ISBN
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnLabel.setFont(new Font("Arial", Font.BOLD, 15));
        formConstraints1.gridx = 0;
        formConstraints1.gridy = 2;
        formPanel1.add(isbnLabel, formConstraints1);

        isbnTextField = new JTextField();
        isbnTextField.setPreferredSize(new Dimension(200, 25));
        formConstraints1.gridx = 1;
        formConstraints1.gridy = 2;
        formPanel1.add(isbnTextField, formConstraints1);

        // Genere
        JLabel genereLabel = new JLabel("Genere:");
        genereLabel.setFont(new Font("Arial", Font.BOLD, 15));
        formConstraints1.gridx = 0;
        formConstraints1.gridy = 3;
        formPanel1.add(genereLabel, formConstraints1);

        genereTextField = new JTextField();
        genereTextField.setPreferredSize(new Dimension(200, 25));
        formConstraints1.gridx = 1;
        formConstraints1.gridy = 3;
        formPanel1.add(genereTextField, formConstraints1);

        // Valutazione
        JLabel valutazioneLabel = new JLabel("Valutazione:");
        valutazioneLabel.setFont(new Font("Arial", Font.BOLD, 15));
        formConstraints1.gridx = 0;
        formConstraints1.gridy = 4;
        formPanel1.add(valutazioneLabel, formConstraints1);

        valutazioneComboBox = new JComboBox<>(Libro.valutazione.values());
        valutazioneComboBox.setPreferredSize(new Dimension(200, 25));
        formConstraints1.gridx = 1;
        formConstraints1.gridy = 4;
        formPanel1.add(valutazioneComboBox, formConstraints1);

        // Stato Lettura
        JLabel statoLetturaLabel = new JLabel("Stato Lettura:");
        statoLetturaLabel.setFont(new Font("Arial", Font.BOLD, 15));
        formConstraints1.gridx = 0;
        formConstraints1.gridy = 5;
        formPanel1.add(statoLetturaLabel, formConstraints1);

        statoLetturaComboBox = new JComboBox<>(Libro.statolettura.values());
        statoLetturaComboBox.setPreferredSize(new Dimension(200, 25));
        formConstraints1.gridx = 1;
        formConstraints1.gridy = 5;
        formPanel1.add(statoLetturaComboBox, formConstraints1);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        

        JButton filterButton = new JButton("Filter");
        buttonPanel.add(filterButton);

        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(deleteButton);

        JButton sortButton = new JButton("Sort");
        buttonPanel.add(sortButton);
        
        JButton updateButton = new JButton("Update");
        buttonPanel.add(updateButton);
        
        JButton SaveButton = new JButton("Save");
        buttonPanel.add(SaveButton);
        
        
        
        
        JButton[] buttons = { addButton, filterButton, deleteButton, sortButton,updateButton,SaveButton};

        for (JButton button : buttons) {
            button.setBackground(new Color(240, 240, 240));
            button.setPreferredSize(new Dimension(80, 30));
            button.setForeground(Color.DARK_GRAY);
            button.setFocusPainted(false);
            button.setBorder(
                    BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
                            BorderFactory.createEmptyBorder(8, 16, 8, 16)));
            button.setFont(button.getFont().deriveFont(Font.BOLD, 12f));

            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(220, 220, 220));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(240, 240, 240));
                }
            });
        }
        
        

        
        JButton closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(100, 30));
        closeButton.setBackground(new Color(240, 240, 240));
        closeButton.setForeground(Color.DARK_GRAY);

        // Apply additional visual enhancements
        closeButton.setFocusPainted(false);
        closeButton.setBorder(
                BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
                        BorderFactory.createEmptyBorder(8, 16, 8, 16)));
        closeButton.setFont(closeButton.getFont().deriveFont(Font.BOLD, 12f));

        // Add a hover effect
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(new Color(240, 240, 240));
            }
        });
        
       


        // Add an ActionListener to the "Close" button
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window or exit the application
                // You can replace this line with the necessary code to close the window
                System.exit(0);
            }
        });

        // Add the "Close" button to the button panel
        buttonPanel.add(closeButton);

        // Table
        tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel for table and buttons
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        
        
     // Aggiungere un pannello di ricerca sopra la tabella
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 15));
        searchPanel.add(searchLabel);

        searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension(200, 25));
        searchPanel.add(searchTextField);

        JLabel searchTypeLabel = new JLabel("By:");
        searchTypeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        searchPanel.add(searchTypeLabel);

        searchTypeComboBox = new JComboBox<>(new String[]{"Title", "Author", "ISBN"});
        searchPanel.add(searchTypeComboBox);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 25));
        searchPanel.add(searchButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(100, 25));
        searchPanel.add(resetButton);

        // Aggiungere il pannello di ricerca sopra la tabella
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        
        

        // Add panels to the main frame
        add(formPanel1, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);

        tableModel.addColumn("<html><b>ISBN</b></html>");
        tableModel.addColumn("<html><b>Titolo</b></html>");
        tableModel.addColumn("<html><b>Autore</b></html>");
        tableModel.addColumn("<html><b>Genere</b></html>");
        tableModel.addColumn("<html><b>Valutazione</b></html>");
        tableModel.addColumn("<html><b>Stato lettura</b></html>");

        // Add an ActionListener to the "Close" button

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String title = titleTextField.getText();
                    String author = authorTextField.getText();
                    String isbn = isbnTextField.getText();
                    String genere = genereTextField.getText();
                    Libro.valutazione val = (Libro.valutazione) valutazioneComboBox.getSelectedItem();
                    Libro.statolettura stato = (Libro.statolettura) statoLetturaComboBox.getSelectedItem();
                    
                    // Validazione dei dati
                    if(title.isEmpty() || author.isEmpty() || isbn.isEmpty() || genere.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati!", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    // Aggiungi il libro all'oggetto bookShop
                    bookShop.addBook(author, title, genere, val, stato, isbn);
                    
                    // Aggiungi il libro alla tabella
                    Object[] rowData = { isbn, title, author, genere, val, stato };
                    tableModel.addRow(rowData);
                    
                    // Pulisci i campi di input
                    clearTextFields();
                    
                    // Salva i libri nel file
                    bookShop.salvalibriinfile();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Errore durante l'aggiunta del libro: " + ex.getMessage(), 
                        "Errore", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

     // Sostituisci l'action listener esistente del sortButton con questo:
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crea un pannello per la selezione del criterio di ordinamento
                JPanel sortPanel = new JPanel();
                sortPanel.setLayout(new GridLayout(3, 1, 5, 5));
                
                // Titolo del pannello
                JLabel titleLabel = new JLabel("Seleziona il criterio di ordinamento:");
                titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
                sortPanel.add(titleLabel);
                
                // Opzioni di ordinamento
                String[] sortOptions = {"Titolo (A-Z)", "Autore (A-Z)"};
                JComboBox<String> sortCriteriaComboBox = new JComboBox<>(sortOptions);
                sortPanel.add(sortCriteriaComboBox);
                
                // Mostra il dialog di selezione
                int result = JOptionPane.showConfirmDialog(
                    null, 
                    sortPanel, 
                    "Ordina libri", 
                    JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.PLAIN_MESSAGE
                );
                
                if (result == JOptionPane.OK_OPTION) {
                    // Applica l'ordinamento in base alla selezione
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
                    
                    int columnIndex;
                    if (sortCriteriaComboBox.getSelectedIndex() == 0) {
                        // Ordina per titolo (colonna 1)
                        columnIndex = 1;
                        sorter.setComparator(columnIndex, String.CASE_INSENSITIVE_ORDER);
                    } else {
                        // Ordina per autore (colonna 2)
                        columnIndex = 2;
                        sorter.setComparator(columnIndex, String.CASE_INSENSITIVE_ORDER);
                    }
                    
                    table.setRowSorter(sorter);
                    ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
                    sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING));
                    sorter.setSortKeys(sortKeys);
                    sorter.sort();
                    
                    // Mostra messaggio di conferma
                    String criterio = (columnIndex == 1) ? "titolo" : "autore";
                    JOptionPane.showMessageDialog(null, "Libri ordinati per " + criterio + " in ordine alfabetico.");
                }
            }
        });
        
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crea il pannello per il filtro
                JPanel filterPanel = new JPanel();
                filterPanel.setLayout(new GridLayout(3, 2, 10, 10));
                
                // Filtro per genere
                JLabel genereLabel = new JLabel("Genere:");
                filterPanel.add(genereLabel);
                
                // Ottieni tutti i generi unici
                ArrayList<String> generi = new ArrayList<>();
                generi.add("Tutti"); // Opzione per mostrare tutti i generi
                
                for (Libro libro : bookShop.getLibri()) {
                    if (!generi.contains(libro.getGenere())) {
                        generi.add(libro.getGenere());
                    }
                }
                
                filterGenereComboBox = new JComboBox<>(generi.toArray(new String[0]));
                filterPanel.add(filterGenereComboBox);
                
                // Filtro per stato di lettura
                JLabel statoLabel = new JLabel("Stato lettura:");
                filterPanel.add(statoLabel);
                
                // Crea un array con "Tutti" come prima opzione seguito dai valori dell'enumerazione
                String[] statoOptions = new String[Libro.statolettura.values().length + 1];
                statoOptions[0] = "Tutti";
                for (int i = 0; i < Libro.statolettura.values().length; i++) {
                    statoOptions[i + 1] = Libro.statolettura.values()[i].toString();
                }
                
                JComboBox<String> statoComboBox = new JComboBox<>(statoOptions);
                filterPanel.add(statoComboBox);
                
                // Pulsanti di conferma
                JButton applyButton = new JButton("Applica filtri");
                filterPanel.add(applyButton);
                
                JButton cancelButton = new JButton("Annulla");
                filterPanel.add(cancelButton);
                
                // Crea il dialogo
                JDialog filterDialog = new JDialog();
                filterDialog.setTitle("Filtra libri");
                filterDialog.setSize(300, 200);
                filterDialog.setLocationRelativeTo(null);
                filterDialog.setModal(true);
                filterDialog.setLayout(new BorderLayout());
                filterDialog.add(filterPanel, BorderLayout.CENTER);
                
                // Azione per il pulsante Applica
                applyButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String genereSelezionato = (String) filterGenereComboBox.getSelectedItem();
                        String statoSelezionato = (String) statoComboBox.getSelectedItem();
                        
                        // Applica i filtri
                        applicaFiltri(genereSelezionato, statoSelezionato);
                        
                        filterDialog.dispose();
                    }

                    private void applicaFiltri(String genere, String stato) {
                        // Svuota la tabella
                        while (tableModel.getRowCount() > 0) {
                            tableModel.removeRow(0);
                        }
                        
                        // Ottieni tutti i libri
                        Libro[] libri = bookShop.getLibri();
                        
                        // Applica i filtri
                        for (Libro libro : libri) {
                            boolean matchGenere = genere.equals("Tutti") || libro.getGenere().equals(genere);
                            boolean matchStato = stato.equals("Tutti") || libro.getL().toString().equals(stato);
                            
                            if (matchGenere && matchStato) {
                                Object[] rowData = {
                                    libro.getISBN(),
                                    libro.getTitolo(),
                                    libro.getAutore(),
                                    libro.getGenere(),
                                    libro.getV(),
                                    libro.getL()
                                };
                                tableModel.addRow(rowData);
                            }
                        }
                    }
                });
                
                // Azione per il pulsante Annulla
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        filterDialog.dispose();
                    }
                });
                
                filterDialog.setVisible(true);
            }
        });

        

     // Sostituisci l'action listener esistente del deleteButton con questo:
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ottieni l'indice della riga selezionata
                int selectedRowIndex = table.getSelectedRow();

                if (selectedRowIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Per favore, seleziona un libro da eliminare.");
                    return;
                }

                // Ottieni l'ISBN dalla riga selezionata
                String isbn = (String) tableModel.getValueAt(selectedRowIndex, 0);
                
                // Chiedi conferma prima dell'eliminazione
                int confirm = JOptionPane.showConfirmDialog(null, 
                        "Sei sicuro di voler eliminare il libro con ISBN: " + isbn + "?", 
                        "Conferma eliminazione", 
                        JOptionPane.YES_NO_OPTION);
                        
                if (confirm == JOptionPane.YES_OPTION) {
                    // Elimina il libro dall'oggetto bookShop
                    boolean bookDeleted = bookShop.deleteBook(isbn);
                    
                    if (bookDeleted) {
                        // Rimuovi la riga dalla tabella
                        tableModel.removeRow(selectedRowIndex);
                        JOptionPane.showMessageDialog(null, "Libro eliminato con successo!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione del libro!");
                    }
                }
            }
        });
        
     // Aggiungere questi action listener per i pulsanti di ricerca e reset
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String testoRicerca = searchTextField.getText().trim();
                String tipoRicerca = (String) searchTypeComboBox.getSelectedItem();
                
                if (!testoRicerca.isEmpty()) {
                    cercaLibri(testoRicerca, tipoRicerca);
                } else {
                    JOptionPane.showMessageDialog(null, "Inserisci un testo per la ricerca");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchTextField.setText("");
                aggiornaTabellaDaLibreria();
            }
        });
        
        SaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookShop.salvalibriinfile();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the selected row index
                int selectedRowIndex = table.getSelectedRow();

                if (selectedRowIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Per favore, seleziona un libro da aggiornare.");
                    return;
                }

                // Get the values from the selected row
                String isbn = (String) tableModel.getValueAt(selectedRowIndex, 0);
                String title = (String) tableModel.getValueAt(selectedRowIndex, 1);
                String author = (String) tableModel.getValueAt(selectedRowIndex, 2);
                String genere = (String) tableModel.getValueAt(selectedRowIndex, 3);
                Libro.valutazione val = (Libro.valutazione) tableModel.getValueAt(selectedRowIndex, 4);
                Libro.statolettura stato = (Libro.statolettura) tableModel.getValueAt(selectedRowIndex, 5);

                // Mostra un dialogo per aggiornare le informazioni del libro
                JTextField isbnfield = new JTextField(isbn);
                JTextField titleField = new JTextField(title);
                JTextField authorField = new JTextField(author);
                JTextField genereField = new JTextField(genere);
                
                JComboBox<Libro.valutazione> valComboBox = new JComboBox<>(Libro.valutazione.values());
                valComboBox.setSelectedItem(val);
                
                JComboBox<Libro.statolettura> statoComboBox = new JComboBox<>(Libro.statolettura.values());
                statoComboBox.setSelectedItem(stato);

                Object[] fields = { 
                    "ISBN:",    isbnfield ,
                    "Titolo:", titleField, 
                    "Autore:", authorField, 
                    "Genere:", genereField,
                    "Valutazione:", valComboBox,
                    "Stato Lettura:", statoComboBox
                };

                int result = JOptionPane.showConfirmDialog(null, fields, "Aggiorna Libro", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    // Aggiorna le informazioni del libro
                    String newTitle = titleField.getText();
                    String newAuthor = authorField.getText();
                    String newGenere = genereField.getText();
                    Libro.valutazione newVal = (Libro.valutazione) valComboBox.getSelectedItem();
                    Libro.statolettura newStato = (Libro.statolettura) statoComboBox.getSelectedItem();

                    // Aggiorna i valori nella tabella
                    tableModel.setValueAt(newTitle, selectedRowIndex, 1);
                    tableModel.setValueAt(newAuthor, selectedRowIndex, 2);
                    tableModel.setValueAt(newGenere, selectedRowIndex, 3);
                    tableModel.setValueAt(newVal, selectedRowIndex, 4);
                    tableModel.setValueAt(newStato, selectedRowIndex, 5);

                    // Aggiorna le informazioni del libro nel BookShop
                    bookShop.updateBook(newAuthor, newTitle, newGenere, newVal, newStato, isbn);
                }
            }
        });
        aggiornaTabellaDaLibreria();

        
    }
    
    

 // Sostituire il metodo esistente con questa versione migliorata
    private void aggiornaTabellaDaLibreria() {
        // Pulisci la tabella
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        
        // Aggiungi tutti i libri dalla libreria
        Libro[] libri = bookShop.getLibri();
        for (Libro libro : libri) {
            Object[] rowData = {
                libro.getISBN(),
                libro.getTitolo(),
                libro.getAutore(),
                libro.getGenere(),
                libro.getV(),
                libro.getL()
            };
            tableModel.addRow(rowData);
        }
    }
    
    private void cercaLibri(String testo, String tipo) {
        // Svuota la tabella
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        
        // Ottieni tutti i libri
        Libro[] libri = bookShop.getLibri();
        
        // Cerca i libri in base al tipo selezionato
        for (Libro libro : libri) {
            boolean match = false;
            
            switch (tipo) {
                case "Title":
                    match = libro.getTitolo().toLowerCase().contains(testo.toLowerCase());
                    break;
                case "Author":
                    match = libro.getAutore().toLowerCase().contains(testo.toLowerCase());
                    break;
                case "ISBN":
                    match = libro.getISBN().toLowerCase().contains(testo.toLowerCase());
                    break;
            }
            
            if (match) {
                Object[] rowData = {
                    libro.getISBN(),
                    libro.getTitolo(),
                    libro.getAutore(),
                    libro.getGenere(),
                    libro.getV(),
                    libro.getL()
                };
                tableModel.addRow(rowData);
            }
        }
    }
    
    

    private void clearTextFields() {
        titleTextField.setText("");
        authorTextField.setText("");
        isbnTextField.setText("");
        genereTextField.setText("");
        valutazioneComboBox.setSelectedIndex(0);
        statoLetturaComboBox.setSelectedIndex(0);
    }

    private int findRowIndexByBookId(String isbn) {
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            String id = (String)tableModel.getValueAt(row, 0);
            if (id.equals(isbn)) {
                return row;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LibreriaSystem bookManagementSystem = new LibreriaSystem();
        bookManagementSystem.setVisible(true);
    }
}
