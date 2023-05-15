package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Controladores.ControladorAnyadirProfesor;
import Sistema.Sistema;
import Usuarios.Asignatura;

public class AnyadirProfesor {
    private JFrame frame;
    private ControladorAnyadirProfesor controlador;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel formPanel;
    private JPanel buttonsPanel;
    private JLabel titleLabel;
    private JLabel nombreLabel;
    private JTextField nombreField;
    private JLabel apellidoLabel;
    private JTextField apellidoField;
    private JLabel asignaturasLabel;
    private List<JCheckBox> asignaturasCheckBoxes;
    private JButton anyadirButton;
    private JButton backButton;

    public AnyadirProfesor() {
        Sistema s = Sistema.getInstance();
        frame = new JFrame("TeachRating");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        controlador = new ControladorAnyadirProfesor(this);

        mainPanel = new JPanel(new BorderLayout());
        titlePanel = new JPanel(new FlowLayout());
        formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        buttonsPanel = new JPanel(new FlowLayout());

        // Configurar el título de la ventana
        titleLabel = new JLabel("Añadir Profesor/a");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // Configurar el campo para introducir el nombre del profesor/a
        nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();
        formPanel.add(nombreLabel);
        formPanel.add(nombreField);

        // Configurar el campo para introducir el apellido del profesor/a
        apellidoLabel = new JLabel("Apellido:");
        apellidoField = new JTextField();
        formPanel.add(apellidoLabel);
        formPanel.add(apellidoField);

        // Configurar el checkbox para seleccionar las asignaturas que imparte el profesor/a
        asignaturasLabel = new JLabel("Asignaturas:");
        formPanel.add(asignaturasLabel);
        asignaturasCheckBoxes = new ArrayList<>();
        for (Asignatura asignatura : s.getAsignaturas()) {
            JCheckBox checkBox = new JCheckBox(asignatura.getNombre());
            asignaturasCheckBoxes.add(checkBox);
            formPanel.add(checkBox);
        }

        // Configurar el botón para añadir el profesor/a
        anyadirButton = new JButton("Añadir Profesor");
        anyadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText().trim();
                String apellido = apellidoField.getText().trim();
                controlador.anyadirProfesor(nombre, apellido, asignaturasCheckBoxes);
            }
        });
        buttonsPanel.add(anyadirButton);

        // Configurar el botón para volver a la pantalla de administrador
        backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.volver();
            }
        });
        buttonsPanel.add(backButton, FlowLayout.LEFT);

        // Agregar los paneles al panel principal
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        show();
    }
    public void show() {
        frame.setVisible(true);
    }

    public Frame getFrame() {
        return frame;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void dispose() {
        frame.dispose();
    }
}
