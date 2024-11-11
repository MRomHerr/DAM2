package tema2.RA2.TareaCalculo;

import java.util.Random;


public class TareaCalculo_a extends Thread {
    private int suma = 0;
    private Random random = new Random();

    public TareaCalculo_a (String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        while (true) {
            int n = random.nextInt(901) + 100; // Genera número entre 100 y 1000
            suma += n;
            System.out.println(getName() + ": Suma acumulada = " + suma);
            try {
                Thread.sleep(10000); // Espera 10 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
