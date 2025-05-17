package model.observer;

import java.util.ArrayList;
import java.util.List;

import model.observer.LibreriaObserver.TipoEvento;

public class LibreriaSubject {
    
    protected final List<LibreriaObserver> observers = new ArrayList<>();
    
    /**
     * Registra un observer per ricevere notifiche.
     * 
     * @param observer L'observer da registrare
     */
    public void registraObserver(LibreriaObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    /**
     * Rimuove un observer dalla lista degli observer registrati.
     * 
     * @param observer L'observer da rimuovere
     */
    public void rimuoviObserver(LibreriaObserver observer) {
        observers.remove(observer);
    }
    
    /**
     * Notifica tutti gli observer registrati che è avvenuto un cambiamento.
     * 
     * @param tipoEvento Il tipo di evento che è avvenuto
     * @param dettagli Eventuali dettagli aggiuntivi sull'evento
     */
    public void notificaObserver(TipoEvento tipoEvento, Object dettagli) {
        for (LibreriaObserver observer : observers) {
            observer.aggiornamento(tipoEvento, dettagli);
        }
    }
}
