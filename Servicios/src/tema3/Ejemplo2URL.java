package tema3;

import java.io.*;
import java.net.*;

public class Ejemplo2URL {
    public static void main(String[] args) {
        URL url = null;
        try {
            // Crea un objeto URL con la dirección especificada
            url = new URL("https://www.geo-fs.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in;
        try {
            // Abre un flujo de entrada desde la URL
            InputStream inputStream = url.openStream();
            in = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;

            // Lee cada línea de la entrada y la imprime en la consola
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close(); // Cierra el BufferedReader
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
