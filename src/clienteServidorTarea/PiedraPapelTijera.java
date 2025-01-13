package clienteServidorTarea;

import java.io.*;
import java.util.Random;

public class PiedraPapelTijera {
    private BufferedReader entrada;
    private PrintWriter salida;
    private String nombreCliente;
    private int rondas;
    private int victoriasCliente;
    private int victoriasServidor;
    private boolean clienteGana;
    private Random random;

    public PiedraPapelTijera(BufferedReader entrada, PrintWriter salida, String nombreCliente) {
        this.entrada = entrada;
        this.salida =salida;
        this.nombreCliente = nombreCliente;
        this.random = new Random();
    }

    public void jugar() throws IOException {
        System.out.println(nombreCliente + " comienza el juego de PIEDRA, PAPEL, TIJERA.");

        while (rondas < 5) {
            salida.println("elije");
            int eleccionCliente = Integer.parseInt(entrada.readLine());
            int eleccionServidor = lanzarPiedraPapelTijera();

            String mapeoEleccionCliente = mapearEleccion(eleccionCliente);
            String mapeoEleccionServidor = mapearEleccion(eleccionServidor);

            determinarGanadorDeRonda(mapeoEleccionCliente, mapeoEleccionServidor);
        }

        // Determinar ganador de Partida
        clienteGana = victoriasCliente > victoriasServidor;

        salida.println("fin");
        salida.println(clienteGana ? "exito" : "fracaso");
    }

    public int lanzarPiedraPapelTijera() {
        return random.nextInt(3)+1;
    }

    private String mapearEleccion(int eleccion) {
        switch (eleccion) {
            case 1:
                return "Piedra";
            case 2:
                return "Papel";
            case 3:
                return "Tijera";
            default:
                throw new IllegalArgumentException("Elección inválida: " + eleccion);
        }
    }

    private void determinarGanadorDeRonda(String eleccionCliente, String eleccionServidor) {
        if (!eleccionCliente.equals(eleccionServidor)) {
            if ((eleccionCliente.equals("Piedra") && eleccionServidor.equals("Tijera")) ||
                    (eleccionCliente.equals("Papel") && eleccionServidor.equals("Piedra")) ||
                    (eleccionCliente.equals("Tijera") && eleccionServidor.equals("Papel"))) {
                victoriasCliente++;
                rondas++;
            } else {
                victoriasServidor++;
                rondas++;
            }
        }
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
