package clienteServidorTarea;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class JuegoLanzarDados extends Juego{
    private static final Object lock = new Object();
    private final String nombreJugador;

    public JuegoLanzarDados(PrintWriter escritor, Socket socket, String nombreJugador) {
        super(escritor, socket);
        this.nombreJugador = nombreJugador;
    }
    @Override
    public void jugar(BufferedReader entrada, PrintWriter salida) throws IOException {
        Random random = new Random();
        int victoriasCliente = 0;
        int victoriasServidor = 0;

        for (int i = 0; i < 5; i++) {
            // TODO esto no puede quedar asi.
            int lanzamientoCliente = Integer.parseInt(entrada.readLine());
            //int lanzamientoCliente = random.nextInt(6) + 1;
            int lanzamientoServidor = random.nextInt(6) + 1;

            if (lanzamientoCliente > lanzamientoServidor) {
                victoriasCliente++;
            } else if (lanzamientoServidor > lanzamientoCliente) {
                victoriasServidor++;
            } else {
                i--; // repite la ronda en caso de empate
            }
        }

        String resultado = victoriasCliente > victoriasServidor ? "Gana " + victoriasCliente + "-" + victoriasServidor + " contra el servidor." : "Pierde " + victoriasServidor + "-" + victoriasCliente + " contra el servidor.";
        salida.println(resultado);

        System.out.println("Jugador " + nombreJugador + " finalizó el juego. Resultado: " + resultado); // msg depurando

        synchronized (lock) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("Juego2.txt", true))) {
                writer.println(nombreJugador + ": " + resultado);
            }
        }
    }
}
