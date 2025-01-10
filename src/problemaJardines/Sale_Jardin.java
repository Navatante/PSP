package problemaJardines;

public class Sale_Jardin extends Thread {
    //clase derivada de Thread que define un hilo
    private RecursoJardin jardin;

    public Sale_Jardin(String nombre, RecursoJardin j) {
        this.setName(nombre);
        this.jardin = j;
    }

    @Override
    public void run() {
        jardin.decrementaCuenta();
        //invoca al método que decrementa la cuenta de accesos al jardín
    }
}