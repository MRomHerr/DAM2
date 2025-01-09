package tema3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Ejemplo2urlCon {
    public static void main(String[] args) {
        try {
            // Crear un objeto URL con la dirección del script PHP
            URL url = new URL("http://localhost/vernombre.php");
            URLConnection conexion = url.openConnection();
            conexion.setDoOutput(true); // Permitir escritura en la conexión

            // Cadena de datos a enviar
            String cadena = "nombre=Fernando&apellidos=Martinez+Cava";

            // ESCRIBIR EN LA URL: flujo de salida
            PrintWriter output = new PrintWriter(conexion.getOutputStream());
            output.write(cadena);
            output.close(); // Cerrar flujo de salida

            // LEER DE LA URL: flujo de entrada
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea); // Imprimir cada línea leída
            }
            reader.close(); // Cerrar flujo de entrada

        } catch (MalformedURLException me) {
            System.err.println("MalformedURLException: " + me);
        } catch (IOException lce) {
            System.err.println("IOException: " + lce);
        }
    } // main
} // EjemplozurlCon
