package model.enums;

public enum Valutazione {
   NEGATIVO("Negativo"),
   
   MEDIOCRE("Mediocre"),
   
   BUONO("Buono"),
   
   OTTIMO("Ottimo"),
   
   PERFETTO("Perfetto");
   
   private final String descrizione;
   
   Valutazione(String descrizione) {
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