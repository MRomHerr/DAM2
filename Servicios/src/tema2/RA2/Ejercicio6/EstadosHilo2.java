package tema2.RA2.Ejercicio6;

public class EstadosHilo2 {
    public static void main(String[] args) {
        final Object lock = new Object();

        Thread hilo = new Thread(() -> {
            try {
                System.out.println("El hilo está en el estado: " + Thread.currentThread().getState());

                // Estado RUNNABLE
                for (int i = 0; i < 1000000; i++) {
                    if (i == 500000) {
                        System.out.println("El hilo está en el estado: RUNNABLE");
                    }
                }

                // Estado TIMED_WAITING
                Thread.sleep(1000);
                System.out.println("El hilo está en el estado: " + Thread.currentThread().getState());

                synchronized (lock) {
                    // Estado WAITING
                    lock.wait();
                    System.out.println("El hilo está en el estado: " + Thread.currentThread().getState());
                }

                // Intentamos entrar en estado BLOCKED
                synchronized (EstadosHilo2.class) {
                    System.out.println("El hilo está en el estado: " + Thread.currentThread().getState());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Estado inicial: " + hilo.getState());
        hilo.start();

        try {
            // Esperamos un poco para que el hilo entre en RUNNABLE
            Thread.sleep(10);
            System.out.println("Estado desde main mientras el hilo está en bucle: " + hilo.getState());

            // Esperamos a que el hilo entre en TIMED_WAITING
            Thread.sleep(500);
            System.out.println("Estado desde main mientras el hilo está dormido: " + hilo.getState());

            // Esperamos a que el hilo entre en WAITING
            Thread.sleep(1000);
            synchronized (lock) {
                System.out.println("Estado desde main mientras el hilo está en espera: " + hilo.getState());
                lock.notify();
            }

            // Intentamos que el hilo entre en BLOCKED
            synchronized (EstadosHilo2.class) {
                Thread.sleep(100);
                System.out.println("Estado desde main mientras el hilo intenta adquirir un monitor ocupado: " + hilo.getState());
            }

            // Esperamos a que el hilo termine
            hilo.join();
            System.out.println("Estado final: " + hilo.getState());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }//fin main
}//fin clase