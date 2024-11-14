package tema2.RA2.Ejercicio6;

/**
 * Clase que muestra el ciclo de vida de un hilo utilizando los diferentes estados en Java.
 * El hilo pasa por los estados: NEW, RUNNABLE, TIMED_WAITING, WAITING y TERMINATED.
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 10/11/2024
 */
public class EstadosHilo {

    /**
     * metodo principal que crea y ejecuta un hilo, mostrando su ciclo de vida
     * a través de los diferentes estados.
     *
     * @param args los argumentos del programa (no utilizados)
     */
    public static void main(String[] args) {
        // Estado NEW
        System.out.println("estado NEW: hilo creado pero no iniciado.");

        // Crear el objeto Runnable
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // Estado RUNNABLE
                    System.out.println("estado RUNNABLE: el hilo está en ejecución.");

                    // Estado TIMED_WAITING (sleep)
                    System.out.println("entrando en estado TIMED_WAITING (sleep)...");
                    Thread.sleep(1000);
                    System.out.println("saliendo de estado TIMED_WAITING (sleep).");

                    // Estado WAITING
                    synchronized (this) {
                        System.out.println("entrando en estado WAITING...");
                        wait();  // El hilo espera en este punto
                        System.out.println("saliendo de estado WAITING.");
                    }

                } catch (InterruptedException e) {
                    System.out.println("hilo interrumpido.");
                }
            }
        };

        // Crear el hilo y pasarle el runnable
        Thread hilo = new Thread(runnable);
        hilo.start(); // El hilo pasa a estado RUNNABLE

        try {
            Thread.sleep(500); // Pequeña pausa para asegurar que el hilo pase a RUNNABLE

            // Estado RUNNABLE: el hilo comienza a ejecutarse
            System.out.println("El hilo ha pasado al estado RUNNABLE.");

            // Estado WAITING: el hilo está esperando
            Thread.sleep(2000); // Dar tiempo para que el hilo entre en WAITING

            // Notificar sobre el objeto runnable
            synchronized (runnable) {
                System.out.println("notificando para salir del estado WAITING.");
                runnable.notify(); // Despierta al hilo
            }

            hilo.join(); // Espera a que el hilo termine

            // Estado TERMINATED
            System.out.println("estado TERMINATED: el hilo ha finalizado su ejecución.");

        } catch (InterruptedException e) {
            System.out.println("hilo principal interrumpido.");
        }
    }
}
