package multihilostarea1;

import java.io.PrintWriter;

public class Consumidor implements Runnable{
    private GestorDeArray gestor = GestorDeArray.getInstance();
    private PrintWriter escritor;

    public Consumidor(PrintWriter escritor) {
        this.escritor = escritor;
    }

    @Override
    public void run() {

        while(gestor.getSuma() < gestor.getValorMaximo()) {
            try {
                gestor.extraerNumero(escritor);
                Thread.sleep(100); // creo que aqui es donde deberia llamar a sleep()
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
