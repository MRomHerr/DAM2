package Tema1Ficheros.XmlotroFormato.libreria;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class ObjetoaXml {
    private static final String MIARCHIVO = "C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\XmlotroFormato\\libreria\\Libreria.xml";

    public static void main(String[] args) throws JAXBException, IOException {
        // Se crea la lista de libros.
        ArrayList<Libro> libroLista = new ArrayList<Libro>();

        // Creamos dos libros y los añadimos.
        Libro libro1 = new Libro("Entornos de Desarrollo", "Alicia Ramos", "Garceta", "978-84-1545-297-3");
        libroLista.add(libro1);

        Libro libro2 = new Libro("Acceso a Datos", "María Jesús Ramos", "Garceta", "978-84-1545-228-7");
        libroLista.add(libro2);

        // Se crea la librería y se le asigna la lista de libros.
        Libreria miLibreria = new Libreria();
        miLibreria.setNombre("Prueba de librería JAXB");
        miLibreria.setLugar("Talavera, como no");
        miLibreria.setListaLibro(libroLista);

        // Creamos el contexto indicando la clase raíz.
        JAXBContext context = JAXBContext.newInstance(Libreria.class);

        // Creamos el Marshaller, convierte el objeto Java en una cadena XML.
        Marshaller m = context.createMarshaller();

        // Formateamos el XML para que quede bien.
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Lo visualizamos en la consola.
        m.marshal(miLibreria, System.out);

        // Escribimos el XML en un archivo.
        m.marshal(miLibreria, new File(MIARCHIVO));
    }

}
