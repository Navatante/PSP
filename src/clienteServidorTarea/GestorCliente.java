package clienteServidorTarea;

import java.io.*;
import java.net.Socket;
// TODO maybe esta clase llevatela a Servidor, y a ver si entiendo bien la comunicacion cliente servidor
public class GestorCliente extends Thread{
    private Socket socket;

    public GestorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            String juegoElegido = entrada.readLine(); // La primera entrada es el juego
            String nombreJugador = entrada.readLine(); // La segunda entrada es el nombre del jugador
            System.out.println("Juego elegido: " + juegoElegido + " por " + nombreJugador); // Mensaje de depuración
            Juego juego = null;

            switch (juegoElegido) {
                case "1":
                    juego = new JuegoAdivinarNumero(salida, socket, nombreJugador);
                    break;
                case "2":
                    juego = new JuegoLanzarDados(salida, socket, nombreJugador);
                    break;
                case "3":
                    juego = new JuegoPiedraPapelTijera(salida, socket, nombreJugador);
                    break;
                default:
                    salida.println("Eleccion de juego erronea.");
            }

            if (juego != null) {
                juego.jugar(entrada, salida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
