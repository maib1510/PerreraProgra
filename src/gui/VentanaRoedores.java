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

import Domain.Roedor;

public class VentanaRoedores extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Roedor[] roedores;

	private VentanaPrincipal ventanaPrincipal;

	public VentanaRoedores(VentanaPrincipal ventanaPrincipal, Roedor[] roedores) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.roedores = roedores;

		setTitle("ROEDORES");
		setSize(850, 600);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);

		//--------------------------------------------------------TITULO EN FRAME-----------------------------------------------------------
		JPanel frame = new JPanel();
		frame.setLayout(new BorderLayout());
		frame.setBackground(new Color(200, 180, 155));

		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new BorderLayout());
		panelTitulo.setBackground(new Color(200, 180, 155));
		panelTitulo.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(new Color(80, 55, 30)), 
				"cui cui", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(80, 55, 30)
				));

		JLabel titulo = new JLabel();
		titulo.setText("ROEDORES PARA ADOPTAR");
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
			//VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(gatos, pajaros, roedores);
			//ventanaPrincipal.setVisible(false);
			SwingUtilities.invokeLater(() -> ventanaPrincipal.setVisible(true));
			this.setVisible(false);
		});



		//-----------------------------------------------------PANEL PRINCIPAL--------------------------------------------------------------------

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(2, 3, 10, 10));
		panelPrincipal.setBackground(new Color(200, 180, 155));

		//jscroll para el panel principal
		JScrollPane jscroll = new JScrollPane(panelPrincipal);


		//---------------------------------------------------------PANELES PARA CADA GATO----------------------------------------------------------

		int n = roedores.length; 
		
		//array de paneles para 6 roedores
		JPanel[] paneles = new JPanel[n];

		//paneles para cada roedor
		for (int i = 0; i < n; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setPreferredSize(new Dimension(250, 250)); // casilla grande
			panel.setBackground(new Color(233, 220, 209));

			panel.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(new Color(80, 55, 30)), 
					roedores[i].getNombre(), TitledBorder.LEFT, TitledBorder.TOP, null, new Color(80, 55, 30)
					));
			paneles[i] = panel;
			panelPrincipal.add(panel);

		}


		//---------------------------------------------------LABELS PARA CADA ROEDOR-------------------------------------------------------------------

		//array de labels (6 labels)
		JLabel[] labels = new JLabel[6];

		//añadir cada label al panel y poner una imagen para cada una
		for (int i = 0; i < 6; i ++) {
			JLabel label = crearImagen("imagenes/roedores/roedor" + (i+1) + ".png", 200, 150);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			paneles[i].add(Box.createVerticalStrut(15));
			labels[i] = label;
			paneles[i].add(label);
		}

		//---------------------------------------------------------BUTTONS VER MAS PARA CADA ROEDOR-----------------------------------------------------

		//array de botones (6 botones) para adoptar
		JButton[] botones = new JButton[6];


		//botones para ver mas caracteristicas de cada gato
		for(int i = 0; i < 6; i++) {
			JButton boton = new JButton("Ver más");
			boton.setAlignmentX(Component.CENTER_ALIGNMENT);
			paneles[i].add(Box.createVerticalStrut(20));
			botones[i] = boton;
			paneles[i].add(boton);
		}

		//----------------------------------------LISTENER PARA LOS BOTONES VER MAS--------------------------------------------------------
		for(int i = 0; i < n; i++) {
			final int indiceRoedor = i; // 
			botones[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mostrarCaracteristicas(indiceRoedor);
				}
			});
		}

		frame.add(jscroll, BorderLayout.CENTER);
		add(frame);
		setVisible(true);

	}

	//----------------------------------FUNCION PARA MOSTRAR CARACTERISTICAS DE CADA ROEDOR---------------------------------------------------------


	//---------------------------------------------------ventana caracteristicas roedor-------------------------------------------------------------
	//panel que se abre despues de darle al boton 'ver mas' para ver mejor las caracteristicas de cada roedor

	private void mostrarCaracteristicas(int indiceRoedor) {
		JFrame ventanaCarac = new JFrame("Características del roedor");
		ventanaCarac.setSize(700, 500);
		ventanaCarac.setDefaultCloseOperation(HIDE_ON_CLOSE);
		ventanaCarac.setLocationRelativeTo(null);
		ventanaCarac.getContentPane().setLayout(new BoxLayout(ventanaCarac.getContentPane(), BoxLayout.Y_AXIS));

		//--------------------------------------------------------panel perfil-------------------------------------------------------------------------

		JPanel panelPerfil = new JPanel();
		panelPerfil.setPreferredSize(new Dimension(500, 140));
		panelPerfil.setBackground(new Color(200, 180, 155)); 

		ImageIcon fotoPerfilRoedor = new ImageIcon(createCircleImage("imagenes/roedores/roedor" + (indiceRoedor+1) + ".png", 110, new Color(80, 55, 30))); // un terracota elegante
		JLabel fotoLabelRoedor = new JLabel(fotoPerfilRoedor);
		fotoLabelRoedor.setHorizontalAlignment(JLabel.CENTER);
		panelPerfil.add(fotoLabelRoedor);

		panelPerfil.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(new Color(80, 55, 30)), 
				roedores[indiceRoedor].sonidoAnimales(), TitledBorder.LEFT, TitledBorder.TOP, null, new Color(80, 55, 30)
				));

		//----------------------------------------------------------panel info-------------------------------------------------------------------------

		JPanel panelInfo = new JPanel();
		panelInfo.setPreferredSize(new Dimension(500, 260));
		panelInfo.setBackground(new Color(233, 220, 209)); 
		panelInfo.setLayout(new BorderLayout(20, 20));

		Roedor roedor = roedores[indiceRoedor];

		// -----------titulo del roedor en grande--------

		JLabel nombreRoedor = new JLabel(roedor.getNombre(), JLabel.CENTER);
		nombreRoedor.setFont(new Font("Verdana", Font.BOLD, 36));
		nombreRoedor.setForeground(new Color(80, 55, 30)); 
		panelInfo.add(nombreRoedor, BorderLayout.NORTH);

		//-----------datos en caja en dos columnas--------

		JPanel panelDatos = new JPanel(new GridLayout(3, 2, 14, 14));
		panelDatos.setBackground(new Color(233, 220, 209)); 

		// -----------borde tipo caja para los datos--------
		Border bordeDatos = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(80, 55, 30), 2, true), 
				BorderFactory.createEmptyBorder(10, 20, 10, 20)
				);

		// -----------etiquetas para cada dato--------

		//edad (para que no aparezca float si es entero)
		double edad = roedor.getEdad();
		String roedorEdad;
		if (edad == Math.floor(edad)) {
			//número entero (sin decimales)
			roedorEdad = String.format("%.0f", edad);
		} else {
			//número decimal no entero
			roedorEdad = String.format("%.1f", edad);
		}

		JLabel edadLabel = new JLabel("<html><b>Edad:</b> " + roedorEdad + " años</html>", JLabel.CENTER);// poner en negrita edad
		edadLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		edadLabel.setBorder(bordeDatos);
		edadLabel.setForeground(new Color(80, 55, 30));
		panelDatos.add(edadLabel);

		//sexo
		JLabel sexoLabel = new JLabel("<html><b>Sexo:</b> " + roedor.getSexo() + "</html>", JLabel.CENTER);
		sexoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		sexoLabel.setBorder(bordeDatos);
		sexoLabel.setForeground(new Color(80, 55, 30));
		panelDatos.add(sexoLabel);

		//raza
		JLabel razaLabel = new JLabel("<html><b>Raza:</b> " + roedor.getRaza() + "</html>", JLabel.CENTER);
		razaLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		razaLabel.setBorder(bordeDatos);
		razaLabel.setForeground(new Color(80, 55, 30));
		panelDatos.add(razaLabel);

		//peso
		JLabel pesoLabel = new JLabel("<html><b>Peso:</b> " + roedor.getPeso() + " kg</html>", JLabel.CENTER);
		pesoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		pesoLabel.setBorder(bordeDatos);
		pesoLabel.setForeground(new Color(80, 55, 30));
		panelDatos.add(pesoLabel);

		//personalidad
		JLabel personalidadLabel = new JLabel("<html><b>Personalidad:</b> " + roedor.getDescripcion_personalidad() + "</html>", JLabel.CENTER);
		personalidadLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		personalidadLabel.setForeground(new Color(80, 55, 30));
		personalidadLabel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(80, 55, 30), 2, true),
				BorderFactory.createEmptyBorder(10, 20, 10, 20)
				));
		panelDatos.add(personalidadLabel);

		//rasgos
		JLabel rasgosLabel = new JLabel("<html><b>Rasgos:</b> " + roedor.getDescripcion_fisica() + "</html>", JLabel.CENTER);
		rasgosLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		rasgosLabel.setForeground(new Color(80, 55, 30));
		rasgosLabel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(80, 55, 30), 2, true), 
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
			if(roedores[indiceRoedor].isAdoptado() == false) {
				confirmarAdopcion(indiceRoedor);
				roedores[indiceRoedor].setAdoptado(true);
			}else {
				JOptionPane.showMessageDialog(null,
			            "¡" + roedores[indiceRoedor].getNombre() + " ya ha sido adoptado!");
				
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
			//VentanaRoedores ventanaRoedores = new VentanaGatos(roedores);
			//VentanaRoedores.setVisible(false);
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
	private void confirmarAdopcion(int indiceRoedor) {
	    JFrame ventana = new JFrame("Confirmación de la adopción");
	    ventana.setSize(700, 500);
	    ventana.setLocationRelativeTo(null);

	    JPanel panelPrincipal = new JPanel(new BorderLayout());
	    panelPrincipal.setBackground(new Color(233, 220, 209));
	    panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(80, 55, 30), 30));

	    // ---- Central: foto + botón ----
	    JPanel panelCentral = new JPanel(new GridLayout(1, 2, 30, 10));
	    panelCentral.setBackground(new Color(233, 220, 209));

	    JPanel panelFoto = new JPanel();
	    panelFoto.setBackground(new Color(233, 220, 209));
	    panelFoto.setLayout(new BoxLayout(panelFoto, BoxLayout.Y_AXIS));
	    ImageIcon icono = new ImageIcon(createCircleImage("imagenes/roedores/roedor" + (indiceRoedor + 1) + ".png", 180, new Color(80, 55, 30)));
	    JLabel foto = new JLabel(icono);
	    foto.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelFoto.add(Box.createVerticalGlue());
	    panelFoto.add(foto);
	    panelFoto.add(Box.createVerticalGlue());

	    JPanel panelDer = new JPanel();
	    panelDer.setBackground(new Color(233, 220, 209));
	    panelDer.setLayout(new BoxLayout(panelDer, BoxLayout.Y_AXIS));

	    JLabel huellas = new JLabel("🐾  🐾  🐾");
	    huellas.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 44));
	    huellas.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JButton botonDescargar = new JButton("Descargar certificado");
	    botonDescargar.setFont(new Font("Verdana", Font.BOLD, 15));
	    botonDescargar.setBackground(new Color(206, 171, 139));
	    botonDescargar.setForeground(Color.WHITE);
	    botonDescargar.setFocusPainted(false);
	    botonDescargar.setAlignmentX(Component.CENTER_ALIGNMENT);
	    botonDescargar.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
	    botonDescargar.addActionListener(e -> generarCertificadoRoedor(indiceRoedor));

	    JPanel panelMensajeDescarga = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    panelMensajeDescarga.setBackground(new Color(233, 220, 209));
	    JLabel mensajeDescarga = new JLabel("¡Guarda tu certificado!");
	    mensajeDescarga.setFont(new Font("Verdana", Font.ITALIC, 14));
	    mensajeDescarga.setForeground(new Color(95, 65, 45));
	    panelMensajeDescarga.add(mensajeDescarga);

	    panelDer.add(Box.createVerticalGlue());
	    panelDer.add(huellas);
	    panelDer.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelDer.add(botonDescargar);
	    panelDer.add(panelMensajeDescarga);
	    panelDer.add(Box.createVerticalGlue());

	    panelCentral.add(panelFoto);
	    panelCentral.add(panelDer);
	    panelPrincipal.add(panelCentral, BorderLayout.CENTER);

	    // ---- Mensaje final: dos JLabel perfectamente centrados ----
	    JPanel panelMensajes = new JPanel();
	    panelMensajes.setBackground(new Color(233, 220, 209));
	    panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));

	    // COLOR INTENSO para el nombre
	    String colorNombre = "#FF7300"; // NARANJA VIBRANTE

	    JLabel labelAgradecimiento = new JLabel(
	        "<html>¡Gracias por adoptar a <span style='color:" + colorNombre + "; font-weight:bold;'>" +
	        roedores[indiceRoedor].getNombre() +
	        "</span> y darle un nuevo hogar lleno de amor!</html>"
	    );
	    labelAgradecimiento.setFont(new Font("Verdana", Font.BOLD, 15));
	    labelAgradecimiento.setForeground(new Color(80, 55, 30));
	    labelAgradecimiento.setHorizontalAlignment(SwingConstants.CENTER);
	    labelAgradecimiento.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel labelCambioVida = new JLabel(
	        "<html>Tu adopción ha cambiado una vida.</html>"
	    );
	    labelCambioVida.setFont(new Font("Verdana", Font.BOLD, 15));
	    labelCambioVida.setForeground(new Color(80, 55, 30));
	    labelCambioVida.setHorizontalAlignment(SwingConstants.CENTER);
	    labelCambioVida.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel labelCierre = new JLabel(
	        "<html><div style='font-style:italic; color:#5F412D; font-size:12pt;'>¡Gracias por creer en el amor animal!</div></html>"
	    );
	    labelCierre.setFont(new Font("Georgia", Font.ITALIC, 14));
	    labelCierre.setHorizontalAlignment(SwingConstants.CENTER);
	    labelCierre.setAlignmentX(Component.CENTER_ALIGNMENT);

	    panelMensajes.add(labelAgradecimiento);
	    panelMensajes.add(labelCambioVida);
	    panelMensajes.add(Box.createRigidArea(new Dimension(0, 5)));
	    panelMensajes.add(labelCierre);

	    panelPrincipal.add(panelMensajes, BorderLayout.SOUTH);

	    ventana.add(panelPrincipal);
	    ventana.setVisible(true);
	}


	//---------------------------------------------FUNCION PARA GENERAR Y DESCARGAR CERTIFICADO------------------------------------------------

	private void generarCertificadoRoedor(int indiceRoedor) {
	    // Carpeta donde se guardarán los certificados
	    File carpeta = new File("certificados/certificadosRoedores"); 
	    
	    // Archivo dentro de la carpeta
	    File archivo = new File(carpeta, "Certificado_" + roedores[indiceRoedor].getNombre() + ".txt");

	    try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
	        writer.println("       🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾");
	        writer.println("       Certificado de Adopción       ");
	        writer.println("       🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾");
	        writer.println();
	        writer.println("Se confirma que has adoptado a " + roedores[indiceRoedor].getNombre() + " 🐹");
	        writer.println();
	        writer.println("Queremos expresarte nuestro agradecimiento por brindarle un hogar seguro y lleno de cuidado.");
	        writer.println(roedores[indiceRoedor].getNombre() + " podrá disfrutar de una vida protegida y feliz gracias a tu decisión.");
	        writer.println();
	        writer.println("Esperamos que cada día junto a tu nuevo compañero sea especial y lleno de alegría.");
	        writer.println();
	        writer.println("────────────────────────────");
	        writer.println("Fecha: " + java.time.LocalDate.now());
	        writer.println("Refugio: 🐾Colas y Plumas🐾");
	        writer.println("────────────────────────────");
	        writer.println();

	        JOptionPane.showMessageDialog(null,
	            "¡Certificado de " + roedores[indiceRoedor].getNombre() + " guardado exitosamente!");
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
