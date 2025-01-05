package multihilostarea1;

import java.util.Random;

public class Utilitario {
    static int dameUnNumeroEntre0y10() {
        Random r = new Random();
        return r.nextInt(10) + 1;
    }
}
