package Tema1Ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class contadorFichero {
    public static void main (String args[]) {
        File f = new File("C:\\Users\\aludam2\\Downloads\\fichero.odt");
        
        //leer fichero
        try (FileReader lector = new FileReader(f);
             BufferedReader bufferedReader = new BufferedReader(lector)) {
            String linea;
            StringBuilder contenido = new StringBuilder();
            System.out.println("Texto del fichero:");
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
                contenido.append(linea).append("\n");
            }
            
            String texto = contenido.toString();
            int numCaracteres = texto.length();
            int numPalabras = contarPalabras(texto);
            
            if (numCaracteres > 1000 || numPalabras > 150) {
                System.out.println("Artículo inválido");
            } else {
                System.out.println("Artículo válido");
            }
            
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el fichero: " + e.getMessage());
        }
    }
    
    private static int contarPalabras(String texto) {
        String[] palabras = texto.split("\\s+");
        int contador = 0;
        for (String palabra : palabras) {
            if (palabra.length() >= 2) {
                contador++;
            }
        }
        return contador;
    }
}






