package tema2.hilo2_1;

public class Ejecutar2_1 {
    public static void main(String[] args) {
        // Crear e iniciar los hilos
        hiloTIC2_1 hiloTic = new hiloTIC2_1();
        hiloTAC2_1 hiloTac = new hiloTAC2_1();

        hiloTic.start();
        hiloTac.start();
    }
}
/*
HiloTic y HiloTac son dos clases que extienden Thread y ejecutan bucles infinitos para imprimir "TIC" y "TAC",
 respectivamente. Ambos hilos utilizan Thread.sleep(500) para hacer una pausa de 500 milisegundos,
 permitiendo que las palabras se alternen en la salida. El bloque try-catch maneja las posibles excepciones
 InterruptedException que podrían ocurrir cuando el hilo se interrumpe durante el sleep().
 */