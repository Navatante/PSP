package clienteServidorTarea;

import java.io.*;
import java.util.Random;

public class LanzarDados {
    private BufferedReader entrada;
    private PrintWriter salida;
    private String nombreCliente;
    private int rondas;
    private int victoriasCliente;
    private int victoriasServidor;
    private boolean clienteGana;
    private Random random;

    public LanzarDados(BufferedReader entrada, PrintWriter salida, String nombreCliente) {
        this.entrada = entrada;
        this.salida =salida;
        this.nombreCliente = nombreCliente;
        this.random = new Random();
    }

    public void jugar() throws IOException {
        int dadoCliente;
        int dadoServidor;

        while(rondas < 5) {
            // Pedimos al cliente que lance el dado
            salida.println("lanzameElDado");
            // Leer dado del cliente
            dadoCliente = Integer.parseInt(entrada.readLine());
            // Lanazar dado del servidor
            dadoServidor = lanzarDado();

            if (dadoCliente == dadoServidor) {
                // Empate, no hacemos nada.
            } else if (dadoCliente > dadoServidor) {
                victoriasCliente++;
                rondas++;
            } else {
                victoriasServidor++;
                rondas++;
            }
        }

        // Determinar ganador de Partida
        clienteGana = victoriasCliente > victoriasServidor;

        salida.println("fin");
        salida.println(clienteGana ? "exito" : "fracaso");
    }

    public int lanzarDado() {
        return random.nextInt(6) + 1;
    }

    @Override
    public String toString() {
        if (clienteGana) {
            return nombreCliente + ": Gana " + victoriasCliente + "-" + victoriasServidor + " contra el Servidor.";
        } else {
            return nombreCliente + ": Pierde " + victoriasServidor + "-" + victoriasCliente + " contra el Servidor.";
        }
    }
}
