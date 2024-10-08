package tema1.LeerNombre1_1;

import java.io.IOException;

/*
Este segundo programa debe ejecutar LeerNombre.java como un proceso externo, y utilizar el
metodo waitFor() para capturar el valor de salida del proceso. Dependiendo de si se introducen
los argumentos correctamente o no, el proceso devolverá 1 o -1.
 */


public class LeerNombre2{
    public static void main(String[] args) {
        try {
            // Ejecutar LeerNombre.java con o sin argumento desde la línea de comandos
            ProcessBuilder pb = new ProcessBuilder("java", "LeerNombre", "Juan"); // Poner "Juan" como ejemplo de argumento
            Process process = pb.start();

            // Esperar a que el proceso termine y obtener el valor de salida
            int exitCode = process.waitFor();

            // Comprobar el valor de salida
            if (exitCode == 1) {
                System.out.println("El programa finalizó correctamente con código: " + exitCode);
            } else if (exitCode == -1) {
                System.out.println("El programa no recibió argumentos correctamente. Código: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
