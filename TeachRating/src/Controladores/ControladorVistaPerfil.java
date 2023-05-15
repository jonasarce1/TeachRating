package Controladores;

import Usuarios.Asignatura;
import Vistas.VistaEstudiante;
import Vistas.VistaPerfil;
import Sistema.Sistema;

import javax.swing.*;
import java.awt.*;

public class ControladorVistaPerfil {
    private VistaPerfil vistaPerfil;

    private Sistema s = Sistema.getInstance();
    public ControladorVistaPerfil(VistaPerfil vistaPerfil) {
        this.vistaPerfil = vistaPerfil;
    }

    public void mostrarVista() {
        vistaPerfil.show();
    }

    public void cambiarAsignaturas(){
        vistaPerfil.getEstudiante().clearAsignaturas();
        vistaPerfil.getEstudiante().clearProfesores();
        Component[] components = vistaPerfil.getCheckboxPanel().getComponents();
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) component;
                if (checkbox.isSelected()) {
                    String nombreAsignatura = checkbox.getText();
                    Asignatura asignatura = s.getAsignaturaPorNombre(nombreAsignatura);
                    vistaPerfil.getEstudiante().addAsignatura(asignatura);
                }
            }
        }
        JOptionPane.showMessageDialog(vistaPerfil.getFrame(), "Las asignaturas han sido actualizadas");
        VistaEstudiante vE = new VistaEstudiante();
        vE.show();
        vistaPerfil.dispose();
    }

    public void volver(){
        VistaEstudiante vE = new VistaEstudiante();
        vE.show();
        vistaPerfil.dispose();
    }
}
