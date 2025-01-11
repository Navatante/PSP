package hilosSincronizadosSalaPartida;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.PrintWriter;

// SalaPartida es el recurso compartido.
public class SalaPartida {
    private String nombreSala;
    private final int capacidadEspectadoresMaxima;
    private int espectadoresEnSala;
    private String jugadorEnDirecto;
    private boolean enDirecto;
    PrintWriter escritor;

    public SalaPartida(PrintWriter escritor, int capacidadEspectadoresMaxima, String nombreSala) {
        this.capacidadEspectadoresMaxima = capacidadEspectadoresMaxima;
        this.escritor = escritor;
        this.nombreSala = nombreSala;
    }

    public synchronized void entrarJugador(int tiempo) throws InterruptedException {
        while(enDirecto || espectadoresEnSala>0) { // si hay otro en directo o aun quedan espectadores, te esperas.
            wait();
        }
        this.enDirecto = true;
        this.jugadorEnDirecto = Thread.currentThread().getName();
        escritor.println(this.jugadorEnDirecto + " Entro a la Sala: " + this.nombreSala);
        System.out.println(jugadorEnDirecto + " va a estar jugando durante " + tiempo/1000 +" seg.");
    }

    public synchronized void salirJugador() {
        if(enDirecto) {
            espectadoresEnSala = 0;
            enDirecto = false;
            jugadorEnDirecto = ""; // borramos el nombre del jugador que estaba en directo.
            notifyAll(); // notificamos que la sala esta libre a otros hilos jugadores.
        }
    }

    public synchronized void entrarEspectador(int tiempo) throws InterruptedException {
        while(!enDirecto) {
            wait(15000); // te pones a la espera, y si alguien te avisa con .notify() o .notifyAll() lo intentas, y sino, a los 15 seg lo intentas tb.
            // pasarle el tiempo por parametros es importante porque el unico que esta haciendo notifyAll() es el jugador cuando sale.
        }

        if(espectadoresEnSala < capacidadEspectadoresMaxima) {
            espectadoresEnSala++;
            escritor.println( Thread.currentThread().getName() + " Entro a la Sala: " + this.nombreSala );
            escritor.println("Numero de espectadores:" + espectadoresEnSala);
            wait(tiempo); // el espectador se pone en wait (se queda en la sala) hasta que pase un tiempo Aleatorio o alguien le avise(un jugador sale).
        } else {
            escritor.println( Thread.currentThread().getName() + " no puedo entrar a la sala: " + this.nombreSala );
        }
    }

    public synchronized void salirEspectador() {
        if(enDirecto == true) {
            espectadoresEnSala--;
            if (espectadoresEnSala == 0) {
                notifyAll(); // si has sido el ultimo en salir, avisa, sobretodo para que un jugador entre.
            }
            escritor.println( Thread.currentThread().getName() + " Ha salido de la Sala: " + this.nombreSala );
            escritor.println("Numero de espectadores:" + espectadoresEnSala);
        }
    }

    @Override
    public String toString() {
        if(jugadorEnDirecto == null) {
            jugadorEnDirecto = "No hay nadie en directo.";
        }

        return "Nombre Sala: " + this.nombreSala + "\nJugador en Directo: " + this.jugadorEnDirecto + "\nEspectadores: " + espectadoresEnSala;
    }

    // Getters and Setters
    public boolean isEnDirecto() {
        return enDirecto;
    }

    public void setEnDirecto(boolean enDirecto) {
        this.enDirecto = enDirecto;
    }
}
