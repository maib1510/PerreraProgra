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
import Domain.Gato;
import Domain.Pajaro;
import Domain.Perro;
import Domain.Roedor;
import Domain.Usuario;


public class VentanaPrincipal extends JFrame {
	private Gato[] gatos;
	private Roedor[] roedores;
	private Pajaro[] pajaros;
	private Perro[] perros;
	private Usuario user;
	private GestorBD gestor;
	
	private static final long serialVersionUID = 1L;
	
	public VentanaPrincipal(Gato[] gatos, Roedor[] roedores, Pajaro[] pajaros, Perro[] perros, Usuario user, GestorBD gestor) {
		
		this.gatos = gatos;
        this.roedores = roedores;
        this.pajaros= pajaros;
        this.perros=perros;
        this.user = user;
        this.gestor = gestor;
        
        
		this.setTitle("ventana principal - perrera");
		this.setSize(500, 600);
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
		            VentanaPerros ventanaPerros = new VentanaPerros(this, perros);
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
		            VentanaGatos ventanaGatos = new VentanaGatos(this, gatos);
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
		            VentanaPajaros ventanaPajaros = new VentanaPajaros(this, pajaros);
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
		            VentanaRoedores ventanaRoedores = new VentanaRoedores(this, roedores);
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
		panelMenu.setLayout(new FlowLayout()); // para que la barra se ajuste bien
		
		// botones
		JButton animales = new JButton("animales");
		JButton tienda = new JButton("tienda");
		JButton perfil = new JButton("perfil");
		JButton noticias = new JButton("ver noticias");
		
		animales.setFont(fuenteTitulos);
		tienda.setFont(fuenteTitulos);
		perfil.setFont(fuenteTitulos);
		noticias.setFont(fuenteTitulos);
		
		animales.setForeground(new Color(80, 55, 30));
		tienda.setForeground(new Color(80, 55, 30));
		perfil.setForeground(new Color(80, 55, 30));
		noticias.setForeground(new Color(80, 55, 30));
		
		// action listener para ir a la ventana de perfil
		perfil.addActionListener(e -> {
			
			VentanaPerfil ventanaPerfil = new VentanaPerfil(this, user, gestor);
			ventanaPerfil.setVisible(true);
			this.setVisible(false);
		});
		

		tienda.addActionListener(e -> {
			VentanaTienda ventanaTienda = new VentanaTienda(this, user, gestor);
			ventanaTienda.setVisible(true);
			this.setVisible(false);
		});
		
		noticias.addActionListener(e -> {
			NewsTicker ventanaNoticias = new NewsTicker(this);
			ventanaNoticias.setVisible(true);
			this.setVisible(false);
		});
		
		panelMenu.add(perfil);
		panelMenu.add(animales);
		panelMenu.add(tienda);
		panelMenu.add(noticias);
		
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

}

