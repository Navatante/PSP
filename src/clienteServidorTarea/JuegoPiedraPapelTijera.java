package clienteServidorTarea;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class JuegoPiedraPapelTijera extends Juego{
    private static final Object lock = new Object();
    private final String nombreJugador;

    public JuegoPiedraPapelTijera(PrintWriter escritor, Socket socket, String nombreJugador) {
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
            int eleccionCliente = Integer.parseInt(entrada.readLine());
            //int eleccionCliente = random.nextInt(3) + 1;
            int eleccionServidor = random.nextInt(3) + 1;

            if (eleccionCliente == eleccionServidor) {
                i--; // repite la ronda en caso de empate
            } else if ((eleccionCliente == 1 && eleccionServidor == 3) ||
                    (eleccionCliente == 2 && eleccionServidor == 1) ||
                    (eleccionCliente == 3 && eleccionServidor == 2)) {
                victoriasCliente++;
            } else {
                victoriasServidor++;
            }
        }

        String resultado = victoriasCliente > victoriasServidor ? "Gana " + victoriasCliente + "-" + victoriasServidor + " contra el servidor." : "Pierde " + victoriasServidor + "-" + victoriasCliente + " contra el servidor.";
        salida.println(resultado);

        System.out.println("Jugador " + nombreJugador + " finalizó el juego. Resultado: " + resultado); // msg depurando
        synchronized (lock) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("Juego3.txt", true))) {
                writer.println(nombreJugador + ": " + resultado);
            }
        }
    }
}
