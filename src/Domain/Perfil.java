package Domain;

import java.util.Objects;

public class Perfil {

	private String username;
	private String password;
	
	
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

	
}
