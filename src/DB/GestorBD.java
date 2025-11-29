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

	public void crearBBDD() {
		// Se abre la conexión y se obtiene el Statement
		// Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
				Statement stmt = con.createStatement()) {

			// Tabla PERFIL (login)
			String sqlPerfil = "CREATE TABLE IF NOT EXISTS PERFIL (\n"
					+ " ID_PERFIL INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " USERNAME TEXT UNIQUE NOT NULL,\n"
					+ " PASSWORD TEXT NOT NULL\n"
					+ ");";
			if (!stmt.execute(sqlPerfil)) {
				System.out.println("- Tabla PERFIL creada");
			}

			// Tabla USUARIO
			String sqlUsuario = "CREATE TABLE IF NOT EXISTS USUARIO (\n"
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " NOMBRE TEXT NOT NULL,\n"
					+ " APELLIDO TEXT NOT NULL,\n"
					+ " EDAD INTEGER,\n"
					+ " USERNAME TEXT UNIQUE NOT NULL,\n"
					+ " EMAIL TEXT NOT NULL,\n"
					+ " TELEFONO TEXT,\n"
					+ " TARJETA TEXT,\n"
					+ " PASSWORD TEXT NOT NULL\n"
					+ ");";
			if (!stmt.execute(sqlUsuario)) {
				System.out.println("- Tabla USUARIO creada");
			}

			// Tabla ANIMAL
			String sqlAnimal = "CREATE TABLE IF NOT EXISTS ANIMAL (\n"
					+ " ID_ANIMAL TEXT PRIMARY KEY,\n"
					+ " NOMBRE TEXT NOT NULL,\n"
					+ " TIPO_ANIMAL TEXT NOT NULL,\n"
					+ " SEXO TEXT,\n"
					+ " EDAD REAL,\n"
					+ " ESPECIE TEXT,\n"
					+ " PESO REAL,\n"
					+ " PERSONALIDAD TEXT,\n"
					+ " RASGOS TEXT,\n"
					+ " ADOPTADO INTEGER DEFAULT 0\n"
					+ ");";
			if (!stmt.execute(sqlAnimal)) {
				System.out.println("- Tabla ANIMAL creada");
			}

			// Tabla ADOPCION
			String sqlAdopcion = "CREATE TABLE IF NOT EXISTS ADOPCION (\n"
					+ " ID_MASCOTA INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " USERNAME TEXT NOT NULL,\n"
					+ " ID_ANIMAL TEXT NOT NULL,\n"
					+ " FECHA_ADOPCION TEXT,\n"
					+ " FOREIGN KEY (USERNAME) REFERENCES USUARIO(USERNAME),\n"
					+ " FOREIGN KEY (ID_ANIMAL) REFERENCES ANIMAL(ID_ANIMAL)\n"
					+ ");";
			if (!stmt.execute(sqlAdopcion)) {
				System.out.println("- Tabla ADOPCION creada");
			}

			// Tabla PRODUCTO
			String sqlProducto = "CREATE TABLE IF NOT EXISTS PRODUCTO (\n"
					+ " ID_PRODUCTO TEXT PRIMARY KEY,\n"
					+ " NOMBRE TEXT NOT NULL,\n"
					+ " CATEGORIA_ANIMAL TEXT,\n"
					+ " PRECIO REAL NOT NULL,\n"
					+ " UNIDADES INTEGER NOT NULL,\n"
					+ " AGOTADO INTEGER DEFAULT 0\n"
					+ ");";
			if (!stmt.execute(sqlProducto)) {
				System.out.println("- Tabla PRODUCTO creada");
			}

		} catch (Exception ex) {
			System.err.format("* Error al crear la BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}

	}

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
	

}


