package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import DB.GestorBD;
import Domain.Adopcion;
import Domain.Animal;
import Domain.Gato;
import Domain.Pajaro;
import Domain.Perro;
import Domain.Producto;
import Domain.Roedor;
import Domain.Usuario;


public class VentanaPrincipal extends JFrame {
	private Gato[] gatos;
	private Roedor[] roedores;
	private Pajaro[] pajaros;
	private Perro[] perros;
	private Usuario user;
	private GestorBD gestor;
	private Adopcion[] adopciones;
	private Producto[] productos;

	
	private static final long serialVersionUID = 1L;
	
	public VentanaPrincipal(Gato[] gatos, Roedor[] roedores, Pajaro[] pajaros, Perro[] perros, Usuario user, GestorBD gestor) {
		
		this.gatos = gatos;
        this.roedores = roedores;
        this.pajaros= pajaros;
        this.perros=perros;
        this.user = user;
        this.gestor = gestor;
        // Para las funciones de recursividad:
        this.adopciones = gestor.cargarAdopciones().toArray(new Adopcion[0]);
        this.productos = gestor.cargarProductos().toArray(new Producto[0]);

        
        
		this.setTitle("ventana principal - perrera");
		this.setSize(680, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Font fuenteTitulos = new Font("Arial", Font.BOLD, 14);
		
		JPanel frame = new JPanel(new BorderLayout());
		frame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // top, left, bottom, right
		frame.setBackground(new Color(80, 55, 30));
		// panel animales
		JPanel panelAnimales = new JPanel(new GridLayout(2, 2, 20, 10));
		panelAnimales.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // top, left, bottom, right
		panelAnimales.setBackground(new Color(80, 55, 30));
		
		// ----------------------------------------- panel perros ----------------------------------------- 
		JPanel panelPerros = new JPanel();
		TitledBorder bordePerros = BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(new Color(80, 55, 30),2), "PERROS", TitledBorder.CENTER, TitledBorder.TOP);
		
		bordePerros.setTitleFont(fuenteTitulos);
		bordePerros.setTitleColor(new Color(80, 55, 30));
		panelPerros.setBorder(bordePerros);
		
		panelPerros.setLayout(new BoxLayout(panelPerros, BoxLayout.Y_AXIS));
		panelPerros.setBackground(new Color(200, 180, 155));

		JLabel labelPerro = crearImagen("imagenes/fotosVentanaPrincipal/perritoMenu.png.jpeg", 175, 175);
		JButton botonPerros = new JButton("Adoptar");
		botonPerros.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		botonPerros.setFont(fuenteTitulos);
		botonPerros.setForeground(new Color(80, 55, 30));
		
		botonPerros.addActionListener(e -> {
		    this.setVisible(false);
		    HiloCargar carga = new HiloCargar(
		        this,
		        () -> SwingUtilities.invokeLater(() -> {
		            VentanaPerros ventanaPerros = new VentanaPerros(this, perros, user, gestor);
		            ventanaPerros.setVisible(true);
		        })
		    );
		    new Thread(carga).start();
		});


		panelPerros.add(labelPerro);
		panelPerros.add(botonPerros);
		panelAnimales.add(panelPerros);

		// ----------------------------------------- panel gatos ----------------------------------------- 
		
		JPanel panelGatos = new JPanel();
		
		TitledBorder bordeGatos = BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(new Color(80, 55, 30),2), "GATOS", TitledBorder.CENTER, TitledBorder.TOP);
		bordeGatos.setTitleFont(fuenteTitulos);
		bordeGatos.setTitleColor(new Color(80, 55, 30));
		panelGatos.setBorder(bordeGatos);
		
		panelGatos.setLayout(new BoxLayout(panelGatos, BoxLayout.Y_AXIS));
		panelGatos.setBackground(new Color(200, 180, 155));


		JLabel labelGato = crearImagen("imagenes/fotosVentanaPrincipal/gatitoMenu.png.jpeg", 175, 175);
		JButton botonGatos = new JButton("Adoptar");
		botonGatos.setAlignmentX(Component.CENTER_ALIGNMENT);

		botonGatos.setFont(fuenteTitulos);
		botonGatos.setForeground(new Color(80, 55, 30));
		
		botonGatos.addActionListener(e -> {
			this.setVisible(false);
		    HiloCargar carga = new HiloCargar(
		        this,
		        () -> SwingUtilities.invokeLater(() -> {
		            VentanaGatos ventanaGatos = new VentanaGatos(this, gatos, user, gestor);
		            ventanaGatos.setVisible(true);
		        })
		    );
		    new Thread(carga).start();
		});

