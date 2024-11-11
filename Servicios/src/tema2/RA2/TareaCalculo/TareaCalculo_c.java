package tema2.RA2.TareaCalculo;

import java.util.Random;

public class TareaCalculo_c implements Runnable {
    private int suma = 0;
    private Random random = new Random();
    private String nombre;
    private boolean ejecutar = true;

    public TareaCalculo_c (String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (ejecutar) {
            int n = random.nextInt(901) + 100; // Genera número entre 100 y 1000
            suma += n;
            System.out.println(nombre + ": Suma acumulada = " + suma);
            if (suma >= 1000000) {
                detener();
            }
            try {
                Thread.sleep(10000); // Espera 10 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nombre + ": Hilo detenido. Suma final = " + suma);
    }

    public void detener() {
        ejecutar = false;
    }
}
