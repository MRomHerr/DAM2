package tema2.RA2.Ejercicio4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Lenguaje implements Runnable {
    private int numPalabras;
    private String nombreArchivo;
    private static final String LETRAS = "abcdefghijklmnopqrstuvwxyz";
    private static final int LONGITUD_PALABRA = 5;
    private static final Random random = new Random();

    public Lenguaje(int numPalabras, String nombreArchivo) {
        this.numPalabras = numPalabras;
        this.nombreArchivo = nombreArchivo;
    }

    private String generarPalabra() {
        StringBuilder palabra = new StringBuilder(LONGITUD_PALABRA);
        for (int i = 0; i < LONGITUD_PALABRA; i++) {
            palabra.append(LETRAS.charAt(random.nextInt(LETRAS.length())));
        }
        return palabra.toString();
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            for (int i = 0; i < numPalabras; i++) {
                writer.println(generarPalabra());
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java Lenguaje <numPalabras> <nombreArchivo>");
            return;
        }

        int numPalabras = Integer.parseInt(args[0]);
        String nombreArchivo = args[1];

        Lenguaje lenguaje = new Lenguaje(numPalabras, nombreArchivo);
        new Thread(lenguaje).start();
    }
}
