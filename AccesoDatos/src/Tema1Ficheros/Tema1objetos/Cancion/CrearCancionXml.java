package Tema1Ficheros.Tema1objetos.Cancion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CrearCancionXml {
    private static final String FichAleCancion = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Cancion\\FichAleCancion.dat";
    private static final String XMLCancion = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Cancion\\CancionesAle.xml";

    public static void main(String[] args) {
        File fichero = new File(FichAleCancion);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("Canciones");
            document.appendChild(root);

            // leer el archivo de canciones en acceso aleatorio
            try (RandomAccessFile file = new RandomAccessFile(fichero, "r")) {
                while (file.getFilePointer() < file.length()) {
                    // leer cada campo de la cancion desde el archivo
                    int id = file.readInt(); // id
                    int año = file.readInt(); // año
                    String tituloCancion = leerCadena(file, 20); // titulo
                    String nombreArtista = leerCadena(file, 20); // artista
                    String duracionCancion = leerCadena(file, 20); // duracion
                    boolean español = file.readBoolean(); // español

                    // crear un elemento cancion
                    Element cancionElement = document.createElement("Cancion");

                    crearElemento("ID", String.valueOf(id), cancionElement, document);
                    crearElemento("Año", String.valueOf(año), cancionElement, document);
                    crearElemento("Título", tituloCancion, cancionElement, document);
                    crearElemento("Artista", nombreArtista, cancionElement, document);
                    crearElemento("Duración", duracionCancion, cancionElement, document);
                    crearElemento("Español", String.valueOf(español), cancionElement, document);

                    root.appendChild(cancionElement);
                }
            } catch (IOException e) {
                // se lanzara al alcanzar el final del archivo
                e.printStackTrace();
            }

            // guardar el documento xml en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(XMLCancion));
            transformer.transform(source, result);

            System.out.println("el archivo xml de canciones ha sido creado correctamente en: " + XMLCancion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metodo para crear un nuevo elemento en el xml
    private static void crearElemento(String nombre, String valor, Element parent, Document document) {
        Element element = document.createElement(nombre);
        Text text = document.createTextNode(valor);
        element.appendChild(text);
        parent.appendChild(element);
    }

    // metodo para leer una cadena de un archivo de acceso aleatorio
    private static String leerCadena(RandomAccessFile file, int size) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(file.readChar());
        }
        return sb.toString().trim(); // convertir a string y eliminar espacios
    }
}
