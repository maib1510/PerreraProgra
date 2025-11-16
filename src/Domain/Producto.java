package Domain;

public class Producto {
    private String id;
    private String nombre;
    private String categoria;   // perro, gato, pajaro, roedor
    private double precio;
    private boolean agotado;
    private int unidadesDisponibles;

    public Producto(String id, String nombre, String categoria, double precio, int unidadesDisponibles, boolean agotado) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.unidadesDisponibles = unidadesDisponibles;
        this.agotado = agotado;
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

