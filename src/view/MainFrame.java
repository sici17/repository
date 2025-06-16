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


 // frame principale dell'applicazione.

public class MainFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private final BookManagerController bookManagerController;
    private final JTable tableLibri;
    private final DefaultTableModel tableModel;
   
    public MainFrame(String percorsoFile) {
        //inizializzazione del modello della tabella
        tableModel = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // rende tutte le celle non modificabili
            }
        };
        
        // inizializzazione dei controller
        LibreriaController libreriaController = LibreriaController.getInstance(100, percorsoFile, 
                LibreriaController.TipoPersistenza.JSON);
        bookManagerController = new BookManagerController(libreriaController, tableModel);
        
        // configurazione del frame
        setTitle(Constants.APP_TITLE);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // gestione della chiusura della finestra
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // non salvare automaticamente
                dispose();
                System.exit(0);
            }
        });
        
        // inizializzazione dei componenti UI
        
        // titolo
        JLabel titleLabel = new JLabel(Constants.APP_TITLE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        
        // tabella libri
        tableLibri = new JTable(tableModel);
        tableLibri.setRowHeight(25);
        tableLibri.setAutoCreateRowSorter(true);
        tableLibri.getTableHeader().setReorderingAllowed(false);
        tableLibri.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableLibri.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        // pannello di ricerca
        SearchPanel searchPanel = new SearchPanel(bookManagerController);
        
        // pannello per il form di input
        BookFormPanel formPanel = new BookFormPanel(bookManagerController);
        
        // pannello per la tabella
        BookTablePanel tablePanel = new BookTablePanel(tableLibri, bookManagerController);
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        
        // aggiunta dei componenti al frame
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        
        // imposta margini esterni
        int borderSize = 20;
        getRootPane().setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
        
        pack();
        setMinimumSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }
}