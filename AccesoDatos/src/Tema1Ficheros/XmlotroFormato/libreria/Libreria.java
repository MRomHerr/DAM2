package Tema1Ficheros.XmlotroFormato.libreria;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement  // Indica que esta clase puede ser la raíz de un documento XML
public class Libreria {

    private ArrayList<Libro> listaLibro;
    private String nombre;
    private String lugar;

    // Constructor con parámetros
    public Libreria(ArrayList<Libro> listaLibro, String nombre, String lugar) {
        this.listaLibro = listaLibro;
        this.nombre = nombre;
        this.lugar = lugar;
    }

    // Constructor sin parámetros (necesario para JAXB)
    public Libreria() {}

    // Método para establecer el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para establecer el lugar
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    // Método para obtener el nombre
    @XmlElement  // Indica que este campo se debe incluir en el XML
    public String getNombre() {
        return nombre;
    }

    // Método para obtener el lugar
    @XmlElement  // Indica que este campo se debe incluir en el XML
    public String getLugar() {
        return lugar;
    }

    // Método para obtener la lista de libros
    @XmlElement(name = "libro")  // Indica que los elementos de la lista se deben incluir en el XML con el nombre "libro"
    public List<Libro> getListaLibro() {
        return listaLibro;
    }

    // Método para establecer la lista de libros
    public void setListaLibro(List<Libro> listaLibros) {
        this.listaLibro = (ArrayList<Libro>) listaLibros;
    }
}