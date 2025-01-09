package hilosSincronizados1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        PrintWriter escritor = new PrintWriter("resultadoCuentaBancaria.txt");
        CuentaBancaria cuenta = new CuentaBancaria(escritor, 1000.0);
        Random random = new Random();

        List<Thread> hilos = new ArrayList<>(); // los meto en una lista simplemente para poder hacerles el join() despues.
        for(int i = 0; i<10; i++) {
            Thread hilo = new Thread(new Transaccion(cuenta, random.nextDouble() * 100.0)); // esta es la forma de crear un double aleatorio entre 0.0 y 100.0
            hilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        escritor.close(); // MUY IMPORTANTE. Sino cierro el escritor no se va a escribir en el txt.

    }
}
