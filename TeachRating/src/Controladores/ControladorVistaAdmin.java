package Controladores;

import Profesores.Profesor;
import Sistema.Sistema;
import Vistas.AnyadirProfesor;
import Vistas.VistaAdmin;
import Vistas.VistaPrincipal;
import Vistas.VistaProfesores;

import javax.swing.*;

public class ControladorVistaAdmin {
    private VistaAdmin vistaAdmin;
    private  Sistema s = Sistema.getInstance();

    public ControladorVistaAdmin(VistaAdmin vistaAdmin) {
        this.vistaAdmin = vistaAdmin;
    }

    public void mostrarVista() {
        vistaAdmin.show();
    }

    public void mostrarProfesores(Profesor p) {
        VistaProfesores vp = new VistaProfesores(p);
        vp.show();
    }

    public void quitarProfesor(Profesor p) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "¿Estas seguro de que quieres borrar al profesor/a " + p.getNombre() + " " + p.getApellido() + "?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            s.deleteProfesor(p.getNombre(), p.getApellido());
            VistaAdmin va = new VistaAdmin();
            va.show();
            vistaAdmin.dispose();
        }
    }

    public void salir() {
        vistaAdmin.dispose();
        VistaPrincipal vp = new VistaPrincipal();
        vp.show();
    }

    public void mostrarAnyadirProfesor() {
        vistaAdmin.dispose();
        AnyadirProfesor ap = new AnyadirProfesor();
        ap.show();
    }
}
