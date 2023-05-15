package Test;

import Usuarios.Estudiante;
import Usuarios.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Estudiante("manuel@test.com", "1234");
    }

    @Test
    void testvalidar() {
        assertTrue(usuario.validar("manuel@test.com", "1234"));
        assertFalse(usuario.validar("manuel@test.com", "4321"));
        assertFalse(usuario.validar("manuel2@test.com", "1234"));
    }

    @Test
    void getCorreo() {
        assertEquals("manuel@test.com", usuario.getCorreo());
    }

    @Test
    void getContrasena() {
        assertEquals("2345", usuario.getContrasena());
    }

    @Test
    void encriptar() {
        assertEquals("2345", usuario.encriptar("1234"));
    }

    @Test
    void desencriptar() {
        assertEquals("1234", usuario.desencriptar("2345"));
    }
}
