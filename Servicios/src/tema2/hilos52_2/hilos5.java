package tema2.hilos52_2;

public class hilos5 extends Thread {
    private int id;  // Identificador del hilo

    // Constructor que recibe el identificador del hilo
    public hilos5(int id) {
        this.id = id;
    }

    // metodo run sobreescrito
    public void run() {
        System.out.println("Hola mundo desde el hilo " + id);
    }
}