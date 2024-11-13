package tema2.RA2.TareaCalculo.Ejercicio1;

public class Ejercicio_1 {
    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            new TareaCalculo_a ("Hilo-" + i).start();
        }
    }
}
