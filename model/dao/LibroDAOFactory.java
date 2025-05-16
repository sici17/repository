package model.dao;


/**
 * Interfaccia per il Factory Method che crea istanze di LibroDAO.
 */
public interface LibroDAOFactory {
    
    /**
     * Crea un'istanza di LibroDAO.
     * 
     * @param capacitaMassima Capacità massima della libreria
     * @param percorsoFile Percorso del file per la persistenza
     * @return Una nuova istanza di LibroDAO
     */
    LibroDAO creaLibroDAO(int capacitaMassima, String percorsoFile);
}