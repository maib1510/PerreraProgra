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
import Domain.Roedor;
import Domain.Usuario;
import gui.HiloBienvenida;
import gui.VentanaInicioSesion;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//--------------------------------------------------------------GATOS-----------------------------------------------------------------------------
		/*
		Gato[] gatos = new Gato[12];
		
		Gato gato1 = new Gato("Misu", "Hembra", 1.5f, "Bengala", 4.2f, "Curiosa, atenta y cariñosa", "Atigrado, manchas tipo leopardo, ojos verdes", false);
		Gato gato2 = new Gato("Nieve", "Hembra", 3.0f, "Europeo", 4.5f, "Tranquila y observadora", "Pelaje blanco, ojos verdes", false);
		Gato gato3 = new Gato("Tris", "Hembra", 2.0f, "Carey", 4.0f, "Curiosa, serena y elegante", "Tricolor, ojos verdes", false);
		Gato gato4 = new Gato("Luna", "Hembra", 2.5f, "Atigrado/Carey", 4.1f, "Activa, curiosa y cariñosa", "Tricolor, ojos amarillos", false);
		Gato gato5 = new Gato("Copito", "Macho", 4.0f, "Persa bicolor", 5.2f, "Sociable, tranquilo y dulce", "Blanco con manchas naranjas, ojos claros", false);
		Gato gato6 = new Gato("Maya", "Hembra", 3.0f, "Siamés tricolor", 3.7f, "Inteligente, tranquila y sociable", "Claro con manchas oscuras, ojos amarillos", false);
		Gato gato7 = new Gato("Tigre", "Macho", 2.8f, "Bengala", 4.4f, "Enérgico, juguetón y curioso", "Marrón con rayas oscuras, ojos dorados", false);
		Gato gato8 = new Gato("Olaf", "Macho", 2.0f, "Europeo", 4.0f, "Juguetón, amistoso y curioso", "Atigrado marrón y blanco, ojos azul y ámbar", false);
		Gato gato9 = new Gato("Nube", "Hembra", 2.5f, "Europeo", 3.8f, "Tímida, inteligente y curiosa", "Atigrado marrón, ojos azules", false);
		Gato gato10 = new Gato("Leo", "Macho", 4.5f, "Siberiano", 5.3f, "Tranquilo, independiente y elegante", "Crema y gris, ojos verdes", false);
		Gato gato11 = new Gato("Shadow", "Macho", 3.2f, "Bombay", 4.3f, "Observador, tranquilo y leal", "Negro, ojos amarillos", false);
		Gato gato12 = new Gato("Simba", "Macho", 3.5f, "Europeo", 4.6f, "Sociable, juguetón y amigable", "Naranja atigrado, ojos verdes", false);

		
		gatos[0]  = gato1;
		gatos[1]  = gato2;
		gatos[2]  = gato3;
		gatos[3]  = gato4;
		gatos[4]  = gato5;
		gatos[5]  = gato6;
		gatos[6]  = gato7;
		gatos[7]  = gato8;
		gatos[8]  = gato9;
		gatos[9]  = gato10;
		gatos[10] = gato11;
		gatos[11] = gato12;
		
		//--------------------------------------------------------------PERROS-----------------------------------------------------------------------------

		Perro[] perros = new Perro[12];

		Perro perro1 = new Perro("Kiara", "Hembra", 2.0f, "Mestiza de Podenco", 14.5f, "Activa, cariñosa y muy curiosa", "Canela claro, orejas grandes", false);
		Perro perro2 = new Perro("Rufo", "Macho", 4.5f, "Cruce de Pastor Vasco", 22.3f, "Guardian, tranquilo y leal", "Negro con manchas blancas", false);
		Perro perro3 = new Perro("Lola", "Hembra", 1.7f, "Terrier Ibicenco mestizo", 11.8f, "Sociable, lista y divertida", "Beige y blanco, hocico alargado", false);
		Perro perro4 = new Perro("Tango", "Macho", 3.3f, "Mestizo de Galgo y Pointer", 18.9f, "Deportista, obediente y sensible", "Atigrado oscuro, patas largas", false);
		Perro perro5 = new Perro("Asia", "Hembra", 2.3f, "Podenca Andaluza mestiza", 12.7f, "Tímida, cariñosa y tranquila", "Marrón rojizo, orejas puntiagudas", false);
		Perro perro6 = new Perro("Eros", "Macho", 5.0f, "X de Sabueso Español", 25.2f, "Paciente, noble y buen compañero", "Tricolor, orejas caídas, mirada dulce", false);
		Perro perro7 = new Perro("Jara", "Hembra", 3.8f, "Mestiza de Ratonero", 8.5f, "Vivaz, inteligente y graciosa", "Negra y fuego, pequeña y ágil", false);
		Perro perro8 = new Perro("Simba", "Macho", 4.1f, "Cruce de Setter", 20.4f, "Extrovertido, fiel y juguetón", "Blanco y marrón, pelaje largo", false);
		Perro perro9 = new Perro("Mía", "Hembra", 2.6f, "Mestiza de Mastín", 29.7f, "Soñadora, protectora y dulce", "Color leonado, maciza y fuerte", false);
		Perro perro10 = new Perro("Coco", "Macho", 1.5f, "Mestizo pequeño", 6.9f, "Alegre, simpático y adaptable", "Gris claro, pelo corto, ojos vivos", false);
		Perro perro11 = new Perro("India", "Hembra", 3.2f, "Mestiza de Braco", 17.6f, "Atlética, exploradora y cercana", "Marrón oscuro, pecho blanco", false);
		Perro perro12 = new Perro("Jazz", "Macho", 2.0f, "Mestizo mediano", 15.8f, "Amistoso, juguetón y versátil", "Beige tostado, orejas semi-caídas", false);


		perros[0]  = perro1;
		perros[1]  = perro2;
		perros[2]  = perro3;
		perros[3]  = perro4;
		perros[4]  = perro5;
		perros[5]  = perro6;
		perros[6]  = perro7;
		perros[7]  = perro8;
		perros[8]  = perro9;
		perros[9]  = perro10;
		perros[10] = perro11;
		perros[11] = perro12;

		
		//--------------------------------------------------------------PAJAROS-----------------------------------------------------------------------------
		
		Pajaro[] pajaros = new Pajaro[6];
		
		Pajaro pajaro1 = new Pajaro("Lima", "Macho", 1.5f, "Periquito australiano", 0.035f, "Curioso y sociable", "Verde con cabeza amarilla y rayas negras", false); 
		Pajaro pajaro3 = new Pajaro("Kiwi", "Hembra", 3.0f, "Amazona frentiazul", 0.450f, "Inteligente y juguetona", "Verde con amarillo, azul y toques rojos", false);
		Pajaro pajaro5 = new Pajaro("Sol", "Macho", 2.0f, "Canario", 0.025f, "Alegre y cantarín", "Amarillo brillante con algo de verde", false);
		Pajaro pajaro4 = new Pajaro("Luz", "Hembra", 4.0f, "Cacatúa amarilla", 0.850f, "Extrovertida y divertida", "Cresta y cabeza amarilla, cuerpo blanco, pico negro", false);
		Pajaro pajaro6 = new Pajaro("Fuego", "Macho", 5.0f, "Guacamayo híbrido", 1.200f, "Llamativo y sociable", "Verde con tonos amarillos, azul en las alas y detalles rojos", false);
		Pajaro pajaro2 = new Pajaro("Mandarina", "Macho", 2.5f, "Canario rojo", 0.027f, "Activo y vivaz", "Naranja brillante, pico y patas amarillos", false);

		
		pajaros[0]  = pajaro1;
		pajaros[1]  = pajaro2;
		pajaros[2]  = pajaro3;
		pajaros[3]  = pajaro4;
		pajaros[4]  = pajaro5;
		pajaros[5]  = pajaro6;
		
		//--------------------------------------------------------------ROEDORES-----------------------------------------------------------------------------

		Roedor[] roedores = new Roedor[6];

		Roedor roedor1 = new Roedor("Nibbles", "Macho", 1.0f, "Hámster común", 0.12f, "Curioso y simpático", "Pelaje marrón y blanco", false);
		Roedor roedor2 = new Roedor("Puffy", "Hembra", 0.8f, "Hámster ruso", 0.05f, "Tranquila y dulce", "Pelaje blanco y gris", false);
		Roedor roedor3 = new Roedor("Manchitas", "Hembra", 2.0f, "Cobaya abisinia", 0.9f, "Sociable y tranquila", "Pelaje marrón, blanco y naranja", false);
		Roedor roedor4 = new Roedor("Chocolate", "Macho", 3.0f, "Conejo negro", 2.1f, "Tímido y observador", "Pelaje negro y brillante", false);
		Roedor roedor5 = new Roedor("Nube", "Hembra", 2.5f, "Conejo gris", 1.8f, "Cariñosa y activa", "Pelaje gris claro y suave", false);
		Roedor roedor6 = new Roedor("Ricky", "Macho", 1.4f, "Ratón doméstico", 0.3f, "Inteligente y sociable", "Pelaje gris y suave", false); 

		roedores[0]  = roedor1;
		roedores[1]  = roedor2;
		roedores[2]  = roedor3;
		roedores[3]  = roedor4;
		roedores[4]  = roedor5;
		roedores[5]  = roedor6;
		*/
		
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

		ArrayList<Usuario> lista = leerCSV("resources/db/usuarios.csv");
		for (Usuario u : lista) {
		    gestor.insertarUsuario(u); 
		    System.out.println("usuario - " + u.getNombre() + " insertado");
		}
		
		ArrayList<Gato> gatos    = new ArrayList<>();
		ArrayList<Perro> perros  = new ArrayList<>();
		ArrayList<Pajaro> pajaros = new ArrayList<>();
		ArrayList<Roedor> roedores = new ArrayList<>();

		leerCSVAnimales("resources/db/animales.csv", gatos, perros, pajaros, roedores, gestor);

		Gato[] gatosArr = gatos.toArray(new Gato[0]);
		Roedor[] roedoresArr = roedores.toArray(new Roedor[0]);
		Pajaro[] pajarosArr = pajaros.toArray(new Pajaro[0]);
		Perro[] perrosArr = perros.toArray(new Perro[0]);

		
		// Ahora mostrar la ventana principal
		javax.swing.SwingUtilities.invokeLater(() -> {
		    new VentanaInicioSesion(gatosArr, roedoresArr, pajarosArr, perrosArr, gestor).setVisible(true);
		});
	}

	private static ArrayList<Usuario> leerCSV(String rutaArchivo) {
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


}

