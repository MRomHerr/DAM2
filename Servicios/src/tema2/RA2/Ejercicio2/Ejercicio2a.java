package tema2.RA2.Ejercicio2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * clase que genera identificadores alfanumericos unicos
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 14/11/2024
 */
public class Ejercicio2a {
    private static final String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int longitudID = 6;
    private static final int cantidadIDs = 200;

    /**
     * genera un conjunto de identificadores alfanumericos unicos
     * @return conjunto de identificadores unicos
     */
    public static Set<String> generarIdentificadores() {
        Set<String> identificadores = new HashSet<>();
        Random random = new Random();

        while (identificadores.size() < cantidadIDs) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < longitudID; i++) {
                sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
            }
            identificadores.add(sb.toString());
        }

        return identificadores;
    }//fin generarIdentificadores

    /**
     * metodo principal para ejecutar la generacion de identificadores
     * @param args argumentos de linea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        // generar identificadores
        Set<String> identificadores = generarIdentificadores();

        //imprimir los identificadores generados
        System.out.println("se han generado " + identificadores.size() + " identificadores:");
        for (String id : identificadores) {
            System.out.println(id);
        }
    }//fin main
}//fin clase