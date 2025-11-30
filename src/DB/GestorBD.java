package DB;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Domain.Perfil;
import Domain.Usuario;

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

	// =============== BORRAR LA BASE DE DATOS ====================================================================================================================
		public void borrarBBDD() {
		// Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
				Statement stmt = con.createStatement()) {

			String sqlAdopcion = "DROP TABLE IF EXISTS ADOPCION;";
			if (!stmt.execute(sqlAdopcion)) {
				System.out.println("\n- Se ha borrado la tabla ADOPCION");
			}

			String sqlProducto = "DROP TABLE IF EXISTS PRODUCTO;";
			if (!stmt.execute(sqlProducto)) {
				System.out.println("- Se ha borrado la tabla PRODUCTO");
			}

			String sqlAnimal = "DROP TABLE IF EXISTS ANIMAL;";
			if (!stmt.execute(sqlAnimal)) {
				System.out.println("- Se ha borrado la tabla ANIMAL");
			}

			String sqlUsuario = "DROP TABLE IF EXISTS USUARIO;";
			if (!stmt.execute(sqlUsuario)) {
				System.out.println("- Se ha borrado la tabla USUARIO");
			}

			if (!stmt.execute("DROP TABLE IF EXISTS PERFIL;")) {
				System.out.println("- Se ha borrado la tabla PERFIL");
			}

		} catch (Exception ex) {
			System.err.format("\n* Error al borrar la BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}

		try {
			// Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE));
			System.out.println("\n- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.format("\n* Error al borrar el archivo de la BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}

	}
	
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

	public Usuario obtenerUsuarioConPerfil(String username, String password) {
    String sql = "SELECT u.id_usuario, u.nombre, u.apellido, u.email, u.telefono, u.tarjeta_bancaria, " +
                 "p.id_perfil, p.username, p.password " +
                 "FROM USUARIO u " +
                 "JOIN PERFIL p ON u.id_perfil = p.id_perfil " +
                 "WHERE p.username = ? AND p.password = ?";

    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario u = new Usuario();
            u.setId_usuario(rs.getInt("id_usuario"));
            u.setNombre(rs.getString("nombre"));
            u.setApellido(rs.getString("apellido"));
            u.setEmail(rs.getString("email"));
            u.setTelefono(rs.getString("telefono"));
            u.setTarjeta_bancaria(rs.getString("tarjeta_bancaria"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));

            // Si tienes un objeto Perfil, puedes mapearlo también
            Perfil perfil = new Perfil();
            perfil.setId_perfil(rs.getInt("id_perfil"));
            perfil.setUsername(rs.getString("username"));
            perfil.setPassword(rs.getString("password"));
            u.setPerfil(perfil);

            return u;
        } else {
            return null; // No se encontró el usuario o contraseña incorrecta
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


	
	

}


