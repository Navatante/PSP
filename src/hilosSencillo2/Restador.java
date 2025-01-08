package hilosSencillo2;

public class Restador implements Runnable {
    private GestorDeArray gestor;

    public Restador(GestorDeArray gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {
        int resta = 0;
        for (int num : gestor.arrayDeEnteros) {
            resta -= num;
        }
        System.out.println("Resta: " + resta);
    }
}