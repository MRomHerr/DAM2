package tema2.RA2.TareaCalculo.Ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * clase que extiende Thread y genera números aleatorios entre 100 y 1000,
 * los acumula y muestra la suma total cada 10 segundos.
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 8/11/2024
 */
public class TareaCalculo_a extends Thread {
    private int suma = 0;  //variable que guarda la suma de los numeros generados
    private Random random = new Random();  //objeto para generar numeros aleatorios
    private List<Integer> numerosGenerados = new ArrayList<>();  //lista para almacenar los numeros generados
    private String nombre;  //nombre del hilo

    /**
     * constructor que inicializa el nombre del hilo.
     *
     * @param nombre el nombre del hilo
     */
    public TareaCalculo_a (String nombre) {
        this.nombre = nombre;
    }//fin TareaCalculo_a

    /**
     * metodo que ejecuta el hilo, genera numeros aleatorios y calcula la suma
     * acumulada, mostrando el resultado cada 10 segundos.
     */
    @Override
    public void run() {
        while (true) {
            int n = random.nextInt(901) + 100; //genera un numero aleatorio entre 100 y 1000
            suma += n;
            numerosGenerados.add(n);
            System.out.println(nombre + ": numero generado = " + numerosGenerados + ", suma acumulada = " + suma);
            try {
                Thread.sleep(10000); //espera 10 segundos antes de generar el siguiente numero
            } catch (InterruptedException e) {
                e.printStackTrace(); //captura la excepcion si el hilo es interrumpido
            }
        }
    }//fin run
}//fin clase
