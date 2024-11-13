package tema2.RA2.Ejercicio6;

/**
 * clase que muestra el ciclo de vida de un hilo utilizando los diferentes estados en java.
 * el hilo pasa por los estados: NEW, RUNNABLE, TIMED_WAITING, WAITING y TERMINATED.
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
        //crear el objeto runnable
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //estado RUNNABLE
                    System.out.println("estado runnable: el hilo esta en ejecucion.");

                    //estado TIMED_WAITING (sleep)
                    System.out.println("entrando en estado timed_waiting (sleep)...");
                    Thread.sleep(1000);
                    System.out.println("saliendo de estado timed_waiting (sleep).");

                    //estado WAITING
                    synchronized (this) {
                        System.out.println("entrando en estado waiting...");
                        wait();
                        System.out.println("saliendo de estado waiting.");
                    }

                } catch (InterruptedException e) {
                    System.out.println("hilo interrumpido.");
                }
            }
        };

        //estado NEW
        System.out.println("estado new: hilo creado pero no iniciado.");

        //crear el hilo y pasarle el runnable
        Thread hilo = new Thread(runnable);
        hilo.start();

        try {
            Thread.sleep(2000); // dar tiempo para que el hilo entre en WAITING

            //notificar sobre el objeto runnable
            synchronized (runnable) {
                runnable.notify(); // notificar al hilo para salir de WAITING
            }

            hilo.join(); //esperar a que el hilo termine

            //estado TERMINATED
            System.out.println("estado terminated: el hilo ha finalizado su ejecucion.");
        } catch (InterruptedException e) {
            System.out.println("hilo principal interrumpido.");
        }
    }
}
