package Vistas;

import javax.swing.*;

import Controladores.ControladorVistaPuntuar;
import Sistema.Sistema;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPuntuar {

    private Sistema s = Sistema.getInstance();

    private ControladorVistaPuntuar controlador;
    private JFrame frame;
    private String nombreProfesor;
    private JRadioButton unoRadioButton;
    private JRadioButton dosRadioButton;
    private JRadioButton tresRadioButton;
    private JRadioButton cuatroRadioButton;
    private JRadioButton cincoRadioButton;

    public VistaPuntuar(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
        controlador = new ControladorVistaPuntuar(this);
        frame = new JFrame("TeachRating - Puntuar");

        JLabel titleLabel = new JLabel("Profesor: " + nombreProfesor, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        frame.add(titleLabel, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel optionsPanel = new JPanel(new GridLayout(5, 1));
        unoRadioButton = new JRadioButton("1");
        dosRadioButton = new JRadioButton("2");
        tresRadioButton = new JRadioButton("3");
        cuatroRadioButton = new JRadioButton("4");
        cincoRadioButton = new JRadioButton("5");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(unoRadioButton);
        buttonGroup.add(dosRadioButton);
        buttonGroup.add(tresRadioButton);
        buttonGroup.add(cuatroRadioButton);
        buttonGroup.add(cincoRadioButton);

        optionsPanel.add(unoRadioButton);
        optionsPanel.add(dosRadioButton);
        optionsPanel.add(tresRadioButton);
        optionsPanel.add(cuatroRadioButton);
        optionsPanel.add(cincoRadioButton);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.volver();
            }
        });
        JButton submitButton = new JButton("Enviar");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.enviarPuntuacion();
            }
        });

        buttonsPanel.add(backButton);
        buttonsPanel.add(submitButton);

        frame.add(optionsPanel, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void show() {
        frame.setVisible(true);
    }

    public int getPuntuacion() {
        if (unoRadioButton.isSelected()) {
            return 1;
        } else if (dosRadioButton.isSelected()) {
            return 2;
        } else if (tresRadioButton.isSelected()) {
            return 3;
        } else if (cuatroRadioButton.isSelected()) {
            return 4;
        } else if (cincoRadioButton.isSelected()) {
            return 5;
        } else {
            return -1;
        }
    }

    public String separarNombre(){
        String[] nombre = nombreProfesor.split(" ");
        return nombre[0];
    }

    public String separarApellido(){
        String[] apellido = nombreProfesor.split(" ");
        return apellido[1];
    }

    public void dispose(){
        frame.dispose();
    }
}