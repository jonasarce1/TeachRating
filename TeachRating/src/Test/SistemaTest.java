package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Profesores.Profesor;
import Usuarios.Asignatura;
import Usuarios.Estudiante;
import Usuarios.Usuario;
import Valoracion.Comentario;
import Valoracion.Puntuacion;
import Sistema.Sistema;


import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SistemaTest {
    private Sistema sistema;

    private ArrayList<Usuario> usuarios;

    private ArrayList<Asignatura> asignaturas;

    private ArrayList<Profesor> profesores;

    private ArrayList<Profesor> profesoresUsuarioActual;

    @BeforeEach
    void setUp() {
        sistema = new Sistema();
        usuarios = new ArrayList<>();
        asignaturas = new ArrayList<>();
        profesores = new ArrayList<>();
        profesores.add(new Profesor("Juan", "Perez"));
        profesores.add(new Profesor("Maria", "Garcia"));
        profesoresUsuarioActual = new ArrayList<>();
    }

    @Test
    void getInstance() {
        Sistema sistema1 = Sistema.getInstance();
        Sistema sistema2 = Sistema.getInstance();
        assertEquals(sistema1, sistema2);
    }

    @Test
    void getUsuarioActual() throws Exception {
        Usuario usuario = new Estudiante("jorge@gmail.com", "1234");
        sistema.registrarse(usuario);
        assertEquals(usuario, sistema.getUsuarioActual());
    }

    @Test
    void testRegistrarse() throws Exception {
        Usuario usuario = new Estudiante("jorge@gmail.com", "1234");
        sistema.registrarse(usuario);
        assertEquals(usuario, sistema.getUsuarioActual());
    }

    @Test
    void login() throws Exception {
        Usuario usuario1 = new Estudiante("jorge@gmail.com", "1234");
        sistema.registrarse(usuario1);
        Usuario usuario2 = new Estudiante("guille@nebrija.com", "12345");
        sistema.registrarse(usuario2);
        sistema.login("jorge@gmail.com", "1234");
        assertEquals(usuario1, sistema.getUsuarioActual());
    }

    @Test
    void checkMail() throws Exception {
        Usuario usuario1 = new Estudiante("jorge@gmail.com", "1234");
        sistema.registrarse(usuario1);
        assertTrue(sistema.checkMail("jorge@gmail.com"));
        assertFalse(sistema.checkMail("guille@nebrija.com"));
    }

    @Test
    void getAsignaturas() {
        Asignatura asignatura1 = new Asignatura("Matemáticas");
        Asignatura asignatura2 = new Asignatura("Física");
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);
        sistema.setAsignaturas(asignaturas);
        assertEquals(asignaturas, sistema.getAsignaturas());
    }

    @Test
    void setAsignaturas() {
        Asignatura asignatura1 = new Asignatura("Matematicas");
        Asignatura asignatura2 = new Asignatura("Fisica");
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);
        sistema.setAsignaturas(asignaturas);
        assertEquals(asignaturas, sistema.getAsignaturas());
    }

    @Test
    void addAsignatura() throws Exception {
        Asignatura asignatura1 = new Asignatura("Matematicas");
        Asignatura asignatura2 = new Asignatura("Física");
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);
        sistema.setAsignaturas(asignaturas);
        Estudiante usuario1 = new Estudiante("jorge@gmail.com", "1234");
        sistema.registrarse(usuario1);
        sistema.addAsignatura(usuario1, "Quimica");
        assertEquals("Quimica", usuario1.getAsignaturas().get(0).getNombre());
    }

    @Test
    void getUsuario() throws Exception {
        Usuario usuario1 = new Estudiante("jorge@gmail.com", "1234");
        sistema.registrarse(usuario1);
        assertEquals(usuario1, sistema.getUsuario("jorge@gmail.com"));
    }

    @Test
    void checkMailText() {
        assertTrue(sistema.checkMailText("jorge@gmail.com"));
        assertFalse(sistema.checkMailText("jorgegmail.com"));
        assertFalse(sistema.checkMailText("jorge@gmailcom"));
    }

    @Test
    void getProfesores() {
        sistema.setProfesores(profesores);
        assertEquals(profesores, sistema.getProfesores());
    }

    @Test
    void findProfesor() {
        sistema.setProfesores(profesores);
        assertEquals(profesores.get(0), sistema.findProfesor("Juan", "Perez"));
    }

    @Test
    void deleteProfesor() {
        sistema.setProfesores(profesores);
        sistema.deleteProfesor(profesores.get(0).getNombre(), profesores.get(0).getApellido());
        assertEquals(1, sistema.getProfesores().size());
    }

    @Test
    void setProfesores() {
        sistema.setProfesores(profesores);
        assertEquals(profesores, sistema.getProfesores());
    }

    @Test
    void puntuarProfesor() {
        sistema.setProfesores(profesores);
        sistema.puntuarProfesor(profesores.get(0).getNombre(), profesores.get(0).getApellido(), 5);
        assertEquals(5, profesores.get(0).getMedia());
    }

    @Test
    void checkComentario() {
        assertFalse(sistema.checkComentario("Muy buen profesor"));
        assertTrue(sistema.checkComentario("Es un capullo y un inutil"));
    }

    @Test
    void insertarComentario() {
        sistema.setProfesores(profesores);
        sistema.insertarComentario(profesores.get(0).getNombre(), profesores.get(0).getApellido(), "Muy buen profesor");
        assertEquals("Muy buen profesor", profesores.get(0).getComentarios().get(0).getMensaje());
    }

    @Test
    void setProfesoresUsuarioActual() throws Exception {
        Estudiante usuario1 = new Estudiante("jorge@gmail.com", "1234");
        Asignatura asignatura1 = new Asignatura("Matematicas");
        Asignatura asignatura2 = new Asignatura("Fisica");
        ArrayList<Asignatura> asignaturasAux = new ArrayList<Asignatura>();
        asignaturasAux.add(asignatura1);
        asignaturasAux.add(asignatura2);
        usuario1.addAsignatura(asignatura1);
        usuario1.addAsignatura(asignatura2);
        Profesor profesor1 = new Profesor("Juan", "Perez");
        Profesor profesor2 = new Profesor("Pedro", "Garcia");
        profesor1.addAsignatura(asignatura1);
        profesor2.addAsignatura(asignatura2);
        ArrayList<Profesor> profesoresAux = new ArrayList<Profesor>();
        profesoresAux.add(profesor1);
        profesoresAux.add(profesor2);
        sistema.setAsignaturas(asignaturasAux);
        sistema.setProfesores(profesoresAux);
        sistema.registrarse(usuario1);
        sistema.setProfesoresUsuarioActual();
        Estudiante e1 = (Estudiante) sistema.getUsuarioActual();
        assertEquals(profesoresAux, e1.getProfesores());
    }

    @Test
    void getAsignaturaPorNombre() {
        Asignatura asignatura1 = new Asignatura("Matematicas");
        Asignatura asignatura2 = new Asignatura("Fisica");
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);
        sistema.setAsignaturas(asignaturas);
        assertEquals(asignatura1, sistema.getAsignaturaPorNombre("Matematicas"));
    }

    @Test
    void isAdmin() throws Exception {
        Usuario usuario1 = new Estudiante("jorge@gmail.com", "1234");
        sistema.registrarse(usuario1);
        assertFalse(sistema.isAdmin());
        sistema.login("admin", "contra");
        assertTrue(sistema.isAdmin());
    }

    @Test
    void findAsignatura() {
        Asignatura asignatura1 = new Asignatura("Matematicas");
        Asignatura asignatura2 = new Asignatura("Fisica");
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);
        sistema.setAsignaturas(asignaturas);
        assertEquals(asignatura1, sistema.findAsignatura("Matematicas"));
    }

    @Test
    void addProfesor() {
        sistema.setProfesores(profesores);
        sistema.addProfesor("Juan", "Perez", asignaturas);
        assertEquals("Juan", sistema.getProfesores().get(0).getNombre());
    }
}