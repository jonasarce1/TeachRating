package Valoracion;

import java.io.Serializable;

public class Puntuacion implements Serializable{
    private static final long serialVersionUID = 1L;

    private int valor;

    private void checkValor(int v) {
        if (v < 1 || v > 5) {
            throw new IllegalArgumentException("La puntuación debe ser un valor entre 1 y 5.");
        }
    }

    public Puntuacion(int valor) {
        checkValor(valor);
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        checkValor(valor);
        this.valor = valor;
    }
}