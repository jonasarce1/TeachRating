package Usuarios;

import java.io.Serializable;
import java.util.ArrayList;

public class Asignatura implements Serializable{

    private static final long serialVersionUID = 1L;

    private ArrayList<Usuario> estudiantes = new ArrayList<Usuario>();

    private String nombre;

    public Asignatura(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
