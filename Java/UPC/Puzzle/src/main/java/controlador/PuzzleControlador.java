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
        int tile = Integer.parseInt(textoBoton);
        if (!modelo.Mover(tile)) {
            vista.MostrarMensaje();
            return;
        }

        if (modelo.EstaResuelto()) {
            vista.MostrarMensajeGanador();
        }
        vista.ActualizarTablero(modelo.getTablero());
        vista.ActualizarMovimientosLabel(modelo.getMovimientos());
    }

    public void NuevoJuegoBoton() {
        modelo.InicializarTablero();
        modelo.MezclarTablero();
        vista.ActualizarTablero(modelo.getTablero());
        vista.ActualizarMovimientosLabel(modelo.getMovimientos());
        vista.setTiempoDeInicio(System.currentTimeMillis());
    }
}
