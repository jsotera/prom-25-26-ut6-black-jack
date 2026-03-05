package es.masanz.ut6.ejercicios.gui;

import es.masanz.ut6.ejercicios.model.Carta;

import java.util.List;
import java.util.Scanner;

public class GUI {

    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarMensaje(String msg) {
        System.out.println();
        System.out.println(msg);
    }

    public static void mensajeBienvenida() {
        System.out.println();
        System.out.println("*".repeat(33));
        System.out.println("*     WELCOME TO BLACK JACK     *");
        System.out.println("*".repeat(33));
    }

    public static int leerNumero() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static int leerNumero(String msg) {
        System.out.println();
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    public static String leerTexto(String msg) {
        System.out.println();
        System.out.print(msg);
        return scanner.nextLine();
    }

    public static void mensajeFin(int dinero) {
        System.out.println();
        System.out.println("*".repeat(29));
        System.out.println("*     GRACIAS POR JUGAR     *");
        System.out.println("*".repeat(29));
    }

    public static void mostrarMano(List<Carta> mano) {
        System.out.println();
        for (Carta carta : mano) {
            System.out.println(carta);
        }
    }
}
