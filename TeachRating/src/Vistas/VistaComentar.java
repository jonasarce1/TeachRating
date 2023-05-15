package Vistas;

import Controladores.ControladorVistaComentar;
import Sistema.Sistema;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class VistaComentar {
    private JFrame frame;
    private ControladorVistaComentar controlador;
    private String nombreProfesor;

    public VistaComentar(String profesor) {
        this.nombreProfesor = profesor;
        Sistema s = Sistema.getInstance();
        frame = new JFrame("Insertar comentario");
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controlador = new ControladorVistaComentar(this);

        // Label del título
        JLabel titleLabel = new JLabel("Insertar comentario");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Label del nombre del profesor
        JLabel professorNameLabel = new JLabel("Profesor: " + profesor);
        professorNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        professorNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        mainPanel.add(professorNameLabel, BorderLayout.CENTER);

        // Panel para el texto del comentario
        JPanel commentPanel = new JPanel(new BorderLayout());
        JLabel commentLabel = new JLabel("Inserte su comentario aquí:");
        commentLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextArea commentArea = new JTextArea(10, 30);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(commentArea);
        commentPanel.add(commentLabel, BorderLayout.NORTH);
        commentPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(commentPanel, BorderLayout.SOUTH);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.volver();
            }
        });
        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.enviarComentario(commentArea.getText(), separarNombre(), separarApellido());
            }
        });
        buttonPanel.add(backButton);
        buttonPanel.add(enviarButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void show() {
        frame.setVisible(true);
    }

    public String separarNombre(){
        String[] nombre = nombreProfesor.split(" ");
        return nombre[0];
    }

    public String separarApellido(){
        String[] apellido = nombreProfesor.split(" ");
        return apellido[1];
    }

    public void dispose() {
        frame.dispose();
    }

    public Frame getFrame() {
        return frame;
    }
}
