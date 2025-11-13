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

import Domain.Gato;
import Domain.Usuario;


public class VentanaPrincipal extends JFrame {
	private Gato[] gatos;
	private Usuario user;
	private static final long serialVersionUID = 1L;
	
	public VentanaPrincipal(Gato[] gatos, Usuario user) {
		this.setTitle("ventana principal - perrera");
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		JPanel frame = new JPanel(new BorderLayout());
		frame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // top, left, bottom, right
		frame.setBackground(new Color(255, 182, 193));
		// panel animales
		JPanel panelAnimales = new JPanel(new GridLayout(2, 2, 20, 10));
		panelAnimales.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // top, left, bottom, right
		panelAnimales.setBackground(new Color(255, 182, 193));
		
		// ----------------------------------------- panel perros ----------------------------------------- 
		JPanel panelPerros = new JPanel();
		panelPerros.setBorder(BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(Color.BLACK), "Perros", TitledBorder.LEFT, TitledBorder.TOP
		));
		panelPerros.setLayout(new BoxLayout(panelPerros, BoxLayout.Y_AXIS));
		panelPerros.setBackground(new Color(204, 236, 247));

		JLabel labelPerro = crearImagen("imagenes/fotosVentanaPrincipal/perritoMenu.png.jpeg", 175, 175);
		JButton botonPerros = new JButton("Adoptar");
		botonPerros.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelPerros.add(labelPerro);
		panelPerros.add(botonPerros);
		panelAnimales.add(panelPerros);

		// ----------------------------------------- panel gatos ----------------------------------------- 
		
		JPanel panelGatos = new JPanel();
		panelGatos.setBorder(BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(Color.BLACK), "Gatos", TitledBorder.LEFT, TitledBorder.TOP
		));
		panelGatos.setLayout(new BoxLayout(panelGatos, BoxLayout.Y_AXIS));
		panelGatos.setBackground(new Color(204, 236, 247));


		JLabel labelGato = crearImagen("imagenes/fotosVentanaPrincipal/gatitoMenu.png.jpeg", 175, 175);
		JButton botonGatos = new JButton("Adoptar");
		botonGatos.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		botonGatos.addActionListener(e -> {
			VentanaGatos ventanaGatos = new VentanaGatos(this,gatos);
			ventanaGatos.setVisible(false);
			SwingUtilities.invokeLater(() -> ventanaGatos.setVisible(true));
			this.setVisible(false);
		});

		panelGatos.add(labelGato);
		panelGatos.add(botonGatos);
		panelAnimales.add(panelGatos);

		// ----------------------------------------- panel pajaros ----------------------------------------- 
		
		JPanel panelPajaros = new JPanel();
		panelPajaros.setBorder(BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(Color.BLACK), "Pajaros", TitledBorder.LEFT, TitledBorder.TOP
		));
		panelPajaros.setLayout(new BoxLayout(panelPajaros, BoxLayout.Y_AXIS));
		panelPajaros.setBackground(new Color(204, 236, 247));


		JLabel labelPajaro = crearImagen("imagenes/fotosVentanaPrincipal/loritoMenu.png.jpeg", 175, 175);
		JButton botonPajaros = new JButton("Adoptar");
		botonPajaros.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelPajaros.add(labelPajaro);
		panelPajaros.add(botonPajaros);
		panelAnimales.add(panelPajaros);

		// ----------------------------------------- panel roedores ----------------------------------------- 
		
		JPanel panelRoedores = new JPanel();
		panelRoedores.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK), "Roedores", TitledBorder.LEFT, TitledBorder.TOP
		));
		panelRoedores.setLayout(new BoxLayout(panelRoedores, BoxLayout.Y_AXIS));
		panelRoedores.setBackground(new Color(204, 236, 247));


		JLabel labelRoedor = crearImagen("imagenes/fotosVentanaPrincipal/hamstercitoMenu.png.jpeg", 175, 175);
		JButton botonRoedor = new JButton("Adoptar");
		botonRoedor.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelRoedores.add(labelRoedor);
		panelRoedores.add(botonRoedor);
		panelAnimales.add(panelRoedores);

		frame.add(panelAnimales, BorderLayout.CENTER);
		
		// -------------------------------------- panel menu / botones a otros -----------------------------------
		JPanel panelMenu = new JPanel();
		panelMenu.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK),
				"Menu", 
				TitledBorder.CENTER, 
				TitledBorder.TOP)
		);
		
		panelMenu.setBackground(new Color(204, 236, 247)); 
		panelMenu.setLayout(new FlowLayout()); // para que la barra se ajuste bien
		
		// botones
		JButton animales = new JButton("animales");
		JButton tienda = new JButton("tienda");
		JButton perfil = new JButton("perfil");
		JButton noticias = new JButton("ver noticias");
		
		
		// action listener para ir a la ventana de perfil
		perfil.addActionListener(e -> {
			
			VentanaPerfil ventanaPerfil = new VentanaPerfil(this, user);
			ventanaPerfil.setVisible(true);
			this.setVisible(false);
		});
		

		tienda.addActionListener(e -> {
			VentanaTienda ventanaTienda = new VentanaTienda(this, user);
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

