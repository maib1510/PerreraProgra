package Domain;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(agotado, categoria, id_producto, nombre, precio, unidadesDisponibles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return agotado == other.agotado && Objects.equals(categoria, other.categoria)
				&& id_producto == other.id_producto && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& unidadesDisponibles == other.unidadesDisponibles;
	}


}

