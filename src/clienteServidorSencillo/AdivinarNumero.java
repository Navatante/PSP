package clienteServidorSencillo;

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

        salida.println("no"); // Mensaje inicial para que el cliente comience a enviar intentos.

        while (intentos < 10 && !acertado) {

            // Leer el intento del cliente.
            String intentoDelCliente = entrada.readLine();
            if (intentoDelCliente == null) {
                salida.println("Error: no se recibió ningún intento. Inténtalo de nuevo.");
                break; // Salimos del bucle si no hay datos.
            }

            int intento = Integer.parseInt(intentoDelCliente); // Convertimos el intento a número.
            intentos++; // Incrementamos el contador de intentos.

            if (intento == numeroAdivinar) {
                acertado = true;
                salida.println("fin"); // El cliente acertó.
                salida.println("exito");
            } else if (intento < numeroAdivinar) {
                salida.println("mayor"); // El número es mayor.
            } else {
                salida.println("menor"); // El número es menor.
            }
        }

        if (!acertado) {
            salida.println("fin");
            salida.println("fracaso");
        }
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
