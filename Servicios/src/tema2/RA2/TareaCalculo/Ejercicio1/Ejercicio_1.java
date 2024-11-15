package tema2.RA2.TareaCalculo.Ejercicio1;

/**
 * clase que crea y arranca 30 hilos que ejecutan la tarea TareaCalculo_a.
 * cada hilo se nombra como "Hilo-X" donde X es el número de hilo.
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 8/11/2024
 */
public class Ejercicio_1 {
    /**
     * metodo principal que crea y ejecuta 30 hilos con nombres secuenciales.
     *
     * @param args los argumentos del programa (no utilizados)
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            //crear y arrancar un nuevo hilo con la tarea TareaCalculo_a
            new TareaCalculo_a("Hilo-" + i).start();
        }
    }//fin main
}//fin clase
