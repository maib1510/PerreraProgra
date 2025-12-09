package Domain;

import java.time.LocalDate;
import java.util.Objects;

public class Adopcion {
	
	
	private Animal animal;
	private Usuario usuario;
	private LocalDate fecha_adopcion;
	
	
	public Adopcion(Animal animal, Usuario usuario) {
		super();
		this.animal = animal;
		this.usuario = usuario;
	}


	public Animal getAnimal() {
		return animal;
	}


	public void setAnimal(Animal animal) {
		this.animal = animal;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public LocalDate getFecha_adopcion() {
		return fecha_adopcion;
	}


	public void setFecha_adopcion(LocalDate fecha_adopcion) {
		this.fecha_adopcion = fecha_adopcion;
	}


	@Override
	public String toString() {
		return "Adopcion [animal=" + animal + ", usuario=" + usuario + ", fecha_adopcion=" + fecha_adopcion + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(animal, fecha_adopcion, usuario);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adopcion other = (Adopcion) obj;
		return Objects.equals(animal, other.animal) && Objects.equals(fecha_adopcion, other.fecha_adopcion)
				&& Objects.equals(usuario, other.usuario);
	}
	
	
	
	
	
}
