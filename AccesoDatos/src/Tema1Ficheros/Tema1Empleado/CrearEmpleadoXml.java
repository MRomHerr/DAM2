package Tema1Ficheros.Tema1Empleado;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CrearEmpleadoXml {
    public static void main(String[] args) throws IOException {
        File fichero = new File("C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1Empleado\\AleatorioEmple.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int id, dep, posicion = 0; // Para situarnos al principio del fichero
        double salario; // Cambiar Double a double
        char[] apellido = new char[10];
        char aux;

        // Crear una instancia de DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument(); // Crear un nuevo documento
            Element raiz = document.createElement("Empleados"); // Elemento raíz
            document.appendChild(raiz);
            document.setXmlVersion("1.0"); // Cambiar a setXmlVersion

            while (true) {
                file.seek(posicion); // Nos posicionamos
                id = file.readInt(); // Obtener ID de empleado

                // Leer el apellido
                for (int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();
                    apellido[i] = aux;
                }
                String apellidos = new String(apellido).trim(); // Convertir a String y eliminar espacios
                dep = file.readInt(); // Leer departamento
                salario = file.readDouble(); // Leer salario

                if (id > 0) { // ID válidos a partir de 1
                    Element empleado = document.createElement("empleado"); // Nodo empleado
                    raiz.appendChild(empleado); // Añadir a la raíz

                    // Añadir ID
                    crearElemento("id", Integer.toString(id), empleado, document);
                    // Añadir Apellido
                    crearElemento("apellido", apellidos, empleado, document);
                    // Añadir DEP
                    crearElemento("dep", Integer.toString(dep), empleado, document);
                    // Añadir Salario
                    crearElemento("salario", Double.toString(salario), empleado, document);
                }

                posicion += 36; // Me posiciono para el siguiente empleado

                if (file.getFilePointer() >= file.length()) break; // Comprobar el final del archivo
            } // Fin del bucle que recorre el fichero

            // Guardar el documento XML en un archivo
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1Empleado\\Empleados.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            System.out.println("Archivo XML creado correctamente.");

        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            file.close(); // Cerrar fichero
        } // Fin de main
    } // Fin de main

    // Inserción de los datos del empleado
    static void crearElemento(String datoEmple, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor); // Damos valor
        raiz.appendChild(elem); // Pegamos el elemento hijo a la raíz
        elem.appendChild(text); // Pegamos el valor
    } // Fin de crearElemento
} // Fin de la clase
