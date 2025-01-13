package clienteServidorTarea;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {

    private static final int PORT = 1234;
    private static Impresora impresora;
    private static List<Thread> hilos = new ArrayList<>();
    private static boolean aceptarConexiones = true;



    public static void main(String[] args) throws IOException {
        impresora = new Impresora();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado. Esperando conexiones en el puerto " + PORT + "...");

            while (aceptarConexiones) {
                Socket clientSocket = serverSocket.accept(); // Bloquea hasta recibir conexión
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                // Crea un nuevo hilo para manejar al cliente y lo agrega a la lista.
                Thread hilo = new Thread(new ClientHandler(clientSocket, impresora));
                hilo.start();
                hilos.add(hilo);
            }

            // Espera a que todos los hilos finalicen antes de cerrar el programa
            for (Thread hilo : hilos) {
                hilo.join();
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Error al esperar los hilos: " + e.getMessage());
        }
    }

}

// Clase para manejar la conexión con un cliente en un hilo separado
class ClientHandler implements Runnable {

    // Socket, impresora
    private final Socket clientSocket;
    private final Impresora impresora;

    // Juegos
    private AdivinarNumero adivinarNumero;
    private LanzarDados lanzarDados;
    private PiedraPapelTijera piedraPapelTijera;

    // Datos del cliente
    private String nombreCliente;
    private String juego;

    public ClientHandler(Socket clientSocket, Impresora impresora) {
        this.clientSocket = clientSocket;
        this.impresora = impresora;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter salida = new PrintWriter(clientSocket.getOutputStream(), true)) {

            nombreCliente = entrada.readLine(); // Recibe el nombre del cliente
            juego = entrada.readLine(); // Recibe el juego al que va a jugar
            System.out.println("SERVIDOR: Manejando cliente: " + nombreCliente + " para jugar al Juego" + juego);

            switch (juego) {
                case "1":
                    System.out.println("SERVIDOR:" + nombreCliente + " va a jugar al Juego1.");
                    adivinarNumero = new AdivinarNumero(entrada, salida, nombreCliente);
                    adivinarNumero.jugar();
                    impresora.imprimirResultadoJuego1(adivinarNumero.toString());
                    break;
                case "2":
                    System.out.println("SERVIDOR:" + nombreCliente + " va a jugar al Juego2.");
                    lanzarDados = new LanzarDados(entrada, salida, nombreCliente);
                    lanzarDados.jugar();
                    impresora.imprimirResultadoJuego2(lanzarDados.toString());
                    break;
                case "3":
                    System.out.println("SERVIDOR:" + nombreCliente + " va a jugar al Juego3.");
                    piedraPapelTijera = new PiedraPapelTijera(entrada, salida, nombreCliente);
                    piedraPapelTijera.jugar();
                    impresora.imprimirResultadoJuego3(piedraPapelTijera.toString());
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error al manejar al cliente: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Conexión con cliente cerrada: " + nombreCliente);
            } catch (IOException e) {
                System.out.println("Error al cerrar la conexión con el cliente: " + e.getMessage());
            }
        }
    }
}