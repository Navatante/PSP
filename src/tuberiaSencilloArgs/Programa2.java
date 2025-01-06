package tuberiaSencilloArgs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Programa2 {
    public static void main(String[] args) throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader lector = new BufferedReader(isr);
        String linea = lector.readLine();

        switch (linea) {
            case "1":
                new ProcessBuilder("notepad").start();
                break;
            case "2":
                new ProcessBuilder("calc").start();
                break;
            default:
                System.out.println("Numero no valido.");
                break;
        }
    }
}
