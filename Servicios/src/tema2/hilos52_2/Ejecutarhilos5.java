package tema2.hilos52_2;

public class Ejecutarhilos5  {
    public static void main(String[] args) {
        // Crear e iniciar 5 hilos
        for (int i = 1; i <= 5; i++) {
            hilos5 hilo = new hilos5(i);
            hilo.start();  // Iniciar el hilo
        }
    }
}