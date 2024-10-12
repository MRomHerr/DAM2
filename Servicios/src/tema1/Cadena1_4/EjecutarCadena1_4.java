package tema1.Cadena1_4;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class EjecutarCadena1_4{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario que introduzca una cadena
        System.out.print("Introduce una cadena: ");
        String cadena = scanner.nextLine();

        try {
            // Crear el proceso para ejecutar MostrarCadenaFichero con la cadena introducida
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    "C:\\Users\\marco\\IdeaProjects\\DAM2\\Servicios\\src\\tema1\\Cadena1_4\\Cadena1_4.java",
                    cadena);
            Process process = pb.start();

            int exitCode = process.waitFor();

            if (exitCode == 1) {
                System.out.println("El proceso finalizó con un error (no se proporcionó una cadena).");
            } else {
                System.out.println("La cadena se ha escrito correctamente en el archivo.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}