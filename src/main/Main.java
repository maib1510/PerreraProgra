package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import DB.GestorBD;
import Domain.Animal;
import Domain.Gato;
import Domain.Pajaro;
import Domain.Perfil;
import Domain.Perro;
import Domain.Producto;
import Domain.Roedor;
import Domain.Usuario;
import gui.HiloBienvenida;
import gui.VentanaInicioSesion;


public class Main {

	public static void main(String[] args) {

		
		//mejorar botones visualmente (IA generated)
		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// =========== HILO DE BIENVENIDA =======================================================
		// Crear y lanzar el hilo de bienvenida
		HiloBienvenida bienvenida = new HiloBienvenida("imagenes/fotosPerfil/bienvenida.png", 4);
		Thread hilo = new Thread(bienvenida);
		hilo.start();

		// Esperar a que termine la bienvenida ANTES de abrir VentanaPrincipal
		try {
			hilo.join();  // Aquí el main se queda esperando hasta que el hilo termine
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// =============== INICIALIZAR BASE DE DATOS ============================================
		GestorBD gestor = new GestorBD();
		try {
		    Class.forName("org.sqlite.JDBC");
		    System.out.println("Driver cargado correctamente");
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		gestor.crearBBDD();

		// INSERTAR USUARIOS =====================================================================
		ArrayList<Usuario> lista = leerCSV_usuarios("resources/db/usuarios.csv");
		for (Usuario u : lista) {
		    gestor.insertarUsuario(u); 
		}
		
		// INSERTAR ANIMALES =====================================================================
		ArrayList<Gato> gatos    = new ArrayList<>();
		ArrayList<Perro> perros  = new ArrayList<>();
		ArrayList<Pajaro> pajaros = new ArrayList<>();
		ArrayList<Roedor> roedores = new ArrayList<>();

		leerCSVAnimales("resources/db/animales.csv", gatos, perros, pajaros, roedores, gestor);

		Gato[] gatosArr = gatos.toArray(new Gato[0]);
		Roedor[] roedoresArr = roedores.toArray(new Roedor[0]);
		Pajaro[] pajarosArr = pajaros.toArray(new Pajaro[0]);
		Perro[] perrosArr = perros.toArray(new Perro[0]);

		// INSERTAR PRODUCTOS =====================================================================
		ArrayList<Producto> productos = leerCSV_productos("resources/db/productos.csv");
		for (Producto p : productos) {
			gestor.insertarProducto(p);
		}
		// Ahora mostrar la ventana principal
		javax.swing.SwingUtilities.invokeLater(() -> {
		    new VentanaInicioSesion(gatosArr, roedoresArr, pajarosArr, perrosArr, gestor).setVisible(true);
		});
	}

	private static ArrayList<Usuario> leerCSV_usuarios(String rutaArchivo) {
	    ArrayList<Usuario> usuarios = new ArrayList<>();

	    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
	        String linea;
	        boolean primeraLinea = true;

	        while ((linea = br.readLine()) != null) {
	            if (primeraLinea) {
	                primeraLinea = false;
	                continue; // Saltar cabecera
	            }

	            String[] valores = linea.split(",");
	            if (valores.length < 8) continue; // Control básico

	            Usuario user = new Usuario();
	            Perfil perfil = new Perfil(); // ESTO era lo que faltaba

	            user.setNombre(valores[0].trim());
	            user.setApellido(valores[1].trim());
	            user.setEdad(Integer.parseInt(valores[2].trim()));

	            perfil.setUsername(valores[3].trim());

	            user.setEmail(valores[4].trim());
	            user.setTelefono(valores[5].trim());
	            user.setTarjeta_bancaria(valores[6].trim());

	            perfil.setPassword(valores[7].trim());
	            user.setPerfil(perfil);
	            perfil.setUsuario(user);
	            usuarios.add(user);
	        }

	    } catch (IOException e) {
	       e.printStackTrace();
	    }
	    return usuarios;
	}

	private static void leerCSVAnimales(String rutaArchivo,
			ArrayList<Gato> gatos,
			ArrayList<Perro> perros,
			ArrayList<Pajaro> pajaros,
			ArrayList<Roedor> roedores,
			GestorBD gestor) {

		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			boolean primeraLinea = true;

			while ((linea = br.readLine()) != null) {
				if (primeraLinea) {
					primeraLinea = false;   // Saltar cabecera
					continue;
				}

				String[] v = linea.split(",");
				if (v.length < 10) continue; // Control básico

				// idAnimal,Nombre,TipoAnimal,Sexo,Edad,Especie,Peso,Personalidad,Rasgos,Adoptado
				String tipo = v[2];  // "Gato", "Perro", "Pajaro", "Roedor"

				Animal a;

				switch (tipo) {
				case "Gato":
					a = new Gato();
					break;
				case "Perro":
					a = new Perro();
					break;
				case "Pajaro":
					a = new Pajaro();
					break;
				case "Roedor":
					a = new Roedor();
					break;
				default:
					System.out.println("Tipo animal desconocido: " + tipo);
					continue;
				}

				a.setNombre(v[1]);
				a.setTipoAnimal(tipo);
				a.setSexo(v[3]);
				a.setEdad((int) Float.parseFloat(v[4]));
				a.setRaza(v[5]);
				a.setPeso(Float.parseFloat(v[6]));
				a.setDescripcion_personalidad(v[7]);
				a.setDescripcion_fisica(v[8]);
				a.setAdoptado(v[9].equalsIgnoreCase("TRUE"));

				// Añadir a la lista correspondiente
				if (a instanceof Gato) {
					gatos.add((Gato) a);
				} else if (a instanceof Perro) {
					perros.add((Perro) a);
				} else if (a instanceof Pajaro) {
					pajaros.add((Pajaro) a);
				} else if (a instanceof Roedor) {
					roedores.add((Roedor) a);
				}
				gestor.insertarAnimal(a);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Producto> leerCSV_productos(String rutaCSV) {
		ArrayList<Producto> productos = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            boolean primera = true;

            while ((linea = br.readLine()) != null) {
                if (primera) { primera = false; continue; }

                String[] datos = linea.split(";"); 
                if (datos.length != 6) continue;

                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String categoria = datos[2];
                double precio = Double.parseDouble(datos[3]);
                int unidades = Integer.parseInt(datos[4]);
                boolean agotado = Boolean.parseBoolean(datos[5]);

                productos.add(new Producto(id, nombre, categoria, precio, unidades, agotado));
            }

        } catch (Exception e) {
            System.out.println("Error al leer productos: " + e.getMessage());
        }
		return productos;
    }


}

