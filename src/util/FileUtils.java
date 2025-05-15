package util;

import java.io.File;

/**
 * Classe di utilità per la gestione dei file.
 */
public class FileUtils {
    
    /**
     * Assicura che il percorso del file termini con l'estensione .json.
     * 
     * @param path Il percorso del file
     * @return Il percorso con l'estensione .json
     */
    public static String assicuraEstensioneJson(String path) {
        if (path == null || path.trim().isEmpty()) {
            return Constants.DEFAULT_FILE_PATH;
        }
        
        if (!path.toLowerCase().endsWith(".json")) {
            path = path.replaceAll("\\.[^.]*$", "") + ".json";
        }
        
        return path;
    }
    
    /**
     * Verifica se un file esiste.
     * 
     * @param path Il percorso del file
     * @return true se il file esiste, false altrimenti
     */
    public static boolean fileEsiste(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }
    
    /**
     * Crea le directory necessarie per un file.
     * 
     * @param path Il percorso del file
     * @return true se le directory sono state create con successo o esistono già
     */
    public static boolean creaDirectories(String path) {
        File file = new File(path);
        File parent = file.getParentFile();
        
        if (parent != null && !parent.exists()) {
            return parent.mkdirs();
        }
        
        return true;
    }
}