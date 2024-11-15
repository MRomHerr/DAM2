package tema2.RA2.ejercicio5;

/**
 * Clase que representa un hilo con diferentes prioridades.
 * La clase HiloPrioridades permite establecer el nombre y la prioridad de un hilo,
 * ejecutando métodos específicos según la prioridad del hilo (1, 3 o 5).
 * Muestra un mensaje al iniciar el hilo con su nombre y, en un bucle sin fin,
 * ejecuta métodos diferenciados según la prioridad del hilo.
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 13/11/2024
 */
public class HiloPrioridades extends Thread {

    /**
     * Constructor para establecer el nombre y la prioridad del hilo.
     *
     * @param nombre    Nombre del hilo.
     * @param prioridad Prioridad del hilo (1 para baja, 3 para media, 5 para alta).
     */
    public HiloPrioridades(String nombre, int prioridad) {
        setName(nombre);
        setPriority(prioridad);
    }//fin HiloPrioridades

    /**
     *metodo principal del hilo que muestra un mensaje al iniciar y, en un bucle,
     * ejecuta tareas diferentes según la prioridad del hilo.
     */
    @Override
    public void run() {
        System.out.println("Hilo iniciado: " + getName() + " con prioridad " + getPriority());

        // bucle infinito para verificar la prioridad y ejecutar el metodo correspondiente
        while (true) {
            if (getPriority() == 1) {
                tarea1();
            } else if (getPriority() == 3) {
                tarea3();
            } else if (getPriority() == 5) {
                tarea5();
            }

            // demora de 1 segundo después de cada verificación de prioridad
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido: " + getName());
            }
        }//fin while
    }//fin run

    //metodo que se ejecuta si la prioridad del hilo es 1 (tarea lenta).
    private void tarea1() {
        System.out.println(getName() + " (prioridad " + getPriority() + "): tarea lenta");
    }

    //metodo que se ejecuta si la prioridad del hilo es 3 (tarea normal).
    private void tarea3() {
        System.out.println(getName() + " (prioridad " + getPriority() + "): tarea normal");
    }

    //metodo que se ejecuta si la prioridad del hilo es 5 (tarea rápida).
    private void tarea5() {
        System.out.println(getName() + " (prioridad " + getPriority() + "): tarea rápida");
    }
}//fin clase
