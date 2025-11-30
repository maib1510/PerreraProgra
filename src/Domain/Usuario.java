package Domain;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {
	
	private int id_usuario;
	private String nombre;
	private String apellido;
	private ArrayList<Animal> mascotas;
	private int edad;
	private String telefono;
	private String email;
	private String tarjeta_bancaria;
	private Perfil perfil;
	
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Usuario(String username, String nombre, String apellido, ArrayList<Animal> mascotas, int edad, String telefono,
			String email, String tarjeta_bancaria, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mascotas = mascotas;
		this.edad = edad;
		this.telefono = telefono;
		this.email = email;
		this.tarjeta_bancaria = tarjeta_bancaria;
	}




	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public ArrayList<Animal> getMascotas() {
		return mascotas;
	}


	public void setMascotas(ArrayList<Animal> mascotas) {
		this.mascotas = mascotas;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTarjeta_bancaria() {
		return tarjeta_bancaria;
	}


	public void setTarjeta_bancaria(String tarjeta_bancaria) {
		this.tarjeta_bancaria = tarjeta_bancaria;
	}





	@Override
	public String toString() {
		return "Usuario [username=" + ", nombre=" + nombre + ", apellido=" + apellido + ", mascotas="
				+ mascotas + ", edad=" + edad + ", telefono=" + telefono + ", email=" + email + ", tarjeta_bancaria="
				+ tarjeta_bancaria + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(apellido, edad, email, mascotas, nombre, tarjeta_bancaria, telefono);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && edad == other.edad && Objects.equals(email, other.email)
				&& Objects.equals(mascotas, other.mascotas) && Objects.equals(nombre, other.nombre)
				&& tarjeta_bancaria == other.tarjeta_bancaria && telefono == other.telefono;
	}


	public int getId_usuario() {
		return id_usuario;
	}


	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}


	public Perfil getPerfil() {
		return perfil;
	}


	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}




	
	
	
}
