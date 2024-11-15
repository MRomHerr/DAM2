package tema2.RA2.ejercicio5;


/**
 * Clase main
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 13/11/2024
 */
public class Ejercicio_6 {
    public static void main(String[] args) {
        //creación de tres hilos con diferentes prioridades
        HiloPrioridades hilo1 = new HiloPrioridades("Hilo1", 1); // Prioridad baja
        HiloPrioridades hilo2 = new HiloPrioridades("Hilo2", 3); // Prioridad media
        HiloPrioridades hilo3 = new HiloPrioridades("Hilo3", 5); // Prioridad alta

        //inicio de los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }//fin main
}//fin clase
