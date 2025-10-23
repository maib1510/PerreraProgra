package Domain;

public class Pajaro extends Animal{
	
	public Pajaro(String nombre, float edad, String raza, float peso, String descripcion_personalidad,
			String descripcion_fisica, boolean adoptado) {
		super(nombre, edad, raza, peso, descripcion_personalidad, descripcion_fisica, adoptado);
	}

	public Pajaro() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Perro [getNombre()=" + getNombre() + ", getEdad()=" + getEdad() + ", getRaza()=" + getRaza()
				+ ", getPeso()=" + getPeso() + ", getDescripcion_personalidad()=" + getDescripcion_personalidad()
				+ ", getDescripcion_fisica()=" + getDescripcion_fisica() + ", isAdoptado()=" + isAdoptado()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}
