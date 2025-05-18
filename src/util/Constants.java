package util;

// costanti utili
public class Constants {
    
    // percorsi file
    public static final String DEFAULT_FILE_PATH = "libreria.json";
    
    // titoli finestre
    public static final String APP_TITLE = "libreria Personale";
    public static final String DIALOG_FILTER_TITLE = "filtra Libri";
    public static final String DIALOG_UPDATE_TITLE = "aggiorna Libro";
    
    // campi di ricerca
    public static final String SEARCH_FIELD_TITLE = "titolo";
    public static final String SEARCH_FIELD_AUTHOR = "autore";
    public static final String SEARCH_FIELD_ISBN = "isbn";
    
    // messaggi
    public static final String MSG_SAVE_SUCCESS = "libri salvati con successo";
    public static final String MSG_SAVE_ERROR = "errore durante il salvataggio";
    public static final String MSG_LOAD_SUCCESS = "caricati %d libri";
    public static final String MSG_LOAD_ERROR = "errore durante il caricamento";
    public static final String MSG_ADD_SUCCESS = "libro aggiunto con successo";
    public static final String MSG_ADD_ERROR = "errore durante l'aggiunta del libro";
    public static final String MSG_UPDATE_SUCCESS = "libro aggiornato con successo";
    public static final String MSG_UPDATE_ERROR = "errore durante l'aggiornamento del libro";
    public static final String MSG_DELETE_SUCCESS = "libro eliminato con successo";
    public static final String MSG_DELETE_ERROR = "errore durante l'eliminazione del libro";
    public static final String MSG_EMPTY_FIELDS = "tutti i campi devono essere compilati";
    public static final String MSG_SELECT_BOOK = "seleziona un libro prima di continuare";
    public static final String MSG_CONFIRM_DELETE = "sei sicuro di voler eliminare il libro con ISBN: %s?";
    
    // label colonne tabella
    public static final String[] TABLE_COLUMN_NAMES = {
        "ISBN", "Titolo", "Autore", "Genere", "Valutazione", "Stato lettura"
    };
    
    // dimensioni componenti UI
    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 600;
    public static final int TEXT_FIELD_WIDTH = 200;
    public static final int TEXT_FIELD_HEIGHT = 25;
    public static final int BUTTON_WIDTH = 100;
    public static final int BUTTON_HEIGHT = 30;
    
    // colori
    public static final java.awt.Color COLOR_BUTTON_BG = new java.awt.Color(240, 240, 240);
    public static final java.awt.Color COLOR_BUTTON_BORDER = new java.awt.Color(100, 100, 100);
    public static final java.awt.Color COLOR_BUTTON_FG = java.awt.Color.DARK_GRAY;
    public static final java.awt.Color COLOR_BUTTON_HOVER = new java.awt.Color(220, 220, 220);
    
    // filtri
    public static final String[] GENERI_PREDEFINITI = {
            "Romanzo", "Saggio", "Biografia","Fantasy","Fantascienza","Horror","Thriller",
            "Psicologico", "Avventura", "Azione"
        };
}
