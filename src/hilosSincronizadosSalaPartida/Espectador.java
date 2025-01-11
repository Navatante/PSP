package hilosSincronizadosSalaPartida;

import java.util.Random;

public class Espectador implements Runnable{
    private String nombre;
    private SalaPartida salaPartida;

    public Espectador(SalaPartida salaPartida, String nombre) {
        this.nombre = nombre;
        this.salaPartida = salaPartida;
    }

    private int tiempoAleatorio() {
        Random random = new Random();
        return random.nextInt(15000)+1; // genera un numero aleatorio entre 1 y 15000
    }

    private int numeroAleatorio() {
        Random random = new Random();
        return random.nextInt(5)+1; // numero entre 1 y 5
    }

    @Override
    public void run() {
        if (salaPartida!= null) {
            try {
                while(numeroAleatorio()!=5) { // con este while pues creo una aleatoriedad para dejar de entrar en algun momento
                    salaPartida.entrarEspectador(tiempoAleatorio());
                    salaPartida.salirEspectador(); // pasado el tiempo aleatorio de arriba, se sale.
                    Thread.sleep(tiempoAleatorio()); // que duerma un tiempo aleatorio
                }

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
