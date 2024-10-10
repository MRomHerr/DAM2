package Tema1Ficheros.Tema1objetos.Cancion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CrearFichAleatorio {

    private static final String FILE_PATH_OBJETOS = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Cancion\\FichCancion.dat";
    private static final String FILE_PATH_ALEATORIO = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Cancion\\FichAleCancion.dat";
    private static final int TAMANO_REGISTRO = 129;  // 129 bytes por registro

    public static void main(String[] args) {
        List<Cancion> canciones = obtenerCancionesDesdeFicheroObjetos();
        if (canciones != null) {
            escribirFicheroAleatorio(canciones);
        } else {
            System.out.println("No se pudieron obtener las canciones desde el fichero de objetos.");
        }
    }

    // metodo para escribir las canciones en un fichero de acceso aleatorio
    private static void escribirFicheroAleatorio(List<Cancion> canciones) {
        File fichero = new File(FILE_PATH_ALEATORIO);

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            for (Cancion c : canciones) {
                escribirCancion(raf, c);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo aleatorio: " + e.getMessage());
        }
    }

    // metodo para escribir una canción en el fichero aleatorio
    private static void escribirCancion(RandomAccessFile raf, Cancion cancion) throws IOException {
        raf.writeInt(cancion.getId());             // 4 bytes para el ID
        raf.writeInt(cancion.getAño());            // 4 bytes para el año
        raf.writeChars(String.format("%-20s", cancion.getTitulo())); // Escribir título
        raf.writeChars(String.format("%-20s", cancion.getArtista())); // Escribir artista
        raf.writeChars(String.format("%-20s", cancion.getDuracion())); // Escribir duración
        raf.writeBoolean(cancion.getEspañol());

    }

    // metodo para escribir una cadena en un tamaño fijo de caracteres
    private static void escribirCadena(RandomAccessFile raf, String cadena, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(cadena);
        sb.setLength(longitud); // Fijar la longitud de la cadena a la longitud requerida
        raf.writeChars(sb.toString());  // Escribir la cadena carácter por carácter (2 bytes por carácter)
    }

    // metodo que lee las canciones desde el fichero de objetos FichCancion.dat
    private static List<Cancion> obtenerCancionesDesdeFicheroObjetos() {
        List<Cancion> canciones = new ArrayList<>();
        File fichero = new File(FILE_PATH_OBJETOS);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            // Leer todas las canciones del fichero de objetos
            while (true) {
                try {
                    Cancion cancion = (Cancion) ois.readObject();
                    canciones.add(cancion);
                } catch (EOFException e) {
                    // Fin del fichero
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el fichero de objetos: " + e.getMessage());
        }

        return canciones;
    }
}
