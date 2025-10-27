package Domain;

import java.util.Objects;

public abstract class Animal {
	
	private String nombre;
	private String sexo;
	private float edad;
	private String raza;
	private float peso;
	private String descripcion_personalidad;
	private String descripcion_fisica;
	private boolean adoptado;
	
	
	public Animal(String nombre,String sexo, float edad, String raza, float peso, String descripcion_personalidad,
			String descripcion_fisica, boolean adoptado) {
		super();
		this.nombre = nombre;
		this.sexo= sexo;
		this.edad = edad;
		this.raza = raza;
		this.peso = peso;
		this.descripcion_personalidad = descripcion_personalidad;
		this.descripcion_fisica = descripcion_fisica;
		this.adoptado = adoptado;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Animal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getEdad() {
		return edad;
	}
	public void setEdad(float edad) {
		this.edad = edad;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public String getDescripcion_personalidad() {
		return descripcion_personalidad;
	}
	public void setDescripcion_personalidad(String descripcion_personalidad) {
		this.descripcion_personalidad = descripcion_personalidad;
	}
	public String getDescripcion_fisica() {
		return descripcion_fisica;
	}
	public void setDescripcion_fisica(String descripcion_fisica) {
		this.descripcion_fisica = descripcion_fisica;
	}
	public boolean isAdoptado() {
		return adoptado;
	}
	public void setAdoptado(boolean adoptado) {
		this.adoptado = adoptado;
	}
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(adoptado, descripcion_fisica, descripcion_personalidad, edad, nombre, peso, raza);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return adoptado == other.adoptado && Objects.equals(descripcion_fisica, other.descripcion_fisica)
				&& Objects.equals(descripcion_personalidad, other.descripcion_personalidad)
				&& Float.floatToIntBits(edad) == Float.floatToIntBits(other.edad)
				&& Objects.equals(nombre, other.nombre)
				&& Float.floatToIntBits(peso) == Float.floatToIntBits(other.peso) && Objects.equals(raza, other.raza);
	}
	
	
	
	@Override
	public String toString() {
		return "Animal [nombre=" + nombre + ", edad=" + edad + ", raza=" + raza + ", peso=" + peso
				+ ", descripcion_personalidad=" + descripcion_personalidad + ", descripcion_fisica="
				+ descripcion_fisica + ", adoptado=" + adoptado + "]";
	}
	
	public abstract String sonidoAnimales();
	

}
