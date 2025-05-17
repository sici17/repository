package model.accesslogic;


/**
 * Factory Method che crea istanze  per gestire file XML.
 * Nota: questa è una classe segnaposto, l'implementazione vera
 * dovrebbe essere creata quando necessario.
 */
public class XMLLibroFactory implements LibroFactory {
    
    @Override
    public LibroInt creaLibro(int capacitaMassima, String percorsoFile) {
        // Implementazione segnaposto  in futuro si potrebbe
        // creare una vera implementazione LibroDAOImpl che usa XML
        throw new UnsupportedOperationException("XML DAO non ancora implementato");
    }
}