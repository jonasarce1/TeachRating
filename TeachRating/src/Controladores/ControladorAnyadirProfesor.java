package Controladores;

import Sistema.Sistema;
import Usuarios.Asignatura;
import Vistas.AnyadirProfesor;
import Vistas.VistaAdmin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorAnyadirProfesor {
    private AnyadirProfesor AnyadirProfesor;
    private Sistema s = Sistema.getInstance();

    public ControladorAnyadirProfesor(AnyadirProfesor AnyadirProfesor) {
        this.AnyadirProfesor = AnyadirProfesor;
    }

    public void mostrarVista() {
        AnyadirProfesor.show();
    }

    public void anyadirProfesor(String nombre, String apellido, List<JCheckBox> asignaturasCheckBoxes) {
        // Comprobar si se ha introducido un nombre y un apellido
        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(AnyadirProfesor.getFrame(), "Error: Introduzca un nombre y un apellido");
            return;
        }

        if(s.findProfesor(nombre, apellido) != null){
            JOptionPane.showMessageDialog(AnyadirProfesor.getFrame(), "Error: Ya existe un profesor/a con ese nombre y apellido");
            return;
        }

        // Comprobar si se ha seleccionado al menos una asignatura
        List<Asignatura> asignaturas = new ArrayList<>();
        for (JCheckBox checkBox : asignaturasCheckBoxes) {
            if (checkBox.isSelected()) {
                Asignatura asignatura = s.findAsignatura(checkBox.getText());
                asignaturas.add(asignatura);
            }
        }
        if (asignaturas.isEmpty()) {
            JOptionPane.showMessageDialog(AnyadirProfesor.getFrame(), "Error: Seleccione al menos una asignatura");
            return;
        }

        // Añadir el profesor/a
        s.addProfesor(nombre, apellido, asignaturas);
        JOptionPane.showMessageDialog(AnyadirProfesor.getFrame(), "Profesor/a anyadido/a correctamente");
        AnyadirProfesor.getBackButton().doClick();
    }

    public void volver() {
        VistaAdmin vistaAdmin = new VistaAdmin();
        vistaAdmin.show();
        AnyadirProfesor.dispose();
    }
}
