package Vistas;

import javax.swing.*;

import Controladores.ControladorVistaRegistrarse;
import Sistema.Sistema;
import Usuarios.Asignatura;
import Usuarios.Estudiante;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaRegistrarse {
    private JFrame frame;

    private ControladorVistaRegistrarse controlador;
    private JPanel centerPanel;
    public ArrayList<JCheckBox> asignaturasCheckBox = new ArrayList<JCheckBox>();
    private JTextField emailField;
    private JPasswordField passwordField, repeatPasswordField;

    public VistaRegistrarse() {
        Sistema s = Sistema.getInstance();
        frame = new JFrame("TeachRating - Registrarse");
        controlador = new ControladorVistaRegistrarse(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("TeachRating");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        centerPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        centerPanel.add(emailLabel);

        emailField = new JTextField();
        centerPanel.add(emailField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        centerPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        centerPanel.add(passwordField);

        JLabel repeatPasswordLabel = new JLabel("Repetir contraseña:");
        centerPanel.add(repeatPasswordLabel);

        repeatPasswordField = new JPasswordField();
        centerPanel.add(repeatPasswordField);

        JLabel subjectsLabel = new JLabel("Asignaturas:");
        centerPanel.add(subjectsLabel);

        for(Asignatura a : s.getAsignaturas()){
            JCheckBox cb = new JCheckBox(a.getNombre());
            this.asignaturasCheckBox.add(cb);
            centerPanel.add(cb);
        }

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout());

        JButton registerButton = new JButton("Registrarse");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws NumberFormatException {
                // Acción a realizar cuando se presione el botón de Registrarse
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String repeatPassword = String.valueOf(repeatPasswordField.getPassword());

                controlador.registrarse(email, password, repeatPassword);

                // Verificar que los campos no estén vacíos y que las contraseñas coincidan

            }
        });
        southPanel.add(registerButton);

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.volver();
            }
        });
        southPanel.add(backButton);

        frame.add(southPanel, BorderLayout.SOUTH);

    }

    public boolean checkSelected(){
        for(JCheckBox cb : asignaturasCheckBox){
            if(cb.isSelected()){
                return true;
            }
        }
        return false;
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void dispose() {
        frame.dispose();
    }

    public Frame getFrame() {
        return frame;
    }
}