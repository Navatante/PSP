package clienteServidorSencillo;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Cliente implements Runnable {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 1234;

    private final String nombreJugador;
    private final String juego;

    public Cliente(String juego, String nombreJugador) {
        this.nombreJugador = nombreJugador;
        this.juego = juego;
    }

    @Override
    public void run() {
        switch (juego) {
            case "1":
                jugarJuego1();
                break;
            case "2":
                jugarJuego2();
                break;
            case "3":
                jugarJuego3();
                break;
        }
    }

    public void jugarJuego1() {
        try {
            // Crear socket y conectar al servidor
            try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                System.out.println(nombreJugador + " conectado al servidor");

                // Envia datos al servidor
                salida.println(nombreJugador); // Enviamos el nombre del cliente
                salida.println(juego); // Enviamos el juego al que va a jugar
                System.out.println("Mensaje enviado al servidor: " + nombreJugador + ". Va a jugar al juego: " + juego);

                // Empezar intentos
                Random random = new Random();
                int intento = -1;
                int intentoAnterior = -1;
                int limiteInferior = 1;
                int limiteSuperior = 100;

                String mensaje;

                // Bucle principal del juego
                while (!(mensaje = entrada.readLine()).equals("fin")) {
                    switch (mensaje) {
                        case "mayor":
                            limiteInferior = intentoAnterior + 1; // Ajustar el rango inferior
                            intento = generarNumeroAleatorio(limiteInferior, limiteSuperior, random);
                            break;
                        case "menor":
                            limiteSuperior = intentoAnterior - 1; // Ajustar el rango superior
                            intento = generarNumeroAleatorio(limiteInferior, limiteSuperior, random);
                            break;
                        default:
                            intento = generarNumeroAleatorio(limiteInferior, limiteSuperior, random);
                            break;
                    }

                    salida.println(intento);
                    intentoAnterior = intento;

                }

                // Comprobar el resultado final
                String resultado = entrada.readLine();
                if ("exito".equals(resultado)) {
                    System.out.println("¡Adivinaste el número! Era: " + intento);
                } else if ("fracaso".equals(resultado)) {
                    System.out.println("No se pudo adivinar el número. Detalles: " + entrada.readLine());
                }

            }

        } catch (IOException e) {
            System.out.println("Error de conexión para " + nombreJugador + ": " + e.getMessage());
        }
    }

    public void jugarJuego2() {
        try {
            // Create socket and connect to server
            try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                System.out.println(nombreJugador + " conectado al servidor");

                // Envia datos al servidor
                salida.println(nombreJugador); // Enviamos el nombre del cliente
                salida.println(juego); // Enviamos el juego al que va a jugar
                System.out.println("Mensaje enviado al servidor: " + nombreJugador + ". Va a jugar al juego: " + juego);

                // Recibir respuesta del servidor
                String response = entrada.readLine();
                System.out.println(nombreJugador + " recibió del servidor: " + response);
            }

        } catch (IOException e) {
            System.out.println("Error de conexión para " + nombreJugador + ": " + e.getMessage());
        }
    }

    public void jugarJuego3() {
        try {
            // Create socket and connect to server
            try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                System.out.println(nombreJugador + " conectado al servidor");

                // Envia datos al servidor
                salida.println(nombreJugador); // Enviamos el nombre del cliente
                salida.println(juego); // Enviamos el juego al que va a jugar
                System.out.println("Mensaje enviado al servidor: " + nombreJugador + ". Va a jugar al juego: " + juego);

                // Recibir respuesta del servidor
                String response = entrada.readLine();
                System.out.println(nombreJugador + " recibió del servidor: " + response);
            }

        } catch (IOException e) {
            System.out.println("Error de conexión para " + nombreJugador + ": " + e.getMessage());
        }
    }

    /**
     * Genera un número aleatorio entre un rango dado (inclusive).
     */
    private static int generarNumeroAleatorio(int min, int max, Random random) {
        if (min > max) {
            throw new IllegalArgumentException("El rango mínimo no puede ser mayor que el máximo.");
        }
        return min + random.nextInt(max - min + 1);
    }

}
