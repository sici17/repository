package view;

import javax.swing.JPanel;

import controller.LibreriaController;
import model.observer.LibreriaObserver;

/**
  StatisticheLibreriaPanel - Un esempio di come utilizzare il pattern Observer
  Questo pannello mostrerebbe statistiche sulla libreria di libri e si aggiornerebbe 
  automaticamente quando i dati della libreria cambiano.
  Questa classe svolge il ruolo di ConcreteObserver nel pattern Observer.
 */
public class StatisticheLibreriaPanel extends JPanel implements LibreriaObserver {
    
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
    private LibreriaController libreriaController;

    @Override
    public void aggiornamento(TipoEvento tipoEvento, Object dettagli) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * Nel costruttore, la classe si registrerebbe come observer:
     * 
     * libreriaController.registraObserver(this);
     */
    
}