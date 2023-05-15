package Test;

import Profesores.Profesor;
import Usuarios.Administrador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AdministradorTest {
    private Administrador administrador;

    @BeforeEach
    void setUp() throws Exception {
        administrador = new Administrador("admin", "1234");
    }

    @Test
    void getProfesores() {
        // Caso de prueba 1: la lista de profesores está vacía
        ArrayList<Profesor> profesores = administrador.getProfesores();
        assertEquals(0, profesores.size());

        // Caso de prueba 2: la lista de profesores contiene elementos
        Profesor profesor1 = new Profesor("Juan", "Pérez");
        Profesor profesor2 = new Profesor("María", "García");
        ArrayList<Profesor> profesoresNuevos = new ArrayList<Profesor>();
        profesoresNuevos.add(profesor1);
        profesoresNuevos.add(profesor2);
        administrador.setProfesores(profesoresNuevos);
        profesores = administrador.getProfesores();
        assertEquals(2, profesores.size());
        assertTrue(profesores.contains(profesor1));
        assertTrue(profesores.contains(profesor2));
    }

    @Test
    void setProfesores() {
        ArrayList<Profesor> profesoresNuevos = new ArrayList<Profesor>();
        administrador.setProfesores(profesoresNuevos);
        assertEquals(0, administrador.getProfesores().size());

        Profesor profesor1 = new Profesor("Juan", "Pérez");
        Profesor profesor2 = new Profesor("María", "García");
        profesoresNuevos.add(profesor1);
        profesoresNuevos.add(profesor2);
        administrador.setProfesores(profesoresNuevos);
        ArrayList<Profesor> profesores = administrador.getProfesores();
        assertEquals(2, profesores.size());
        assertTrue(profesores.contains(profesor1));
        assertTrue(profesores.contains(profesor2));
    }
}