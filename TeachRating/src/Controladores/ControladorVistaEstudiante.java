package Controladores;

import Profesores.Profesor;
import Usuarios.Estudiante;
import Vistas.*;
import Sistema.Sistema;

import javax.swing.*;

public class ControladorVistaEstudiante {
    private VistaEstudiante vistaEstudiante;
    private Sistema s = Sistema.getInstance();

    public ControladorVistaEstudiante(VistaEstudiante vistaEstudiante) {
        this.vistaEstudiante = vistaEstudiante;
    }

    public void mostrarProfesores(String nombre, String apellido){
        Profesor p = s.findProfesor(nombre, apellido);
        VistaProfesores vp = new VistaProfesores(p);
        vp.show();
    }

    public void mostrarEstrellas(String nombre, String apellido){
        Profesor p = s.findProfesor(nombre, apellido);
        Estudiante eAux = (Estudiante)s.getUsuarioActual();
        if(!p.checkPuntuacion(eAux)){
            JOptionPane.showMessageDialog(null, "Ya has puntuado a este profesor");
            return;
        }
        VistaPuntuar vp = new VistaPuntuar(nombre + " " + apellido);
        vp.show();
    }

    public void mostrarComentario(String nombre){
        VistaComentar vc = new VistaComentar(nombre);
        vc.show();
    }

    public void mostrarPerfil(){
        vistaEstudiante.dispose();
        VistaPerfil vp = new VistaPerfil((Estudiante)s.getUsuarioActual());
        vp.show();
    }

    public void salir(){
        vistaEstudiante.dispose();
        VistaPrincipal vp = new VistaPrincipal();
        vp.show();
    }

}
