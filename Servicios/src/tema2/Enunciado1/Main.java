package tema2.Enunciado1;

import tema2.RA2.TareaCalculo.Ejercicio1.TareaCalculo_a;

public class Main {

    public static void main(String[] args) {

        for (int i = 1; i <= 30; i++) {
            new Thread(new ControlHilos()).start();
        }

    }
}
