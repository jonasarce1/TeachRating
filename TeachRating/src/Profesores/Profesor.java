package Profesores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Usuarios.Asignatura;
import Usuarios.Estudiante;
import Valoracion.Comentario;
import Valoracion.Puntuacion;

public class Profesor implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido;
    private ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
    private ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
    private ArrayList<Puntuacion> puntuaciones = new ArrayList<Puntuacion>();
    private ArrayList<Estudiante> estudiantesQuePuntuaron = new ArrayList<Estudiante>();
    private float media;
    private int contador;

    private void recalcularMedia() {
        this.media = 0;
        for(Puntuacion puntu : this.puntuaciones) {
            this.media += puntu.getValor();
        }
        this.media /= contador;
    }

    public Profesor(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Profesor(String nombre2, String apellido2, List<Asignatura> asignaturas2) {
        this.nombre = nombre2;
        this.apellido = apellido2;
        this.asignaturas = (ArrayList<Asignatura>) asignaturas2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void addAsignatura(Asignatura a) {
        if(this.asignaturas.isEmpty()) {
            this.asignaturas.add(a);
            return;
        }
        for(Asignatura Aux : this.asignaturas) {
            if(a.equals(Aux)) {
                return;
            }
        }
        this.asignaturas.add(a);
    }

    public void deleteAsignatura(Asignatura a) {
        this.asignaturas.remove(a);
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void addComentario(Comentario c) {
        for(Comentario Aux : this.comentarios) {
            if(c.equals(Aux)) {
                return;
            }
        }
        this.comentarios.add(c);
    }

    public void deleteComentario(Comentario c) {
        this.comentarios.remove(c);
    }

    public ArrayList<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(ArrayList<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public boolean checkPuntuacion(Estudiante e){
        for(Estudiante Aux : this.estudiantesQuePuntuaron) {
            if(e.equals(Aux)) {
                return false;
            }
        }
        return true;
    }

    public void addPuntuacion(Estudiante e, Puntuacion p) {
        if(!checkPuntuacion(e)){
            return;
        }
        for(Puntuacion Aux : this.puntuaciones) {
            if(p.equals(Aux)) {
                return;
            }
        }
        this.estudiantesQuePuntuaron.add(e);
        this.puntuaciones.add(p);
        this.contador++;
        this.recalcularMedia();
    }

    public void deletePuntuacion(Puntuacion p) {
        this.puntuaciones.remove(p);
    }

    public float getMedia() {
        return media;
    }

    public int getContador() {
        return contador;
    }

}
