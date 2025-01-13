package clienteServidorTarea;

import java.io.*;
import java.util.Random;

public class AdivinarNumero {
    private BufferedReader entrada;
    private PrintWriter salida;
    private String nombreCliente;
    private int intentos;
    private boolean acertado;
    private int numeroAdivinar;


    public AdivinarNumero(BufferedReader entrada, PrintWriter salida, String nombreCliente) {
        this.entrada = entrada;
        this.salida =salida;
        this.nombreCliente = nombreCliente;
        this. numeroAdivinar = new Random().nextInt(100)+1;
    }

    public void jugar() throws IOException {

        int intentoCliente;

        salida.println("intenta"); // Solicitar un intento al cliente.
        while (intentos < 10 && !acertado) {
            // Leer el intento del cliente.
            intentoCliente = Integer.parseInt(entrada.readLine());
            intentos++; // Incrementamos el contador de intentos.

            if (intentoCliente == numeroAdivinar) {
                acertado = true;
            } else if (intentoCliente < numeroAdivinar) {
                salida.println("mayor"); // El número es mayor.
            } else {
                salida.println("menor"); // El número es menor.
            }
        }

        salida.println("fin");
        salida.println(acertado ? "exito" : "fracaso");
    }

    @Override
    public String toString() {

        if (acertado) {
            return nombreCliente + ": " + intentos + " intentos.";
        } else {
            return nombreCliente + ": No acertó el número.";
        }
    }
}
