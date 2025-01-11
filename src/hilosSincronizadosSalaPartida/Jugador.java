package hilosSincronizadosSalaPartida;

import java.io.PrintWriter;
import java.util.Random;

public class Jugador implements Runnable{
    private String nombre;
    private SalaPartida salaPartida;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public Jugador(String nombre, SalaPartida salaPartida) {
        this.nombre = nombre;
        this.salaPartida = salaPartida;
    }

    public SalaPartida crearPartida(PrintWriter escritor, int capacidadEspectadoresMaxima, String nombreSala) {
        return this.salaPartida = new SalaPartida(escritor, capacidadEspectadoresMaxima, nombreSala);
    }

    private int tiempoAleatorio() {
        Random random = new Random();
        return random.nextInt(60000)+1; // genera un numero aleatorio entre 1 y 60000 (60seg)
    }

    @Override
    public void run() {
        if (salaPartida!= null) {
            try {
                int tiempo = tiempoAleatorio();
                salaPartida.entrarJugador(tiempo);
                Thread.sleep(tiempo); // aqui se simula el tiempo que esta dentro de la partida
                salaPartida.salirJugador();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Getter
    public String getNombre() {
        return nombre;
    }
}
