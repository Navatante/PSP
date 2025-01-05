package multihilostarea1;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter fichero = new FileWriter("resultado.txt");
        BufferedWriter escritorBuffer = new BufferedWriter(fichero);
        PrintWriter escritor = new PrintWriter(escritorBuffer, true); // true para auto flush

        Productor productor = new Productor(escritor);
        Consumidor consum = new Consumidor(escritor);

        Thread productor1 = new Thread(productor, "Hilo productor 1");
        Thread productor2 = new Thread(productor, "Hilo productor 2");
        Thread consumidor = new Thread(consum, "Hilo consumidor");

        productor1.start();
        productor2.start();
        consumidor.start();

        try {
            productor1.join();
            productor2.join();
            consumidor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        escritor.println("Resultado final: " + GestorDeArray.getInstance().getSuma());

        escritor.close();

    }
}
