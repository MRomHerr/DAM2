import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Ejemplo1urlCon {
    public static void main(String[] args) {
        URL url = null; // Declaración de la variable URL
        URLConnection urlCon = null; // Declaración de la variable URLConnection

        try {
            // Inicializa la URL con la dirección especificada
            url = new URL("https://www.geo-fs.com/");
            // Abre la conexión a la URL
            urlCon = url.openConnection();

            // Crea un BufferedReader para leer el contenido de la conexión
            BufferedReader in;
            InputStream inputStream = urlCon.getInputStream(); // Obtener el InputStream de la conexión
            in = new BufferedReader(new InputStreamReader(inputStream)); // Crear BufferedReader

            String inputLine;
            // Leer línea por línea el contenido de la página
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine); // Imprimir cada línea leída
            }
            in.close(); // Cerrar el BufferedReader
        }
        catch (MalformedURLException e) {
            e.printStackTrace(); // Manejar excepción de URL mal formada
        }
        catch (IOException e) {
            e.printStackTrace(); // Manejar excepciones de entrada/salida
        }
    } // main
} // Ejemplo1urlCon
