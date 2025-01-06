package tuberiaElegirYLanzarPrograma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LanzamientoPrograma {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader lector = new BufferedReader(isr);

        String linea;
        while ((linea = lector.readLine()) != null) {
            if (linea.equals("Cerrar")) {
                System.out.println("Finalizando programa de lanzamiento.");
                break;
            } else {
                ejecutarPrograma(linea);
            }
        }
    }

    private static void ejecutarPrograma(String linea) throws IOException {
        if (linea.equals("1")) {
            System.out.println("Abriendo Notepad...");
            // Al processbuilder se le pasa como parametro lo que pusieramos en la terminal.
            new ProcessBuilder("cmd.exe", "/c", "notepad").start();
        } else if (linea.equals("2")) {
            System.out.println("Abriendo Calculadora...");
            new ProcessBuilder("cmd.exe", "/c", "calc").start();
        } else {
            System.out.println("Entrada no válida: " + linea);
        }
    }
}
