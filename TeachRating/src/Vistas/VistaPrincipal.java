package Vistas;

import javax.swing.*;

import Controladores.ControladorVistaPrincipal;
import Controladores.ControladorVistaPuntuar;
import Sistema.Sistema;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class VistaPrincipal {
    private static JFrame frame;

    private ControladorVistaPrincipal controlador;

    public VistaPrincipal() {
        Sistema s = Sistema.getInstance();
        frame = new JFrame("TeachRating");
        controlador = new ControladorVistaPrincipal(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 175);
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("TeachRating");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        centerPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        centerPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        centerPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        centerPanel.add(passwordField);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout());

        JButton registerButton = new JButton("Registrarse");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana de registro de usuario
                VistaRegistrarse registrarse = new VistaRegistrarse();
                registrarse.setVisible(true);
                frame.dispose();
            }
        });
        southPanel.add(registerButton);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.login(usernameField.getText(), passwordField.getText());
            }

        });
        southPanel.add(loginButton);

        JButton guestButton = new JButton("Invitado");
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.Invitado();
            }
        });
        southPanel.add(guestButton);

        frame.add(southPanel, BorderLayout.SOUTH);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void dispose() {
        frame.dispose();
    }

    public static Frame getFrame() {
        return frame;
    }
}