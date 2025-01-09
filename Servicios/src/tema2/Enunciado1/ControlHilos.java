package tema2.Enunciado1;

public class ControlHilos implements Runnable {
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        // Ejecutar el bucle durante 10 segundos
        while (System.currentTimeMillis() - startTime < 10000) {
            // Crear un array de Dispositivo
            Dispositivo[] dispositivos = new Dispositivo[10]; // Especifica el tamaño del array

            // Aquí puedes inicializar los dispositivos si es necesario
            for (int i = 0; i < dispositivos.length; i++) {
                dispositivos[i] = new Dispositivo(); // Suponiendo que tienes un constructor vacío
            }

            try {
                // Pausa de 2000 ms (2 segundos)
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Crear una instancia de ControlHilos
        ControlHilos controlHilos = new ControlHilos();

        // Crear un hilo y ejecutarlo
        Thread thread = new Thread(controlHilos);
        thread.start();
    }
}
