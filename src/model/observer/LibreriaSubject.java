package model.observer;

import java.util.ArrayList;
import java.util.List;

import model.observer.LibreriaObserver.TipoEvento;

public class LibreriaSubject {
    
    protected final List<LibreriaObserver> observers = new ArrayList<>();
    
  
    public void registraObserver(LibreriaObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }
   
    public void rimuoviObserver(LibreriaObserver observer) {
        observers.remove(observer);
    }
   
    public void notificaObserver(TipoEvento tipoEvento, Object dettagli) {
        for (LibreriaObserver observer : observers) {
            observer.aggiornamento(tipoEvento, dettagli);
        }
    }
}
