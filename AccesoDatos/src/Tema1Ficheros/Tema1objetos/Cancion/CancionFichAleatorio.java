package Tema1Ficheros.Tema1objetos.Cancion;

import java.io.*;

public class CancionFichAleatorio {

    private static final int CANCION_SIZE = 129;  // Cada canción ocupa 129 bytes (4 + 4 + 40 + 40 + 40 + 1)

    // Ruta del fichero existente que lee
    private static final String FILE_PATH = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Cancion\\FichCancion.dat";
    // Ruta del fichero aleatorio que voy a crear
    private static final String FILE_PATH2 = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Cancion\\FichAleaCancion.dat";

    public static void main(String[] args) {
        // Ruta del archivo existente que se va a leer
        File ficheroExistente = new File(FILE_PATH);

        // Ruta del nuevo archivo que se va a crear
        File nuevoFichero = new File(FILE_PATH2);

        try {
            // Leer el archivo existente y crear el nuevo archivo
            leerYCrearNuevoFichero(ficheroExistente, nuevoFichero);
        } catch (IOException e) {
            System.out.println("Error al manejar el archivo: " + e.getMessage());
        }
    }

    // Metodo para leer el archivo existente y crear un nuevo archivo de acceso aleatorio
    private static void leerYCrearNuevoFichero(File ficheroExistente, File nuevoFichero) throws IOException {
        // Crear el nuevo archivo de acceso aleatorio para escritura y lectura
        try (RandomAccessFile nuevoFile = new RandomAccessFile(nuevoFichero, "rw");
             RandomAccessFile file = new RandomAccessFile(ficheroExistente, "r")) {

            // Leer el archivo hasta llegar al final
            while (file.getFilePointer() < file.length()) {
                // Leer ID de la canción desde el archivo existente
                int id = file.readInt();

                // Leer Año desde el archivo existente
                int año = file.readInt();

                // Leer el título (20 caracteres) desde el archivo existente
                String tituloCancion = leerCadena(file, 20);

                // Leer el artista (20 caracteres) desde el archivo existente
                String nombreArtista = leerCadena(file, 20);

                // Leer la duración (20 caracteres) desde el archivo existente
                String duracionCancion = leerCadena(file, 20);

                // Leer si es una canción en español desde el archivo existente
                boolean español = file.readBoolean();

                // Escribir los datos en el nuevo archivo de acceso aleatorio
                nuevoFile.writeInt(id); // Escribir ID en el nuevo archivo
                nuevoFile.writeInt(año); // Escribir Año en el nuevo archivo
                nuevoFile.writeChars(String.format("%-20s", tituloCancion)); // Escribir título en el nuevo archivo
                nuevoFile.writeChars(String.format("%-20s", nombreArtista)); // Escribir artista en el nuevo archivo
                nuevoFile.writeChars(String.format("%-20s", duracionCancion)); // Escribir duración en el nuevo archivo
                nuevoFile.writeBoolean(español); // Escribir si es español en el nuevo archivo
            }

            System.out.println("Datos transferidos al nuevo archivo de acceso aleatorio.");
        }
    }

    // Metodo para leer una cadena de un archivo de acceso aleatorio
    private static String leerCadena(RandomAccessFile file, int size) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(file.readChar());
        }
        return sb.toString().trim(); // Convertir a String y eliminar espacios
    }
}


/*
            %: Indica el inicio de un especificador de formato.
-: Este signo significa que el texto se alineará a la izquierda en el espacio que se le asigne. Sin este signo, el texto se alinearía a la derecha de forma predeterminada.
20: Este número especifica el ancho mínimo del campo. En este caso, significa que se reservarán 20 caracteres para mostrar la cadena. Si la cadena es más corta que 20 caracteres, se completará con espacios en blanco a la derecha (si es alineación a la izquierda) o a la izquierda (si no se incluye el signo -).
s: Indica que se está trabajando con una cadena (string)
             */