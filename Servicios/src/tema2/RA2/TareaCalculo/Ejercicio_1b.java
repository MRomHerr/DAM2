package tema2.RA2.TareaCalculo;

public class Ejercicio_1b {
    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            new Thread(new TareaCalculo_b ("Hilo-" + i)).start();
        }
    }
}
