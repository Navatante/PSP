package clienteServidorTarea;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {

    private static final int PUERTO = 1234;
    private static Impresora impresora;
    private static List<Thread> hilos = new ArrayList<>();
    private static boolean aceptarConexiones = true;



    public static void main(String[] args) throws IOException {
        impresora = new Impresora();

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado. Esperando conexiones en el puerto " + PUERTO + "...");

            while (aceptarConexiones) {
                Socket clientSocket = serverSocket.accept(); // Bloquea hasta recibir conexión

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

            switch (juego) {
                case "1":
                    adivinarNumero = new AdivinarNumero(entrada, salida, nombreCliente);
                    adivinarNumero.jugar();
                    impresora.imprimirResultadoJuego1(adivinarNumero.toString());
                    break;
                case "2":
                    lanzarDados = new LanzarDados(entrada, salida, nombreCliente);
                    lanzarDados.jugar();
                    impresora.imprimirResultadoJuego2(lanzarDados.toString());
                    break;
                case "3":
                    piedraPapelTijera = new PiedraPapelTijera(entrada, salida, nombreCliente);
                    piedraPapelTijera.jugar();
                    impresora.imprimirResultadoJuego3(piedraPapelTijera.toString());
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error al manejar al cliente: " + e.getMessage());
        }
    }
}