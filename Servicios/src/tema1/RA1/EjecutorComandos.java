package tema1.RA1;

import java.io.*;

/**
 * clase que encapsula la funcionalidad para ejecutar comandos del sistema
 * operativo utilizando processbuilder
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 15/10/2024
 */
public class EjecutorComandos {

    /**
     * ejecuta un comando del sistema operativo
     *
     * @param comando el comando a ejecutar
     * @return el codigo de salida del proceso
     * @throws IOException si ocurre un error de entrada/salida
     * @throws InterruptedException si el proceso es interrumpido
     */
    public int ejecutarComando(String comando) throws IOException, InterruptedException {
        // separamos el comando por espacios en blanco y lo ejecutamos
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
        Process proceso = pb.start();
        // esperamos a que termine y devolvemos el codigo de salida
        return proceso.waitFor();

    }// fin del metodo ejecutarComando

    /**
     * ejecuta un comando del sistema operativo y le proporciona una entrada estandar
     *
     * @param comando el comando a ejecutar
     * @param entrada la entrada estandar que se va a pasar al proceso
     * @return el codigo de salida del proceso
     * @throws IOException si ocurre un error de entrada/salida
     * @throws InterruptedException si el proceso es interrumpido
     */
    public int ejecutarComandoConEntrada(String comando, String entrada) throws IOException, InterruptedException {
        // ejecutamos el comando
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
        Process proceso = pb.start();

        // le pasamos la entrada al proceso a traves de su stream de salida
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {
            writer.write(entrada);
            writer.flush();
        }

        // esperamos a que termine y devolvemos el codigo de salida
        return proceso.waitFor();

    }// fin del metodo ejecutarComandoConEntrada

    /**
     * ejecuta un comando del sistema operativo y redirige la salida estandar a un archivo
     *
     * @param comando       el comando a ejecutar
     * @param archivoSalida el archivo donde se redirige la salida del comando
     * @return el codigo de salida del proceso
     * @throws IOException si ocurre un error de entrada/salida
     * @throws InterruptedException si el proceso es interrumpido
     */
    public int ejecutarComandoConRedireccion(String comando, File archivoSalida) throws IOException, InterruptedException {
        // creamos el archivo de salida si no existe
        if (!archivoSalida.exists()) {
            archivoSalida.createNewFile();
        }

        // configuramos el processbuilder para redirigir la salida a un archivo
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
        pb.redirectOutput(archivoSalida);
        Process proceso = pb.start();
        // esperamos a que el proceso termine y devolvemos el codigo de salida
        return proceso.waitFor();

    }// fin del metodo ejecutarComandoConRedireccion

    /**
     * metodo principal para probar la ejecucion de comandos
     *
     * @param args argumentos de la linea de comandos
     */
    public static void main(String[] args) {
        EjecutorComandos ejecutor = new EjecutorComandos();

        try {
            // ejemplo para listar archivos en el directorio actual
            String comandoListaArchivos = "dir";
            int codigoSalida = ejecutor.ejecutarComando(comandoListaArchivos);
            System.out.println("comando listar archivos ejecutado, codigo de salida: " + codigoSalida);
            // fin del ejemplo listar archivos

            // ejemplo para buscar una palabra en un texto
            String comandoBusqueda = "echo esto es una prueba de palabra | findstr prueba";
            codigoSalida = ejecutor.ejecutarComandoConEntrada(comandoBusqueda, "esto es una prueba de palabra.");
            System.out.println("comando de busqueda ejecutado, codigo de salida: " + codigoSalida);
            // fin del ejemplo busqueda

            // ejecutar un comando que redirige la salida estandar a un archivo
            File archivoSalida = new File("salida.txt");
            codigoSalida = ejecutor.ejecutarComandoConRedireccion("echo hola mundo", archivoSalida);
            System.out.println("comando con redireccion ejecutado, codigo de salida: " + codigoSalida);
            // fin del ejemplo redireccion

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    } // fin del metodo main
} // fin clase EjecutorComandos
