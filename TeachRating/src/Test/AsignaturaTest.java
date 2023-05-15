package Test;

import Usuarios.Asignatura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AsignaturaTest {

    private Asignatura asignatura;

    @BeforeEach
    void setUp() {
        asignatura = new Asignatura("Matemáticas");
    }

    @Test
    void getNombre() {
        assertEquals("Matemáticas", asignatura.getNombre());
    }

    @Test
    void setNombre() {
        asignatura.setNombre("Física");
        assertEquals("Física", asignatura.getNombre());
    }
}
