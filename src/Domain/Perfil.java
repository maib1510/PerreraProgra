package Domain;

import java.util.Objects;

public class Perfil {
	
	private int id_perfil;
	private String username;
	private String password;
	private Usuario usuario;
	
	
	public Perfil() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Perfil(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}


	public int getId_perfil() {
		return id_perfil;
	}


	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
