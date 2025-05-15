
import view.MainFrame;

/**
 * Classe principale che avvia l'applicazione.
 */
public class MainApp {
    
    /**
     * Metodo main che inizializza e mostra la finestra principale.
     * 
     * @param args Argomenti da riga di comando
     */
    public static void main(String[] args) {
        // Configurazione look and feel
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Avvio dell'interfaccia grafica
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Percorso del file JSON per la persistenza
                String percorsoFile = System.getProperty("user.home") + "/libreria.json";
                
                MainFrame frame = new MainFrame(percorsoFile);
                frame.setVisible(true);
            }
        });
    }
}
