package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.BookManagerController;
import controller.LibreriaController;
import util.Constants;

/**
 * Frame principale dell'applicazione.
 */
public class MainFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private final BookManagerController bookManagerController;
    private final JTable tableLibri;
    private final DefaultTableModel tableModel;
    
    /**
     * Costruttore che inizializza il frame principale.
     * 
     * @param percorsoFile Percorso del file JSON
     */
    public MainFrame(String percorsoFile) {
        // Inizializzazione del modello della tabella
        tableModel = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rende tutte le celle non modificabili
            }
        };
        
        // Inizializzazione dei controller
        LibreriaController libreriaController = LibreriaController.getInstance(100, percorsoFile);
        bookManagerController = new BookManagerController(libreriaController, tableModel);
        
        // Configurazione del frame
        setTitle(Constants.APP_TITLE);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Gestione della chiusura della finestra
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Non salvare automaticamente
                dispose();
                System.exit(0);
            }
        });
        
        // Inizializzazione dei componenti UI
        
        // Titolo
        JLabel titleLabel = new JLabel(Constants.APP_TITLE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        
        // Tabella libri
        tableLibri = new JTable(tableModel);
        tableLibri.setRowHeight(25);
        tableLibri.setAutoCreateRowSorter(true);
        tableLibri.getTableHeader().setReorderingAllowed(false);
        tableLibri.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableLibri.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        // Pannello di ricerca
        SearchPanel searchPanel = new SearchPanel(bookManagerController);
        
        // Pannello per il form di input
        BookFormPanel formPanel = new BookFormPanel(bookManagerController);
        
        // Pannello per la tabella
        BookTablePanel tablePanel = new BookTablePanel(tableLibri, bookManagerController);
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        
        // Aggiunta dei componenti al frame
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        
        // Imposta margini esterni
        int borderSize = 20;
        getRootPane().setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
        
        pack();
        setMinimumSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }
}
