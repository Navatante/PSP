package hilosSencillo2;

public class Sumador implements Runnable {
    private GestorDeArray gestor;

    public Sumador(GestorDeArray gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {
        int suma = 0;
        for (int num : gestor.arrayDeEnteros) {
            suma += num;
        }
        System.out.println("Suma: " + suma);
    }
}