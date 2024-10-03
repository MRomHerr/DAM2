package Tema1Ficheros.Tema1objetos;


import java.io.*;
import java.util.Scanner;

public class CancionMain {
    public static void main(String[] args) {
        File fichero = new File(".\\src\\Tema1objetos\\FichCancion.dat");
        
        try (ObjectOutputStream dataos = new ObjectOutputStream(new FileOutputStream(fichero, true))) {
            Scanner sc = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("1. Añadir una nueva canción");
                System.out.println("2. Buscar una canción por artista");
                System.out.println("0. Salir");
                System.out.print("Elija una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        Cancion cancion = construirCancion(sc);
                        dataos.writeObject(cancion);
                        break;
                    case 2:
                        buscarCancionPorArtista(sc);
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 0);
        } catch (IOException e) {
            System.out.println("Error al manejar el archivo: " + e.getMessage());
        }
    }

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

    private static void buscarCancionPorArtista(Scanner sc) {
        File fichero = new File("FichCancion.dat");
        try (ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero))) {
            System.out.print("Introduzca el nombre del artista: ");
            String artistaBuscado = sc.nextLine();
            boolean encontrada = false;

            while (true) {
                Cancion c = (Cancion) dataIS.readObject();
                if (c.getArtista().equalsIgnoreCase(artistaBuscado)) {
                    c.mostrarDatosCancion();
                    encontrada = true;
                }
            }
        } catch (EOFException e) {
            if (!encontrada) {
                System.out.println("No se encontró ninguna canción del artista " + artistaBuscado);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al buscar la canción: " + e.getMessage());
        }
    }
}
