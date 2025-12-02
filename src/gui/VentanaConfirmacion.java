package gui;

import java.awt.BorderLayout;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.SwingConstants;

import DB.GestorBD;
import Domain.Adopcion;
import Domain.Animal;
import Domain.Gato;
import Domain.Pajaro;
import Domain.Perro;
import Domain.Roedor;
import Domain.Usuario;

public class VentanaConfirmacion extends JFrame {

	public VentanaConfirmacion(int indiceAnimal, Animal[] animales, Usuario user, GestorBD gestor) {


		setTitle("Confirmación de la adopción");
		setSize(700, 500);
		setLocationRelativeTo(null);


		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(new Color(233, 220, 209));
		panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(80, 55, 30), 30));

		// ---- Central: foto + botón ----
		JPanel panelCentral = new JPanel(new GridLayout(1, 2, 30, 10));
		panelCentral.setBackground(new Color(233, 220, 209));

		JPanel panelFoto = new JPanel();
		panelFoto.setBackground(new Color(233, 220, 209));
		panelFoto.setLayout(new BoxLayout(panelFoto, BoxLayout.Y_AXIS));
		ImageIcon icono = new ImageIcon(createCircleImage(
				devolverRutaAnimal(indiceAnimal, animales), 180, new Color(80, 55, 30)
				));
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
		botonDescargar.addActionListener(e -> generarCertificado(indiceAnimal, animales));

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

		// ---- Mensaje final abajo ----
		JPanel panelMensajes = new JPanel();
		panelMensajes.setBackground(new Color(233, 220, 209));
		panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));

		String colorNombre = "#FF7300"; // naranja (AI generated resalto de color)

		JLabel labelAgradecimiento = new JLabel(
				"<html>¡Gracias por adoptar a <span style='color:" + colorNombre + "; font-weight:bold;'>" +
						animales[indiceAnimal].getNombre() +
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

		add(panelPrincipal);
		setVisible(true);
		
		Adopcion adopcionActual = new Adopcion(animales[indiceAnimal], user);
		gestor.insertarMascota(adopcionActual);

	}

	//---------------------------------FUNCION PARA GENERAR CERTIFICADO----------------------------------------
	
	private void generarCertificado(int indiceAnimal, Animal[] animales) {
		// carpeta donde se guardarán los certificados (puedes hacer la ruta dinámica si también tienes perros)
		File carpeta = new File(devolverRutaCertificadoAnimal(indiceAnimal, animales));

		// archivo dentro de la carpeta
		File archivo = new File(carpeta, "Certificado_" + animales[indiceAnimal].getNombre() + ".txt");

		try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
			writer.println("       🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾");//text AI generated
			writer.println("       Certificado de Adopción       ");
			writer.println("       🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾");
			writer.println();
			writer.println("¡Qué alegría! 🎉");
			writer.println();
			writer.println("Se confirma que has adoptado a " + animales[indiceAnimal].getNombre() + " " + emojiTipoAnimal(indiceAnimal, animales));
			writer.println();
			writer.println("Estamos muy felices por ti y por " + animales[indiceAnimal].getNombre() + ".");
			writer.println("Gracias a tu decisión, " + animales[indiceAnimal].getNombre() + " tendrá un hogar lleno de amor y cuidado.");
			writer.println("Este es un momento muy especial y queremos celebrarlo contigo.");
			writer.println("Esperamos que cada día junto a tu nuevo compañero sea especial y lleno de alegría.");
			writer.println();
			writer.println("Disfruta cada instante junto a tu nuevo compañero,");
			writer.println("y recuerda que tu gesto significa un mundo para él.");
			writer.println();
			writer.println("────────────────────────────");
			writer.println("Fecha: " + java.time.LocalDate.now());
			writer.println("Refugio: 🐾Colas y Plumas🐾");
			writer.println("────────────────────────────");
			writer.println();

			JOptionPane.showMessageDialog(this,
					"¡Certificado de " + animales[indiceAnimal].getNombre() + " guardado exitosamente!");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this,
					"Error al generar el certificado: " + ex.getMessage());
		}
	}
	
	 //----------------------------------------------------FUNCION PARA DEVOLVER RUTA------------------------------------------------------------
	  private String devolverRutaAnimal(int indiceAnimal, Animal[] animales) {
		  
		  String ruta = "";
		  if(animales[indiceAnimal] instanceof Gato) {
			  ruta = "imagenes/gatos/gato"+ (indiceAnimal + 1) + ".png";
		  }
		  else if(animales[indiceAnimal] instanceof Pajaro) {
			  ruta = "imagenes/pajaros/pajaro"+ (indiceAnimal + 1) + ".png";

		  }else if (animales[indiceAnimal] instanceof Perro) {
			  ruta = "imagenes/perros/perro"+ (indiceAnimal + 1) + ".png";
			  
		  } else if (animales[indiceAnimal] instanceof Roedor) {
			  ruta = "imagenes/roedores/roedor"+ (indiceAnimal + 1) + ".png";
		  }
		  return ruta;
	  }
	  
	  
	  private String devolverRutaCertificadoAnimal(int indiceAnimal, Animal[] animales) {
		  
		  String ruta = "";
		  if(animales[indiceAnimal] instanceof Gato) {
			  ruta = "certificados/certificadosGatos";
		  }
		  else if(animales[indiceAnimal] instanceof Pajaro) {
			  ruta = "certificados/certificadosPajaros";

		  }else if (animales[indiceAnimal] instanceof Perro) {
			  ruta = "certificados/certificadosPerros";
			  
		  } else if (animales[indiceAnimal] instanceof Roedor) {
			  ruta = "certificados/certificadosRoedores";
		  }
		  return ruta;
	  }
	  
	
	
	//-------------------------------------------------FUNCION PARA SABER EMOJI DE TIPO ANIMAL--------------------------------------------------
	
	  private String emojiTipoAnimal(int indiceAnimal, Animal[] animales) {
		  
		  String tipo = "";
		  if(animales[indiceAnimal] instanceof Gato) {
			  tipo = "😺";
		  }
		  else if(animales[indiceAnimal] instanceof Pajaro) {
			  tipo = "🐦";

		  }else if (animales[indiceAnimal] instanceof Perro) {
			  tipo = "🐶";
			  
		  } else if (animales[indiceAnimal] instanceof Roedor) {
			  tipo = "🐭";
		  }
		  return tipo;
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

}
