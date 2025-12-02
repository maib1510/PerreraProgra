package DB;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Domain.Adopcion;
import Domain.Animal;
import Domain.Gato;
import Domain.Pajaro;
import Domain.Perfil;
import Domain.Perro;
import Domain.Producto;
import Domain.Roedor;
import Domain.Usuario;

public class GestorBD {

	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "resources/db/database.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;

	public GestorBD() {		
	    try {
	        Class.forName(DRIVER_NAME);
	    } catch (ClassNotFoundException ex) {
	        System.err.println("Error cargando el driver: " + ex.getMessage());
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
	                + "password TEXT NOT NULL, "
	                + "id_usuario INTEGER NOT NULL, "
	                + "FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE" // ON DELETE CASCADE -> cuando se elimina un perfil, se elimina automáticamente el usuario
	                + ");";
	        stmt.execute(sqlPerfil);
	        System.out.println("- Tabla PERFIL creada");

	        // TABLA USUARIO ==============================================================================
	        String sqlUsuario = "CREATE TABLE IF NOT EXISTS usuario ("
	                + "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, "
	                
	                + "nombre TEXT NOT NULL, "
	                + "apellido TEXT NOT NULL, "
	                + "edad INTEGER, "
	                + "email TEXT UNIQUE NOT NULL, "
	                + "telefono TEXT, "
	                + "tarjeta_bancaria TEXT "
	                + ");";
	        stmt.execute(sqlUsuario);
	        System.out.println("- Tabla USUARIO creada");

	        // TABLA ANIMAL ==============================================================================
	        String sqlAnimal = "CREATE TABLE IF NOT EXISTS animal ("
	                + "id_animal INTEGER PRIMARY KEY AUTOINCREMENT, "
	                + "nombre TEXT UNIQUE NOT NULL, "
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
	                + "fecha_adopcion DATE, "
	                + "FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE, "
	                + "FOREIGN KEY (id_animal) REFERENCES animal(id_animal) ON DELETE CASCADE"
	                + ");";
	        stmt.execute(sqlAdopcion);
	        System.out.println("- Tabla ADOPCION creada");

	        // TABLA PRODUCTO ==============================================================================
	        String sqlProducto = "CREATE TABLE IF NOT EXISTS producto ("
	                + "id_producto INTEGER PRIMARY KEY AUTOINCREMENT, "
	                + "nombre TEXT UNIQUE NOT NULL, "
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
	
	

	// ========================================== PERFIL ===============================================

	// funcion para obtenmer usuario a partir del nombre de usuario y contraseña
	public Usuario obtenerUsuarioConPerfil(String username, String password) {
	    String sql = "SELECT u.id_usuario, u.nombre, u.apellido, u.email, u.telefono, u.tarjeta_bancaria, " +
	                 "p.id_perfil, p.username, p.password " +
	                 "FROM USUARIO u " +
	                 "JOIN PERFIL p ON u.id_usuario = p.id_usuario " +
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
	   
	
	            // Si tienes un objeto Perfil, puedes mapearlo también
	            Perfil perfil = new Perfil();
	            perfil.setId_perfil(rs.getInt("id_perfil"));
	            perfil.setUsername(rs.getString("username"));
	            perfil.setPassword(rs.getString("password"));
	            perfil.setUsuario(u);
	            u.setPerfil(perfil);
	
	            System.out.println("Usuario encontrado con exito");
	            return u;
	            
	        } else {
	            return null; // No se encontró el usuario o contraseña incorrecta
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	// FUNCION PARA INSTERTAR USUARIO ------------------------------------------------------------------------------------
	public boolean insertarUsuario(Usuario u) {
	    String sqlUsuario = "INSERT OR IGNORE INTO usuario (nombre, apellido, edad, email, telefono, tarjeta_bancaria) "
	                      + "VALUES (?, ?, ?, ?, ?, ?)";

	    String sqlPerfil = "INSERT OR IGNORE INTO perfil (username, password, id_usuario) "
	                     + "VALUES (?, ?, ?)";

	    try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {

	        int idUsuario = 0;

	        // Insertar usuario
	        try (PreparedStatement stmt = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
	            stmt.setString(1, u.getNombre());
	            stmt.setString(2, u.getApellido());
	            stmt.setInt(3, u.getEdad());
	            stmt.setString(4, u.getEmail());
	            stmt.setString(5, u.getTelefono());
	            stmt.setString(6, u.getTarjeta_bancaria());
	            stmt.executeUpdate();

	            try (ResultSet rs = stmt.getGeneratedKeys()) {
	                if (rs.next()) idUsuario = rs.getInt(1);
	            }
	        }

	        // Insertar perfil asociado
	        try (PreparedStatement stmt = con.prepareStatement(sqlPerfil)) {
	            stmt.setString(1, u.getPerfil().getUsername());
	            stmt.setString(2, u.getPerfil().getPassword());
	            stmt.setInt(3, idUsuario);
	            stmt.executeUpdate();
	        }

	        System.out.println("usuario insertado con éxito");
	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	// ================================== ANIMALES ======================================================================================
	public boolean insertarAnimal(Animal a) {
	    String sqlAnimal = "INSERT OR IGNORE INTO animal (nombre, tipo_animal, sexo, edad, raza, peso, desc_personalidad, desc_fisica, adoptado) "
	                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement stmt = con.prepareStatement(sqlAnimal)) {

	        stmt.setString(1, a.getNombre());
	        stmt.setString(2, a.getTipoAnimal());      // perro/gato/pajaro/roedor
	        stmt.setString(3, a.getSexo());
	        stmt.setFloat(4, a.getEdad());
	        stmt.setString(5, a.getRaza());
	        stmt.setDouble(6, a.getPeso());
	        stmt.setString(7, a.getDescripcion_personalidad());
	        stmt.setString(8, a.getDescripcion_fisica());
	        stmt.setInt(9, a.isAdoptado() ? 1 : 0);

	        stmt.executeUpdate();

	        System.out.println("animal insertado con exito");
	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean actualizarAnimal(int idAnimal) {
	    String sql = "UPDATE animal SET adoptado = 1 WHERE id_animal = ?";

	    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, idAnimal);

	        int filas = stmt.executeUpdate();

	        if (filas > 0) {
	            System.out.println("<3");
	            return true;
	        } else {
	            System.out.println("no existe el animal con id " + idAnimal);
	            return false;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	
	public boolean estaAdoptadoPorId(int i) {
	    String sql = "SELECT adoptado FROM animal WHERE id_animal = ?";

	    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setInt(1, i);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("adoptado") == 1;
	        } else {
	            return false;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	// ================================== PRODUCTOS ======================================================================================

	public boolean insertarProducto(Producto p) {
		String sql = "INSERT OR IGNORE INTO producto (nombre, categoria_animal, precio, unidades_disp, agotado)" + 
						"VALUES (?, ?, ?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setString(1,p.getNombre());
				stmt.setString(2, p.getCategoria());
				stmt.setDouble(3, p.getPrecio());
				stmt.setInt(4, p.getUnidadesDisponibles());
				stmt.setInt(5, p.isAgotado() ? 1 : 0);
				
				stmt.executeUpdate();
				
				System.out.println("producto insertado con éxito");
				return true;
				
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<Producto> cargarProductos() {
		ArrayList<Producto> listaProductos = new ArrayList<>();  

	    String sql = "SELECT * FROM producto";

	    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Producto p = new Producto(
	                rs.getInt("id_producto"),
	                rs.getString("nombre"),
	                rs.getString("categoria_animal"),
	                rs.getDouble("precio"),
	                rs.getInt("unidades_disp"),
	                rs.getInt("agotado") == 1
	            );

	            listaProductos.add(p);
	        }

	        System.out.println("productos cargados desde la BD");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return listaProductos;
	}
	
	public void actualizarProducto(Producto p) {
	    String sql = "UPDATE producto SET unidades_disp = ?, agotado = ? WHERE id_producto = ?";

	    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, p.getUnidadesDisponibles());
	        stmt.setInt(2, p.isAgotado() ? 1 : 0);
	        stmt.setInt(3, p.getId_producto());

	        int filas = stmt.executeUpdate();

	        if (filas > 0) {
	            System.out.println("producto actualizado (unidades y agotado)");
	        } else {
	            System.out.println("no existe el producto con id " + p.getId_producto());
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	// FUNCIONES DE ADOPCIÓN ==============================================================
	public boolean insertarMascota(Adopcion adopcion) {
	    LocalDateTime hoy = LocalDateTime.now();

	    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {

	        String sqlSelect = "SELECT id_animal FROM animal WHERE nombre = ?";
	        PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);
	        stmtSelect.setString(1, adopcion.getAnimal().getNombre());
	        ResultSet rs = stmtSelect.executeQuery();

	        if (rs.next()) {
	            int idAnimal = rs.getInt("id_animal");
	            adopcion.getAnimal().setId_animal(idAnimal); // guardamos el ID en el objeto
	        } else {
	            System.out.println("No existe el animal con ese nombre.");
	            return false;
	        }

	        String sqlInsert = "INSERT INTO adopcion(id_usuario, id_animal, fecha_adopcion) VALUES (?, ?, ?)";
	        PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
	        stmtInsert.setInt(1, adopcion.getUsuario().getId_usuario());
	        stmtInsert.setInt(2, adopcion.getAnimal().getId_animal());
	        stmtInsert.setTimestamp(3, Timestamp.valueOf(hoy));
	        stmtInsert.executeUpdate();

	        System.out.println("Enhorabuena, " + adopcion.getUsuario().getNombre() + " " 
	            + adopcion.getUsuario().getApellido() + ", has adoptado a " + adopcion.getAnimal().getNombre()
	            + " - fecha: " + Timestamp.valueOf(hoy));

	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}



	public ArrayList<Animal> obtenerMascotasUsuario(Usuario u) {
	    ArrayList<Animal> mascotas = new ArrayList<>();

	    String sql = "SELECT a.* FROM adopcion d "
	               + "JOIN animal a ON d.id_animal = a.id_animal "
	               + "WHERE d.id_usuario = ?";

	    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, u.getId_usuario());
	        ResultSet rs = stmt.executeQuery();
	    
	        while (rs.next()) {
	            String tipo = rs.getString("tipo_animal");
	            Animal animal;

	            switch (tipo.toLowerCase()) {
	                case "perro":
	                    animal = new Perro();
	                    break;
	                case "gato":
	                    animal = new Gato();
	                    break;
	                case "pajaro":
	                    animal = new Pajaro();
	                    break;
	                case "roedor":
	                    animal = new Roedor();
	                    break;
	                default:
	                    System.out.println("Tipo desconocido: " + tipo + ". Ignorando fila.");
	                    continue;
	            }

	            animal.setId_animal(rs.getInt("id_animal"));
	            animal.setNombre(rs.getString("nombre"));
	            animal.setSexo(rs.getString("sexo"));
	            animal.setEdad(rs.getFloat("edad"));
	            animal.setRaza(rs.getString("raza"));
	            animal.setPeso(rs.getFloat("peso"));
	            animal.setDescripcion_personalidad(rs.getString("desc_personalidad"));
	            animal.setDescripcion_fisica(rs.getString("desc_fisica"));
	            animal.setAdoptado(rs.getBoolean("adoptado"));
	            animal.setTipoAnimal(tipo);
	            System.out.println(animal);
	            mascotas.add(animal);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return mascotas;
	}



 
}


