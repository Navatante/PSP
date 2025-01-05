package lanzarprogramaejercicio1exfeb;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Por favor, proporcione unicamente el nombre del programa a ejecutar.");
            return;
        }

        String nombrePrograma = args[0];
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", nombrePrograma);
        //processBuilder.directory(new File("C:\\Windows\\System32")); Usalo para poner la carpeta donde este el programa que quieras lanzar, es como el lugar donde te situas en la terminal.

        try {
            Process process = processBuilder.start();
            BufferedReader lectorSalida = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader lectorError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Leer la salida estándar si el programa se ejecuta correctamente
            String s;
            System.out.println("Salida del programa:");
            while ((s = lectorSalida.readLine()) != null) {
                System.out.println(s);
            }

            // Esperar a que el proceso termine y verificar el valor de salida
            int exitVal = process.waitFor();
            if (exitVal != 0) {
                // Si hay errores, escribir en el archivo "errores.txt"
                System.out.println("El programa no se ejecutó correctamente. Guardando errores en errores.txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("errores.txt"))) {
                    while ((s = lectorError.readLine()) != null) {
                        writer.write(s + "\n");
                    }
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
