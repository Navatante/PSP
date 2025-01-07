package processBuilderYgetInputStreamSencillo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProgramaA {
    public static void main(String[] args) throws IOException {

        // Creao el comando que le voy a pasar y me apoyo de un ArrayList para ello.
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("processBuilderYgetInputStreamSencillo.ProgramaB");
        command.add("ProgramaA dice: Oye ProgramaB, que haces?");

        // Creo el processBuilder y le paso el comando creado.
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process programaB = processBuilder.start();

        // Recibo la respuesta del ProgramaB
        InputStreamReader inputStreamReaderInputStream = new InputStreamReader(programaB.getInputStream()); // leemos de la salida estandar del programaB
        BufferedReader bufferedReader = new BufferedReader(inputStreamReaderInputStream);
        String salidaProgramaB = bufferedReader.readLine(); // ten en cuenta que .readLine() solo lee 1 linea, si pusieras \n, necesitarias otro .readLine().

        System.out.println(salidaProgramaB);

    }
}
