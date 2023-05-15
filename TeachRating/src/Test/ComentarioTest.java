package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Valoracion.Comentario;

public class ComentarioTest {
    private Comentario comentario;

    @BeforeEach
    void setUp() {
        comentario = new Comentario("Este es un comentario de prueba.");
    }

    @Test
    void testGetMensaje() {
        assertEquals("Este es un comentario de prueba.", comentario.getMensaje());
    }

    @Test
    void testSetMensaje() {
        comentario.setMensaje("Este es un comentario actualizado.");
        assertEquals("Este es un comentario actualizado.", comentario.getMensaje());
    }

    @Test
    void testToString() {
        assertEquals("Este es un comentario de prueba.", comentario.toString());
    }
}
