package clienteServidorSencillo;

import java.io.*;
import java.net.*;

public class Servidor {

    private static final int PORT = 1234;
    private static Impresora impresora;

    public static void main(String[] args) throws IOException {
        impresora = new Impresora();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado. Esperando conexiones en el puerto " + PORT + "...");

            while (true) {
                // Acepta una nueva conexión de cliente
                Socket clientSocket = serverSocket.accept(); // Esta linea blockea, y no avanza hasta la siguiente hasta que no se conecte un nuevo Cliente
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                // Crea un nuevo hilo para manejar al cliente
                new Thread(new ClientHandler(clientSocket , impresora)).start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}

// Clase para manejar la conexión con un cliente en un hilo separado
class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final Impresora impresora;
    private AdivinarNumero adivinarNumero;
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


            nombreCliente = entrada.readLine();
            juego = entrada.readLine();
            System.out.println("SERVIDOR: Manejando cliente: " + nombreCliente + " para jugar al Juego" + juego);

            switch (juego) {
                case "1":
                    System.out.println("SERVIDOR:" + nombreCliente + " va a jugar al Juego1.");
                    adivinarNumero = new AdivinarNumero(entrada, salida, nombreCliente);
                    adivinarNumero.jugar();
                    impresora.imprimirJuego1(adivinarNumero.toString()); // Imprimimos Resultado
                    break;
                case "2":
                    System.out.println("SERVIDOR:" + nombreCliente + " va a jugar al Juego2.");
                    break;
                case "3":
                    System.out.println("SERVIDOR:" + nombreCliente + " va a jugar al Juego3.");
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
// TODO el servidor es quien escribe el txt. Aun asi tb le paga el resultado al cliente.