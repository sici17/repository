package util;

import java.io.File;


  //classe di utilità per la gestione dei file.
 
public class FileUtils {
    
  
    public static String assicuraEstensioneJson(String path) {
        if (path == null || path.trim().isEmpty()) {
            return Constants.DEFAULT_FILE_PATH;
        }
        if (!path.toLowerCase().endsWith(".json")) {
            path = path.replaceAll("\\.[^.]*$", "") + ".json";
        }   
        return path;
    }
   
    public static boolean creaDirectories(String path) {
        File file = new File(path);
        File parent = file.getParentFile();
        
        if (parent != null && !parent.exists()) {
            return parent.mkdirs();
        }
        
        return true;
    }
}