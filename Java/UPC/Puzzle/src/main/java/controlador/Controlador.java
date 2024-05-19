package controlador;

import interfaz.PanelDatos;
import mundo.Proceso;

public class Controlador {
    private Proceso modelo;
    private PanelDatos vista;

    public Controlador(Proceso modelo, PanelDatos vista) {
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
