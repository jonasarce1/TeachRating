package Vistas;

import javax.swing.*;

import Controladores.ControladorVistaAdmin;
import Sistema.Sistema;
import java.awt.*;
import javax.swing.JLabel;
import Profesores.Profesor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.awt.event.*;

public class VistaAdmin{
    private JFrame frame;
    private ControladorVistaAdmin controlador;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel listPanel;

    public VistaAdmin(){
        Sistema s = Sistema.getInstance();
        frame = new JFrame("TeachRating");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        controlador = new ControladorVistaAdmin(this);

        mainPanel = new JPanel(new BorderLayout());
        titlePanel = new JPanel(new FlowLayout());
        listPanel = new JPanel(new GridLayout(s.getProfesores().size(), 1));

        JLabel titleLabel = new JLabel("Profesores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        //Usamos while e iterator para poder eliminar elementos de la lista sin que de error al actualizar la lista en mitad del for
        Iterator<Profesor> iter = s.getProfesores().iterator();
        while (iter.hasNext()) {
            Profesor p = iter.next();
            JPanel professorPanel = new JPanel(new FlowLayout());
            JLabel professorNameLabel = new JLabel(p.getNombre() + " " + p.getApellido());
            professorNameLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controlador.mostrarProfesores(p);
                }
            });
            professorPanel.add(professorNameLabel);
            listPanel.add(professorPanel);

            JButton quitarLabel = new JButton("Quitar");
            quitarLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controlador.quitarProfesor(p);
                }
            });
            professorPanel.add(quitarLabel);

            JPanel volverPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            mainPanel.add(volverPanel, BorderLayout.WEST);

            JButton volverButton = new JButton("Salir");
            volverButton.addActionListener(e -> {
                controlador.salir();
            });
            volverPanel.add(volverButton);

            JPanel anadirPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            mainPanel.add(anadirPanel, BorderLayout.SOUTH);

            JButton anadirButton = new JButton("Anadir Profesor");
            anadirButton.addActionListener(e -> {
                controlador.mostrarAnyadirProfesor();
            });
            anadirPanel.add(anadirButton);
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