package model.accesslogic;



 //questa è una classe segnaposto che rappresenta la potenzialita 
 //del pattern factory, l'implementazione vera dovrebbe essere creata quando necessario.
 
public class XMLLibroFactory implements LibroFactory {
    
    @Override
    public LibroInt creaLibro(int capacitaMassima, String percorsoFile) {
        
        throw new UnsupportedOperationException("XML DAO non ancora implementato");
    }
}