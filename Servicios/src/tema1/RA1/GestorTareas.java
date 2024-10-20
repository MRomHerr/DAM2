package tema1.RA1;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * la clase gestorTareas permite a los usuarios seleccionar archivos de su sistema
 * y comprimirlos en archivos .tar utilizando procesos en paralelo.
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 21/10/2024
 */
public class GestorTareas {

    /**
     * este metodo comprime un archivo usando el comando 'tar' y devuelve el resultado.
     * @param archivo el archivo que quieres comprimir.
     * @return "exito" si todo salio bien, "error" si algo fallo.
     */
    private static String comprimirArchivo(File archivo) {
        String nombreTar = archivo.getAbsolutePath() + ".tar"; // nombre del archivo tar

        // configurar el proceso de compresion
        ProcessBuilder pb = new ProcessBuilder("tar", "-cvf", nombreTar, archivo.getAbsolutePath());
        pb.redirectErrorStream(true); // redirigir errores a la salida estandar

        try {
            Process proceso = pb.start();
            int codigoSalida = proceso.waitFor();
            return (codigoSalida == 0) ? "exito: " + nombreTar : "error: codigo de salida " + codigoSalida;
        } catch (IOException | InterruptedException e) {
            return "error: " + e.getMessage();
        }

    }// fin del metodo comprimirArchivo

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<File> archivos = new ArrayList<>();

        // usamos try-with-resources para cerrar el scanner automaticamente
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("ingrese las rutas de los archivos a comprimir (escriba 'fin' para terminar):");

            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("fin")) {
                    break;
                }
                File archivo = new File(input);

                // verificar si el archivo existe
                if (archivo.exists() && archivo.isFile()) {
                    archivos.add(archivo);
                    System.out.println("archivo agregado: " + input);
                } else {
                    System.out.println("el archivo no existe o la ruta es incorrecta. intente de nuevo.");
                }
            }
        }// fin del try


        // crear un executor service con el numero de nucleos disponibles en la maquina
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<String>> resultados = new ArrayList<>();

        // comenzamos a comprimir cada archivo
        for (File archivo : archivos) {
            resultados.add(executor.submit(() -> comprimirArchivo(archivo)));
        }
        // fin del bucle de compresion

        // mostrar los resultados de la compresion
        for (int i = 0; i < archivos.size(); i++) {
            System.out.println("resultado para " + archivos.get(i).getName() + ": " + resultados.get(i).get());
        }
        // fin de la visualizacion de resultados

        // apagar el executor service
        executor.shutdown();

    } // fin del metodo main
} // fin clase GestorTareas
