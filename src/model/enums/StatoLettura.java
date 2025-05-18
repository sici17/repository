package model.enums;

public enum StatoLettura {
    LETTO("Letto"),
    
    LEGGERE("Da leggere"),
    
    LETTURA("In lettura");
    
    private final String descrizione;
    StatoLettura(String descrizione) {
        this.descrizione = descrizione;
    }
    
    public String getDescrizione() {
        return descrizione;
    }
    
    @Override
    public String toString() {
        return descrizione;
    }
}