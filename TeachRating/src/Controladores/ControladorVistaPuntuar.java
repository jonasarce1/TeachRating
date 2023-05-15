package Controladores;

import Sistema.Sistema;
import Vistas.VistaPuntuar;

public class ControladorVistaPuntuar {
    private VistaPuntuar vistaPuntuar;

    private Sistema s = Sistema.getInstance();

    public ControladorVistaPuntuar(VistaPuntuar vistaPuntuar) {
        this.vistaPuntuar = vistaPuntuar;
    }

    public void volver() {
        vistaPuntuar.dispose();
    }

    public void enviarPuntuacion() {
        s.getInstance().puntuarProfesor(vistaPuntuar.separarNombre(), vistaPuntuar.separarApellido(), vistaPuntuar.getPuntuacion());
        vistaPuntuar.dispose();
    }
}
