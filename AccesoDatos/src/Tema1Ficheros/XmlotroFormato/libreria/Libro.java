package Tema1Ficheros.XmlotroFormato.libreria;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"autor", "nombre", "editorial", "isbn"})  // Especifica el orden de los elementos en el XML
public class Libro {

    private String nombre;
    private String autor;
    private String editorial;
    private String isbn;

    // Constructor con parámetros
    public Libro(String nombre, String autor, String editorial, String isbn) {
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
    }

    // Constructor sin parámetros (necesario para JAXB)
    public Libro() {}

    // Métodos getter
    @XmlElement  // Indica que esta propiedad se debe incluir en el XML
    public String getNombre() {
        return nombre;
    }

    @XmlElement  // Indica que esta propiedad se debe incluir en el XML
    public String getAutor() {
        return autor;
    }

    @XmlElement  // Indica que esta propiedad se debe incluir en el XML
    public String getEditorial() {
        return editorial;
    }

    @XmlElement  // Indica que esta propiedad se debe incluir en el XML
    public String getIsbn() {
        return isbn;
    }

    // Métodos setter
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}