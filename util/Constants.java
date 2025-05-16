package util;

/**
 * Classe che contiene le costanti utilizzate nell'applicazione.
 */
public class Constants {
    
    // Percorsi file
    public static final String DEFAULT_FILE_PATH = "libreria.json";
    
    // Titoli finestre
    public static final String APP_TITLE = "Libreria Personale";
    public static final String DIALOG_FILTER_TITLE = "Filtra Libri";
    public static final String DIALOG_UPDATE_TITLE = "Aggiorna Libro";
    
    // Campi di ricerca
    public static final String SEARCH_FIELD_TITLE = "titolo";
    public static final String SEARCH_FIELD_AUTHOR = "autore";
    public static final String SEARCH_FIELD_ISBN = "isbn";
    
    // Messaggi
    public static final String MSG_SAVE_SUCCESS = "Libri salvati con successo";
    public static final String MSG_SAVE_ERROR = "Errore durante il salvataggio";
    public static final String MSG_LOAD_SUCCESS = "Caricati %d libri";
    public static final String MSG_LOAD_ERROR = "Errore durante il caricamento";
    public static final String MSG_ADD_SUCCESS = "Libro aggiunto con successo";
    public static final String MSG_ADD_ERROR = "Errore durante l'aggiunta del libro";
    public static final String MSG_UPDATE_SUCCESS = "Libro aggiornato con successo";
    public static final String MSG_UPDATE_ERROR = "Errore durante l'aggiornamento del libro";
    public static final String MSG_DELETE_SUCCESS = "Libro eliminato con successo";
    public static final String MSG_DELETE_ERROR = "Errore durante l'eliminazione del libro";
    public static final String MSG_EMPTY_FIELDS = "Tutti i campi devono essere compilati";
    public static final String MSG_SELECT_BOOK = "Seleziona un libro prima di continuare";
    public static final String MSG_CONFIRM_DELETE = "Sei sicuro di voler eliminare il libro con ISBN: %s?";
    
    // Label colonne tabella
    public static final String[] TABLE_COLUMN_NAMES = {
        "ISBN", "Titolo", "Autore", "Genere", "Valutazione", "Stato lettura"
    };
    
    // Dimensioni componenti UI
    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 600;
    public static final int TEXT_FIELD_WIDTH = 200;
    public static final int TEXT_FIELD_HEIGHT = 25;
    public static final int BUTTON_WIDTH = 100;
    public static final int BUTTON_HEIGHT = 30;
    
    // Colori
    public static final java.awt.Color COLOR_BUTTON_BG = new java.awt.Color(240, 240, 240);
    public static final java.awt.Color COLOR_BUTTON_BORDER = new java.awt.Color(100, 100, 100);
    public static final java.awt.Color COLOR_BUTTON_FG = java.awt.Color.DARK_GRAY;
    public static final java.awt.Color COLOR_BUTTON_HOVER = new java.awt.Color(220, 220, 220);
    
    // Filtri
    public static final String FILTER_ALL = "Tutti";
    
    public static final String[] GENERI_PREDEFINITI = {
            "Romanzo", "Saggio", "Biografia","Fantasy","Fantascienza","Horror","Thriller",
            "Psicologico", "Avventura", "Azione"
        };
}
