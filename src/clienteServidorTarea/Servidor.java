package clienteServidorTarea;

import java.io.IOException;
import java.net.*;

public class Servidor {
    private static final int PUERTO = 49152;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);
            while (true) {
                Socket socket = serverSocket.accept();
                new GestorCliente(socket).start(); // GestorCliente es un hilo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
