package es.masanz.ut6.ejercicios.model;

public class Carta {

    private String palo;
    private String valor;
    private int puntos;

    public Carta(String palo, String valor, int puntos) {
        this.palo = palo;
        this.valor = valor;
        this.puntos = puntos;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}