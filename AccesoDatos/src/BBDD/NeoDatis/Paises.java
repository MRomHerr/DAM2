package BBDD.NeoDatis;

public class Paises {
    private int idPais;
    private String nombrePais;

    public Paises() {};

    public Paises(int id, String nombre) {
        super();
        idPais = id;
        nombrePais = nombre;
    }

    //GETTERS Y SETTERS
    public int getIdPais() {
        return idPais;
    }
    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }
    public String getNombrePais() {
        return nombrePais;
    }
    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
}

