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
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import Domain.Gato;

public class VentanaGatos extends JFrame {

	private static final long serialVersionUID = 1L;
	private Gato[] gatos;

	public VentanaGatos(Gato[] gatos) {
		
		this.gatos = gatos;

		setTitle("GATOS");
		setSize(850, 600);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);

		//------------------------FRAME CON TITULO---------------------------
		JPanel frame = new JPanel();
		frame.setLayout(new BorderLayout());

		JLabel titulo = new JLabel();
		titulo.setText("GATOS PARA ADOPTAR");
		titulo.setHorizontalAlignment(SwingConstants.CENTER); //para poner en el centro con borderlayout
		titulo.setFont(new Font("Verdana", Font.BOLD, 20));
		titulo.setForeground(new Color(36, 37, 130));
		frame.add(titulo, BorderLayout.NORTH);

		//------------------------PANEL PRINCIPAL-----------------------------

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(4, 3, 10, 10));

		//jscroll para el panel principal
		JScrollPane jscroll = new JScrollPane(panelPrincipal);


		//------------------------PANELS---------------------------

		//array de paneles para 12 gatos
		JPanel[] paneles = new JPanel[12];

		//paneles para cada gato
		for (int i = 0; i < 12; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setPreferredSize(new Dimension(250, 250)); // casilla grande

			panel.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(new Color(36, 37, 130)), 
					gatos[i].getNombre(), TitledBorder.LEFT, TitledBorder.TOP, null, new Color(36, 37, 130)
					));
			paneles[i] = panel;
			panelPrincipal.add(panel);

		}


		//------------------------LABELS---------------------------

		//array de labels (12 labels)
		JLabel[] labels = new JLabel[12];

		//añadir cada label al panel y poner una imagen para cada una
		for (int i = 0; i < 12; i ++) {
			JLabel label = crearImagen("imagenes/gatos/gato" + (i+1) + ".png", 200, 150);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			paneles[i].add(Box.createVerticalStrut(15));
			labels[i] = label;
			paneles[i].add(label);
		}

		//------------------------BUTTONS---------------------------

		//array de botones (12 botones) para adoptar
		JButton[] botones = new JButton[12];


		//botones adoptar
		for(int i = 0; i < 12; i++) {
			JButton boton = new JButton();
			boton.setText("Ver más");
			boton.setMaximumSize(new Dimension(80, 25)); // ancho x alto
			boton.setAlignmentX(Component.CENTER_ALIGNMENT); // centrado horizontal
			paneles[i].add(Box.createVerticalStrut(20));
			botones[i] = boton;
			paneles[i].add(boton);
		}

		//----------LISTENER---------
		for(int i = 0; i < 12; i++) {
		    final int indiceGato = i; // 
		    botones[i].addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            mostrarCaracteristicas(indiceGato);
		        }
		    });
		}

		frame.add(jscroll);
		add(frame);
		setVisible(true);

	}

	//funcion para el perfil redondo
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

	//funcion para crear imagen
	private JLabel crearImagen(String ruta, int ancho, int alto) {
		ImageIcon iconoOriginal = new ImageIcon(ruta);
		Image img = iconoOriginal.getImage();
		Image imgEscalada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		ImageIcon icono = new ImageIcon(imgEscalada);
		JLabel label = new JLabel(icono);
		label.setAlignmentX(Component.CENTER_ALIGNMENT); // para BoxLayout vertical
		return label;
	}

	//funcion para mostrar caracteristicas de cada gato
	private void mostrarCaracteristicas(int indiceGato) {

		//-----------------------------------------------VENTANA CARACTERISTICAS GATO--------------------------------------------------------------

		//panel que se abre despues de darle al boton 'ver mas' para ver mejor las caracteristicas de cada gato

		//----------FRAME-----------
		JFrame ventanaCarac = new JFrame("Características del gato");
		ventanaCarac.setSize(500, 400);
		ventanaCarac.setDefaultCloseOperation(HIDE_ON_CLOSE);
		ventanaCarac.setLocationRelativeTo(null);
		ventanaCarac.getContentPane().setLayout(new BoxLayout(ventanaCarac.getContentPane(), BoxLayout.Y_AXIS));

		//añadir componentes con caracteristicas

		//---------PANEL PERFIL-----------

		JPanel panelPerfil = new JPanel();
		panelPerfil.setPreferredSize(new Dimension(500, 120));
		panelPerfil.setBackground(new Color(255, 182, 193));

		ImageIcon fotoPerfilGato = new ImageIcon(createCircleImage("imagenes/gatos/gato" + (indiceGato+1) + ".png", 90, new Color(255, 0, 127)));
		JLabel fotoLabelGato = new JLabel(fotoPerfilGato);
		fotoLabelGato.setHorizontalAlignment(JLabel.CENTER);
		panelPerfil.add(fotoLabelGato);

		panelPerfil.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(new Color(255, 0, 127)), 
				"miau", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 127)
				));


		//---------PANEL INFO----------- FALTA PONER BIEN LAS ESTIQUETAS
		JPanel panelInfo = new JPanel();
		panelInfo.setPreferredSize(new Dimension(500, 280));
		panelInfo.setBackground(new Color(245, 245, 220));
		panelInfo.setLayout(new BorderLayout(20, 20));

		Gato gato = gatos[indiceGato];
		
		JLabel nombreGato = new JLabel(gato.getNombre(), JLabel.CENTER);
		nombreGato.setFont(new Font("Verdana", Font.BOLD, 36));
		panelInfo.add(nombreGato, BorderLayout.NORTH);
		
		JButton botonAdopt = new JButton("Adóptame");
		botonAdopt.setPreferredSize(new Dimension(100, 30)); // ancho x alto
		botonAdopt.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelInfo.add(botonAdopt, BorderLayout.SOUTH);
		
		//para poner el boton pequeño y centrado
		JPanel contenedorBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		contenedorBoton.setBackground(new Color(245, 245, 220));
		contenedorBoton.add(botonAdopt);
		panelInfo.add(contenedorBoton, BorderLayout.SOUTH);
		
		//panel grid para añadir datos que faltan
		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new FlowLayout());
		panelDatos.setBackground(new Color(245, 245, 220));
		
		JPanel panelDatos1 = new JPanel();
		panelDatos1.setLayout(new GridLayout(2, 4, 12, 12));
		panelDatos1.setPreferredSize(new Dimension(500, 60));
		panelDatos1.setBackground(new Color(245, 245, 220));
		panelDatos1.add(new JLabel("<html><b>Edad</b>: " + gatos[indiceGato].getEdad() + "años"));
		panelDatos1.add(new JLabel("<html><b>Sexo</b>: " + gatos[indiceGato].getSexo()));
		panelDatos1.add(new JLabel("<html><b>Peso</b>: " + gatos[indiceGato].getPeso() + "kg"));
		panelDatos1.add(new JLabel("<html><b>Raza</b>: " + gatos[indiceGato].getRaza()));
		
		JPanel panelDatos2 = new JPanel();
		panelDatos2.setLayout(new BoxLayout(panelDatos2, BoxLayout.Y_AXIS));
		panelDatos2.setPreferredSize(new Dimension(500, 60));
		panelDatos2.setBackground(new Color(245, 245, 220));
		panelDatos2.add(new JLabel("<html><b>Personalidad</b>: " + gatos[indiceGato].getDescripcion_personalidad()));
		panelDatos2.add(new JLabel("<html><b>Rasgos</b>: " + gatos[indiceGato].getDescripcion_fisica()));
		
		//añadir paneles al frame
		panelDatos.add(panelDatos1);
		panelDatos.add(panelDatos2);
		panelInfo.add(panelDatos, BorderLayout.CENTER);
		ventanaCarac.add(panelPerfil);
		ventanaCarac.add(panelInfo);
		ventanaCarac.setVisible(true);

	}


}
