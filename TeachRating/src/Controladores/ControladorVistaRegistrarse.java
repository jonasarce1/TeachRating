package Controladores;

import Sistema.Sistema;
import Usuarios.Estudiante;
import Vistas.VistaEstudiante;
import Vistas.VistaPrincipal;
import Vistas.VistaRegistrarse;

import javax.swing.*;

public class ControladorVistaRegistrarse {
    private VistaRegistrarse vistaRegistrarse;

    private Sistema s = Sistema.getInstance();

    public ControladorVistaRegistrarse(VistaRegistrarse vistaRegistrarse) {
        this.vistaRegistrarse = vistaRegistrarse;
    }

    public void registrarse(String email, String password, String repeatPassword) {
        if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            JOptionPane.showMessageDialog(vistaRegistrarse.getFrame(), "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!password.equals(repeatPassword)) {
            JOptionPane.showMessageDialog(vistaRegistrarse.getFrame(), "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(s.checkMail(email)){
            JOptionPane.showMessageDialog(vistaRegistrarse.getFrame(), "El email ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(!s.checkMailText(email)){
            JOptionPane.showMessageDialog(vistaRegistrarse.getFrame(), "El email no es válido. Ha de contener una @ y acabar en .es o .com", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(!vistaRegistrarse.checkSelected()){
            JOptionPane.showMessageDialog(vistaRegistrarse.getFrame(), "Debe seleccionar al menos una asignatura.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                s.registrarse(email, password);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            for(JCheckBox cb : vistaRegistrarse.asignaturasCheckBox){
                if(cb.isSelected()){
                    s.addAsignatura((Estudiante) s.getUsuario(email), cb.getText());
                }
            }
            JOptionPane.showMessageDialog(vistaRegistrarse.getFrame(), "Te has registrado correctamente", "Registro correcto", JOptionPane.INFORMATION_MESSAGE);
            try {
                s.login(email, password);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            VistaEstudiante ve = new VistaEstudiante();
            ve.show();
            vistaRegistrarse.dispose();
        }
    }

    public void volver() {
        VistaPrincipal vp = new VistaPrincipal();
        vp.show();
        vistaRegistrarse.dispose();
    }
}
