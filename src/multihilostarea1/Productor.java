package multihilostarea1;

import java.io.PrintWriter;

public class Productor implements Runnable{
    private GestorDeArray gestor = GestorDeArray.getInstance();
    private PrintWriter escritor;

    public Productor(PrintWriter escritor) {
        this.escritor = escritor;
    }


    @Override
    public void run() {

        while(gestor.getSuma() < gestor.getValorMaximo()) {
            try {
                gestor.introducirNumero(escritor);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
