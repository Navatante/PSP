package clienteServidorTarea;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Cliente implements Runnable {
    private static final String HOST = "localhost";
    private static final int PUERTO = 49152;
    private final String juego;
    private final String nombreJugador;

    public Cliente(String juego, String nombreJugador) {
        this.juego = juego;
        this.nombreJugador = nombreJugador;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println(nombreJugador + " conectado al servidor.");
            salida.println(juego); // Primero envia la elección del juego al servidor
            salida.println(nombreJugador); // Segundo envia el nombre del jugador al servidor

            String respuesta;
            while ((respuesta = entrada.readLine()) != null) {
                System.out.println(nombreJugador + ": " + respuesta);
                if (respuesta.contains("Correcto!") || respuesta.contains("Se te han acabado los intentos") ||
                        respuesta.contains("Gana") || respuesta.contains("Pierde")) {
                    break; // Finaliza si se recibe un mensaje indicando el resultado del juego
                }
                if (respuesta.contains("Eleccion de juego erronea.")) {
                    System.out.println(nombreJugador + ": Intenta de nuevo con una elección válida.");
                    break;
                }
                // Los inputs del cliente van a ser automáticamente generados para que el programa funcione de manera autónoma.
                String inputCliente;
                switch (juego) {
                    case "1":
                        inputCliente = String.valueOf(new Random().nextInt(100) + 1);
                        salida.println(inputCliente); // Enviar la respuesta del cliente al servidor
                        break;
                    case "2":
                        System.out.println("HOOOOOOOOOOOOOOOOOOLA"); // TODO el codigo no esta llegando aqui
                        inputCliente = String.valueOf(new Random().nextInt(6) + 1);
                        salida.println(inputCliente); // Enviar la respuesta del cliente al servidor
                        break;
                    case "3":
                        System.out.println("ADIOOOOOOOOOOOOOS"); // TODO el codigo no esta llegando aqui
                        inputCliente = String.valueOf(new Random().nextInt(3) + 1);
                        salida.println(inputCliente); // Enviar la respuesta del cliente al servidor
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}