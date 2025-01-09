package hilosSincronizados1;

import java.util.Random;

public class Transaccion implements Runnable {
    CuentaBancaria cuenta;
    double cantidad;
    Random random;

    public Transaccion(CuentaBancaria cuenta, double cantidad) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.random = new Random();
    }

    @Override
    public void run() {
        int opcion = random.nextInt(2); // Genera un número aleatorio entre 0 y 1
        if (opcion == 0) {
            cuenta.depositar(cantidad);
            // notifyAll(); no es necesario
        } else {
            cuenta.retirar(cantidad);
            // notifyAll(); no es necesario
        }
    }

    /**
     * La palabra clave synchronized asegura que solo un hilo puede ejecutar cualquiera de estos métodos a la vez en la misma instancia de CuentaBancaria.
     * Por lo tanto, no es necesario usar notifyAll() para notificar a otros hilos, ya que la sincronización se encarga de gestionar el acceso concurrente
     * de manera segura.
     */
}
