package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
import Recursividad.Recursividad;


public class VentanaPrincipal extends JFrame {
	private Gato[] gatos;
	private Roedor[] roedores;
	private Pajaro[] pajaros;
	private Perro[] perros;
	private Usuario user;
	private GestorBD gestor;
	private Adopcion[] adopciones;
	private Producto[] productos;

	private Recursividad rec;
	
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

        this.rec = new Recursividad(gatos, roedores, pajaros, perros,
                                                  adopciones, productos, gestor);


        
        
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

	public void mostrarHerramientasRecursivasAnimales() {
	    if (rec.noHayAnimalesGenerales()) {
	        JOptionPane.showMessageDialog(this, "No hay animales cargados.");
	        return;
	    }

	    JDialog dialog = new JDialog(this, "Búsqueda", true);
	    dialog.setLayout(new BorderLayout());

	    JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
	    panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

	    String[] opciones = {
	        "Buscar animal por nombre",
	        "¿Todos disponibles?",
	        "¿Todos adoptados?",
	        "Animal más viejo",
	        "Animal más joven",
	        "Contar adopciones por año",
	        "Gasto total en la tienda",
	        "Buscar producto por nombre"
	    };

	    for (int i = 0; i < opciones.length; i++) {
	        final int op = i;
	        JButton boton = new JButton(opciones[i]);
	        boton.addActionListener(e -> {
	            dialog.dispose();
	            ejecutarOpcionRecursiva(op);
	        });
	        panel.add(boton);
	    }

	    dialog.add(new JLabel("Opciones de búsqueda entre los animales de la perrera:"), BorderLayout.NORTH);
	    dialog.add(panel, BorderLayout.CENTER);

	    dialog.pack();
	    dialog.setLocationRelativeTo(this);
	    dialog.setVisible(true);
	}

	private void ejecutarOpcionRecursiva(int op) {
	    switch (op) {

	        case 0 -> {
	            String nombre = JOptionPane.showInputDialog(this, "Introduce el nombre del animal:");
	            if (nombre == null) return;
	            Animal a = rec.buscarAnimalPorNombreRec(nombre);
	            JOptionPane.showMessageDialog(this,
	                a == null
	                    ? "No se encontró ningún animal con nombre \"" + nombre + "\""
	                    : formatearAnimalGeneral(a));
	        }

	        case 1 -> {
	            boolean ok = rec.todosDisponiblesRec();
	            JOptionPane.showMessageDialog(this,
	                ok
	                    ? "Sí: algunos están disponibles (NO adoptados)."
	                    : "Sí: todos están disponibles.");
	        }

	        case 2 -> {
	            boolean ok = rec.todosAdoptadosRec();
	            JOptionPane.showMessageDialog(this,
	                ok
	                    ? "Sí: todos están adoptados."
	                    : "No: hay alguno NO adoptado.");
	        }

	        case 3 -> {
	            Animal a = rec.animalMasViejoRec();
	            JOptionPane.showMessageDialog(this,
	                a == null
	                    ? "No hay animales."
	                    : "Animal más viejo:\n\n" + formatearAnimalGeneral(a));
	        }

	        case 4 -> {
	            Animal a = rec.animalMasJovenRec();
	            JOptionPane.showMessageDialog(this,
	                a == null
	                    ? "No hay animales."
	                    : "Animal más joven:\n\n" + formatearAnimalGeneral(a));
	        }

	        case 5 -> {
	            int anio = Integer.parseInt(JOptionPane.showInputDialog(this, "Introduce el año:"));
	            int total = rec.contarAdopcionesPorAno(anio);
	            JOptionPane.showMessageDialog(this,
	                "Adopciones en " + anio + ": " + total);
	        }

	        case 6 -> {
	            double total = rec.gastoTotal();
	            JOptionPane.showMessageDialog(this,
	                "Gasto total: " + total + " €");
	        }

	        case 7 -> {
	            String nombre = JOptionPane.showInputDialog(this, "Nombre del producto:");
	            if (nombre == null) return;
	            Producto p = rec.buscarProductoPorNombre(nombre);
	            JOptionPane.showMessageDialog(this,
	                p == null ? "No encontrado" : p.toString());
	        }
	    }
	}

	private String formatearAnimalGeneral(Animal a) {
	    boolean adoptado = rec.estaAdoptadoGeneral(a);
	    return "ID: " + a.getId_animal()
	            + "\nNombre: " + a.getNombre()
	            + "\nTipo: " + a.getTipoAnimal()
	            + "\nRaza: " + a.getRaza()
	            + "\nEdad: " + a.getEdad()
	            + "\nAdoptado: " + (adoptado ? "Sí" : "No");
	}





}

