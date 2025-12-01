package Domain;

public class Producto {
    private int id_producto;
    private String nombre;
    private String categoria;   // perro, gato, pajaro, roedor
    private double precio;
    private boolean agotado;
    private int unidadesDisponibles;

    public Producto(int id, String nombre, String categoria, double precio, int unidadesDisponibles, boolean agotado) {
        this.id_producto = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.unidadesDisponibles = unidadesDisponibles;
        this.agotado = agotado;
    }

    public int getId_producto() {
        return id_producto;
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
        return id_producto + " - " + nombre + " (" + categoria + ") - " + precio + "€";
    }

	public boolean isAgotado() {
		return agotado;
	}

	public void setAgotado(boolean agotado) {
		this.agotado = agotado;
	}

	public int getUnidadesDisponibles() {
		return unidadesDisponibles;
	}

	public void setUnidadesDisponibles(int unidadesDisponibles) {
		this.unidadesDisponibles = unidadesDisponibles;
	}


}

