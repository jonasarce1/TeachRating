package Usuarios;

import java.io.Serializable;

public abstract class Usuario implements Serializable{

    private static final long serialVersionUID = 1L;

    private String correo;

    private String contrasena;

    public Usuario(String correo, String contrasena) {
        super();
        this.correo = correo;
        this.contrasena = encriptar(contrasena);
    }

    public boolean validar(String correo, String contrasena) {
        if(correo.equals(this.correo) && contrasena.equals(desencriptar(this.contrasena))) {
            return true;
        }
        return false;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean validar(Usuario user) {
        if(user.getCorreo().equals(this.correo) && user.getContrasena().equals(desencriptar(this.contrasena))) {
            return true;
        }
        return false;
    }

    //Encriptacion de contraseña
    public String encriptar(String contrasena) {
        String encriptada = "";
        for(int i = 0; i < contrasena.length(); i++) {
            encriptada += (char) (contrasena.charAt(i) + 1);
        }
        return encriptada;
    }

    //Desencriptacion de contraseña
    public String desencriptar(String contrasena) {
        String desencriptada = "";
        for(int i = 0; i < contrasena.length(); i++) {
            desencriptada += (char) (contrasena.charAt(i) - 1);
        }
        return desencriptada;
    }

}
