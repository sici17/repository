package model.accesslogic;



  //interfaccia per il Factory Method che crea istanze 
 
public interface LibroFactory {
    
    
    LibroInt creaLibro(int capacitaMassima, String percorsoFile);
}