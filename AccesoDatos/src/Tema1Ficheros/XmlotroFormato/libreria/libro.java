package Tema1Ficheros.XmlotroFormato.libreria;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"autor", "nombre", "editorial", "isbn"})
public class libro {
    private String nombre;
    private String autor;
    private String editorial;
    private String isbn;

    // Constructor con parámetros
    public libro(String nombre, String autor, String editorial, String isbn) {
        super();
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
    }

    // Constructor sin parámetros
    public libro() {}

    // Métodos getter
    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

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
