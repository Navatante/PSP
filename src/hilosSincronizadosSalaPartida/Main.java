package hilosSincronizadosSalaPartida;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        FileWriter fichero = new FileWriter("resultadoSalaPartida.txt");
        BufferedWriter escritorBuffer = new BufferedWriter(fichero);
        PrintWriter escritor = new PrintWriter(escritorBuffer, true); // true para auto flush

        Jugador jugador1 = new Jugador("Jugador1");
        SalaPartida salaPartida = jugador1.crearPartida(escritor,20, "Sanguino");

        //Variables necesarias para el menu:
        int numJugador = 1;
        int numEspectador = 1;
        int cantidadEspectadores;
        int opcion;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una opcion:");
        System.out.println("\t 1: Nuevo Jugador");
        System.out.println("\t 2: Mas Espectadores");
        System.out.println("\t 3: Saber estado sala");
        System.out.println("\t 4: Salir");

        do {
            opcion = scanner.nextInt();

            switch (opcion) {

                case 1:
                    Jugador jugador = new Jugador("Jugador " + numJugador, salaPartida);
                    new Thread(jugador,jugador.getNombre()).start();
                    System.out.println("Jugador " + numJugador + " creado.");
                    numJugador++;
                    break;

                case 2:
                    System.out.println("Introduce cuantos espectadores quieres crear:");
                    cantidadEspectadores = scanner.nextInt();
                    for(int i = 0; i < cantidadEspectadores; i++) {
                        Espectador espectador = new Espectador(salaPartida,"Espectador " + numEspectador);
                        new Thread(espectador,espectador.getNombre()).start();
                        System.out.println("Espectador " + numEspectador + " creado.");
                        numEspectador++;
                    }
                    break;

                case 3:
                    System.out.println("\n\nESTADO SALA\n" + salaPartida);
                    break;

                case 4:
                    System.exit(0);
                    break;
            }
        } while(opcion!=4);

    }
}
