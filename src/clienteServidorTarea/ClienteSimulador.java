package clienteServidorTarea;

import java.util.Scanner;

public class ClienteSimulador {
    public static String nombreJugador;
    public static int contadorJugador = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Cuántos jugadores para el Juego 1 (Adivinar un número)? ");
        int jugadoresJuego1 = scanner.nextInt();

        System.out.print("¿Cuántos jugadores para el Juego 2 (Lanzamiento de dados)? ");
        int jugadoresJuego2 = scanner.nextInt();

        System.out.print("¿Cuántos jugadores para el Juego 3 (Piedra, Papel o Tijera)? ");
        int jugadoresJuego3 = scanner.nextInt();

        lanzarHilosClientes(jugadoresJuego1, "1");
        lanzarHilosClientes(jugadoresJuego2, "2");
        lanzarHilosClientes(jugadoresJuego3, "3");
    }

    private static void lanzarHilosClientes(int cantidad, String juego) {
        for (int i = 1; i <= cantidad; i++) {
            nombreJugador = "Jugador" + contadorJugador;
            contadorJugador++;
            new Thread(new Cliente(juego, nombreJugador)).start();
        }
    }
}