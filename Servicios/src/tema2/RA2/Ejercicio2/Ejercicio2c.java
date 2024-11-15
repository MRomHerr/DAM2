package tema2.RA2.Ejercicio2;

import java.util.ArrayList;
import java.util.List;

/**
 * clase que genera multiples archivos csv utilizando hilos
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 14/11/2024
 */
public class Ejercicio2c {
    private static final int numArchivos = 100;

    /**
     * genera multiples archivos csv utilizando hilos
     * @param identificadores lista de identificadores a utilizar en los archivos
     */
    public static void generarArchivos(List<String> identificadores) {
        List<Thread> hilos = new ArrayList<>();

        // crear y iniciar hilos
        for (int i = 0; i < numArchivos; i++) {
            String nombreArchivo = "archivo_" + i + ".csv";
            Ejercicio2b generador = new Ejercicio2b(nombreArchivo, identificadores);
            Thread hilo = new Thread(generador);
            hilos.add(hilo);
            hilo.start();
        }

        // esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }//fin metodo generarArchivos

    /**
     * metodo principal para ejecutar la generacion de archivos
     * @param args argumentos de linea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        // crear lista de identificadores (simulada para este ejemplo)
        List<String> identificadores = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            identificadores.add("ID" + String.format("%03d", i));
        }

        //medir el tiempo de ejecucion
        long tiempoInicio = System.currentTimeMillis();

        //generar archivos
        generarArchivos(identificadores);

        long tiempoFin = System.currentTimeMillis();

        //mostrar tiempo de ejecucion
        System.out.println("tiempo de ejecucion: " + (tiempoFin - tiempoInicio) + " milisegundos");
        System.out.println("generacion de archivos completada");
    }//fin main
}//fin clase