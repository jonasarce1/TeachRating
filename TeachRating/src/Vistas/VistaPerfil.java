package Vistas;

import Controladores.ControladorVistaPerfil;
import Sistema.Sistema;
import Usuarios.Estudiante;
import Usuarios.Asignatura;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VistaPerfil {
    private JFrame frame;

    private ControladorVistaPerfil controlador;
    private Estudiante estu;
    private JPanel panel;
    private JPanel checkboxPanel;
    private JButton volverButton;
    private JButton cambiarAsignaturasButton;

    public VistaPerfil(Estudiante estudiante) {
        Sistema s = Sistema.getInstance();

        frame = new JFrame("TeachRating - Perfil de " + estudiante.getCorreo());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                VistaEstudiante vistaEstudiante = new VistaEstudiante();
                vistaEstudiante.show();
                frame.dispose();
            }
        });

        this.estu = estudiante;
        controlador = new ControladorVistaPerfil(this);

        // Configuramos el panel principal
        panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel);

        // Configuramos los componentes
        JLabel estudianteLabel = new JLabel(estudiante.getCorreo());
        estudianteLabel.setFont(new Font("Arial", Font.BOLD, 32));
        panel.add(estudianteLabel, BorderLayout.NORTH);

        JPanel detallesPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.add(detallesPanel, BorderLayout.CENTER);

        JLabel asignaturasLabel = new JLabel("Asignaturas:");
        detallesPanel.add(asignaturasLabel);

        checkboxPanel = new JPanel(new GridLayout(0, 1));
        detallesPanel.add(checkboxPanel);

        // Creamos los checkboxes
        List<Asignatura> asignaturas = s.getAsignaturas();
        for (Asignatura asignatura : asignaturas) {
            JCheckBox checkbox = new JCheckBox(asignatura.getNombre());
            checkbox.setSelected(estudiante.getAsignaturas().contains(asignatura));
            checkboxPanel.add(checkbox);
        }

        JPanel buttonsPanel = new JPanel(new BorderLayout());
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        cambiarAsignaturasButton = new JButton("Cambiar asignaturas");
        cambiarAsignaturasButton.addActionListener(e -> {
            controlador.cambiarAsignaturas();
        });

        buttonsPanel.add(cambiarAsignaturasButton, BorderLayout.EAST);

        volverButton = new JButton("Volver");
        volverButton.addActionListener(e -> {
            controlador.volver();
        });
        buttonsPanel.add(volverButton, BorderLayout.WEST);

    }

    public void show() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Estudiante getEstudiante() {
        return estu;
    }

    public void dispose() {
        frame.dispose();
    }

    public Frame getFrame() {
        return frame;
    }

    public JPanel getCheckboxPanel() {
        return checkboxPanel;
    }
}