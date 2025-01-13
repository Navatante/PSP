package clienteServidorTarea;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Cliente {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 1234;

    private final String nombreCliente;
    private final String juego;

    private Random random;

    public Cliente(String juego, String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.juego = juego;
        random = new Random();
    }

    public void jugar() {
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

                System.out.println(nombreCliente + " conectado al servidor");

                // Envia datos al servidor
                salida.println(nombreCliente); // Enviamos el nombre del cliente
                salida.println(juego); // Enviamos el juego al que va a jugar
                System.out.println("Mensaje enviado al servidor: " + nombreCliente + ". Va a jugar al juego: " + juego);

                // Empezar intentos
                int intento = -1;
                int intentoAnterior = -1;
                int limiteInferior = 1;
                int limiteSuperior = 100;

                String mensaje;

                // Bucle principal del juego
                while ((mensaje = entrada.readLine()) != null) {
                    switch (mensaje) {
                        case "mayor":
                            limiteInferior = intentoAnterior + 1; // Ajustar el rango inferior
                            intento = generarNumeroAleatorio(limiteInferior, limiteSuperior, random);
                            break;
                        case "menor":
                            limiteSuperior = intentoAnterior - 1; // Ajustar el rango superior
                            intento = generarNumeroAleatorio(limiteInferior, limiteSuperior, random);
                            break;
                        case "fin":
                            return;
                        default: //"intenta" llega aqui
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
            System.out.println("Error de conexión para " + nombreCliente + ": " + e.getMessage());
        }
    }

    public void jugarJuego2() {
        try {
            // Crear socket y conectar al servidor
            try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                System.out.println(nombreCliente + " conectado al servidor");

                // Envia datos al servidor
                salida.println(nombreCliente); // Enviamos el nombre del cliente
                salida.println(juego); // Enviamos el juego al que va a jugar
                System.out.println("Mensaje enviado al servidor: " + nombreCliente + ". Va a jugar al juego: " + juego);

                String mensaje;
                int dadoCliente = -1;

                // Bucle principal del juego
                while ((mensaje = entrada.readLine()) != null) {
                    if ("lanzameElDado".equals(mensaje)) {
                        dadoCliente = lanzarDado();
                        salida.println(dadoCliente);
                    } else if ("fin".equals(mensaje)) {
                        break;
                    }
                }

                // Comprobar el resultado final
                String resultado = entrada.readLine();
                if ("exito".equals(resultado)) {
                    System.out.println("El Cliente ha ganado.");
                } else if ("fracaso".equals(resultado)) {
                    System.out.println("El Servidor ha ganado.");
                }

            }

        } catch (IOException e) {
            System.out.println("Error de conexión para " + nombreCliente + ": " + e.getMessage());
        }
    }

    public void jugarJuego3() {
        try {
            // Crear socket y conectar al servidor
            try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                System.out.println(nombreCliente + " conectado al servidor");

                // Envia datos al servidor
                salida.println(nombreCliente); // Enviamos el nombre del cliente
                salida.println(juego); // Enviamos el juego al que va a jugar
                System.out.println("Mensaje enviado al servidor: " + nombreCliente + ". Va a jugar al juego: " + juego);

                String mensaje;
                int eleccionCliente = -1;

                // Bucle principal del juego
                while ((mensaje = entrada.readLine()) != null) {
                    if ("elije".equals(mensaje)) {
                        eleccionCliente = lanzarPiedraPapelTijera();
                        salida.println(eleccionCliente);
                    } else if ("fin".equals(mensaje)) {
                        break;
                    }
                }

                // Comprobar el resultado final
                String resultado = entrada.readLine();
                if ("exito".equals(resultado)) {
                    System.out.println("El Cliente ha ganado.");
                } else if ("fracaso".equals(resultado)) {
                    System.out.println("El Servidor ha ganado.");
                }

            }

        } catch (IOException e) {
            System.out.println("Error de conexión para " + nombreCliente + ": " + e.getMessage());
        }
    }


    // Genera un número aleatorio entre un rango dado (inclusive).
    private static int generarNumeroAleatorio(int min, int max, Random random) {
        if (min > max) {
            throw new IllegalArgumentException("El rango mínimo no puede ser mayor que el máximo.");
        }
        return min + random.nextInt(max - min + 1);
    }

    // Metodo para lanzar dados
    public int lanzarDado() {
        return random.nextInt(6) + 1;
    }

    // Metodo para lanzar Piedra, Papel, Tijera.
    public int lanzarPiedraPapelTijera() {
        return random.nextInt(3)+1;
    }
}