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

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	VentanaPrincipal() {
		this.setTitle("ventana principal - perrera");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// panel animales
		JPanel panelAnimales = new JPanel(new GridLayout(2,2));
		
		// ----------------------------------------- panel perros ----------------------------------------- 
		JPanel panelPerros = new JPanel();
		panelPerros.setBorder(BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(Color.BLACK), "Perros", TitledBorder.LEFT, TitledBorder.TOP
		));
		panelPerros.setLayout(new BoxLayout(panelPerros, BoxLayout.Y_AXIS));

		JLabel labelPerro = crearImagen("/Users/maialenbarredomuro/Desktop/PerreraProgra_/imagenes/perrito.jpg", 150, 200);
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

		JLabel labelGato = crearImagen("/Users/maialenbarredomuro/Desktop/PerreraProgra_/imagenes/gatito.jpg", 150, 200);
		JButton botonGatos = new JButton("Adoptar");
		botonGatos.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelGatos.add(labelGato);
		panelGatos.add(botonGatos);
		panelAnimales.add(panelGatos);

		// ----------------------------------------- panel pajaros ----------------------------------------- 
		
		JPanel panelPajaros = new JPanel();
		panelPajaros.setBorder(BorderFactory.createTitledBorder(
		        BorderFactory.createLineBorder(Color.BLACK), "Pajaros", TitledBorder.LEFT, TitledBorder.TOP
		));
		panelPajaros.setLayout(new BoxLayout(panelPajaros, BoxLayout.Y_AXIS));

		JLabel labelPajaro = crearImagen("/Users/maialenbarredomuro/Desktop/PerreraProgra_/imagenes/patito.jpg", 150, 200);
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

		JLabel labelRoedor = crearImagen("/Users/maialenbarredomuro/Desktop/PerreraProgra_/imagenes/ratoncito.jpg", 150, 200);
		JButton botonRoedor = new JButton("Adoptar");
		botonRoedor.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelRoedores.add(labelRoedor);
		panelRoedores.add(botonRoedor);
		panelAnimales.add(panelRoedores);

		this.add(panelAnimales);
		
		
		// -------------------------------------- panel menu / botones a otros -----------------------------------
		JPanel panelMenu = new JPanel();
		panelMenu.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK),
				"Menu", 
				TitledBorder.CENTER, 
				TitledBorder.TOP)
		);
		
		panelMenu.setBackground(new Color(245, 245, 245)); // gris claro
		panelMenu.setLayout(new FlowLayout()); // para que la barra se ajuste bien
		
		// botones
		JButton animales = new JButton("animales");
		JButton tienda = new JButton("tienda");
		JButton perfil = new JButton("perfil");
		
		JLabel iconoPerfil = crearImagen("/Users/maialenbarredomuro/Desktop/PerreraProgra_/imagenes/Image.jpeg", 30, 30);
		panelMenu.add(iconoPerfil);
		
		panelMenu.add(perfil);
		panelMenu.add(animales);
		panelMenu.add(tienda);
		
		
		
		
		this.add(panelMenu, BorderLayout.NORTH);

		


		
		
		this.setVisible(true);
	}
	
	private JLabel crearImagen(String ruta, int ancho, int alto) {
	    ImageIcon iconoOriginal = new ImageIcon(ruta);
	    Image img = iconoOriginal.getImage();
	    Image imgEscalada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
	    ImageIcon icono = new ImageIcon(imgEscalada);
	    JLabel label = new JLabel(icono);
	    label.setAlignmentX(Component.CENTER_ALIGNMENT); // para BoxLayout vertical
	    return label;
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(VentanaPrincipal::new);
	}

}
