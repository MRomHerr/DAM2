package Tema1Ficheros.Tema1objetos.Cancion;

import java.io.*;
import java.util.Scanner;

public class AñadirCanFichAle {

    private static final String FILE_ALEATORIO_PATH = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Cancion\\FichAleCancion.dat";
    private static final int TAMANIO_REGISTRO = 129;

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_ALEATORIO_PATH, "rw");
             Scanner sc = new Scanner(System.in)) {

            // Calcular el ID de la nueva canción
            int nuevoId = obtenerUltimoId(raf) + 1;

            // Crear nueva canción
            Cancion nuevaCancion = construirCancion(sc, nuevoId);

            // Mover el puntero al final del fichero
            long posicionFinal = raf.length();
            raf.seek(posicionFinal);

            // Escribir la nueva canción en el fichero aleatorio
            escribirRegistro(raf, nuevaCancion);

            System.out.println("Canción añadida correctamente.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static int obtenerUltimoId(RandomAccessFile raf) throws IOException {
        long numRegistros = raf.length() / TAMANIO_REGISTRO;
        if (numRegistros == 0) {
            return 0;  // Si no hay registros, el primer ID será 1
        } else {
            raf.seek((numRegistros - 1) * TAMANIO_REGISTRO);  // Mover al último registro
            return raf.readInt();  // Leer el ID del último registro
        }
    }

    private static Cancion construirCancion(Scanner sc, int id) {
        Cancion c = new Cancion();
        c.setId(id);
        System.out.print("Introduzca el año de publicación: ");
        c.setAño(sc.nextInt());
        sc.nextLine(); // Limpiar el buffer
        System.out.print("Introduzca el título de la canción: ");
        c.setTitulo(sc.nextLine());
        System.out.print("Introduzca el artista: ");
        c.setArtista(sc.nextLine());
        System.out.print("Introduzca la duración: ");
        c.setDuracion(sc.nextLine());
        System.out.print("¿La canción es española? Pulsa 1 para sí, 0 para no: ");
        c.setEspañol(sc.nextInt() == 1);
        return c;
    }

    private static void escribirRegistro(RandomAccessFile raf, Cancion cancion) throws IOException {
        raf.writeInt(cancion.getId());
        raf.writeInt(cancion.getAño());
        escribirCadena(raf, cancion.getTitulo(), 20);
        escribirCadena(raf, cancion.getArtista(), 20);
        escribirCadena(raf, cancion.getDuracion(), 20);
        raf.writeBoolean(cancion.getEspañol());
    }

    private static void escribirCadena(RandomAccessFile raf, String cadena, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(cadena);
        sb.setLength(longitud);
        raf.writeChars(sb.toString());
    }
}

