package interfaz;

import controlador.PuzzleControlador;
import modelo.PuzzleModelo;
import vista.PuzzleVista;

public class Puzzle15GUI {

    public static void main(String[] args) {
        PuzzleVista vista = new PuzzleVista();
        PuzzleModelo modelo = new PuzzleModelo();
        PuzzleControlador controlador = new PuzzleControlador(modelo, vista);
        vista.InicializarVista(controlador);
    }
}
