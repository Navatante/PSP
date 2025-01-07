package tuberiaYprocessBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProgramaC {

    public static void main(String[] args) throws IOException {
        // Contar números mayores que 20
        int numerosMayoresQue20 = 0;
        List<String> numerosParaProgramaD = new ArrayList<>();

        for (String arg : args) {
            int numero = Integer.parseInt(arg);
            if (numero > 20) {
                numerosMayoresQue20++;
            }
            numerosParaProgramaD.add(arg);
        }

        // Pasar los números al ProgramaD
        // Con este array list creamos la frase que pasamos como comando y nos viene bien para pasar el array completo con el metodo .addAll()
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("tuberiaYprocessBuilder.ProgramaD");
        command.addAll(numerosParaProgramaD);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process programaD = processBuilder.start();

        // Leer la salida del ProgramaD
        InputStreamReader inputStreamReader = new InputStreamReader(programaD.getInputStream());
        BufferedReader bufferedReaderInputStream = new BufferedReader(inputStreamReader);
        String outputD = bufferedReaderInputStream.readLine();

        // Imprimir el resultado final para el ProgramaB
        System.out.println("Hay " + numerosMayoresQue20 + " números mayores que 20. " + outputD);
    }
}
