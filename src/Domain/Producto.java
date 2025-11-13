package Domain;

public class Producto {
    private String id;
    private String nombre;
    private String categoria;   // perro, gato, pajaro, roedor
    private double precio;

    public Producto(String id, String nombre, String categoria, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + categoria + ") - " + precio + "€";
    }
}

