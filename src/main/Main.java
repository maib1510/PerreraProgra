package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import Domain.Gato;
import Domain.Pajaro;
import Domain.Perro;
import Domain.Roedor;
import gui.VentanaInicioSesion;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//--------------------------------------------------------------GATOS-----------------------------------------------------------------------------
		
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

		Perro perro1 = new Perro("Rex", "Macho", 3.0f, "Pastor Alemán", 30.5f, "Leal, protector y obediente", "Marrón y negro, ojos oscuros", false);
		Perro perro2 = new Perro("Luna", "Hembra", 2.5f, "Labrador Retriever", 25.0f, "Juguetona, amable y sociable", "Beige claro, ojos marrones", false);
		Perro perro3 = new Perro("Toby", "Macho", 1.8f, "Beagle", 12.3f, "Curioso, activo y tierno", "Tricolor, orejas largas, ojos miel", false);
		Perro perro4 = new Perro("Nala", "Hembra", 4.2f, "Golden Retriever", 28.7f, "Cariñosa, tranquila y noble", "Dorado brillante, ojos marrones", false);
		Perro perro5 = new Perro("Bruno", "Macho", 5.0f, "Boxer", 30.0f, "Valiente, energético y fiel", "Marrón con máscara negra, ojos oscuros", false);
		Perro perro6 = new Perro("Kira", "Hembra", 3.3f, "Husky Siberiano", 23.1f, "Independiente, activa y curiosa", "Blanco y gris, ojos azules", false);
		Perro perro7 = new Perro("Rocky", "Macho", 2.0f, "Border Collie", 18.4f, "Inteligente, trabajador y ágil", "Blanco y negro, ojos marrones", false);
		Perro perro8 = new Perro("Molly", "Hembra", 1.5f, "Corgi", 11.2f, "Simpática, alegre y muy sociable", "Naranja y blanco, ojos oscuros", false);
		Perro perro9 = new Perro("Duke", "Macho", 3.7f, "Dálmata", 24.0f, "Activo, juguetón y curioso", "Blanco con manchas negras, ojos oscuros", false);
		Perro perro10 = new Perro("Bella", "Hembra", 4.0f, "Shiba Inu", 9.8f, "Independiente, leal y alerta", "Naranja rojizo, ojos marrones", false);
		Perro perro11 = new Perro("Max", "Macho", 2.9f, "French Bulldog", 13.5f, "Tranquilo, cariñoso y simpático", "Gris azulado, ojos oscuros", false);
		Perro perro12 = new Perro("Coco", "Macho", 1.2f, "Caniche", 6.1f, "Inteligente, amable y juguetón", "Blanco rizado, ojos negros", false);

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
		
		Pajaro pajaro1 = new Pajaro("Lima", "Macho", 1.5f, "Periquito australiano", 0.035f, "Curioso y sociable", "Plumaje verde brillante con cabeza amarilla y rayas negras", false); 
		Pajaro pajaro2 = new Pajaro("Kiwi", "Hembra", 3.0f, "Amazona frentiazul", 0.450f, "Inteligente y juguetona", "Plumaje verde con tonos amarillos, azul en alas y toques rojos", false);
		Pajaro pajaro3 = new Pajaro("Sol", "Macho", 2.0f, "Canario", 0.025f, "Alegre y cantarín", "Plumaje amarillo brillante con pequeñas zonas verdosas", false);
		Pajaro pajaro4 = new Pajaro("Luz", "Hembra", 4.0f, "Cacatúa amarilla", 0.850f, "Extrovertida y divertida", "Cresta y cabeza amarilla, cuerpo blanco, pico negro robusto", false);
		Pajaro pajaro5 = new Pajaro("Fuego", "Macho", 5.0f, "Guacamayo híbrido", 1.200f, "Llamativo y sociable", "Cabeza roja-naranja, cuerpo multicolor amarillo-verde-azul", false);
		Pajaro pajaro6 = new Pajaro("Mandarina", "Macho", 2.5f, "Canario rojo", 0.027f, "Activo y vivaz", "Plumaje naranja brillante, patas y pico amarillos", false);
		
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
		
		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(gatos, roedores, pajaros);
		
		

	}

}
