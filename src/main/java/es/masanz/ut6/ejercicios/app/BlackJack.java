package es.masanz.ut6.ejercicios.app;

import es.masanz.ut6.ejercicios.model.Baraja;
import es.masanz.ut6.ejercicios.model.Carta;
import es.masanz.ut6.ejercicios.model.Jugador;
import es.masanz.ut6.ejercicios.gui.GUI;

import java.util.*;
import java.util.Map.Entry;

public class BlackJack {

    private Baraja baraja;
    private HashMap<Jugador, List<Carta>> jugadores;
    private List<Carta> crupier;
    private Map<Jugador, Integer> apuestas;

    public BlackJack() {
        baraja = new Baraja();
        jugadores = new HashMap<>();
        apuestas = new HashMap<>();

        GUI.mensajeBienvenida();

        int numJugadores = GUI.leerNumero("¿Cuantos jugadores van aparticipan? ");

        for (int i = 1; i <= numJugadores; i++) {
            String nombre = GUI.leerTexto("Ingresa el nombre del jugador " + i + ": ");;
            Jugador jugador = new Jugador(nombre);
            jugadores.put(jugador, new ArrayList<>());
        }
        crupier = new ArrayList<>();
    }

    public void jugar() {
        GUI.mostrarMensaje("¡Qué comience la partida!");

        while (!jugadores.isEmpty()) {
            apuestas.clear();
            baraja.inicializarBaraja();
            Set<Entry<Jugador, List<Carta>>> entries = jugadores.entrySet();
            declararApuesta();
            crupier.clear();
            crupier.add(baraja.repartirCarta());
            mostrarMano("Crupier", crupier);
            turnoJugadores();
            crupier.add(baraja.repartirCarta());
            GUI.mostrarMensaje("Turno del crupier...");
            while (calcularPuntaje(crupier) < 17) {
                crupier.add(baraja.repartirCarta());
            }
            mostrarMano("Crupier", crupier);
            calcularResultados();
        }
        GUI.mensajeFin(0);
    }

    private void calcularResultados() {
        Iterator<Jugador> iterador = jugadores.keySet().iterator();
        while (iterador.hasNext()) {

            Jugador jugador = iterador.next();
            int puntajeJugador = calcularPuntaje(jugadores.get(jugador));
            int puntajeCrupier = calcularPuntaje(crupier);
            int apuesta = apuestas.get(jugador);

            if (puntajeJugador > 21) {
                jugador.setDinero(jugador.getDinero()-apuesta);
            } else if (puntajeCrupier > 21 || puntajeJugador > puntajeCrupier) {
                jugador.setDinero(jugador.getDinero()+apuesta);
            } else if (puntajeJugador < puntajeCrupier) {
                jugador.setDinero(jugador.getDinero()-apuesta);
            } else {
                GUI.mostrarMensaje(jugador.getNombre()+" empata con CRUPIER");
            }

            if (jugador.getDinero() < 10) {
                GUI.mostrarMensaje(jugador.getNombre() + " ha sido expulsado con " + jugador.getDinero() + " dinero.");
                iterador.remove();
            } else {
                String respuesta = GUI.leerTexto(jugador.getNombre() + ", ¿quieres seguir jugando? Tienes "+jugador.getDinero()+" dinero. (S/n): ");
                if (respuesta.equalsIgnoreCase("n")) {
                    GUI.mostrarMensaje(jugador.getNombre() + " sale de la mesa con " + jugador.getDinero() + " dinero.");
                    iterador.remove();
                }
            }
        }
    }

    private void turnoJugadores() {
        for (Entry<Jugador, List<Carta>> entry : jugadores.entrySet()) {

            Jugador jugador = entry.getKey();
            List<Carta> mano = entry.getValue();

            GUI.mostrarMensaje("Turno de " + jugador.getNombre());
            mostrarMano(entry.getKey().getNombre(), mano);
            while (calcularPuntaje(jugadores.get(jugador)) < 21) {
                String decision = GUI.leerTexto("¿Quieres otra carta? (s/N): ");
                if (decision.equalsIgnoreCase("s")) {
                    jugadores.get(jugador).add(baraja.repartirCarta());
                    mostrarMano(entry.getKey().getNombre(), entry.getValue());
                } else {
                    break;
                }
            }
        }
    }

    private void declararApuesta() {

        for (Entry<Jugador, List<Carta>> entry : jugadores.entrySet()) {

            Jugador jugador = entry.getKey();
            List<Carta> mano = entry.getValue();

            int apuesta = GUI.leerNumero(jugador.getNombre() + ", ¿cuanto quieres apostar? (Tienes " + jugador.getDinero() + " dinero): ");

            while(apuesta > jugador.getDinero()) {
                GUI.mostrarMensaje("Apuesta invalida. No puede superar tu dinero disponible, vuelve a intentarlo.");
                apuesta = GUI.leerNumero(jugador.getNombre() + ", ¿cuanto quieres apostar? (Tienes " + jugador.getDinero() + " dinero): ");
            }

            apuestas.put(jugador, apuesta);
            mano.clear();
            mano.add(baraja.repartirCarta());
            mano.add(baraja.repartirCarta());
        }

    }

    private void mostrarMano(String nombre, List<Carta> mano) {

        GUI.mostrarMensaje(nombre + " tiene:");
        GUI.mostrarMano(mano);
        GUI.mostrarMensaje("Puntaje: " + calcularPuntaje(mano));
    }

    private int calcularPuntaje(List<Carta> mano) {
        int puntaje = 0;
        int ases = 0;
        for (Carta carta : mano) {
            puntaje += carta.getPuntos();
            if (carta.getValor().equals("As")) {
                ases++;
            }
        }
        while (puntaje > 21 && ases > 0) {
            puntaje -= 10;
            ases--;
        }
        return puntaje;
    }
}