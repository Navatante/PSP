package problemaJardines;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        RecursoJardin jardin = new RecursoJardin();

        ArrayList<Thread> hilos = new ArrayList<>();
        for (int i = 1; i<=10; i++) {
            Thread thread1 = new Thread(new Entra_Jardin("Entra" + i, jardin));
            hilos.add(thread1);

            Thread thread2 = new Thread(new Sale_Jardin("Sale" + i, jardin));
            hilos.add(thread2);
        }

        for(Thread hilo : hilos) {
            hilo.start();
        }

        for(Thread hilo : hilos) {
            hilo.join();
        }

        System.out.println("Personas " + jardin.getCuenta());

    }
}
