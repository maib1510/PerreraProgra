package DB;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class GestorBD {

	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "resources/db/database.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;

	public GestorBD() {		
		try {
			//Cargar el driver SQLite
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.format("\n* Error al cargar el driver de BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}

	}
	// ============================== CREAR BASE DE DATOS =========================================================================================================
	public void crearBBDD() {
	    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	         Statement stmt = con.createStatement()) {

	        // TABLA PERFIL ==============================================================================
	        String sqlPerfil = "CREATE TABLE IF NOT EXISTS perfil ("
	                + "id_perfil INTEGER PRIMARY KEY AUTOINCREMENT, "
	                + "username TEXT UNIQUE NOT NULL, " // text -> SQLlite ignora el tamaño. VARCHAR(x) también vale, pero lo va a ignorar
	                + "password TEXT NOT NULL"
	                + ");";
	        stmt.execute(sqlPerfil);
	        System.out.println("- Tabla PERFIL creada");

	        // TABLA USUARIO ==============================================================================
	        String sqlUsuario = "CREATE TABLE IF NOT EXISTS usuario ("
	                + "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, "
	                + "id_perfil INTEGER NOT NULL, "
	                + "nombre TEXT NOT NULL, "
	                + "apellido TEXT NOT NULL, "
	                + "edad INTEGER, "
	                + "email TEXT UNIQUE NOT NULL, "
	                + "telefono TEXT, "
	                + "tarjeta TEXT, "
	                + "FOREIGN KEY (id_perfil) REFERENCES perfil(id_perfil) ON DELETE CASCADE" // ON DELETE CASCADE -> cuando se elimina un perfil, se elimina automáticamente el usuario
	                + ");";
	        stmt.execute(sqlUsuario);
	        System.out.println("- Tabla USUARIO creada");

	        // TABLA ANIMAL ==============================================================================
	        String sqlAnimal = "CREATE TABLE IF NOT EXISTS animal ("
	                + "id_animal INTEGER PRIMARY KEY AUTOINCREMENT, "
	                + "nombre TEXT NOT NULL, "
	                + "tipo_animal TEXT NOT NULL, "
	                + "sexo TEXT, "
	                + "edad INTEGER, "
	                + "raza TEXT, "
	                + "peso REAL, "
	                + "desc_personalidad TEXT, "
	                + "desc_fisica TEXT, "
	                + "adoptado INTEGER DEFAULT 0"
	                + ");";
	        stmt.execute(sqlAnimal);
	        System.out.println("- Tabla ANIMAL creada");

	        // TABLA ADOPCION ==============================================================================
	        String sqlAdopcion = "CREATE TABLE IF NOT EXISTS adopcion ("
	                + "id_adopcion INTEGER PRIMARY KEY AUTOINCREMENT, "
	                + "id_usuario INTEGER NOT NULL, "
	                + "id_animal INTEGER NOT NULL, "
	                + "fecha_adopcion TEXT, "
	                + "FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE, "
	                + "FOREIGN KEY (id_animal) REFERENCES animal(id_animal) ON DELETE CASCADE"
	                + ");";
	        stmt.execute(sqlAdopcion);
	        System.out.println("- Tabla ADOPCION creada");

	        // TABLA PRODUCTO ==============================================================================
	        String sqlProducto = "CREATE TABLE IF NOT EXISTS producto ("
	                + "id_producto INTEGER PRIMARY KEY AUTOINCREMENT, "
	                + "nombre TEXT NOT NULL, "
	                + "categoria_animal TEXT, "
	                + "precio REAL NOT NULL, "
	                + "unidades_disp INTEGER NOT NULL, "
	                + "agotado INTEGER DEFAULT 0"
	                + ");";
	        stmt.execute(sqlProducto);
	        System.out.println("- Tabla PRODUCTO creada");

	    } catch (Exception ex) {
	        System.err.format("* Error al crear la BBDD: %s", ex.getMessage());
	        ex.printStackTrace();
	    }
	}
	// ============================================================================================================================================================

	
	//---------------------------------------------LEER CSV---------------------------------------------
	
	

	//---------------------------------------------PERFIL---------------------------------------------

	//insertar perfil

	public void insertarPerfil(String username, String password) {
		String sql = "INSERT INTO PERFIL (USERNAME, PASSWORD) VALUES (?, ?);";
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, username);
			pstmt.setString(2, password);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.format("\n- Perfil insertado: %s", username);
			} else {
				System.out.format("\n- No se ha insertado el perfil: %s", username);
			}

		} catch (Exception ex) {
			System.err.format("\n* Error al insertar perfil en la BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}

	}
	

}


