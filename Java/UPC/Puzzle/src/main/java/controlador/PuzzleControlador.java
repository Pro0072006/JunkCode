package controlador;

import modelo.PuzzleModelo;
import vista.PuzzleVista;

public class PuzzleControlador {
    private PuzzleModelo modelo;
    private PuzzleVista vista;

    public PuzzleControlador(PuzzleModelo modelo, PuzzleVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void BotonFicha(String textoBoton) {
        if (textoBoton.equals("")) {
            return;
        }

        int ficha = Integer.parseInt(textoBoton);
        if (!modelo.Mover(ficha)) {
            return;
        }

        vista.ActualizarTablero(modelo.getTablero());
        vista.ActualizarMovimientosLabel(modelo.getMovimientos());

        if (modelo.EstaResuelto()) {
            vista.MostrarMensajeGanador();
        }
    }

    public void NuevoJuegoBoton() {
        modelo.InicializarTablero();
        modelo.MezclarTablero();
        vista.ActualizarTablero(modelo.getTablero());
        vista.ActualizarMovimientosLabel(modelo.getMovimientos());
    }
}
