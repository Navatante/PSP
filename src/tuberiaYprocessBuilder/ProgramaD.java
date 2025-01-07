package tuberiaYprocessBuilder;

public class ProgramaD {

    public static void main(String[] args) {
        // Contar números menores que 30
        int numerosMenoresQue30 = 0;

        for (String arg : args) {
            int numero = Integer.parseInt(arg);
            if (numero < 30) {
                numerosMenoresQue30++;
            }
        }

        // Imprimir el resultado para el ProgramaC
        System.out.println("Hay " + numerosMenoresQue30 + " números menores que 30.");
    }
}
