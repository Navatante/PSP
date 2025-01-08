package hilosSencillo2;
// Vamos a tener 3 hilos, vamos a tener un array y un hilo se va a encargar de sumar ese array, otro hilo los va a restar y otro los va a multiplicar.
public class Main {
    public static void main(String[] args) {
        GestorDeArray gestor = new GestorDeArray();

        Thread sumadorThread = new Thread(new Sumador(gestor));
        Thread restadorThread = new Thread(new Restador(gestor));
        Thread multiplicadorThread = new Thread(new Multiplicador(gestor));

        sumadorThread.start();
        restadorThread.start();
        multiplicadorThread.start();
    }
}
