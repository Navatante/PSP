package hilosSencillo2;

public class Multiplicador implements Runnable {
    private GestorDeArray gestor;

    public Multiplicador(GestorDeArray gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {
        int producto = 1;
        for (int num : gestor.arrayDeEnteros) {
            producto *= num;
        }
        System.out.println("Producto: " + producto);
    }
}