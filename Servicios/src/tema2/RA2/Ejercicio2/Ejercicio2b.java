package tema2.RA2.Ejercicio2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * clase que genera un archivo csv con pares de elementos
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 14/11/2024
 */
public class Ejercicio2b implements Runnable {
    private static final int lineas = 50000;
    private final String nombreArchivo;
    private final List<String> identificadores;

    /**
     * constructor de la clase
     * @param nombreArchivo nombre del archivo a generar
     * @param identificadores lista de identificadores a utilizar
     */
    public Ejercicio2b(String nombreArchivo, List<String> identificadores) {
        this.nombreArchivo = nombreArchivo;
        this.identificadores = identificadores;
    }

    /**
     * metodo que se ejecuta cuando se inicia el hilo
     */
    @Override
    public void run() {
        Random random = new Random();
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (int i = 0; i < lineas; i++) {
                String id = identificadores.get(random.nextInt(identificadores.size()));
                int numero = random.nextInt(20001);
                writer.write(id + "," + numero + "\n");
            }
            System.out.println("archivo generado: " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//fin metodo run

    /**
     * metodo principal para ejecutar la generacion del archivo csv
     * @param args argumentos de linea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        // generar lista de identificadores (simulada para este ejemplo)
        List<String> identificadores = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            identificadores.add("ID" + String.format("%03d", i));
        }

        //crear instancia de Ejercicio2b
        Ejercicio2b generador = new Ejercicio2b("salida.csv", identificadores);

        // ejecutar el generador en un nuevo hilo
        Thread hilo = new Thread(generador);
        hilo.start();

        try {
            // esperar a que el hilo termine
            hilo.join();
            System.out.println("generacion de archivo completada");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }//fin main
}//fin clase