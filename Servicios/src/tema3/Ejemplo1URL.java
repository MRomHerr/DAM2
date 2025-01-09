package tema3;

import java.net.MalformedURLException;
import java.net.URL;

public class Ejemplo1URL {
    public static void main(String[] args) {
        URL url;
        try {
            System.out.println("Constructor simple para una URL:");
            url = new URL("https://docs.oracle.com/");
            visualizar(url);

            System.out.println("Otro constructor simple para una URL:");
            url = new URL("http://localhost/PFC/gest/cli_gestion.php?5=3");
            visualizar(url);

            System.out.println("Const. para protocolo + URL + directorio:");
            url = new URL("https", "docs.oracle.com", "/en/java/javase/11/tools/");
            visualizar(url);

            System.out.println("Constructor para protocolo, URL, puerto + directorio:");
            url = new URL("http", "localhost", 8884, "/WebApp/Controlador?accion=modificar");
            visualizar(url);

            System.out.println("Constructor para un objeto URL en un contexto:");
            URL urlBase = new URL("https://docs.oracle.com");
            url = new URL(urlBase, "/en/java/javase/11/docs/api/java.base/java/net/URL.html");
            visualizar(url);
        } catch (MalformedURLException e) {
            System.out.println(e);
        } // main
    }

    // Método para visualizar la información de la URL
    private static void visualizar(URL url) {
        System.out.println("URL: " + url.toString());
        System.out.println("Protocolo: " + url.getProtocol());
        System.out.println("Host: " + url.getHost());
        System.out.println("Puerto: " + url.getPort());
        System.out.println("Ruta: " + url.getPath());
        System.out.println("Consulta: " + url.getQuery());
        System.out.println();
    }
}
