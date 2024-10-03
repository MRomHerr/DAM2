package Tema1Ficheros.Tema1objetos;

import java.io.Serializable;

public class Cancion implements Serializable {
    private int id;
    private int año;
    private String titulo;
    private String artista;
    private String duracion;
    private boolean español;
    

    //clase vacia
    public Cancion() {}

    public Cancion(int año, int id, String titulo, String artista, String duracion, boolean español) {
    	this.id = 0;
        this.año = 0;
        this.titulo = "";
        this.artista = "";
        this.duracion = "";
        this.español = false;
    }

    //metodos get
    public int getId() {
        return id;
    }


    public int getAño() {
        return año;
    }

    public String getTitulo() {
        return titulo;
    }


    public String getArtista() {
        return artista;
    }


    public String getDuracion() {
        return duracion;
    }


    public boolean isEspañol() {
        return español;
    }

    // metodos set
    public void setId(int id) {
        this.id = id;
    }
    
    public void setAño(int año) {
        this.año = año;
    }
    
    public void setEspañol(boolean español) {
        this.español = español;
    }
    
    public void setTitulo(String titulo) {
        if (titulo.length() <= 20) {
            this.titulo = titulo;
        } else {
            this.titulo = titulo.substring(0, 20); //maximo de 20 caracteres
        }
    }
    
    public void setArtista(String artista) {
        if (artista.length() <= 20) {
            this.artista = artista;
        } else {
            this.artista = artista.substring(0, 20); //maximo de 20 caracteres
        }
    }
    
    public void setDuracion(String duracion) {
        if (duracion.length() <= 20) {
            this.duracion = duracion;
        } else {
            this.duracion = duracion.substring(0, 20); //maximo de 20 caracteres
        }
    }

    //datos
    public void mostrarDatosCancion() {
        System.out.println("ID: " + this.id);
        System.out.println("AÑO: " + this.año);
        System.out.println("TÍTULO: " + this.titulo);
        System.out.println("ARTISTA: " + this.artista);
        System.out.println("DURACIÓN: " + this.duracion);
        System.out.println("ESPAÑOL: " + this.español);

    }
}