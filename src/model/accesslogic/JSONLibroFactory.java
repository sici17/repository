package model.accesslogic;



public class JSONLibroFactory implements LibroFactory {
    
    @Override
    public LibroInt creaLibro(int capacitaMassima, String percorsoFile) {
        return new LibroImpl(capacitaMassima, percorsoFile);
    }
}
