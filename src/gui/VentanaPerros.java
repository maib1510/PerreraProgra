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

import Domain.Perro;

public class VentanaPerros extends JFrame {

	private static final long serialVersionUID = 1L;
	private Perro[] perros;

	private VentanaPrincipal ventanaPrincipal;
	
	public VentanaPerros(VentanaPrincipal ventanaPrincipal, Perro[] perros) {
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
				"wof wof", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(80, 55, 30)
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
					mostrarCaracteristicas(indicePerro);
				}
			});
		}

		frame.add(jscroll, BorderLayout.CENTER);
		add(frame);
		setVisible(true);

	}

	//----------------------------------FUNCION PARA MOSTRAR CARACTERISTICAS DE CADA PERRO ---------------------------------------------------------


	//---------------------------------------------------ventana caracteristicas perro-------------------------------------------------------------
	//panel que se abre despues de darle al boton 'ver mas' para ver mejor las caracteristicas de cada perro

	private void mostrarCaracteristicas(int indicePerro) {
		JFrame ventanaCarac = new JFrame("Características del perro");
		ventanaCarac.setSize(700, 500);
		ventanaCarac.setDefaultCloseOperation(HIDE_ON_CLOSE);
		ventanaCarac.setLocationRelativeTo(null);
		ventanaCarac.getContentPane().setLayout(new BoxLayout(ventanaCarac.getContentPane(), BoxLayout.Y_AXIS));

		//--------------------------------------------------------panel perfil-------------------------------------------------------------------------

		JPanel panelPerfil = new JPanel();
		panelPerfil.setPreferredSize(new Dimension(500, 140));
		panelPerfil.setBackground(new Color(200, 180, 155)); 

		ImageIcon fotoPerfilperro = new ImageIcon(createCircleImage("imagenes/perros/perro" + (indicePerro+1) + ".png", 110, new Color(80, 55, 30))); // un terracota elegante
		JLabel fotoLabelperro = new JLabel(fotoPerfilperro);
		fotoLabelperro.setHorizontalAlignment(JLabel.CENTER);
		panelPerfil.add(fotoLabelperro);

		panelPerfil.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(new Color(80, 55, 30)), 
				perros[indicePerro].sonidoAnimales(), TitledBorder.LEFT, TitledBorder.TOP, null, new Color(80, 55, 30)
				));

		//----------------------------------------------------------panel info-------------------------------------------------------------------------

		JPanel panelInfo = new JPanel();
		panelInfo.setPreferredSize(new Dimension(500, 260));
		panelInfo.setBackground(new Color(233, 220, 209)); 
		panelInfo.setLayout(new BorderLayout(20, 20));

		Perro perro = perros[indicePerro];

		// -----------titulo del perro en grande--------

		JLabel nombreperro = new JLabel(perro.getNombre(), JLabel.CENTER);
		nombreperro.setFont(new Font("Verdana", Font.BOLD, 36));
		nombreperro.setForeground(new Color(80, 55, 30)); 
		panelInfo.add(nombreperro, BorderLayout.NORTH);

		//-----------datos en caja en dos columnas--------

		JPanel panelDatos = new JPanel(new GridLayout(3, 2, 14, 14));
		panelDatos.setBackground(new Color(233, 220, 209)); 

		// -----------borde tipo caja para los datos--------
		Border bordeDatos = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(135, 155, 110), 2, true), 
				BorderFactory.createEmptyBorder(10, 20, 10, 20)
				);

		// -----------etiquetas para cada dato--------

		//edad (para que no aparezca float si es entero)
		double edad = perro.getEdad();
		String perroEdad;
		if (edad == Math.floor(edad)) {
			//número entero (sin decimales)
			perroEdad = String.format("%.0f", edad);
		} else {
			//número decimal no entero
			perroEdad = String.format("%.1f", edad);
		}

		JLabel edadLabel = new JLabel("<html><b>Edad:</b> " + perroEdad + " años</html>", JLabel.CENTER);// poner en negrita edad
		edadLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		edadLabel.setBorder(bordeDatos);
		edadLabel.setForeground(new Color(80, 55, 30));
		panelDatos.add(edadLabel);

		//sexo
		JLabel sexoLabel = new JLabel("<html><b>Sexo:</b> " + perro.getSexo() + "</html>", JLabel.CENTER);
		sexoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		sexoLabel.setBorder(bordeDatos);
		sexoLabel.setForeground(new Color(80, 55, 30));
		panelDatos.add(sexoLabel);

		//raza
		JLabel razaLabel = new JLabel("<html><b>Raza:</b> " + perro.getRaza() + "</html>", JLabel.CENTER);
		razaLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		razaLabel.setBorder(bordeDatos);
		razaLabel.setForeground(new Color(80, 55, 30));
		panelDatos.add(razaLabel);

		//peso
		JLabel pesoLabel = new JLabel("<html><b>Peso:</b> " + perro.getPeso() + " kg</html>", JLabel.CENTER);
		pesoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		pesoLabel.setBorder(bordeDatos);
		pesoLabel.setForeground(new Color(80, 55, 30));
		panelDatos.add(pesoLabel);

		//personalidad
		JLabel personalidadLabel = new JLabel("<html><b>Personalidad:</b> " + perro.getDescripcion_personalidad() + "</html>", JLabel.CENTER);
		personalidadLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		personalidadLabel.setForeground(new Color(80, 55, 30));
		personalidadLabel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(135, 155, 110), 2, true),
				BorderFactory.createEmptyBorder(10, 20, 10, 20)
				));
		panelDatos.add(personalidadLabel);

		//rasgos
		JLabel rasgosLabel = new JLabel("<html><b>Rasgos:</b> " + perro.getDescripcion_fisica() + "</html>", JLabel.CENTER);
		rasgosLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		rasgosLabel.setForeground(new Color(80, 55, 30));
		rasgosLabel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(135, 155, 110), 2, true), 
				BorderFactory.createEmptyBorder(10, 20, 10, 20)
				));

		panelDatos.add(rasgosLabel);

		panelInfo.add(panelDatos, BorderLayout.CENTER);

		// -----------panel + boton adoptar centrado--------

		JPanel panelAdopt = new JPanel();
		panelAdopt.setLayout(new FlowLayout());
		panelAdopt.setBackground(new Color(233, 220, 209));


		JButton botonAdopt = new JButton("¡Adóptame!");
		botonAdopt.setPreferredSize(new Dimension(120, 35));
		botonAdopt.setHorizontalAlignment(botonAdopt.CENTER);

		botonAdopt.addActionListener(e -> {
			if(perros[indicePerro].isAdoptado() == false) {
				confirmarAdopcion(indicePerro);
				perros[indicePerro].setAdoptado(true);
			} else {
				JOptionPane.showMessageDialog(null,
			            "¡" + perros[indicePerro].getNombre() + " ya ha sido adoptado!");
				
			}
		});
		panelAdopt.add(botonAdopt);

		//-----------panel + boton atras izquierda----------

		JPanel panelAtras = new JPanel();
		panelAtras.setLayout(new FlowLayout());
		panelAtras.setBackground(new Color(233, 220, 209));


		JButton botonAtras = new JButton("Atrás");
		botonAtras.setPreferredSize(new Dimension(80, 35));
		botonAtras.setHorizontalAlignment(botonAtras.CENTER);

		panelAtras.add(botonAtras);

		//----------label para centrar boton adoptar
		JLabel vacio = new JLabel();
		vacio.setPreferredSize(new Dimension(80, 30)); //para que el titulo quede centrado
		vacio.setBackground(new Color(200, 180, 155));


		//-----------panel para botones--------------
		JPanel panelBoton = new JPanel(new BorderLayout());
		panelBoton.setBackground(new Color(233, 220, 209)); 
		panelBoton.add(panelAtras, BorderLayout.WEST);
		panelBoton.add(panelAdopt, BorderLayout.CENTER);
		panelBoton.add(vacio, BorderLayout.EAST);
		panelInfo.add(panelBoton, BorderLayout.SOUTH);


		//------------listener boton atras-------------
		botonAtras.addActionListener(e -> {
			//Ventanaperros ventanaperros = new Ventanaperros(perros);
			//ventanaperros.setVisible(false);
			//SwingUtilities.invokeLater(() -> ventanaPrincipal.setVisible(true));
			ventanaCarac.setVisible(false);
			this.setVisible(true);
		});

		// ---------añadir al frame-----------

		ventanaCarac.add(panelPerfil);  
		ventanaCarac.add(panelInfo);
		ventanaCarac.setVisible(true);
	}
	
	//----------------------------------------------------FUNCION PARA CONFIRMAR ADOPCIÓN------------------------------------------------------
	private void confirmarAdopcion(int indicePerro) {
	    JFrame ventana = new JFrame();
	    ventana.setTitle("Confirmación de la adopción");
	    ventana.setSize(700, 500);
	    ventana.setLocationRelativeTo(null);
	    ventana.setLayout(new BorderLayout());

	    // Panel principal con borde y fondo
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(233, 220, 209));
	    panel.setBorder(BorderFactory.createLineBorder(new Color(80, 55, 30), 40));
	    panel.setLayout(new BorderLayout());

	    // ---------- PANEL FOTO + ARCHIVO ----------
	    JPanel panelContenido = new JPanel(new GridLayout(1, 2));
	    panelContenido.setBackground(new Color(233, 220, 209));

	    // Panel izquierdo (foto del perro)
	    JPanel panelFoto = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    panelFoto.setBackground(new Color(233, 220, 209));
	    JLabel foto = crearImagen("imagenes/perros/perro" + (indicePerro + 1) + ".png", 250, 200);
	    panelFoto.add(foto);

	    // Panel derecho (archivo + huellitas)
	    JPanel panelArchivo = new JPanel();
	    panelArchivo.setBackground(new Color(233, 220, 209));
	    panelArchivo.setLayout(new BoxLayout(panelArchivo, BoxLayout.Y_AXIS));

	    // Huellitas grandes
	    JLabel huellas = new JLabel("🐾 🐾 🐾", JLabel.CENTER);
	    huellas.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
	    huellas.setAlignmentX(Component.CENTER_ALIGNMENT);

	    // Botón para descargar archivo
	    JButton botonDescargar = new JButton("Descargar certificado");
	    botonDescargar.setFont(new Font("Verdana", Font.BOLD, 14));
	    botonDescargar.setBackground(new Color(180, 150, 120));
	    botonDescargar.setForeground(Color.WHITE);
	    botonDescargar.setFocusPainted(false);
	    botonDescargar.setAlignmentX(Component.CENTER_ALIGNMENT);
	    botonDescargar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    botonDescargar.addActionListener(e -> {
	    		generarCertificadoperro(indicePerro);
	    	
	    });

	    // Espaciado visual
	    panelArchivo.add(Box.createVerticalGlue());
	    panelArchivo.add(huellas);
	    panelArchivo.add(Box.createRigidArea(new Dimension(0, 30)));
	    panelArchivo.add(botonDescargar);
	    panelArchivo.add(Box.createVerticalGlue());

	    panelContenido.add(panelFoto);
	    panelContenido.add(panelArchivo);

	    // ---------- PANEL TEXTO ----------
	    JPanel panelTexto = new JPanel();
	    panelTexto.setLayout(new BorderLayout());
	    panelTexto.setBackground(new Color(233, 220, 209));

	    JLabel label = new JLabel(
	        "<html><div style='text-align:center;'>¡¡¡Gracias por adoptar a <b>" 
	        + perros[indicePerro].getNombre() + "</b> y darle un hogar lleno de amor!!!</div></html>",
	        JLabel.CENTER
	    );
	    label.setFont(new Font("Verdana", Font.BOLD, 20));
	    label.setForeground(new Color(80, 55, 30));
	    label.setHorizontalAlignment(SwingConstants.CENTER);
	    label.setVerticalAlignment(SwingConstants.BOTTOM); // lo empuja hacia abajo
	    label.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0)); // margen inferior

	    panelTexto.add(label, BorderLayout.SOUTH);

	    panel.add(panelContenido, BorderLayout.CENTER);
	    panel.add(panelTexto, BorderLayout.SOUTH);

	    ventana.add(panel);
	    ventana.setVisible(true);
	}
	
	//---------------------------------------------FUNCION PARA GENERAR Y DESCARGAR CERTIFICADO------------------------------------------------
	//IMPORTANTE!!!!!! -> para descargar el fichero es necesario refrescar el proyecto
	
	private void generarCertificadoperro(int indicePerro) { 
	    // carpeta donde se guardarán los certificados
	    File carpeta = new File("certificados/certificadosperros"); 
	    
	    // archivo dentro de la carpeta
	    File archivo = new File(carpeta, "Certificado_" + perros[indicePerro].getNombre() + ".txt");

	    try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
	        writer.println("🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾");
	        writer.println("       Certificado de Adopción       ");
	        writer.println("🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾");
	        writer.println();
	        writer.println("¡Qué alegría! 🎉");
	        writer.println();
	        writer.println("Se confirma que has adoptado a " + perros[indicePerro].getNombre() + " 🐱");
	        writer.println();
	        writer.println("Estamos muy felices por ti y por " + perros[indicePerro].getNombre() + ".");
	        writer.println("Gracias a tu decisión, " + perros[indicePerro].getNombre() + " tendrá un hogar lleno de amor y cuidado.");
	        writer.println("Este es un momento muy especial y queremos celebrarlo contigo.");
	        writer.println();
	        writer.println("Disfruta cada instante junto a tu nuevo compañero,");
	        writer.println("y recuerda que tu gesto significa un mundo para él.");
	        writer.println();
	        writer.println("────────────────────────────");
	        writer.println("Fecha: " + java.time.LocalDate.now());
	        writer.println("Refugio: Patitas🐾");
	        writer.println("────────────────────────────");
	        writer.println();

	        JOptionPane.showMessageDialog(null,
	            "¡Certificado de " + perros[indicePerro].getNombre() + " guardado exitosamente!");
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(null,
	            "Error al generar el certificado: " + ex.getMessage());
	    }
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


	public static void main(String[] args) {
				
	}
	
}