		panelGatos.add(labelGato);
		panelGatos.add(botonGatos);
		panelAnimales.add(panelGatos);

		// ----------------------------------------- panel pajaros ----------------------------------------- 
		
		JPanel panelPajaros = new JPanel();
		
		TitledBorder bordePajaros = BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(new Color(80, 55, 30),2), "PÁJAROS", TitledBorder.CENTER, TitledBorder.TOP);
		bordePajaros.setTitleFont(fuenteTitulos);
		bordePajaros.setTitleColor(new Color(80, 55, 30));
		panelPajaros.setBorder(bordePajaros);
		
		panelPajaros.setLayout(new BoxLayout(panelPajaros, BoxLayout.Y_AXIS));
		panelPajaros.setBackground(new Color(200, 180, 155));


		JLabel labelPajaro = crearImagen("imagenes/fotosVentanaPrincipal/loritoMenu.png.jpeg", 175, 175);
		JButton botonPajaros = new JButton("Adoptar");
		botonPajaros.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		botonPajaros.setFont(fuenteTitulos);
		botonPajaros.setForeground(new Color(80, 55, 30));
		
		botonPajaros.addActionListener(e -> {
			this.setVisible(false);
		    HiloCargar carga = new HiloCargar(
		        this,
		        () -> SwingUtilities.invokeLater(() -> {
		            VentanaPajaros ventanaPajaros = new VentanaPajaros(this, pajaros, user, gestor);
		            ventanaPajaros.setVisible(true);
		        })
		    );
		    new Thread(carga).start();
		});

		panelPajaros.add(labelPajaro);
		panelPajaros.add(botonPajaros);
		panelAnimales.add(panelPajaros);
		
		// ----------------------------------------- panel roedores ----------------------------------------- 
		
		JPanel panelRoedores = new JPanel();
		
		TitledBorder bordeRoedores = BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(new Color(80, 55, 30),2), "ROEDORES", TitledBorder.CENTER, TitledBorder.TOP);
		bordeRoedores.setTitleFont(fuenteTitulos);
		bordeRoedores.setTitleColor(new Color(80, 55, 30));
		panelRoedores.setBorder(bordeRoedores);
		
		panelRoedores.setLayout(new BoxLayout(panelRoedores, BoxLayout.Y_AXIS));
		panelRoedores.setBackground(new Color(200, 180, 155));


		JLabel labelRoedor = crearImagen("imagenes/fotosVentanaPrincipal/hamstercitoMenu.png.jpeg", 175, 175);
		JButton botonRoedor = new JButton("Adoptar");
		botonRoedor.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		botonRoedor.setFont(fuenteTitulos);
		botonRoedor.setForeground(new Color(80, 55, 30));
		
		botonRoedor.addActionListener(e -> {
			this.setVisible(false);
		    HiloCargar carga = new HiloCargar(
		        this,
		        () -> SwingUtilities.invokeLater(() -> {
		            VentanaRoedores ventanaRoedores = new VentanaRoedores(this, roedores, user, gestor);
		            ventanaRoedores.setVisible(true);
		        })
		    );
		    new Thread(carga).start();
        });

		panelRoedores.add(labelRoedor);
		panelRoedores.add(botonRoedor);
		panelAnimales.add(panelRoedores);

		frame.add(panelAnimales, BorderLayout.CENTER);
		
		// -------------------------------------- panel menu / botones a otros -----------------------------------
		JPanel panelMenu = new JPanel();
		
		TitledBorder bordeMenu = BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(new Color(80, 55, 30),2), "MENÚ", TitledBorder.CENTER, TitledBorder.TOP);
		bordeMenu.setTitleFont(fuenteTitulos);
		bordeMenu.setTitleColor(new Color(80, 55, 30));
		panelMenu.setBorder(bordeMenu);
		
		panelMenu.setBackground(new Color(200, 180, 155)); 
		panelMenu.setLayout(new GridLayout(1, 5, 10, 0)); // para que la barra se ajuste bien
		
		// botones
		JButton animales = new JButton("Animales");
		JButton tienda = new JButton("Tienda");
		JButton perfil = new JButton("Perfil");
		JButton noticias = new JButton("Ver noticias");
		JButton recursividad = new JButton("Buscar");
		
		animales.setFont(fuenteTitulos);
		tienda.setFont(fuenteTitulos);
		perfil.setFont(fuenteTitulos);
		noticias.setFont(fuenteTitulos);
		recursividad.setFont(fuenteTitulos);
		

		
		animales.setForeground(new Color(80, 55, 30));
		tienda.setForeground(new Color(80, 55, 30));
		perfil.setForeground(new Color(80, 55, 30));
		noticias.setForeground(new Color(80, 55, 30));
		recursividad.setForeground(new Color(80, 55, 30));
		
		
		// action listener para ir a la ventana de perfil
		perfil.addActionListener(e -> {
			
			VentanaPerfil ventanaPerfil = new VentanaPerfil(this, gatos, roedores, pajaros, perros, user, gestor);
			ventanaPerfil.setVisible(true);
			this.setVisible(false);
		});
		

		tienda.addActionListener(e -> {
			VentanaTienda ventanaTienda = new VentanaTienda(this, gatos, roedores, pajaros, perros, user, gestor);
			ventanaTienda.setVisible(true);
			this.setVisible(false);
		});
		
		noticias.addActionListener(e -> {
			NewsTicker ventanaNoticias = new NewsTicker(this);
			ventanaNoticias.setVisible(true);
			this.setVisible(false);
		});
		
		recursividad.addActionListener(e -> mostrarHerramientasRecursivasAnimales());

		
		panelMenu.add(perfil);
		panelMenu.add(animales);
		panelMenu.add(tienda);
		panelMenu.add(noticias);
		panelMenu.add(recursividad);
		
		frame.add(panelMenu, BorderLayout.NORTH);
		this.add(frame);
		
		
		this.setVisible(false);
	}
	
	private JLabel crearImagen(String ruta, int ancho, int alto) {
	    ImageIcon iconoOriginal = new ImageIcon(ruta);
	    Image img = iconoOriginal.getImage();
	    Image imgEscalada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
	    ImageIcon icono = new ImageIcon(imgEscalada);
	    JLabel label = new JLabel(icono);
	    label.setAlignmentX(Component.CENTER_ALIGNMENT); // para BoxLayout vertical
	    label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	    return label;
	}


	// RECUSIVIDAD  --------------------------------------------------------------------------------

	private void mostrarHerramientasRecursivasAnimales() {
	    if (noHayAnimalesGenerales()) {
	        JOptionPane.showMessageDialog(this, "No hay animales cargados.");
	        return;
	    }

	    String[] opciones = {
	            "Buscar animal por nombre",
	            "¿Todos disponibles?",
	            "¿Todos adoptados?",
	            "Animal más viejo",
	            "Animal más joven",
	            // más opciones: ----------
	            "Contar adopciones por año",
	            "Gasto total en la tienda",
	            "Buscar producto por nombre"
	    };

	    int op = JOptionPane.showOptionDialog(
	            this,
	            "Opciones de busqueda entre los animales de la perrera:",
	            "Busqueda",
	            JOptionPane.DEFAULT_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            opciones,
	            opciones[0]
	    );

	    if (op == -1) return;

	    switch (op) {
	      
	        case 0: { // Buscar por nombre
	            String nombre = JOptionPane.showInputDialog(this, "Introduce el nombre del animal:");
	            if (nombre == null) return;
	            Animal a = buscarAnimalPorNombreRec(nombre);
	            JOptionPane.showMessageDialog(this,
	                    (a == null) ? "No se encontró ningún animal con nombre \"" + nombre + "\"" : formatearAnimalGeneral(a));
	            break;
	        }
	        case 1: { // Todos disponibles
	            boolean ok = todosDisponiblesRec();
	            JOptionPane.showMessageDialog(this,
	                    ok ? "Sí: algunos están disponibles (NO adoptados)." : "Si: todos estan disponibles.");
	            break;
	        }
	        case 2: { // Todos adoptados
	            boolean ok = todosAdoptadosRec();
	            JOptionPane.showMessageDialog(this,
	                    ok ? "Sí: todos están adoptados." : "No: hay alguno NO adoptado.");
	            break;
	        }
	        case 3: { // Más viejo
	            Animal a = animalMasViejoRec();
	            JOptionPane.showMessageDialog(this,
	                    (a == null) ? "No hay animales." : "Animal más viejo:\n\n" + formatearAnimalGeneral(a));
	            break;
	        }
	        case 4: { // Más joven
	            Animal a = animalMasJovenRec();
	            JOptionPane.showMessageDialog(this,
	                    (a == null) ? "No hay animales." : "Animal más joven:\n\n" + formatearAnimalGeneral(a));
	            break;
	        }
	        case 5: {
	            int anio = Integer.parseInt(JOptionPane.showInputDialog("Introduce el año:"));
	            int total = contarAdopcionesPorAno(anio);
	            JOptionPane.showMessageDialog(this, "Adopciones en " + anio + ": " + total);
	            break;
	        }
	        case 6: {
	            double total = gastoTotal();
	            JOptionPane.showMessageDialog(this, "Gasto total: " + total + " €");
	            break;
	        }
	        case 7: {
	            String nombre = JOptionPane.showInputDialog("Nombre del producto:");
	            Producto p = buscarProductoPorNombre(nombre);
	            JOptionPane.showMessageDialog(this, p == null ? "No encontrado" : p.toString());
	            break;
	        }

	        default:
	            break;
	    }
	}

	private String formatearAnimalGeneral(Animal a) {
	    boolean adoptado = estaAdoptadoGeneral(a);
	    return "ID: " + a.getId_animal()
	            + "\nNombre: " + a.getNombre()
	            + "\nTipo: " + a.getTipoAnimal()
	            + "\nRaza: " + a.getRaza()
	            + "\nEdad: " + a.getEdad()
	            + "\nAdoptado: " + (adoptado ? "Sí" : "No");
	}

	private boolean noHayAnimalesGenerales() {
	    return (gatos == null || gatos.length == 0)
	            && (roedores == null || roedores.length == 0)
	            && (pajaros == null || pajaros.length == 0)
	            && (perros == null || perros.length == 0);
	}

	// ---------- helpers de estado adopción ----------
	private boolean estaAdoptadoGeneral(Animal a) {
	    if (a == null) return false;
	    // Si tienes BD (como en tus ventanas de tipos), usamos la BD para el estado real:
	    if (gestor != null) {
	        return gestor.estaAdoptadoPorId(a.getId_animal());
	    }
	    // fallback si no hay gestor:
	    return a.isAdoptado();
	}

	

	// ---------- 1) BUSCAR POR NOMBRE ----------
	private Animal buscarAnimalPorNombreRec(String nombre) {
	    if (nombre == null) return null;
	    String obj = nombre.trim();
	    if (obj.isEmpty()) return null;

	    Animal[][] grupos = new Animal[][] { gatos, roedores, pajaros, perros };
	    return buscarPorNombreEnGruposRec(grupos, 0, 0, obj);
	}

	private Animal buscarPorNombreEnGruposRec(Animal[][] grupos, int gIdx, int idx, String nombre) {
	    if (grupos == null || gIdx >= grupos.length) return null;
	    Animal[] grupo = grupos[gIdx];

	    if (grupo == null || idx >= grupo.length) {
	        return buscarPorNombreEnGruposRec(grupos, gIdx + 1, 0, nombre);
	    }

	    Animal actual = grupo[idx];
	    if (actual != null && actual.getNombre() != null && actual.getNombre().equalsIgnoreCase(nombre)) return actual;

	    return buscarPorNombreEnGruposRec(grupos, gIdx, idx + 1, nombre);
	}

	// ---------- 2) TODOS CUMPLEN CONDICIÓN ----------
	private boolean todosDisponiblesRec() {
	    Animal[][] grupos = new Animal[][] { gatos, roedores, pajaros, perros };
	    return todosDisponiblesEnGruposRec(grupos, 0, 0);
	}

	private boolean todosDisponiblesEnGruposRec(Animal[][] grupos, int gIdx, int idx) {
	    if (grupos == null || gIdx >= grupos.length) return true;
	    Animal[] grupo = grupos[gIdx];

	    if (grupo == null || idx >= grupo.length) {
	        return todosDisponiblesEnGruposRec(grupos, gIdx + 1, 0);
	    }

	    Animal actual = grupo[idx];
	    boolean cumple = (actual == null) ? true : !estaAdoptadoGeneral(actual);
	    return cumple && todosDisponiblesEnGruposRec(grupos, gIdx, idx + 1);
	}

	private boolean todosAdoptadosRec() {
	    Animal[][] grupos = new Animal[][] { gatos, roedores, pajaros, perros };
	    return todosAdoptadosEnGruposRec(grupos, 0, 0);
	}

	private boolean todosAdoptadosEnGruposRec(Animal[][] grupos, int gIdx, int idx) {
	    if (grupos == null || gIdx >= grupos.length) return true;
	    Animal[] grupo = grupos[gIdx];

	    if (grupo == null || idx >= grupo.length) {
	        return todosAdoptadosEnGruposRec(grupos, gIdx + 1, 0);
	    }

	    Animal actual = grupo[idx];
	    boolean cumple = (actual == null) ? true : estaAdoptadoGeneral(actual);
	    return cumple && todosAdoptadosEnGruposRec(grupos, gIdx, idx + 1);
	}

	// ---------- 3) MÁS VIEJO / MÁS JOVEN ---------
	private Animal animalMasViejoRec() {
	    Animal[][] grupos = new Animal[][] { gatos, roedores, pajaros, perros };
	    return masViejoEnGruposRec(grupos, 0, 0, null);
	}

	private Animal masViejoEnGruposRec(Animal[][] grupos, int gIdx, int idx, Animal mejor) {
	    if (grupos == null || gIdx >= grupos.length) return mejor; // base global
	    Animal[] grupo = grupos[gIdx];

	    if (grupo == null || idx >= grupo.length) {
	        return masViejoEnGruposRec(grupos, gIdx + 1, 0, mejor);
	    }

	    Animal actual = grupo[idx];
	    if (actual != null) {
	        if (mejor == null || actual.getEdad() > mejor.getEdad()) {
	            mejor = actual;
	        }
	    }
	    return masViejoEnGruposRec(grupos, gIdx, idx + 1, mejor);
	}

	private Animal animalMasJovenRec() {
	    Animal[][] grupos = new Animal[][] { gatos, roedores, pajaros, perros };
	    return masJovenEnGruposRec(grupos, 0, 0, null);
	}

	private Animal masJovenEnGruposRec(Animal[][] grupos, int gIdx, int idx, Animal mejor) {
	    if (grupos == null || gIdx >= grupos.length) return mejor;
	    Animal[] grupo = grupos[gIdx];

	    if (grupo == null || idx >= grupo.length) {
	        return masJovenEnGruposRec(grupos, gIdx + 1, 0, mejor);
	    }

	    Animal actual = grupo[idx];
	    if (actual != null) {
	        if (mejor == null || actual.getEdad() < mejor.getEdad()) {
	            mejor = actual;
	        }
	    }
	    return masJovenEnGruposRec(grupos, gIdx, idx + 1, mejor);
	}
	
	
	// Más opciones: ---------------------------------------------------------
	
	// CONTAR ADOPCIONES POR AÑO DE ADOPCIÓN:
	private int contarAdopcionesPorAno(int ano) {
	    return contarAdopcionesPorAnoRec(adopciones, 0, ano);
	}

	private int contarAdopcionesPorAnoRec(Adopcion[] lista_adopciones, int i, int ano) {
	    if (lista_adopciones == null || i >= lista_adopciones.length) {
	        return 0;
	    }

	    int suma = 0;
	    Adopcion adopcion = lista_adopciones[i];
	    if (adopcion != null && adopcion.getFecha_adopcion() != null) {
	        if (adopcion.getFecha_adopcion().getYear() == ano) {
	            suma = 1;
	        }
	    }

	    return suma + contarAdopcionesPorAnoRec(lista_adopciones, i + 1, ano);
	}

	
	// GASTO TOTAL EN LA TIENDA:
	private double gastoTotal() {
	    return sumaPreciosRec(productos, 0);
	}

	private double sumaPreciosRec(Producto[] lista, int i) {
	    // caso base: si llegamos al final del array
	    if (lista == null || i >= lista.length) {
	        return 0.0;
	    }

	    double precio;
	    if (lista[i] != null) {
	        precio = lista[i].getPrecio();
	    } else {
	        precio = 0.0;
	    }

	    // suma del precio actual + suma recursiva del resto
	    return precio + sumaPreciosRec(lista, i + 1);
	}

	
	//BUSCAR PRODUCTO POR NOMBRE:
	private Producto buscarProductoPorNombre(String nombre) {
	    return buscarProductoPorNombreRec(productos, 0, nombre);
	}

	private Producto buscarProductoPorNombreRec(Producto[] lista_productos, int i, String nombre) {
	    if (lista_productos == null || i >= lista_productos.length) { // caso base
	    	return null;
	    }

	    if (lista_productos[i] != null && lista_productos[i].getNombre().equals(nombre)) {
	        return lista_productos[i];
	    }

	    return buscarProductoPorNombreRec(lista_productos, i + 1, nombre);
	}


	
	



}

