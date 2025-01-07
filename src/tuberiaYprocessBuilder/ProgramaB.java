package tuberiaYprocessBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramaB {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Creamos un ISR y un BR para leer del ProgramaA por tuberia.
        InputStreamReader inputStreamReaderSystemin = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReaderSystemin);

        // Creamos un ArrayList para llenarlo de lo que leamos por la entrada estandar.
        List<String> numeros = new ArrayList<>();

        String linea;
        while((linea = bufferedReader.readLine()) != null) {
            numeros.add(linea);
        }

        // Pasar los números como argumentos separados al ProgramaC
        // Con este array list creamos la frase que pasamos como comando y nos viene bien para pasar el array completo con el metodo .addAll()
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("tuberiaYprocessBuilder.ProgramaC");
        command.addAll(numeros);

        // Creamos un PB con .start() para lanzar el ProgramaC
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process programaC = processBuilder.start(); // ejecutamos el programaC

        /**
         * getInputStream(); EXPLICACION
         * Este method devuelve un InputStream que representa la salida estándar del proceso hijo (el proceso que se ha iniciado).
         * Es decir, cualquier cosa que el proceso hijo escriba en su stdout (salida estándar) puede ser leída utilizando este InputStream.
         */
        // Creamos un ISR y un BR para leer del ProgramaC por InputSream.
        InputStreamReader inputStreamReaderInputStream = new InputStreamReader(programaC.getInputStream());
        BufferedReader bufferedReaderInputStream = new BufferedReader(inputStreamReaderInputStream);

        // Esta es la salida final del ejercicio.
        System.out.println("Array: " + numeros);
        System.out.println(bufferedReaderInputStream.readLine());
    }
}
