package Usuarios;

import java.util.ArrayList;
import Profesores.Profesor;

public class Administrador extends Usuario{

    private static final long serialVersionUID = 1L;
    private ArrayList<Profesor> profesores = new ArrayList<Profesor>();

    public Administrador(String usuario, String contrasena) throws Exception{
        super(usuario, contrasena);
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

}
