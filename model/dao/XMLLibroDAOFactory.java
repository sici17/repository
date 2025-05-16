package model.dao;


/**
 * Factory Method che crea istanze di LibroDAO per gestire file XML.
 * Nota: questa è una classe segnaposto, l'implementazione vera
 * dovrebbe essere creata quando necessario.
 */
public class XMLLibroDAOFactory implements LibroDAOFactory {
    
    @Override
    public LibroDAO creaLibroDAO(int capacitaMassima, String percorsoFile) {
        // Implementazione segnaposto - in futuro si potrebbe
        // creare una vera implementazione LibroDAOImpl che usa XML
        throw new UnsupportedOperationException("XML DAO non ancora implementato");
    }
}