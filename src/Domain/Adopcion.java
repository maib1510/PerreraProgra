package Domain;

import java.time.LocalDate;

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
	
	
	
	
	
}
