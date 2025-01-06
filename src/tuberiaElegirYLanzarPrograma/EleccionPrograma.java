package tuberiaElegirYLanzarPrograma;

import java.util.Scanner;
// Es importante saber aqui utilizar err.println en lugar de out.println, ya que lo que sea out.println ira directamente a traves de la tuberia y no se imprimira de primeras.
public class EleccionPrograma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String eleccion;

        while (true) {
            // Print prompts to stderr so they are visible in the terminal
            System.err.println("Elige qué programa quieres lanzar:\n1: Notepad\n2: Calculadora\nEscribe 'Cerrar' para salir.");
            eleccion = scanner.nextLine();

            if (eleccion.equalsIgnoreCase("Cerrar")) {
                System.out.println("Cerrar");
                break;
            } else if (eleccion.equals("1") || eleccion.equals("2")) {
                System.out.println(eleccion); // Pass only the choice to the pipe
                break;
            } else {
                System.err.println("Opción no válida. Elige una opción correcta.");
            }
        }
        scanner.close();
    }
}
