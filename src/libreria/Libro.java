package libreria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;



public class Libro {
    
    private String titolo;
    private String autore;
    private String ISBN;
    private String genere;
    private valutazione V;
    private statolettura L;
    
    public enum valutazione{
        NEGATIVO,
        MEDIOCRE,
        BUONO,
        OTTIMO,
        PERFETTO
        
    }
    public enum statolettura{
        LETTO,
        LEGGERE,
        LETTURA,
        
    }
    @JsonCreator
    public Libro(@JsonProperty("titolo") String titolo, 
                 @JsonProperty("autore") String autore, 
                 @JsonProperty("ISBN") String iSBN, 
                 @JsonProperty("genere") String genere, 
                 @JsonProperty("valutazione") valutazione v, 
                 @JsonProperty("statolettura") statolettura l) {
        super();
        this.titolo = titolo;
        this.autore = autore;
        ISBN = iSBN;
        this.genere = genere;
        V = v;
        L = l;
    }
    
    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public String getAutore() {
        return autore;
    }
    public void setAutore(String autore) {
        this.autore = autore;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }
    public String getGenere() {
        return genere;
    }
    public void setGenere(String genere) {
        this.genere = genere;
    }
    public valutazione getV() {
        return V;
    }
    public void setV(valutazione v) {
        V = v;
    }
    public statolettura getL() {
        return L;
    }
    public void setL(statolettura l) {
        L = l;
    }
    @Override
    public String toString() {
        return "Libro [titolo=" + titolo + ", autore=" + autore + ", ISBN=" + ISBN + ", genere=" + genere + ", V=" + V
                + ", L=" + L + "]";
    }
   
    
    

}
