package model.command;

import java.util.List;
import model.Libro;


public interface FiltroCommand {
    List<Libro> esegui(List<Libro> libri);
    
}