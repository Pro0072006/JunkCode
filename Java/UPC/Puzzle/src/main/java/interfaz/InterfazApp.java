package interfaz;

import controlador.Controlador;
import mundo.Proceso;

public class InterfazApp {

    public static void main(String[] args) {
        PanelDatos vista = new PanelDatos();
        Proceso modelo = new Proceso();
        Controlador controlador = new Controlador(modelo, vista);
        vista.InicializarVista(controlador);
    }
}
