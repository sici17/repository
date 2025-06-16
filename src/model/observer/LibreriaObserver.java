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
    
    
    void aggiornamento(TipoEvento tipoEvento, Object dettagli);
    
    
}


