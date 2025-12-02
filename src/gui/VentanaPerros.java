package gui;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import DB.GestorBD;
import Domain.Perro;
import Domain.Usuario;

public class VentanaPerros extends JFrame {

	private static final long serialVersionUID = 1L;
	private Perro[] perros;

	private VentanaPrincipal ventanaPrincipal;
	
	public VentanaPerros(VentanaPrincipal ventanaPrincipal, Perro[] perros, Usuario user, GestorBD gestor) {
		super();

		this.perros=perros;

		setTitle("PERROS");
		setSize(850, 600);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel frame = new JPanel();
		frame.setLayout(new BorderLayout());
		frame.setBackground(new Color(200, 180, 155));

		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new BorderLayout());
		panelTitulo.setBackground(new Color(200, 180, 155));
		panelTitulo.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(new Color(80, 55, 30)), 
				perros[0].sonidoAnimales(), TitledBorder.LEFT, TitledBorder.TOP, null, new Color(80, 55, 30)
				));

		JLabel titulo = new JLabel();
		titulo.setText("PERROS PARA ADOPTAR");
		titulo.setHorizontalAlignment(SwingConstants.CENTER); //para poner en el centro con borderlayout
		titulo.setFont(new Font("Verdana", Font.BOLD, 20));
		titulo.setForeground(new Color(80, 55, 30));

		JLabel labelVacio = new JLabel();
		labelVacio.setPreferredSize(new Dimension(80, 30)); //para que el titulo quede centrado
		labelVacio.setBackground(new Color(200, 180, 155));
		

		//boton atras

		JButton botonAtras = new JButton("Atrás");
		botonAtras.setPreferredSize(new Dimension(80, 30));
		botonAtras.setHorizontalAlignment(botonAtras.CENTER);
		botonAtras.setForeground(new Color(80, 55, 30));

		panelTitulo.add(labelVacio, BorderLayout.EAST);
		panelTitulo.add(botonAtras, BorderLayout.WEST);
		panelTitulo.add(titulo, BorderLayout.CENTER);
		frame.add(panelTitulo, BorderLayout.NORTH);



		//---------------------------------------------------LISTENER BOTON ATRAS-----------------------------------------------------------------
		botonAtras.addActionListener(e -> {
			//VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(perros, pajaros, roedores);
			//ventanaPrincipal.setVisible(false);
			SwingUtilities.invokeLater(() -> ventanaPrincipal.setVisible(true));
			this.setVisible(false);
		});



		//-----------------------------------------------------PANEL PRINCIPAL--------------------------------------------------------------------

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(4, 3, 10, 10));
		panelPrincipal.setBackground(new Color(200, 180, 155));

		//jscroll para el panel principal
		JScrollPane jscroll = new JScrollPane(panelPrincipal);


		//---------------------------------------------------------PANELES PARA CADA PERRO ----------------------------------------------------------

		//array de paneles para 12 perros
		JPanel[] paneles = new JPanel[12];

		//paneles para cada perro
		for (int i = 0; i < 12; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setPreferredSize(new Dimension(250, 250)); // casilla grande
			panel.setBackground(new Color(233, 220, 209));

			panel.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(new Color(80, 55, 30)), 
					perros[i].getNombre(), TitledBorder.LEFT, TitledBorder.TOP, null, new Color(80, 55, 30)
					));
			paneles[i] = panel;
			panelPrincipal.add(panel);

		}


		//---------------------------------------------------LABELS PARA CADA PERRO -------------------------------------------------------------------

		//array de labels (12 labels)
		JLabel[] labels = new JLabel[12];

		//añadir cada label al panel y poner una imagen para cada una
		for (int i = 0; i < 12; i ++) {
			JLabel label = crearImagen("imagenes/perros/perro" + (i+1) + ".png", 200, 150);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			paneles[i].add(Box.createVerticalStrut(15));
			labels[i] = label;
			paneles[i].add(label);
		}

		//---------------------------------------------------------BUTTONS VER MAS PARA CADA PERRO -----------------------------------------------------

		//array de botones (12 botones) para adoptar
		JButton[] botones = new JButton[12];


		//botones para ver mas caracteristicas de cada perro
		for(int i = 0; i < 12; i++) {
			JButton boton = new JButton("Ver más");
			boton.setAlignmentX(Component.CENTER_ALIGNMENT);
			boton.setForeground(new Color(80, 55, 30));
			paneles[i].add(Box.createVerticalStrut(20));
			botones[i] = boton;
			paneles[i].add(boton);
		}

		//----------------------------------------LISTENER PARA LOS BOTONES VER MAS--------------------------------------------------------
		for(int i = 0; i < 12; i++) {
			final int indicePerro = i; // 
			botones[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaCaracteristicas caracteristicas = new VentanaCaracteristicas(indicePerro, perros, user, gestor);
				}
			});
		}

		frame.add(jscroll, BorderLayout.CENTER);
		add(frame);
		setVisible(true);

	}

	//---------------------------------------------------FUNCION CREAR IMAGEN CIRCULO-----------------------------------------------------------

	private BufferedImage createCircleImage(String imagePath, int diameter, Color borderColor) {
		try {
			BufferedImage originalImage = ImageIO.read(new File(imagePath)); // carga la imagen desde el archivo
			Image scaledImage = originalImage.getScaledInstance(diameter, diameter, Image.SCALE_SMOOTH); // redimensiona suavemente
			BufferedImage circularImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB); // crea imagen transparente para dibujar el círculo

			Graphics2D g = circularImage.createGraphics(); // obtiene el contexto gráfico
			g.setClip(new Ellipse2D.Float(0, 0, diameter, diameter)); // define la región circular de recorte
			g.drawImage(scaledImage, 0, 0, null); // dibuja la imagen escalada dentro del círculo

			g.setClip(null); // desactiva el recorte para poder dibujar el borde
			g.setColor(borderColor); // establece el color del borde
			g.setStroke(new BasicStroke(3)); // define el grosor del borde
			g.drawOval(0, 0, diameter - 1, diameter - 1); // dibuja el borde circular
			g.dispose(); // libera recursos del objeto Graphics2D

			return circularImage; // devuelve la imagen circular resultante
		} catch (IOException e) {
			System.out.println("Error al cargar la imagen: " + e.getMessage()); // muestra error si falla la carga
			return null; // devuelve null en caso de error
		}
	}

	//-------------------------------------------------------FUNCION PARA CREAR IMAGEN EN LABEL----------------------------------------------------
	private JLabel crearImagen(String ruta, int ancho, int alto) {
		ImageIcon iconoOriginal = new ImageIcon(ruta);
		Image img = iconoOriginal.getImage();
		Image imgEscalada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		ImageIcon icono = new ImageIcon(imgEscalada);
		JLabel label = new JLabel(icono);
		label.setAlignmentX(Component.CENTER_ALIGNMENT); // para BoxLayout vertical
		label.setBorder(BorderFactory.createLineBorder(new Color(80, 55, 30), 3));
		return label;
	}
	
}