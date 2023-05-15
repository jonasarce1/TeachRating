package Prueba;

import java.io.Serializable;
import java.util.ArrayList;
import Profesores.Profesor;
import Sistema.Sistema;
import Usuarios.Asignatura;
import Usuarios.Estudiante;
import Valoracion.Comentario;
import Valoracion.Puntuacion;
import Vistas.VistaPrincipal;

public class MainVistas implements Serializable{
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) throws Exception {
        Sistema s= Sistema.getInstance();

        Estudiante e1 = new Estudiante("jonas@nebrija.es", "123");
        Estudiante e2 = new Estudiante("jorge@nebrija.es", "contra");
        Estudiante e3 = new Estudiante("manuel@nebrija.es", "1234");

        ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
        asignaturas.add(new Asignatura("Ingenieria del software"));
        asignaturas.add(new Asignatura("Matematicas"));
        asignaturas.add(new Asignatura("Programacion"));

        s.setAsignaturas(asignaturas);

        ArrayList<Asignatura> asignaturas1 = new ArrayList<Asignatura>();
        asignaturas1.add(new Asignatura("Ingenieria del software"));

        ArrayList<Asignatura> asignaturas2 = new ArrayList<Asignatura>();
        asignaturas2.add(new Asignatura("Ingenieria del software"));
        asignaturas2.add(new Asignatura("Matematicas"));

        ArrayList<Asignatura> asignaturas3 = new ArrayList<Asignatura>();
        asignaturas3.add(new Asignatura("Programacion"));

        Profesor p1 = new Profesor("Gonzalo", "Diaz");
        p1.addAsignatura(new Asignatura("Ingenieria del software"));
        p1.addComentario(new Comentario("Siempre está dispuesto a ayudar y explicar las cosas de una manera clara y fácil de entender. Sus clases son interesantes y nunca me aburro. Definitivamente recomendaría a este profesor a cualquier estudiante que quiera aprender y disfrutar al mismo tiempo."));
        p1.addPuntuacion(e3, new Puntuacion(5));

        Profesor p2 = new Profesor("Daniela", "Jimenez");
        p2.addAsignatura(new Asignatura("Matematicas"));
        p2.addComentario(new Comentario("Se nota que tiene una gran pasión por la materia que enseña y siempre busca maneras de hacerla interesante y relevante para nosotros como estudiantes."));

        Profesor p3 = new Profesor("Alberto", "Valero");
        p3.addAsignatura(new Asignatura("Programacion"));
        p3.addComentario(new Comentario("Este profesor a veces puede ser un poco estricto y exigir mucho."));

        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        profesores.add(p1);
        profesores.add(p2);
        profesores.add(p3);
        s.setProfesores(profesores);

        e1.setAsignaturas(asignaturas1);
        e2.setAsignaturas(asignaturas2);
        e3.setAsignaturas(asignaturas1);

        s.registrarse(e1);
        s.registrarse(e2);
        s.registrarse(e3);

        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        vistaPrincipal.show();
    }
}