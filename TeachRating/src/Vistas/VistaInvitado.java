package Vistas;

import javax.swing.*;

import Controladores.ControladorVistaInvitado;
import Sistema.Sistema;
import java.awt.*;
import javax.swing.JLabel;
import java.awt.event.*;
import Profesores.Profesor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaInvitado{
    private JFrame frame;
    private ControladorVistaInvitado controlador;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel listPanel;

    public VistaInvitado(){
        Sistema s = Sistema.getInstance();
        frame = new JFrame("TeachRating");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        controlador = new ControladorVistaInvitado(this);

        mainPanel = new JPanel(new BorderLayout());
        titlePanel = new JPanel(new FlowLayout());
        listPanel = new JPanel(new GridLayout(s.getProfesores().size(), 1));

        JLabel titleLabel = new JLabel("Profesores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        for (int i = 0; i < s.getProfesores().size(); i++) {
            JPanel professorPanel = new JPanel(new FlowLayout());
            JLabel professorNameLabel = new JLabel(s.getProfesores().get(i).getNombre() + " " + s.getProfesores().get(i).getApellido());
            professorNameLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controlador.mostrarProfesores(separarNombre(professorNameLabel.getText()), separarApellido(professorNameLabel.getText()));
                }
            });
            professorPanel.add(professorNameLabel);
            listPanel.add(professorPanel);

            JPanel volverPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            mainPanel.add(volverPanel, BorderLayout.SOUTH);

            JButton volverButton = new JButton("Salir");
            volverButton.addActionListener(e -> {
                controlador.salir();
            });
            volverPanel.add(volverButton);
        }

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(listPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
    }

    public void show(){
        frame.setVisible(true);
    }

    public String separarNombre(String n){
        String[] nombre = n.split(" ");
        return nombre[0];
    }

    public String separarApellido(String n){
        String[] apellido = n.split(" ");
        return apellido[1];
    }

    public void dispose(){
        frame.dispose();
    }
}