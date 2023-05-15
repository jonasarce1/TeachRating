package Test;

import Profesores.Profesor;
import Usuarios.Asignatura;
import Usuarios.Estudiante;
import Valoracion.Comentario;
import Valoracion.Puntuacion;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProfesorTest {
    private Profesor profesor;
    private Asignatura asignatura1;
    private Asignatura asignatura2;
    private Comentario comentario1;
    private Comentario comentario2;
    private Puntuacion puntuacion1;
    private Puntuacion puntuacion2;
    private Estudiante estudiante1;
    private Estudiante estudiante2;

    @BeforeEach
    void setUp() {
        profesor = new Profesor("Jose", "Pacheco");
        asignatura1 = new Asignatura("Matematicas");
        asignatura2 = new Asignatura("Fisica");
        comentario1 = new Comentario("Buen profesor");
        comentario2 = new Comentario("Mal profesor");
        puntuacion1 = new Puntuacion(4);
        puntuacion2 = new Puntuacion(2);
        ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);
        profesor.addAsignatura(asignatura1);
        profesor.addAsignatura(asignatura2);
        estudiante1 = new Estudiante("guillermo@nebrija.es", "12345678", asignaturas);
        estudiante2 = new Estudiante("jorge@nebrija.es", "87654321", asignaturas);
        profesor.addComentario(comentario1);
        profesor.addComentario(comentario2);
        profesor.addPuntuacion(estudiante1, puntuacion1);
        profesor.addPuntuacion(estudiante2, puntuacion2);
    }

    @org.junit.jupiter.api.Test
    void getNombre() {
        assertEquals("Jose", profesor.getNombre());
    }

    @org.junit.jupiter.api.Test
    void setNombre() {
        profesor.setNombre("Juan");
        assertEquals("Juan", profesor.getNombre());
    }

    @org.junit.jupiter.api.Test
    void getApellido() {
        assertEquals("Pacheco", profesor.getApellido());
    }

    @org.junit.jupiter.api.Test
    void setApellido() {
        profesor.setApellido("Gonzalez");
        assertEquals("Gonzalez", profesor.getApellido());
    }

    @org.junit.jupiter.api.Test
    void setAsignaturas() {
        ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);
        profesor.setAsignaturas(asignaturas);
        assertEquals(asignaturas, profesor.getAsignaturas());
    }

    @org.junit.jupiter.api.Test
    void getAsignaturas() {
        ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);
        assertEquals(asignaturas, profesor.getAsignaturas());
    }

    @org.junit.jupiter.api.Test
    void addAsignatura() {
        Asignatura asignatura3 = new Asignatura("Quimica");
        profesor.addAsignatura(asignatura3);
        assertEquals(asignatura3, profesor.getAsignaturas().get(2));
    }

    @org.junit.jupiter.api.Test
    void deleteAsignatura() {
        profesor.deleteAsignatura(asignatura1);
        assertEquals(asignatura2, profesor.getAsignaturas().get(0));
    }

    @org.junit.jupiter.api.Test
    void getComentarios() {
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        comentarios.add(comentario1);
        comentarios.add(comentario2);
        assertEquals(comentarios, profesor.getComentarios());
    }

    @org.junit.jupiter.api.Test
    void setComentarios() {
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        comentarios.add(comentario1);
        comentarios.add(comentario2);
        profesor.setComentarios(comentarios);
        assertEquals(comentarios, profesor.getComentarios());
    }

    @org.junit.jupiter.api.Test
    void addComentario() {
        Comentario comentario3 = new Comentario("Muy buen profesor");
        profesor.addComentario(comentario3);
        assertEquals(comentario3, profesor.getComentarios().get(2));
    }

    @org.junit.jupiter.api.Test
    void deleteComentario() {
        profesor.deleteComentario(comentario1);
        assertEquals(comentario2, profesor.getComentarios().get(0));
    }

    @org.junit.jupiter.api.Test
    void getPuntuaciones() {
        ArrayList<Puntuacion> puntuaciones = new ArrayList<Puntuacion>();
        puntuaciones.add(puntuacion1);
        puntuaciones.add(puntuacion2);
        assertEquals(puntuaciones, profesor.getPuntuaciones());
    }

    @org.junit.jupiter.api.Test
    void setPuntuaciones() {
        ArrayList<Puntuacion> puntuaciones = new ArrayList<Puntuacion>();
        puntuaciones.add(puntuacion1);
        puntuaciones.add(puntuacion2);
        profesor.setPuntuaciones(puntuaciones);
        assertEquals(puntuaciones, profesor.getPuntuaciones());
    }

    @org.junit.jupiter.api.Test
    void checkPuntuacion() {
        Estudiante estudiante3 = new Estudiante("pepe@nebrija.es", "12345678", new ArrayList<Asignatura>());
        assertTrue(profesor.checkPuntuacion(estudiante3));
        assertFalse(profesor.checkPuntuacion(estudiante2));
    }

    @org.junit.jupiter.api.Test
    void addPuntuacion() {
        Estudiante estudiante3 = new Estudiante("pepe@nebrija.es", "12345678", new ArrayList<Asignatura>());
        Puntuacion puntuacion3 = new Puntuacion(5);
        profesor.addPuntuacion(estudiante3, puntuacion3);
        assertEquals(puntuacion3, profesor.getPuntuaciones().get(2));
    }

    @org.junit.jupiter.api.Test
    void deletePuntuacion() {
        profesor.deletePuntuacion(puntuacion1);
        assertEquals(puntuacion2, profesor.getPuntuaciones().get(0));
    }

    @org.junit.jupiter.api.Test
    void getMedia() {
        assertEquals(3, profesor.getMedia());
    }

    @org.junit.jupiter.api.Test
    void getContador() {
        assertEquals(2, profesor.getContador());
    }
}