package tema1.Cadena1_4;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Cadena1_4 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Error: No se ha proporcionado ninguna cadena.");
            System.exit(1);
        }

        String cadena = args[0];

        // ruta para guardar en la ubicación solicitada
        String rutaArchivo = "C:\\Users\\marco\\IdeaProjects\\DAM2\\Servicios\\src\\tema1\\Cadena1_4\\salida.txt";

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            for (int i = 0; i < 5; i++) {
                writer.write((i + 1) + ": " + cadena + "\n");
            }
            System.out.println("Cadena escrita en el archivo 'salida.txt'.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }

        System.exit(0);
    }
}