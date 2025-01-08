package hilosSencillo1;


public class Contador implements Runnable{
    int num = 0;

    @Override
    public void run() {
        for(int i = 1; i <=10; i++) {
            System.out.println("Soy " + Thread.currentThread().getName() + " -> " + i);
        }
    }
}
