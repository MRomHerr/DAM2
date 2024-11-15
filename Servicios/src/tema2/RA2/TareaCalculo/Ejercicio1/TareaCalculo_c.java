package tema2.RA2.TareaCalculo.Ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * clase que implementa Runnable y genera numeros aleatorios entre 100 y 1000,
 * los acumula y muestra la suma total cada 10 segundos, y se detiene cuando la
 * suma alcanza el valor de 1,000,000.
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 8/11/2024
 */
public class TareaCalculo_c implements Runnable {
    private int suma = 0;  //variable que guarda la suma de los numeros generados
    private Random random = new Random();  //objeto para generar numeros aleatorios
    private List<Integer> numerosGenerados = new ArrayList<>();  //lista para almacenar los numeros generados
    private String nombre;  //nombre del hilo
    private boolean ejecutar = true;  //variable de control para detener el hilo

    /**
     * constructor que inicializa el nombre del hilo.
     *
     * @param nombre el nombre del hilo
     */
    public TareaCalculo_c (String nombre) {
        this.nombre = nombre;
    }//fin TareaCalculo_c

    /**
     * metodo que ejecuta el hilo, genera numeros aleatorios y calcula la suma
     * acumulada, mostrando el resultado cada 10 segundos. El hilo se detiene cuando
     * la suma alcanza 1,000,000.
     */
    @Override
    public void run() {
        while (ejecutar) {
            int n = random.nextInt(901) + 100; //genera un numero aleatorio entre 100 y 1000
            suma += n;
            numerosGenerados.add(n);
            System.out.println(nombre + ": numero generado = " + numerosGenerados + ", suma acumulada = " + suma);
            if (suma >= 1000000) {  //si la suma alcanza 1,000,000, detiene el hilo
                detener();
            }
            try {
                Thread.sleep(10000); //espera 10 segundos antes de generar el siguiente numero
            } catch (InterruptedException e) {
                e.printStackTrace(); //captura la excepcion si el hilo es interrumpido
            }
        }
        System.out.println("el hilo "+nombre+ " alcanzó el millón"); //mensaje final cuando el hilo se detiene
    }//fin run

    /**
     * metodo para detener la ejecucion del hilo.
     */
    public void detener() {
        ejecutar = false;
    }//fin detener
}//fin clase
