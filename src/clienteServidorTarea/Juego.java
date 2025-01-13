package clienteServidorTarea;

import java.io.*;
import java.net.*;

// Se crea una clase abstracta como base para compartir la inicialización de recursos y evitar redundancias.
public abstract class Juego {
    // Estos campos seran compartidos y usados por las subclases.
    protected PrintWriter escritor;
    protected Socket socket;

    // Todas las subclases llaman a este constructor con super().
    // De tal manera que se comparte y tenemos un unico escritor y socket.
    public Juego(PrintWriter writer, Socket socket) {
        this.escritor = writer;
        this.socket = socket;
    }

    // Metodo abstracto que implementaran las subclases.
    public abstract void jugar(BufferedReader entrada, PrintWriter salida) throws IOException;
}
