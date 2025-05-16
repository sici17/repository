package model.dao;


/**
 * Factory Method che crea istanze di LibroDAOImpl per gestire file JSON.
 */
public class JSONLibroDAOFactory implements LibroDAOFactory {
    
    @Override
    public LibroDAO creaLibroDAO(int capacitaMassima, String percorsoFile) {
        return new LibroDAOImpl(capacitaMassima, percorsoFile);
    }
}
