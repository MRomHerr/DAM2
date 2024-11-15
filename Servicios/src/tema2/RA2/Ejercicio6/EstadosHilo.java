package tema2.RA2.Ejercicio6;

/**
 * Esta clase demuestra los diferentes estados de un hilo en Java.
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 9/11/2024
 */
public class EstadosHilo {
    /**
     * El metodo principal que crea y gestiona un hilo para mostrar sus diferentes estados.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        final Object lock = new Object();

        /**
         * Crea un nuevo hilo que pasará por diferentes estados.
         */
        Thread hilo = new Thread(() -> {
            try {
                System.out.println("El hilo está en el estado: " + Thread.currentThread().getState());

                //estado RUNNABLE
                for (int i = 0; i < 1000000; i++) {
                    if (i == 500000) {
                        System.out.println("El hilo está en el estado: RUNNABLE");
                    }
                }

                //estado TIMED_WAITING
                Thread.sleep(1000);
                System.out.println("El hilo está en el estado: " + Thread.currentThread().getState());

                synchronized (lock) {
                    // Estado WAITING
                    lock.wait();
                    System.out.println("El hilo está en el estado: " + Thread.currentThread().getState());
                }

                // estado BLOCKED
                synchronized (EstadosHilo.class) {
                    System.out.println("El hilo está en el estado: " + Thread.currentThread().getState());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Estado inicial: " + hilo.getState());
        hilo.start();

        try {
            // espera para que el hilo entre en RUNNABLE
            Thread.sleep(10);
            System.out.println("Estado desde main mientras el hilo está en bucle: " + hilo.getState());

            //espera a que el hilo entre en TIMED_WAITING
            Thread.sleep(500);
            System.out.println("Estado desde main mientras el hilo está dormido: " + hilo.getState());

            // espera a que el hilo entre en WAITING
            Thread.sleep(1000);
            synchronized (lock) {
                System.out.println("Estado desde main mientras el hilo está en espera: " + hilo.getState());
                lock.notify();
            }

            //estado BLOCKED
            synchronized (EstadosHilo.class) {
                Thread.sleep(100);
                System.out.println("Estado desde main mientras el hilo intenta adquirir un monitor ocupado: " + hilo.getState());
            }

            // espera a que el hilo termine
            hilo.join();
            System.out.println("Estado final: " + hilo.getState());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }//fin main
}// fin clase