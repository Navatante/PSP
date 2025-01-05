package multihilostarea1;

import java.io.PrintWriter;

// todos los metodos declarados como synchronized significa que solo un hilo puede entrar en el al mismo tiempo.

public class GestorDeArray {
    private static GestorDeArray gestorSingleton;
    private Integer[] arrayDeNumeros = new Integer[10];
    private Integer suma = 0;
    private final Integer VALOR_MAXIMO = 100;

    private GestorDeArray() {} // Constructor privado para evitar creación de nuevas instancias


    public static synchronized GestorDeArray getInstance() { // Metodo para obtener el singleton
        if (gestorSingleton == null) {
            gestorSingleton = new GestorDeArray();
        }
        return gestorSingleton;
    }

    public synchronized void extraerNumero(PrintWriter escritor) throws InterruptedException {
        String hiloNombre = Thread.currentThread().getName();
        Integer valorExtraido = null;
        Integer posicionDelValor = null;

        while(arrayEstaVacio()) {
            wait();
        }

        for(int i = 0; i < arrayDeNumeros.length; i++) {
            if (arrayDeNumeros[i] != null) {
                valorExtraido = arrayDeNumeros[i];
                posicionDelValor = i;
                sumarNumero(valorExtraido);
                escritor.println(hiloNombre + " saca el valor " + valorExtraido + " de la posición " + posicionDelValor + ", " + this.toString());
                arrayDeNumeros[i] = null;
                notify();
                Thread.sleep(150);
                break; // detenemos el bucle despues de extrar un valor, asi sacamos uno a uno
            }
        } // break nos trae aqui, osea termina el metodo extraerNumero().
    }

    public synchronized void introducirNumero(PrintWriter escritor) throws InterruptedException {
        String hiloNombre = Thread.currentThread().getName();
        Integer valorIntroducido = null;
        Integer posicionDelValor = null;

        while(arrayEstaLleno()) {
            wait();
        }

        for(int i = 0; i < arrayDeNumeros.length; i++) {
            if (arrayDeNumeros[i] == null) {
                valorIntroducido = Utilitario.dameUnNumeroEntre0y10();
                arrayDeNumeros[i] = valorIntroducido;
                posicionDelValor = i;
                escritor.println(hiloNombre + " introduce el valor " + valorIntroducido + " en la posición " + posicionDelValor + ", " + this.toString());
                notifyAll();
                Thread.sleep(100);
                break;
            }
        } // break nos trae aqui, osea termina el metodo introducirNumero().
    }

    public synchronized void sumarNumero(Integer numeroAsumar) {
        if(suma<VALOR_MAXIMO) {
            suma+=numeroAsumar;
        }
    }

    public boolean arrayEstaVacio() {
        if (arrayDeNumeros == null || arrayDeNumeros.length == 0) {
            return true;
        }
        for (Integer numero : arrayDeNumeros) {
            if (numero != null) {
                return false;
            }
        }
        return true;
    }

    public boolean arrayEstaLleno() {
        for (Integer numero : arrayDeNumeros) {
            if (numero == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean esPrimerDigito = true;

        for(Integer numero : arrayDeNumeros) {
            if (numero != null) {
                if (!esPrimerDigito) {
                    sb.append(" - ");
                }
                sb.append(numero);
                esPrimerDigito = false;
            }
        }

        return "Array = " + sb + " , Suma=" + suma;
    }


    // GETTERS
    public Integer getSuma() {
        return this.suma;
    }

    public Integer getValorMaximo() {
        return this.VALOR_MAXIMO;
    }
}
