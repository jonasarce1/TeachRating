package Valoracion;

import java.io.Serializable;

public class Comentario implements Serializable{
    private static final long serialVersionUID = 1L;

    private String mensaje;

    public Comentario(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public String toString() {
        return this.mensaje;
    }

}
