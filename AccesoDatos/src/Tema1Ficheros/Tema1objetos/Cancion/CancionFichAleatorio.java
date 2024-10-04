package Tema1Ficheros.Tema1objetos.Cancion;

import Tema1Ficheros.Tema1objetos.Persona;

import java.io.*;

public class CancionFichAleatorio {

    private static final int CANCION_SIZE = 129;  // Cada canción ocupa 129 bytes (4 + 4 + 40 + 40 + 40 + 1)

    //ruta del fichero existente que lee
    private static final String FILE_PATH = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Cancion\\FichCancion.dat";
    //rurta del ficheo aleatorio que voy a crear
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
        RandomAccessFile nuevoFile = new RandomAccessFile(nuevoFichero, "rw");

        // Abrir el archivo existente para lectura
        try (RandomAccessFile file = new RandomAccessFile(ficheroExistente, "r")) {
            // Variables para almacenar los datos leídos
            int id, año;
            char[] titulo = new char[20];   // Array para almacenar el título
            char[] artista = new char[20];   // Array para almacenar el artista
            char[] duracion = new char[20];  // Array para almacenar la duración
            boolean español;               // Variable para almacenar si es español
            char aux;

            // Leer el archivo hasta llegar al final
            while (file.getFilePointer() < file.length()) {
                // Leer ID de la canción desde el archivo existente
                id = file.readInt();

                // Leer Año desde el archivo existente
                año = file.readInt();

                // Leer el título (20 caracteres) desde el archivo existente
                for (int i = 0; i < titulo.length; i++) {
                    aux = file.readChar();
                    titulo[i] = aux;
                }
                String tituloCancion = new String(titulo).trim(); // Convertir a String y eliminar espacios

                // Leer el artista (20 caracteres) desde el archivo existente
                for (int i = 0; i < artista.length; i++) {
                    aux = file.readChar();
                    artista[i] = aux;
                }
                String nombreArtista = new String(artista).trim(); // Convertir a String y eliminar espacios

                // Leer la duración (20 caracteres) desde el archivo existente
                for (int i = 0; i < duracion.length; i++) {
                    aux = file.readChar();
                    duracion[i] = aux;
                }
                String duracionCancion = new String(duracion).trim(); // Convertir a String y eliminar espacios

                // Leer si es una canción en español desde el archivo existente
                español = file.readBoolean();

                // Escribir los datos en el nuevo archivo de acceso aleatorio
                nuevoFile.writeInt(id); // Escribir ID en el nuevo archivo
                nuevoFile.writeInt(año); // Escribir Año en el nuevo archivo
                nuevoFile.writeChars(String.format("%-20s", tituloCancion)); // Escribir título en el nuevo archivo
                nuevoFile.writeChars(String.format("%-20s", nombreArtista)); // Escribir artista en el nuevo archivo
                nuevoFile.writeChars(String.format("%-20s", duracionCancion)); // Escribir duración en el nuevo archivo
                nuevoFile.writeBoolean(español); // Escribir si es español en el nuevo archivo
            }
            /*
            %: Indica el inicio de un especificador de formato.
-: Este signo significa que el texto se alineará a la izquierda en el espacio que se le asigne. Sin este signo, el texto se alinearía a la derecha de forma predeterminada.
20: Este número especifica el ancho mínimo del campo. En este caso, significa que se reservarán 20 caracteres para mostrar la cadena. Si la cadena es más corta que 20 caracteres, se completará con espacios en blanco a la derecha (si es alineación a la izquierda) o a la izquierda (si no se incluye el signo -).
s: Indica que se está trabajando con una cadena (string)
             */

            System.out.println("Datos transferidos al nuevo archivo de acceso aleatorio.");

        } finally {
            // Cerrar el nuevo archivo después de escribir
            nuevoFile.close();
        }
    }
}