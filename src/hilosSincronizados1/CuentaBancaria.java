package hilosSincronizados1;

import java.io.PrintWriter;

/**
 * El objeto compartido es la instancia de CuentaBancaria.
 * No se puede depositar y retirar al mismo tiempo porque los métodos depositar y retirar están sincronizados,
 * lo que garantiza que solo un hilo puede modificar el saldo de la cuenta a la vez.
 * Esto asegura la consistencia del saldo de la cuenta bancaria.
 */

public class CuentaBancaria {
    private double saldo;
    private PrintWriter escritor;

    public CuentaBancaria(PrintWriter escritor, double saldoInicial) {
        this.escritor = escritor;
        this.saldo = saldoInicial;
    }

    public synchronized void depositar(double cantidad) {
        String nombreHilo = Thread.currentThread().getName();
        saldo+=cantidad;
        escritor.println(nombreHilo + " ha depositado " + cantidad + " en la cuenta bancaria.\nSaldo actualizado: " + saldo);
        escritor.println("-----");
    }

    public synchronized void retirar(double cantidad) {
        String nombreHilo = Thread.currentThread().getName();
        if ( (saldo-cantidad) >= 0) {
            saldo-=cantidad;
            escritor.println(nombreHilo + " ha retirado " + cantidad + " de la cuenta bancaria.\nSaldo actualizado: " + saldo);
            escritor.println("-----");
        } else {
            System.out.println("Imposible retirar, sin saldo suficiente.");
        }

    }
}
