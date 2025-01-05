package tuberias;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Frecuencia {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);   // InputStreamReader solo le caracter a caracter. (aqui estamos diciendo: leeme caracter a caracter de la consola "System.in")
        BufferedReader lector = new BufferedReader(isr);            // Gracias a BufferedReader leemos cadenas de caracteres enteras. (aqui estamos diciendo: leeme todos los caracteres de "isr" del tiron)

        String linea;
        while ((linea = lector.readLine()) != null) {
            contarVocales(linea);
        }
    }

    public static void contarVocales(String cadena) {
        HashMap<Character, Integer> frecuenciaVocales = new HashMap<>();
        frecuenciaVocales.put('a', 0);
        frecuenciaVocales.put('e', 0);
        frecuenciaVocales.put('i', 0);
        frecuenciaVocales.put('o', 0);
        frecuenciaVocales.put('u', 0);

        for (char c : cadena.toLowerCase().toCharArray()) {
            if (frecuenciaVocales.containsKey(c)) {
                frecuenciaVocales.put(c, frecuenciaVocales.get(c) + 1);
            }
        }

        System.out.printf("Cadena: '%s' --> %d %d %d %d %d\n",
                cadena,
                frecuenciaVocales.get('a'),
                frecuenciaVocales.get('e'),
                frecuenciaVocales.get('i'),
                frecuenciaVocales.get('o'),
                frecuenciaVocales.get('u'));
    }

}
