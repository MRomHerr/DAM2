package tema2.RA2;

public class EstadosHilo {
    private static final Object lock = new Object();
    private static boolean flag = false;

    public static void main(String[] args) {
        Thread hilo = new Thread(() -> {
            try {
                // Estado RUNNABLE
                System.out.println("Estado RUNNABLE: El hilo está en ejecución.");

                // Estado TIMED_WAITING (sleep)
                System.out.println("Entrando en estado TIMED_WAITING (sleep)...");
                Thread.sleep(1000);
                System.out.println("Saliendo de estado TIMED_WAITING (sleep).");

                synchronized (lock) {
                    while (!flag) {
                        // Estado WAITING (wait)
                        System.out.println("Entrando en estado WAITING (wait)...");
                        lock.wait();
                        System.out.println("Saliendo de estado WAITING.");
                    }
                }

                // Estado BLOCKED
                System.out.println("Intentando entrar en estado BLOCKED...");
                synchronized (EstadosHilo.class) {
                    System.out.println("Dentro del bloque sincronizado (BLOCKED).");
                }

            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido.");
            }
        });

        // Estado NEW
        System.out.println("Estado NEW: Hilo creado pero no iniciado.");

        hilo.start();

        try {
            // Dar tiempo para que el hilo entre en TIMED_WAITING
            Thread.sleep(500);

            // Demostrar estado BLOCKED
            synchronized (EstadosHilo.class) {
                System.out.println("Hilo principal tiene el bloqueo, forzando BLOCKED en el hilo secundario.");
                Thread.sleep(1000);
            }

            // Notificar al hilo para salir de WAITING
            synchronized (lock) {
                flag = true;
                lock.notify();
            }

            // Esperar a que el hilo termine (join)
            hilo.join();

            // Estado TERMINATED
            System.out.println("Estado TERMINATED: El hilo ha finalizado su ejecución.");
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido.");
        }
    }
}