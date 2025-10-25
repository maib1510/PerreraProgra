package main;

import Domain.Gato;
import gui.VentanaGatos;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//-------------------GATOS------------------
		Gato[] gatos = new Gato[12];
		
		Gato gato1 = new Gato("Misu", "hembra", 1.5f, "Bengala", 4.2f, "Curiosa, atenta y cariñosa", "Pelaje corto, atigrado, manchas tipo leopardo, ojos grandes verdes", false);
		Gato gato2 = new Gato("Nieve", "hembra", 3.0f, "Europeo", 4.5f, "Tranquila y observadora", "Pelaje blanco, ojos verdes, tamaño mediano", false);
		Gato gato3 = new Gato("Tris", "hembra", 2.0f, "Carey", 4.0f, "Curiosa, serena y elegante", "Pelaje tricolor con manchas naranjas, negras y blancas; ojos grandes verdes; orejas puntiagudas", false);
		Gato gato4 = new Gato("Luna", "hembra", 2.5f, "Atigrado/Carey", 4.1f, "Activa, curiosa y cariñosa", "Pelaje tricolor con mezcla de naranja, negro y crema; ojos amarillos grandes", false);
		Gato gato5 = new Gato("Copito", "macho", 4.0f, "Persa bicolor", 5.2f, "Sociable, tranquilo y dulce", "Pelaje largo blanco con manchas naranjas, ojos claros redondos, cuerpo robusto", false);
		Gato gato6 = new Gato("Maya", "hembra", 3.0f, "Siamés tricolor", 3.7f, "Inteligente, tranquila y sociable", "Pelaje corto claro con manchas oscuras en cara, patas y orejas; ojos amarillos; rostro mitad oscuro", false);
		Gato gato7 = new Gato("Tigre", "macho", 2.8f, "Bengala", 4.4f, "Enérgico, juguetón y curioso", "Pelaje largo marrón con rayas oscuras y patrón tipo tigre, ojos marrón dorado", false);
		Gato gato8 = new Gato("Olaf", "macho", 2.0f, "Europeo", 4.0f, "Juguetón, amistoso y curioso", "Pelaje atigrado marrón con blanco, ojos de diferente color (uno azul y uno ámbar), patas blancas", false);
		Gato gato9 = new Gato("Nube", "hembra", 2.5f, "Europeo", 3.8f, "Tímida, inteligente y curiosa", "Pelaje corto atigrado marrón con tonos anaranjados, ojos azules", false);
		Gato gato10 = new Gato("Leo", "macho", 4.5f, "Siberiano", 5.3f, "Tranquilo, independiente y elegante", "Pelaje largo color crema y gris, ojos verdes, tamaño grande", false);
		Gato gato11 = new Gato("Shadow", "macho", 3.2f, "Bombay", 4.3f, "Observador, tranquilo y leal", "Pelaje corto negro con reflejos marrón, ojos amarillos intensos", false);
		Gato gato12 = new Gato("Simba", "macho", 3.5f, "Europeo", 4.6f, "Sociable, juguetón y amigable", "Pelaje corto naranja atigrado, ojos verdes, cuerpo atlético", false);

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
