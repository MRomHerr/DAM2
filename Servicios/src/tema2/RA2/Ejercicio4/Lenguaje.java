package tema2.RA2.Ejercicio4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * clase que genera un archivo con un numero determinado de palabras aleatorias.
 * cada palabra tiene una longitud fija de 5 caracteres.
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 10/11/2024
 */
public class Lenguaje implements Runnable {
    private int numLineas;           // numero de lineas a generar (palabras)
    private String nombreArchivo;     // nombre del archivo donde se guardaran las palabras
    private static final String letras = "abcdefghijklmnopqrstuvwxyz"; // letras disponibles para generar las palabras
    private static final int longitud = 5; // longitud de cada palabra
    private static final Random random = new Random(); // generador de numeros aleatorios

    /**
     * constructor que inicializa los parametros del archivo y el numero de lineas.
     *
     * @param numLineas el numero de lineas a generar
     * @param nombreArchivo el nombre del archivo donde se guardaran las palabras
     */
    public Lenguaje(int numLineas, String nombreArchivo) {
        this.numLineas = numLineas;
        this.nombreArchivo = nombreArchivo;
    }// fin metodo Lenguaje

    /**
     * metodo privado que genera una palabra aleatoria de longitud fija.
     *
     * @return la palabra generada aleatoriamente
     */
    private String generarPalabra() {
        StringBuilder palabra = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            palabra.append(letras.charAt(random.nextInt(letras.length()))); // agrega una letra aleatoria
        }
        return palabra.toString(); // retorna la palabra generada
    }//fin metodo generarPalabra

    /**
     * metodo que se ejecuta al correr el hilo, crea el archivo si no existe
     * y agrega palabras al final del archivo en modo append.
     */
    @Override
    public void run() {
        try {
            // comprobar si el archivo existe
            File archivo = new File(nombreArchivo);

            // si el archivo no existe, crearlo
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("archivo creado: " + nombreArchivo); // mensaje si se crea el archivo
            } else {
                System.out.println("el archivo ya existe, se añadira contenido."); // mensaje si el archivo ya existe
            }

            // abrir el archivo en modo append (agregar al final)
            try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, true))) {
                for (int i = 0; i < numLineas; i++) {
                    writer.println(generarPalabra()); // escribe una nueva linea con una palabra generada
                }
            }

        } catch (IOException e) {
            System.err.println("error al escribir en el archivo: " + e.getMessage()); // mensaje de error si ocurre una excepcion
        }
    }//fin metodo run

    /**
     * metodo principal donde se solicita al usuario el numero de lineas
     * y la ruta del archivo. Luego se ejecuta el hilo para generar las palabras.
     *
     * @param args los argumentos del programa (no utilizados)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // preguntar al usuario cuantas lineas debe tener el archivo
        System.out.print("¿cuantas lineas quieres generar en el archivo? ");
        int numLineas = sc.nextInt();

        // preguntar la ruta del archivo donde se guardaran las palabras
        sc.nextLine();  // limpiar el buffer
        System.out.print("introduce la ruta completa del archivo donde se guardaran las palabras: ");
        String nombreArchivo = sc.nextLine();

        // crear la instancia de la clase Lenguaje con los parametros proporcionados
        Lenguaje lenguaje = new Lenguaje(numLineas, nombreArchivo);

        // crear y ejecutar el hilo que realiza la tarea
        new Thread(lenguaje).start();
    }//fin main
}//fin clase
