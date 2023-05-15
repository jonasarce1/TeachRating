package Test;

import Profesores.Profesor;
import Usuarios.Asignatura;
import Usuarios.Estudiante;
import Valoracion.Comentario;
import Valoracion.Puntuacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EstudianteTest {
    private Estudiante estudiante;
    private Asignatura asignatura1;
    private Asignatura asignatura2;
    private Profesor profesor1;
    private Profesor profesor2;
    private Puntuacion puntuacion;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante("ejemplo@nebrija.com", "contrasena");
        asignatura1 = new Asignatura("Matematicas");
        asignatura2 = new Asignatura("Fisica");
        profesor1 = new Profesor("Gabriel", "Gomez");
        profesor2 = new Profesor("Ana", "Perez");
        puntuacion = new Puntuacion(4);
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);
        estudiante.setAsignaturas(asignaturas);
        ArrayList<Profesor> profesores = new ArrayList<>();
        profesores.add(profesor1);
        profesores.add(profesor2);
        profesor1.setAsignaturas(asignaturas);
        profesor2.setAsignaturas(asignaturas);
        estudiante.asignarProfesores(profesores);
        profesor1.addPuntuacion(estudiante, puntuacion);
    }

    @Test
    void getAsignaturas() {
        ArrayList<Asignatura> asignaturas = estudiante.getAsignaturas();
        assertEquals(2, asignaturas.size());
        assertTrue(asignaturas.contains(asignatura1));
        assertTrue(asignaturas.contains(asignatura2));
    }

    @Test
    void setAsignaturas() {
        ArrayList<Asignatura> nuevasAsignaturas = new ArrayList<>();
        nuevasAsignaturas.add(new Asignatura("Química"));
        nuevasAsignaturas.add(new Asignatura("Historia"));
        estudiante.setAsignaturas(nuevasAsignaturas);
        ArrayList<Asignatura> asignaturas = estudiante.getAsignaturas();
        assertEquals(2, asignaturas.size());
        assertFalse(asignaturas.contains(asignatura1));
        assertFalse(asignaturas.contains(asignatura2));
        assertTrue(asignaturas.contains(nuevasAsignaturas.get(0)));
        assertTrue(asignaturas.contains(nuevasAsignaturas.get(1)));
    }

    @Test
    void addAsignaturas(){
        Asignatura asignatura3 = new Asignatura("Química");
        estudiante.addAsignatura(asignatura3);
        ArrayList<Asignatura> asignaturas = estudiante.getAsignaturas();
        assertEquals(3, asignaturas.size());
        assertTrue(asignaturas.contains(asignatura3));
    }

    @Test
    void asignarProfesores(){
        ArrayList<Profesor> profesores = estudiante.getProfesores();
        for(Profesor profesor : profesores){
            System.out.println(profesor.getNombre());
        }
        assertEquals(2, profesores.size());
        assertTrue(profesores.contains(profesor1));
        assertTrue(profesores.contains(profesor2));
    }

    @Test
    void puntuarProfesor(){
        ArrayList<Puntuacion> puntuaciones = profesor1.getPuntuaciones();
        assertEquals(1, puntuaciones.size());
        assertTrue(puntuaciones.contains(puntuacion));
    }

    @Test
    void getProfesores(){
        ArrayList<Profesor> profesores = estudiante.getProfesores();
        assertEquals(2, profesores.size());
        assertTrue(profesores.contains(profesor1));
    }

    @Test
    void findProfesor(){
        assertEquals(profesor1, estudiante.findProfesor("Gabriel", "Gomez"));
        assertEquals(null, estudiante.findProfesor("Fallo", "Fallito"));
    }

    @Test
    void addProfesor(){
        Profesor profesor3 = new Profesor("Juan", "Garcia");
        estudiante.addProfesor(profesor3);
        ArrayList<Profesor> profesores = estudiante.getProfesores();
        assertEquals(3, profesores.size());
        assertTrue(profesores.contains(profesor3));
    }

    @Test
    void clearAsignaturas(){
        estudiante.clearAsignaturas();
        assertEquals(0, estudiante.getAsignaturas().size());
    }

    @Test
    void clearProfesores(){
        estudiante.clearProfesores();
        assertEquals(0, estudiante.getProfesores().size());
    }
}