package Tema1Ficheros.Tema1objetos.Cancion;

import java.io.*;
import java.util.Scanner;

public class BuscarCanAleID {

    private static final String FILE_ALEATORIO_PATH = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Cancion\\FichAleCancion.dat";
    private static final int TAMANIO_REGISTRO = 129; // 4 + 4 + (20*2) + (20*2) + (20*2) + 1

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_ALEATORIO_PATH, "r");
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Introduzca el ID de la canción que desea buscar: ");
            int idBuscado = sc.nextInt();

            // Buscar la canción por ID
            Cancion cancion = buscarCancionPorId(raf, idBuscado);

            if (cancion != null) {
                cancion.mostrarDatosCancion();
            } else {
                System.out.println("No se ha encontrado ninguna canción con ese ID.");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static Cancion buscarCancionPorId(RandomAccessFile raf, int idBuscado) throws IOException {
        long numRegistros = raf.length() / TAMANIO_REGISTRO;

        for (int i = 0; i < numRegistros; i++) {
            raf.seek(i * TAMANIO_REGISTRO);  // Mover al inicio del registro
            int id = raf.readInt();  // Leer el ID

            // Si el ID coincide con el buscado, leer y devolver la canción
            if (id == idBuscado) {
                return leerCancion(raf);  // Leer el resto de los datos
            }
        }
        return null;  // Si no se encuentra, devolver null
    }

    private static Cancion leerCancion(RandomAccessFile raf) throws IOException {
        // Leer los datos restantes de la canción
        int id = raf.readInt(); // Leer el ID
        int año = raf.readInt(); // Leer el año
        String titulo = leerCadena(raf, 20); // Leer el título
        String artista = leerCadena(raf, 20); // Leer el artista
        String duracion = leerCadena(raf, 20); // Leer la duración
        boolean español = raf.readBoolean(); // Leer si es en español

        // Crear y devolver la canción
        return new Cancion(año, id, titulo, artista, duracion, español);
    }

    private static String leerCadena(RandomAccessFile raf, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim();  // Eliminar espacios en blanco
    }
}
