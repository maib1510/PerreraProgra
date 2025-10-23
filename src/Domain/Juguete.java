package Domain;

import java.util.Objects;

public class Juguete {
	
	private String nombre;
	private float precio;
	
	
	public Juguete() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Juguete(String nombre, float precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Juguete other = (Juguete) obj;
		return Objects.equals(nombre, other.nombre)
				&& Float.floatToIntBits(precio) == Float.floatToIntBits(other.precio);
	}

	@Override
	public String toString() {
		return "Juguete [nombre=" + nombre + ", precio=" + precio + "]";
	}
	
	
	
	
	
	

}
