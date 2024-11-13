package tema2.RA2.TareaCalculo.Ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TareaCalculo_b implements Runnable {
    private int suma = 0;
    private Random random = new Random();
    private List<Integer> numerosGenerados = new ArrayList<>();
    private String nombre;

    public TareaCalculo_b (String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true) {
            int n = random.nextInt(901) + 100; // Genera número entre 100 y 1000
            suma += n;
            numerosGenerados.add(n);
            System.out.println(nombre + ": Número generado = " + numerosGenerados + ", Suma acumulada = " + suma);
            try {
                Thread.sleep(10000); // Espera 10 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
