package tuberiaYprocessBuilder;

/**
 * El ProgramaA le va a pasar mediante tuberia un array de 10 numeros al ProgramaB, el array sera creado manualmente.
 * El ProgramaB recibe esos 10 numeros y lo unico que va a hacer es pasarselos al ProgamaC (ya con ProcessBuilder).
 * El ProgramaC va a contar cuantos numeros de ese array son mayores que 20. y le va a pasar ese array de numeros al ProgramaD.
 * El ProgramaD va a contar cuantos numeros de ese array son menores que 30 y le pasa el resultado al ProgramaC.
 * El ProgramaC le pasa al ProgramaB la cantidad de numeros mayores que 20 y menores que 30.
 * El ProgramaB se encarga de mostrar por pantalla el mensaje con los resultados.
 */
public class ProgramaA {

    public static void main(String[] args) {
        String[] arrayDeNumeros = {"15","24","63","84","35","12","2","35","24","45"};

        for (String numero : arrayDeNumeros) {
            System.out.println(numero); // Esto pasa los números al ProgramaB por la salida estándar.
        }

    }
}
