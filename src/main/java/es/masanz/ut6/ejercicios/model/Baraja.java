package es.masanz.ut6.ejercicios.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {

    public static final String[] palos = {"Corazones", "Diamantes", "Treboles", "Picas"};
    public static final String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    public static final int[] puntos = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    private List<Carta> cartas;

    public Baraja() {
        cartas = new ArrayList<>();
    }

    public void inicializarBaraja(){
        cartas.clear();
        for (String palo : palos) {
            for (int i = 0; i < valores.length; i++) {
                cartas.add(new Carta(palo, valores[i], puntos[i]));
            }
        }
        Collections.shuffle(cartas);
    }

    public Carta repartirCarta() {
        return cartas.remove(0);
    }
}