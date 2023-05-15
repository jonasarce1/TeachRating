package Controladores;

import Profesores.Profesor;
import Sistema.Sistema;
import Vistas.VistaInvitado;
import Vistas.VistaPrincipal;
import Vistas.VistaProfesores;

public class ControladorVistaInvitado {
    private VistaInvitado vistaInvitado;
    private Sistema s = Sistema.getInstance();
    public ControladorVistaInvitado(VistaInvitado vistaInvitado) {
        this.vistaInvitado = vistaInvitado;
    }

    public void mostrarVista() {
        vistaInvitado.show();
    }

    public void mostrarProfesores(String nombre, String apellido){
        Profesor p = s.findProfesor(nombre, apellido);
        VistaProfesores vp = new VistaProfesores(p);
        vp.show();
    }
    public void salir(){
        vistaInvitado.dispose();
        VistaPrincipal vp = new VistaPrincipal();
        vp.show();
    }
}
