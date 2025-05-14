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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private Libreria bookShop;
    private DefaultTableModel tableModel;

    public LibreriaSystem() {
        this.bookShop = new Libreria(100, "C:\\Users\\franc\\Desktop\\libri.txt");
        this.bookShop.caricaLibriDaFile();
        //aggiornaTabellaDaLibreria();
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
        
        
        
        JComboBox<Libro.valutazione> valutazioneComboBox;
        JComboBox<Libro.statolettura> statoLetturaComboBox;

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

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 15));

        formConstraints.gridx = 0;
        formConstraints.gridy = 3;
        formPanel.add(quantityLabel, formConstraints);

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

        JButton addButton = new JButton("Add Book");
        buttonPanel.add(addButton);

        JButton sellButton = new JButton("Sell Book");
        buttonPanel.add(sellButton);

        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(deleteButton);

        JButton sortButton = new JButton("Sort");
        buttonPanel.add(sortButton);
        JButton[] buttons = { addButton, sellButton, deleteButton, sortButton };

        for (JButton button : buttons) {
            button.setBackground(new Color(240, 240, 240));
            button.setPreferredSize(new Dimension(105, 30));
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

        JButton updateButton = new JButton("Update");
        buttonPanel.add(updateButton);
        updateButton.setBackground(new Color(240, 240, 240));
        updateButton.setForeground(Color.DARK_GRAY);
        updateButton.setPreferredSize(new Dimension(100, 30));
        // Apply additional visual enhancements
        updateButton.setFocusPainted(false);
        updateButton.setBorder(
                BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
                        BorderFactory.createEmptyBorder(8, 16, 8, 16)));
        updateButton.setFont(updateButton.getFont().deriveFont(Font.BOLD, 12f));

        // Add a hover effect
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateButton.setBackground(new Color(220, 220, 220));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateButton.setBackground(new Color(240, 240, 240));
            }
        });
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

        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Sort the table data in ascending order based on the book titles
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
                sorter.setComparator(1, String.CASE_INSENSITIVE_ORDER); // Sort the second column (title) in
                                                                        // case-insensitive order
                table.setRowSorter(sorter);
                ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
                sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING)); // Sort the second column (title) in
                                                                                // ascending order
                sorter.setSortKeys(sortKeys);
                sorter.sort();
            }
        });

        sellButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(null, "Enter the book title:");

                // Find the book using the title
                Libro book = bookShop.findBook(title);

                if (book != null) {
                    // Display the book details
                    StringBuilder sb = new StringBuilder();
                    sb.append("ISBN: ").append(book.getISBN()).append("\n");
                    sb.append("Title: ").append(book.getTitolo()).append("\n");
                    sb.append("Author: ").append(book.getAutore()).append("\n");
                    sb.append("Genere: ").append(book.getGenere()).append("\n");
                    sb.append("Valutazione: ").append(book.getV()).append("\n");
                    sb.append("Stato lettura: ").append(book.getL()).append("\n");
                    JOptionPane.showMessageDialog(null, sb.toString());

                    // Get the quantity to sell
                    String quantityInput = JOptionPane.showInputDialog(null, "Enter the quantity to sell:");
                    if (quantityInput.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No quantity entered. Please try again.");
                        return;
                    }
                    int quantity = Integer.parseInt(quantityInput);

                    //if (quantity > 0 && quantity <= book.getQuantity()) {
                        // Sell the book
                        //bookShop.sellBook(title, quantity);

                        // Show the success message
                        JOptionPane.showMessageDialog(null, "Book sold successfully!");

                        // Update the quantity in the table
                        //int rowIndex = findRowIndexByBookId(book.getBookId());
                        //if (rowIndex != -1) {
                            //tableModel.setValueAt(book.getQuantity(), rowIndex, 4);
                        }
                    //} else if (quantity > book.getQuantity()) {
                        //JOptionPane.showMessageDialog(null, "Requested quantity exceeds the available quantity. Only "
                                //+ book.getQuantity() + " books available.");
                   // } else {
                    //    JOptionPane.showMessageDialog(null, "Invalid quantity entered. Please try again.");
                   // }
                //} else {
                    JOptionPane.showMessageDialog(null, "Book not found!");
                }

                //clearTextFields();
            }
        );

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbn = (JOptionPane.showInputDialog(null, "Enter the book ID to delete:"));

                boolean bookDeleted = bookShop.deleteBook(isbn);

                if (bookDeleted) {
                    JOptionPane.showMessageDialog(null, "Book deleted successfully!");

                    // Remove the book from the table
                    int rowIndex = findRowIndexByBookId(isbn);
                    if (rowIndex != -1) {
                        tableModel.removeRow(rowIndex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Book not found!");
                }

                clearTextFields();
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
    }

    private void aggiornaTabellaDaLibreria() {
        // TODO Auto-generated method stub
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
