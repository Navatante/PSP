package clienteServidorTarea;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class JuegoAdivinarNumero extends Juego{
    private static final Object lock = new Object();
    private final String nombreJugador;

    public JuegoAdivinarNumero(PrintWriter escritor, Socket socket, String nombreJugador) {
        super(escritor, socket);
        this.nombreJugador = nombreJugador;
    }

    @Override
    public void jugar(BufferedReader entrada, PrintWriter salida) throws IOException {
        Random random = new Random();
        int numeroAdivinar = random.nextInt(100) + 1;
        int intentos = 0;
        boolean acertado = false;

        salida.println("Adivina el número entre 1 y 100"); // Mensaje inicial al cliente

        while (intentos < 10 && !acertado) {
            int intento = Integer.parseInt(entrada.readLine());
            intentos++;
            if (intento == numeroAdivinar) {
                acertado = true;
                salida.println("Correcto! Has acertado el numero en " + intentos + " intentos.");
            } else if (intento < numeroAdivinar) {
                salida.println("Te has pasado");
            } else {
                salida.println("Te has quedado corto");
            }
        }

        if (!acertado) {
            salida.println("Se te han acabado los intentos. El numero era " + numeroAdivinar);
        }

        synchronized (lock) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("Juego1.txt", true))) {
                if (acertado) {
                    writer.println(nombreJugador + ": " + intentos + " intentos.");
                } else {
                    writer.println(nombreJugador + ": No acertó el número.");
                }
            }
        }
    }
}
