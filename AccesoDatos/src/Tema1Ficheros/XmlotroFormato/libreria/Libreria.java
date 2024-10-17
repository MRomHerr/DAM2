package Tema1Ficheros.XmlotroFormato.libreria;

import java.util.ArrayList;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

public class Libreria {
    private ArrayList<Libro> listaLibro;
    private String nombre;
    private String lugar;

    // Constructor con parámetros
    public Libreria(ArrayList<Libro> listaLibro, String nombre, String lugar) {
        super();
        this.listaLibro = listaLibro;
        this.nombre = nombre;
        this.lugar = lugar;
    }

    // Constructor sin parámetros
    public Libreria() {}

    // metodo para establecer el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // metodo para establecer el lugar
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    // metodo para obtener el nombre
    public String getNombre() {
        return nombre;
    }

    // metodo para obtener el lugar
    public String getLugar() {
        return lugar;
    }

    // Wrapper, envoltura alrededor de la representación XML
    @XmlElementWrapper(name = "ListaLibro")
    @XmlElement(name = "Libro")
    public ArrayList<Libro> getListaLibro() {
        return listaLibro;
    }

    public void setListaLibro(ArrayList<Libro> listaLibro) {
        this.listaLibro = listaLibro;
    }
}