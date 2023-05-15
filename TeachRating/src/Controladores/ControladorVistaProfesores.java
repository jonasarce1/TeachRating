package Controladores;

import Vistas.VistaProfesores;

public class ControladorVistaProfesores {
    private VistaProfesores vistaProfesores;

    public ControladorVistaProfesores(VistaProfesores vistaProfesores) {
        this.vistaProfesores = vistaProfesores;
    }

    public void volver() {
        vistaProfesores.dispose();
    }
}
