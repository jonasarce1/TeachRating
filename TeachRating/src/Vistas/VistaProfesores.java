package Vistas;

import Controladores.ControladorVistaProfesores;
import Profesores.Profesor;
import Sistema.Sistema;
import Usuarios.Asignatura;
import Valoracion.Comentario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.stream.Collectors;
import java.util.List;

public class VistaProfesores {
    private JFrame frame;

    private ControladorVistaProfesores controlador;
    @SuppressWarnings("unused")
    private Profesor profesor;
    @SuppressWarnings("unused")
    private Sistema sistema;
    private JPanel panel;

    public VistaProfesores(Profesor profesor) {
        this.profesor = profesor;
        Sistema s = Sistema.getInstance();

        frame = new JFrame("TeachRating - " + profesor.getNombre());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Configuramos el panel principal
        panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel);

        controlador = new ControladorVistaProfesores(this);

        // Configuramos los componentes
        JLabel profesorLabel = new JLabel(profesor.getNombre());
        profesorLabel.setFont(new Font("Arial", Font.BOLD, 32));
        panel.add(profesorLabel, BorderLayout.NORTH);

        JPanel detallesPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.add(detallesPanel, BorderLayout.CENTER);

        JLabel asignaturasLabel = new JLabel("Asignaturas:");
        detallesPanel.add(asignaturasLabel);

        List<String> asignaturasList = profesor.getAsignaturas().stream()
                .map(Asignatura::getNombre)
                .collect(Collectors.toList());

        JLabel asignaturasText = new JLabel(String.join(", ", asignaturasList));
        detallesPanel.add(asignaturasText);

        JLabel valoracionMediaLabel = new JLabel("Valoración media:");
        detallesPanel.add(valoracionMediaLabel);

        JLabel valoracionMediaText = new JLabel(String.format("%.2f", profesor.getMedia()));
        detallesPanel.add(valoracionMediaText);

        JLabel numVotosLabel = new JLabel("Número de usuarios que valoraron:");
        detallesPanel.add(numVotosLabel);

        JLabel numVotosText = new JLabel(String.valueOf(profesor.getContador()));
        detallesPanel.add(numVotosText);

        JTextArea comentariosArea = new JTextArea(10, 50);
        comentariosArea.setEditable(false);
        JScrollPane comentariosScrollPane = new JScrollPane(comentariosArea);
        panel.add(comentariosScrollPane, BorderLayout.SOUTH);

        JPanel volverPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(volverPanel, BorderLayout.NORTH);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(e -> {
            controlador.volver();
        });
        volverPanel.add(volverButton);

        // Agregamos los comentarios
        for (Comentario comentario : profesor.getComentarios()) {
            comentariosArea.append("- " + comentario + "\n"); //OJO: Falta el toString() en Comentario
        }
    }

    public void show() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void dispose() {
        frame.dispose();
    }

}

