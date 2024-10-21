package tema2.compra2_7;

public class Producto {
    private int id, cantidad;

    //constructores
    public Producto(int id) {
        this.id = id;
    }

    public Producto(int año, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    //metodos get
    public int getid() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    // metodos set
    public void setid(int id) {
        this.id = id;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
