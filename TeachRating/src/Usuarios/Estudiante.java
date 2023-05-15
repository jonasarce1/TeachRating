package Usuarios;

import java.util.ArrayList;

import Profesores.Profesor;
import Valoracion.Comentario;
import Valoracion.Puntuacion;

public class Estudiante extends Usuario{

    private static final long serialVersionUID = 1L;

    private ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
    private ArrayList<Profesor> profesores = new ArrayList<Profesor>();

    public Estudiante(String correo, String contrasena){
        super(correo, contrasena);
    }

    public Estudiante(String correo, String contrasena, ArrayList<Asignatura> asignaturas) {
        super(correo, contrasena);
        this.asignaturas = asignaturas;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void addAsignatura(Asignatura a) {
        for(Asignatura Aux : this.asignaturas) {
            if(a.equals(Aux)) {
                return;
            }
        }
        this.asignaturas.add(a);
    }

    public void asignarProfesores(ArrayList<Profesor> profesores) {
        for(Profesor p : profesores) {
            for(Asignatura a : p.getAsignaturas()) {
                for(Asignatura a1 : this.asignaturas) {
                    if(a.equals(a1)) {
                        this.addProfesor(p);
                    }
                }
            }
        }
    }

    public void puntuarProfesor(Profesor p, Puntuacion puntu) {
        p.addPuntuacion(this, puntu);
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public Profesor findProfesor(String nombre, String apellido){
        for (Profesor p: this.profesores) {
            if(p.getNombre().equals(nombre) && p.getApellido().equals(apellido)) {
                return p;
            }
        }
        return null;
    }

    public void addProfesor(Profesor p) {
        for(Profesor prof: this.profesores){
            if(p.equals(prof)){
                return;
            }
        }
        this.profesores.add(p);
    }

    public void clearAsignaturas() {
        this.asignaturas.clear();
    }

    public void clearProfesores(){
        this.profesores.clear();
    }

}
