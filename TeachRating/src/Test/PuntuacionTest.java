package Test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Valoracion.Puntuacion;

public class PuntuacionTest {

    @Test
    void testGetValor() {
        Puntuacion p = new Puntuacion(4);
        assertEquals(4, p.getValor());
    }

    @Test
    void testSetValor() {
        Puntuacion p = new Puntuacion(3);
        p.setValor(5);
        assertEquals(5, p.getValor());
        assertThrows(IllegalArgumentException.class, () -> {
            p.setValor(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            p.setValor(6);
        });
    }
}
