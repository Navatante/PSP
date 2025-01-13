package clienteServidorSencillo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** Recurso compartido para escritura compartida por distintos hilos a un mismo fichero. */
public class Impresora {
    FileWriter ficheroJuego1;
    BufferedWriter escritorBufferJuego1;
    PrintWriter escritorJuego1;

    FileWriter ficheroJuego2;
    BufferedWriter escritorBufferJuego2;
    PrintWriter escritorJuego2;

    FileWriter ficheroJuego3;
    BufferedWriter escritorBufferJuego3;
    PrintWriter escritorJuego3;

    public Impresora() throws IOException {
        ficheroJuego1 = new FileWriter("juego1.txt");
        escritorBufferJuego1 = new BufferedWriter(ficheroJuego1);
        escritorJuego1 = new PrintWriter(escritorBufferJuego1, true); // true para auto flush

        ficheroJuego2 = new FileWriter("juego2.txt");
        escritorBufferJuego2 = new BufferedWriter(ficheroJuego2);
        escritorJuego2 = new PrintWriter(escritorBufferJuego2, true); // true para auto flush

        ficheroJuego3 = new FileWriter("juego3.txt");
        escritorBufferJuego3 = new BufferedWriter(ficheroJuego3);
        escritorJuego3 = new PrintWriter(escritorBufferJuego3, true); // true para auto flush
    }

    public synchronized void imprimirJuego1(String texto) {
        escritorJuego1.println(texto);
    }

    public synchronized void imprimirJuego2(String texto) {
        escritorJuego2.println(texto);
    }

    public synchronized void imprimirJuego3(String texto) {
        escritorJuego3.println(texto);
    }
}
