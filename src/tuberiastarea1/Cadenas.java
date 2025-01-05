package tuberiastarea1;

import java.util.Random;

public class Cadenas {
    public static void main(String[] args) {

        generarCadenasConSilabas(Integer.parseInt(args[0]));

    }

    public static void generarCadenasConSilabas(int cantidadCadenas) {
        Random random = new Random();
        String[] silabas = {"ma", "te", "lo", "vi", "ca", "re", "du", "fo", "sa", "ni", "co", "lu", "ba", "qu", "ta", "ne", "ra", "pu", "di", "su"};


        for (int i = 0; i < cantidadCadenas; i++) {
            int longitudCadena = random.nextInt(10) + 1;            // nextInt(10), me da un rango entre 0-9, entonces sumandole 1 al rango, me queda del 1-10.
            longitudCadena /= 2;                                    // lo reduzco a la mitad, porque las silabas contienen dos caracteres
            StringBuilder cadenaDeCaracteres = new StringBuilder();

            for (int j = 0; j < longitudCadena; j++) {
                int num = random.nextInt(silabas.length-1);         // .length da el numero de elementos, en este caso 20. por eso le resto uno, porque el maximo indice es el 19, al empezar por 0
                cadenaDeCaracteres.append(silabas[num]);
            }

            System.out.println(cadenaDeCaracteres.toString());
        }
    }
}

