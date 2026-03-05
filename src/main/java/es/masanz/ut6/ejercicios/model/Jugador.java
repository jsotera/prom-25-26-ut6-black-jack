package es.masanz.ut6.ejercicios.model;

public class Jugador {
    private String nombre;
    private int dinero;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.dinero = 100;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    @Override
    public String toString() {
        return nombre + " tiene " +dinero+"$";
    }
}