package tema2.RA2.Ejercicio4;

public class Colaborar {
    private static final int NUM_HILOS = 10;
    private static final String NOMBRE_ARCHIVO = "granFichero.txt";

    public static void main(String[] args) {
        Thread[] hilos = new Thread[NUM_HILOS];

        for (int i = 0; i < NUM_HILOS; i++) {
            int numPalabras = (i + 1) * 10;
            Lenguaje lenguaje = new Lenguaje(numPalabras, NOMBRE_ARCHIVO);
            hilos[i] = new Thread(lenguaje);
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Hilo interrumpido: " + e.getMessage());
            }
        }

        System.out.println("Todos los hilos han terminado. El archivo " + NOMBRE_ARCHIVO + " ha sido generado.");
    }
}
