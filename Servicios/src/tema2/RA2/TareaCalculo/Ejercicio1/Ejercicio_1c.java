package tema2.RA2.TareaCalculo.Ejercicio1;

public class Ejercicio_1c {
    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            // Crear una instancia de TareaCalculo_c con un nombre único para cada hilo
            TareaCalculo_c tarea = new TareaCalculo_c("Hilo-" + i);

            // Crear un hilo para ejecutar la tarea y darle inicio
            Thread hilo = new Thread(tarea);
            hilo.start();
        }
    }
}