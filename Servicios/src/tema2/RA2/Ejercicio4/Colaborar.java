package tema2.RA2.Ejercicio4;

/**
 * Clase que coordina la creación y ejecución de varios hilos que generarán palabras
 * en un archivo especificado.
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 11/11/2024
 */
public class Colaborar {
    private static final int numhilos = 10; // Número de hilos que se crearán
    private static final String archivo = "C:\\Users\\marco\\IdeaProjects\\DAM2\\Servicios\\src\\tema2\\RA2\\Ejercicio4\\miFichero.txt"; // Ruta del archivo donde se generarán las palabras

    /**
     * Metodo principal que crea y ejecuta varios hilos que generaran palabras
     * en el archivo especificado.
     *
     * @param args los argumentos del programa (no utilizados)
     */
    public static void main(String[] args) {
        Thread[] hilos = new Thread[numhilos]; // Array para almacenar los hilos

        //crear y arrancar los hilos
        for (int i = 0; i < numhilos; i++) { // Recorremos el array de hilos
            int numPalabras = (i + 1) * 10; // Determinamos cuántas palabras generará cada hilo
            Lenguaje lenguaje = new Lenguaje(numPalabras, archivo); // Creamos una instancia de Lenguaje para cada hilo
            hilos[i] = new Thread(lenguaje); // Asignamos un nuevo hilo que ejecutará el objeto Lenguaje
            hilos[i].start(); // Arrancamos el hilo
        }

        //esperar a que todos los hilos terminen
        for (Thread hilo : hilos) { // Recorremos el array de hilos para esperar su finalización
            try {
                hilo.join(); // Esperamos a que cada hilo termine su ejecución
            } catch (InterruptedException e) {
                System.err.println("hilo interrumpido: " + e.getMessage()); // Si un hilo es interrumpido, mostramos un error
            }
        }

        //informar cuando todos los hilos hayan terminado
        System.out.println("todos los hilos han terminado. el archivo " + archivo + " ha sido generado."); // Mensaje final indicando que todos los hilos han terminado
    }//fin main
}//fin clase
