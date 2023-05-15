package Controladores;

import Sistema.Sistema;
import Vistas.VistaAdmin;
import Vistas.VistaEstudiante;
import Vistas.VistaInvitado;
import Vistas.VistaPrincipal;

import javax.swing.*;

public class ControladorVistaPrincipal {

    private VistaPrincipal vistaPrincipal;

    Sistema s = Sistema.getInstance();

    public ControladorVistaPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
    }

    public void login(String username, String password) {
        try {
            if(s.login(username, password)){
                if(s.isAdmin()){
                    // Crear y mostrar la ventana de inicio de sesión
                    VistaAdmin va = new VistaAdmin();
                    va.show();
                    vistaPrincipal.dispose();
                }else{
                    // Crear y mostrar la ventana de inicio de sesión
                    VistaEstudiante ve = new VistaEstudiante();
                    ve.show();
                    vistaPrincipal.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(VistaPrincipal.getFrame(), "Usuario o contraseña incorrectos");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void Invitado(){
        VistaInvitado vi = new VistaInvitado();
        vi.show();
        vistaPrincipal.dispose();
    }

}