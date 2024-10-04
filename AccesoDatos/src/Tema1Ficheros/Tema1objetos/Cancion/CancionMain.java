package Tema1Ficheros.Tema1objetos.Cancion;

import java.io.*;
import java.util.Scanner;

public class CancionMain {
    private static final String FILE_PATH = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Cancion\\FichCancion.dat";

    public static void main(String[] args) {
        File fichero = new File(FILE_PATH);

        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Añadir una nueva canción");
            System.out.println("2. Buscar una canción por artista");
            System.out.println("3. Mostrar todas las canciones");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Añadir canción abriendo ObjectOutputStream de manera adecuada
                    añadirCancion(fichero, sc);
                    break;
                case 2:
                    buscarCancionPorArtista(sc);
                    break;
                case 3:
                    mostrarTodasLasCanciones();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // Método para añadir una canción al archivo
    private static void añadirCancion(File fichero, Scanner sc) {
        try (ObjectOutputStream datos = getObjectOutputStream(fichero)) {
            Cancion cancion = construirCancion(sc);
            datos.writeObject(cancion);
        } catch (IOException e) {
            System.out.println("Error al manejar el archivo: " + e.getMessage());
        }
    }

    // Método que devuelve un ObjectOutputStream que maneja el modo de agregar correctamente
    private static ObjectOutputStream getObjectOutputStream(File fichero) throws IOException {
        if (fichero.exists()) {
            // Si el archivo ya existe, usamos una clase personalizada para evitar escribir el encabezado
            return new AppendableObjectOutputStream(new FileOutputStream(fichero, true));
        } else {
            // Si el archivo no existe, se crea normalmente con ObjectOutputStream
            return new ObjectOutputStream(new FileOutputStream(fichero));
        }
    }

    // Método para construir una nueva canción solicitando datos al usuario
    private static Cancion construirCancion(Scanner sc) {
        Cancion c = new Cancion();
        System.out.print("Introduzca el identificador de la canción: ");
        c.setId(sc.nextInt());
        sc.nextLine(); // Limpiar el buffer
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

    // Método para buscar canciones por el nombre del artista
    private static void buscarCancionPorArtista(Scanner sc) {
        File fichero = new File(FILE_PATH);
        boolean encontrada = false;

        try (ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero))) {
            System.out.print("Introduzca el nombre del artista: ");
            String artistaBuscado = sc.nextLine();

            // Leer las canciones del archivo y buscar por artista
            while (true) {
                Cancion c = (Cancion) dataIS.readObject();
                if (c.getArtista().equalsIgnoreCase(artistaBuscado)) {
                    c.mostrarDatosCancion();
                    encontrada = true;
                }
            }
        } catch (EOFException e) {
            // Capturamos el final del archivo para evitar errores al terminar la búsqueda
            if (!encontrada) {
                System.out.println("No se encontró ninguna canción del artista.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al buscar la canción: " + e.getMessage());
        }
    }

    // Método para mostrar todas las canciones del archivo
    private static void mostrarTodasLasCanciones() {
        File fichero = new File(FILE_PATH);

        try (ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero))) {
            System.out.println("Listado de todas las canciones:");

            // Leer todas las canciones y mostrarlas
            while (true) {
                Cancion c = (Cancion) dataIS.readObject();  // Leer una canción del archivo
                c.mostrarDatosCancion();                    // Mostrar los datos de la canción
                System.out.println("-----------------------");  // Separador entre canciones
            }
        } catch (EOFException e) {
            // Final del archivo alcanzado, terminamos la lectura
            System.out.println("Fin de la lista de canciones.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer las canciones: " + e.getMessage());
        }
    }

    // Clase personalizada para evitar escribir el encabezado del archivo al agregar datos
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // No escribir un nuevo encabezado al agregar datos
            reset();
        }
    }
}
