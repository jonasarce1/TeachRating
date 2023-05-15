package Controladores;

import Sistema.Sistema;
import Vistas.VistaComentar;

import javax.swing.*;

public class ControladorVistaComentar {
    private VistaComentar vistaComentar;
    private Sistema s = Sistema.getInstance();

    public ControladorVistaComentar(VistaComentar vistaComentar) {
        this.vistaComentar = vistaComentar;
    }

    public void enviarComentario(String comentario, String nombre, String apellido) {
        if(comentario.isEmpty()){
            JOptionPane.showMessageDialog(vistaComentar.getFrame(), "No se puede enviar un comentario vacío", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(s.insertarComentario(nombre, apellido, comentario))
            JOptionPane.showMessageDialog(vistaComentar.getFrame(), "Comentario enviado exitosamente", "Comentario enviado", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(vistaComentar.getFrame(), "No se pudo enviar el comentario, asegurese de no incluir palabras malsonantes", "Error", JOptionPane.ERROR_MESSAGE);
        vistaComentar.dispose();
    }

    public void volver() {
        vistaComentar.dispose();
    }
}
