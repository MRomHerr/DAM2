package Tema1Ficheros.XmlotroFormato.canciones;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class convertidor {
    public static void main(String[] args) {
        String hojaEstilo = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\XmlotroFormato\\canciones\\canciones.xsl"; // Ruta del archivo XSL
        String datosAlumnos = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\XmlotroFormato\\canciones\\Canciones.xml"; // Ruta del archivo XML
        File pagHTML = new File("C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\XmlotroFormato\\canciones.html"); // Crear archivo HTML

        // Crear fichero HTML
        try (FileOutputStream os = new FileOutputStream(pagHTML)) {
            Source estilos = new StreamSource(hojaEstilo); // Fuente XSL
            Source datos = new StreamSource(datosAlumnos); // Fuente XML

            // Resultado de la transformación
            Result result = new StreamResult(os);
            try {
                Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
                transformer.transform(datos, result); // Obtiene el HTML
            } catch (Exception e) {
                System.err.println("Error en la transformación: " + e);
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo HTML: " + e);
        }
    }
}
