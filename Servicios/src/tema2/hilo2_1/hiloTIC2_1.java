package tema2.hilo2_1;

public class hiloTIC2_1 extends Thread {
    public void run() {
        while (true) {
            System.out.println("TIC");
            try {
                Thread.sleep(500); // Pausa de 500 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}