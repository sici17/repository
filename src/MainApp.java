
import view.MainFrame;


  //classe principale che avvia l'applicazione.
 
public class MainApp {
   
    public static void main(String[] args) {
        // configurazione look and feel
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // avvio dell'interfaccia grafica
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
        // percorso del file json per la persistenza
        String percorsoFile = System.getProperty("user.home") + "/libreria.json";        
        MainFrame frame = new MainFrame(percorsoFile);
        frame.setVisible(true);
            }
        });
    }
}
