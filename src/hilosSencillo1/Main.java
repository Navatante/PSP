package hilosSencillo1;

// Crear 3 hilos que cada uno va a contar hasta 10
public class Main {
    public static void main(String[] args) {

        Thread hilo1 = new Thread(new Contador(),"Hilo 1");
        Thread hilo2 = new Thread(new Contador(), "Hilo 2");
        Thread hilo3 = new Thread(new Contador(), "Hilo 3");

        hilo1.start();
        hilo2.start();
        hilo3.start();

    }
}
