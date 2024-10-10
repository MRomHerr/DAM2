package Tema1Ficheros.Tema1objetos.Cancion;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class VerFichAle {
    private static final String FILE_PATH = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Cancion\\FichAleCancion.dat";
    private static final int TAMANIO_REGISTRO = 129; // Tamaño de cada registro de una canción en bytes

    public static void main(String[] args) {
        File fichero = new File(FILE_PATH);

        if (fichero.exists()) {
            mostrarCanciones(fichero);
        } else {
            System.out.println("El archivo no existe.");
        }
    }

    // metodo para mostrar todas las canciones del fichero aleatorio
    private static void mostrarCanciones(File fichero) {
        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            long numeroRegistros = raf.length() / TAMANIO_REGISTRO;  // Calcular el número de registros
            System.out.println("Total de canciones en el fichero: " + numeroRegistros);

            for (int i = 0; i < numeroRegistros; i++) {
                // Nos posicionamos al principio del registro i
                raf.seek(i * TAMANIO_REGISTRO);

                // Leer cada atributo de la canción
                int id = raf.readInt();  // 4 bytes
                int año = raf.readInt();  // 4 bytes

                // Leer título (40 bytes = 20 chars)
                byte[] tituloBytes = new byte[40];
                raf.read(tituloBytes);
                String titulo = new String(tituloBytes).trim();

                // Leer artista (40 bytes = 20 chars)
                byte[] artistaBytes = new byte[40];
                raf.read(artistaBytes);
                String artista = new String(artistaBytes).trim();

                // Leer duración (40 bytes = 20 chars)
                byte[] duracionBytes = new byte[40];
                raf.read(duracionBytes);
                String duracion = new String(duracionBytes).trim();

                // Leer si es canción española (1 byte)
                boolean esEspañola = raf.readBoolean();

                // Mostrar los datos de la canción
                System.out.println("Canción ID: " + id);
                System.out.println("Año: " + año);
                System.out.println("Título: " + titulo);
                System.out.println("Artista: " + artista);
                System.out.println("Duración: " + duracion);
                System.out.println("Canción española: " + (esEspañola ? "Sí" : "No"));
                System.out.println("-----------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
