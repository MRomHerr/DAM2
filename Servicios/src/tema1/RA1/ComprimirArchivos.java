package tema1.RA1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * la clase comprimirarchivos permite comprimir multiples archivos en un archivo .tar utilizando
 * el comando 'tar' del sistema operativo.
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 16/10/2024
 */
public class ComprimirArchivos {

    /**
     * metodo que verifica la existencia de archivos y los crea si no existen.
     *
     * @param listaArchivos lista de nombres de archivos a verificar.
     */
    public static void verificarYGenerarArchivos(List<String> listaArchivos) {
        for (String archivo : listaArchivos) {
            File file = new File(archivo);
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        System.out.println("se ha creado el archivo: " + archivo);
                        // escribir contenido en el archivo (opcional)
                        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(file))) {
                            escritor.write("contenido de " + file.getName()); // personaliza el contenido si deseas
                        }
                    }
                } catch (IOException e) {
                    System.err.println("no se pudo generar el archivo: " + archivo);
                    e.printStackTrace();
                }
            } else {
                System.out.println("el archivo " + archivo + " ya existe");
            }
        }

    }// fin del metodo verificarYGenerarArchivos

    /**
     * metodo que procesa la salida del proceso y la imprime en consola.
     *
     * @param input flujo de entrada a procesar
     * @param esError indica si es un flujo de error
     * @throws IOException si ocurre un error al leer el flujo
     */
    private static void procesarSalida(InputStream input, boolean esError) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (esError) {
                    System.err.println(linea); // mostrar errores
                } else {
                    System.out.println(linea);  // mostrar cada linea de la salida del comando
                }
            }
        }

    }// fin del metodo procesarSalida

    /**
     * metodo principal que inicia la compresion de archivos utilizando el comando tar.
     *
     * @param args argumentos de la linea de comandos (no se utilizan aqui)
     */
    public static void main(String[] args) {
        // ruta base donde se crearan los archivos
        String ruta = "C:\\Users\\marco\\IdeaProjects\\DAM2\\Servicios\\src\\tema1\\RA1\\";
        // asegurarse de que rutaBase termine con un separador
        if (!ruta.endsWith(File.separator)) {
            ruta += File.separator;
        }
        // lista de archivos a comprimir con la ruta completa
        List<String> listaArchivos = List.of(ruta + "archivo1.txt", ruta + "archivo2.txt", ruta + "archivo3.txt", ruta + "archivo4.txt");
        String archivoSalida = ruta + "archivos.tar"; // nombre del archivo comprimido que se creara
        // verificar y generar archivos si no existen
        verificarYGenerarArchivos(listaArchivos);
        // construir el comando para el processbuilder
        List<String> comandoTar = new ArrayList<>();
        comandoTar.add("tar");  // el comando principal
        comandoTar.add("-cvf"); // indicar que queremos crear un archivo y mostrar los archivos que se anaden
        comandoTar.add(archivoSalida); // especificar el nombre del archivo comprimido
        comandoTar.addAll(listaArchivos); // anadir los archivos a la lista de comandos
        // crear el processbuilder con el comando
        ProcessBuilder builder = new ProcessBuilder(comandoTar);
        try {
            Process proceso = builder.start(); // iniciar el proceso
            // leer la salida del proceso con un buffer para capturar lo que imprime el comando tar
            procesarSalida(proceso.getInputStream(), false);
            procesarSalida(proceso.getErrorStream(), true);
            // esperar a que el proceso termine y obtener el codigo de salida
            int codigoSalida = proceso.waitFor();
            System.out.println("el proceso finalizo con codigo de salida: " + codigoSalida);
            if (codigoSalida == 0) {
                System.out.println("compresion realizada con exito");
            } else {
                System.err.println("hubo un error en la compresion");
            }
        } catch (IOException e) {
            System.err.println("error al ejecutar el proceso de compresion: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("el proceso fue interrumpido: " + e.getMessage());
            Thread.currentThread().interrupt(); // restaurar el estado de interrupcion
        }

    } // fin del metodo main
} // fin clase ComprimirArchivos
