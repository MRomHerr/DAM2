package tema2.RA2.Ejercicio6;

public class EstadosHilo {
    public static void main(String[] args) {
        // Crear el objeto Runnable
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // Estado RUNNABLE
                    System.out.println("Estado RUNNABLE: El hilo está en ejecución.");

                    // Estado TIMED_WAITING (sleep)
                    System.out.println("Entrando en estado TIMED_WAITING (sleep)...");
                    Thread.sleep(1000);
                    System.out.println("Saliendo de estado TIMED_WAITING (sleep).");

                    // Estado WAITING
                    synchronized (this) {
                        System.out.println("Entrando en estado WAITING...");
                        wait();
                        System.out.println("Saliendo de estado WAITING.");
                    }

                } catch (InterruptedException e) {
                    System.out.println("Hilo interrumpido.");
                }
            }
        };

        // Estado NEW
        System.out.println("Estado NEW: Hilo creado pero no iniciado.");

        // Crear el hilo y pasarle el Runnable
        Thread hilo = new Thread(runnable);
        hilo.start();

        try {
            Thread.sleep(2000); // Dar tiempo para que el hilo entre en WAITING

            // Notificar sobre el objeto runnable
            synchronized (runnable) {
                runnable.notify(); // Notificar al hilo para salir de WAITING
            }

            hilo.join(); // Esperar a que el hilo termine

            // Estado TERMINATED
            System.out.println("Estado TERMINATED: El hilo ha finalizado su ejecución.");
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido.");
        }
    }
}
