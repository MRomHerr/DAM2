package tema2.RA2.Combinado;

/**
 * clase que demuestra los principales estados de un hilo en java usando los métodos wait(), notify(), sleep() y join().
 * el hilo pasa por los estados: new, runnable, timed_waiting, waiting, blocked y terminated.
 * muestra el cambio de estado en tiempo real y cómo interactúan los hilos.
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 10/11/2024
 */
public class EstadosHilo {

    /**
     * metodo principal que crea y ejecuta un hilo, mostrando su ciclo de vida a través de los diferentes estados.
     * @param args los argumentos del programa (no utilizados)
     */
    public static void main(String[] args) {
        //objeto de bloqueo compartido para sincronización
        final Object bloqueo = new Object();

        //hilo bloqueador que simula el estado blocked en el hilo principal
        Thread hiloBloqueador = new Thread(() -> {
            synchronized (bloqueo) {
                System.out.println("hilo bloqueador: adquirido bloqueo y entrando en estado timed_waiting...");
                try {
                    Thread.sleep(2000);  //mantiene el bloqueo por 2 segundos
                } catch (InterruptedException e) {
                    System.out.println("hilo bloqueador interrumpido.");
                }
                System.out.println("hilo bloqueador: liberando bloqueo y terminando.");
            }
        });

        //hilo principal que muestra los estados runnable, waiting y blocked
        Thread hiloPrincipal = new Thread(() -> {
            try {
                System.out.println("estado runnable: el hilo principal está en ejecución.");

                System.out.println("entrando en estado timed_waiting (sleep)...");
                Thread.sleep(1000);  //simula trabajo en ejecución
                System.out.println("saliendo de estado timed_waiting (sleep).");

                synchronized (bloqueo) {
                    System.out.println("intentando entrar en estado waiting...");
                    bloqueo.wait();  //el hilo principal entra en estado waiting
                    System.out.println("saliendo de estado waiting.");
                }

                System.out.println("intentando adquirir el bloqueo...");
                synchronized (bloqueo) {
                    System.out.println("hilo principal: adquirido bloqueo y en ejecución.");
                }
            } catch (InterruptedException e) {
                System.out.println("hilo principal interrumpido.");
            }
        });

        //estado new
        System.out.println("estado new: hilo principal creado pero no iniciado.");

        hiloPrincipal.start(); //el hilo principal pasa a estado runnable
        hiloBloqueador.start(); //inicia el hilo bloqueador y mantiene el bloqueo temporalmente

        try {
            Thread.sleep(500); //pausa para asegurar que el hilo principal entre en runnable

            System.out.println("estado runnable: el hilo principal está en ejecución.");

            Thread.sleep(2000); //pausa para permitir que el hilo principal entre en waiting

            //notificación para liberar al hilo principal del estado waiting
            synchronized (bloqueo) {
                System.out.println("notificando para que el hilo principal salga del estado waiting.");
                bloqueo.notify();
            }

            hiloPrincipal.join(); //espera a que el hilo principal termine

            //estado terminated
            System.out.println("estado terminated: el hilo principal ha finalizado su ejecución.");

        } catch (InterruptedException e) {
            System.out.println("hilo principal interrumpido.");
        }
    }
}
