package main;

import Domain.Gato;
import Domain.Pajaro;
import Domain.Perro;
import Domain.Roedor;
import gui.VentanaGatos;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//-------------------GATOS------------------
		Gato[] gatos = new Gato[12];
		Perro[] perros = new Perro[12];
		Roedor[] roedores = new Roedor[12];
		Pajaro[] pajaros = new Pajaro[12];
		
		Gato gato1 = new Gato("Misu", "hembra", 1.5f, "Bengala", 4.2f, "Curiosa, atenta y cariñosa", "Atigrado, manchas tipo leopardo, ojos verdes", false);
		Gato gato2 = new Gato("Nieve", "hembra", 3.0f, "Europeo", 4.5f, "Tranquila y observadora", "Pelaje blanco, ojos verdes", false);
		Gato gato3 = new Gato("Tris", "hembra", 2.0f, "Carey", 4.0f, "Curiosa, serena y elegante", "Tricolor, ojos verdes", false);
		Gato gato4 = new Gato("Luna", "hembra", 2.5f, "Atigrado/Carey", 4.1f, "Activa, curiosa y cariñosa", "Tricolor, ojos amarillos", false);
		Gato gato5 = new Gato("Copito", "macho", 4.0f, "Persa bicolor", 5.2f, "Sociable, tranquilo y dulce", "Blanco con manchas naranjas, ojos claros", false);
		Gato gato6 = new Gato("Maya", "hembra", 3.0f, "Siamés tricolor", 3.7f, "Inteligente, tranquila y sociable", "Claro con manchas oscuras, ojos amarillos", false);
		Gato gato7 = new Gato("Tigre", "macho", 2.8f, "Bengala", 4.4f, "Enérgico, juguetón y curioso", "Marrón con rayas oscuras, ojos dorados", false);
		Gato gato8 = new Gato("Olaf", "macho", 2.0f, "Europeo", 4.0f, "Juguetón, amistoso y curioso", "Atigrado marrón y blanco, ojos azul y ámbar", false);
		Gato gato9 = new Gato("Nube", "hembra", 2.5f, "Europeo", 3.8f, "Tímida, inteligente y curiosa", "Atigrado marrón, ojos azules", false);
		Gato gato10 = new Gato("Leo", "macho", 4.5f, "Siberiano", 5.3f, "Tranquilo, independiente y elegante", "Crema y gris, ojos verdes", false);
		Gato gato11 = new Gato("Shadow", "macho", 3.2f, "Bombay", 4.3f, "Observador, tranquilo y leal", "Negro, ojos amarillos", false);
		Gato gato12 = new Gato("Simba", "macho", 3.5f, "Europeo", 4.6f, "Sociable, juguetón y amigable", "Naranja atigrado, ojos verdes", false);


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
		
		VentanaGatos ventanaGatos = new VentanaGatos(gatos);
		System.out.println(ventanaGatos);

	}

}
