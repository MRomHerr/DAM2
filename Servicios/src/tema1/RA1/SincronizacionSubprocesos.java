package tema1.RA1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * la clase sincronizacionsubprocesos permite iniciar dos subprocesos para contar lineas y palabras en archivos
 * y sincroniza la obtencion de los resultados.
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 19/10/2024
 */
public class SincronizacionSubprocesos {

    // rutas de los archivos
    static String archivo1 = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\Servicios\\src\\tema1\\RA1\\archivo1.txt";
    static String archivo2 = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\Servicios\\src\\tema1\\RA1\\archivo2.txt";

    /**
     * metodo para contar lineas en un archivo usando powershell
     *
     * @param archivo ruta del archivo
     * @return numero de lineas en el archivo
     * @throws Exception si ocurre un error durante la ejecucion
     */
    private static int contarLineas(String archivo) throws Exception {
        String command = String.format("Get-Content '%s' | Measure-Object -Line | Select-Object -ExpandProperty Lines", archivo);
        ProcessBuilder processBuilder = new ProcessBuilder("powershell.exe", "-Command", command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String result = reader.readLine();
        process.waitFor(); // esperar a que el proceso termine
        return Integer.parseInt(result);
    } // fin metodo contarLineas

    /**
     * metodo para contar palabras en un archivo usando powershell
     *
     * @param archivo ruta del archivo
     * @return numero de palabras en el archivo
     * @throws Exception si ocurre un error durante la ejecucion
     */
    private static int contarPalabras(String archivo) throws Exception {
        String command = String.format("Get-Content '%s' | Measure-Object -Word | Select-Object -ExpandProperty Words", archivo);
        ProcessBuilder processBuilder = new ProcessBuilder("powershell.exe", "-Command", command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String result = reader.readLine();
        process.waitFor(); // esperar a que el proceso termine
        return Integer.parseInt(result);
    } // fin metodo contarPalabras

    /**
     * metodo principal que inicia los subprocesos para contar lineas y palabras y sincroniza los resultados
     *
     * @param args argumentos de la linea de comandos (no se utilizan aqui)
     */
    public static void main(String[] args) {
        // crear un executorservice para manejar los subprocesos
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // tarea para contar las lineas en archivo1
        Future<Integer> lineCountFuture = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return contarLineas(archivo1);
            }
        });

        // tarea para contar las palabras en archivo2
        Future<Integer> wordCountFuture = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return contarPalabras(archivo2);
            }
        });

        try {
            // esperar a que ambas tareas terminen y obtener los resultados
            Integer totalLineas = lineCountFuture.get();
            Integer totalPalabras = wordCountFuture.get();

            // mostrar el total combinado de lineas y palabras
            System.out.println("total de lineas en archivo1: " + totalLineas);
            System.out.println("total de palabras en archivo2: " + totalPalabras);
            System.out.println("total combinado: " + (totalLineas + totalPalabras));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // cerrar el executor
            executor.shutdown();
        }
    } // fin metodo main
}// fin de la clase
