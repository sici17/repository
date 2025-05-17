package model.observer;


public interface LibreriaObserver {
    
    
    
    public enum TipoEvento {
        LIBRO_AGGIUNTO,
        LIBRO_AGGIORNATO,
        LIBRO_ELIMINATO,
        LIBRERIA_CARICATA,
        LIBRERIA_SALVATA,
        FILTRO_APPLICATO
    }
    
    /**
     * Questo metodo viene chiamato quando ci sono cambiamenti nella libreria.
     * 
     * @param tipoEvento Il tipo di evento che è avvenuto
     * @param dettagli Eventuali dettagli aggiuntivi sull'evento
     */
    void aggiornamento(TipoEvento tipoEvento, Object dettagli);
    
    
}


