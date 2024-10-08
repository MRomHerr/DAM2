package tema1.ManejoArgumentos1_2;

/*
Este segundo programa ejecutará el anterior (ManejoArgumentos.java) como un proceso externo y mostrará
en pantalla un mensaje dependiendo del código de salida obtenido.
 */

import java.io.IOException;

public class EjecutorManejoArgumentos {
    public static void main(String[] args) {
        try {
            // Ejemplo: ProcesBuilder para ejecutar "ManejoArgumentos" con un argumento "5"
            ProcessBuilder pb = new ProcessBuilder("java", "ManejoArgumentos", "5");
            Process process = pb.start();

            // Esperar a que el proceso termine y obtener el código de salida
            int exitCode = process.waitFor();

            // Mostrar un mensaje en función del valor devuelto
            switch (exitCode) {
                case 1:
                    System.out.println("El programa terminó porque no se proporcionaron argumentos.");
                    break;
                case 2:
                    System.out.println("El programa terminó porque el argumento es una cadena.");
                    break;
                case 3:
                    System.out.println("El programa terminó porque el argumento es un número entero negativo.");
                    break;
                case 0:
                    System.out.println("El programa terminó correctamente.");
                    break;
                default:
                    System.out.println("El programa terminó con un código inesperado: " + exitCode);
                    break;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
