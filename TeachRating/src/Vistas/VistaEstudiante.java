package Vistas;

import javax.swing.*;

import Controladores.ControladorVistaEstudiante;
import Sistema.Sistema;
import Usuarios.Estudiante;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JLabel;
import Profesores.Profesor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaEstudiante{
    private JFrame frame;
    private ControladorVistaEstudiante controlador;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel listPanel;

    private String profNombre;
    private Estudiante estu;

    public VistaEstudiante(){
        Sistema s = Sistema.getInstance();
        frame = new JFrame("TeachRating");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        controlador = new ControladorVistaEstudiante(this);

        s.setProfesoresUsuarioActual();

        estu = (Estudiante) s.getUsuarioActual();

        mainPanel = new JPanel(new BorderLayout());
        titlePanel = new JPanel(new FlowLayout());
        listPanel = new JPanel(new GridLayout(estu.getProfesores().size(), 1));

        JLabel titleLabel = new JLabel("Profesores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        for (int i = 0; i < estu.getProfesores().size(); i++) {
            JPanel professorPanel = new JPanel(new FlowLayout());
            JLabel professorNameLabel = new JLabel(estu.getProfesores().get(i).getNombre() + " " + estu.getProfesores().get(i).getApellido());
            professorNameLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controlador.mostrarProfesores(separarNombre(professorNameLabel.getText()), separarApellido(professorNameLabel.getText()));
                }
            });
            professorPanel.add(professorNameLabel);

            JLabel starLabel = new JLabel("★");
            starLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controlador.mostrarEstrellas(separarNombre(professorNameLabel.getText()), separarApellido(professorNameLabel.getText()));
                }
            });
            professorPanel.add(starLabel);

            JLabel commentLabel = new JLabel("Comentar");
            commentLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controlador.mostrarComentario(professorNameLabel.getText());
                }
            });
            professorPanel.add(commentLabel);

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

        //nuevo boton Perfil que lleve a la vista de perfil
        JButton perfilButton = new JButton("Perfil");
        perfilButton.addActionListener(e -> {
            controlador.mostrarPerfil();
        });
        titlePanel.add(perfilButton);
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
