package gui;

import java.awt.BorderLayout;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import DB.GestorBD;
import Domain.Animal;
import Domain.Gato;
import Domain.Pajaro;
import Domain.Perro;
import Domain.Roedor;
import Domain.Usuario;

//--------------------------CLASE APARTE PARA MOSTRAR CARACTERISTICAS DE UN ANIMAL--------------------------
public class VentanaCaracteristicas {

  //---------------------------------------------------ventana caracteristicas animal-------------------------------------------------------------
  //panel que se abre despues de darle al boton 'ver mas' para ver mejor las caracteristicas de cada animal

  public VentanaCaracteristicas(int indiceAnimal, Animal[] animales, Usuario user, GestorBD gestor) {
      JFrame ventanaCarac = new JFrame("Características del " + devolverTipoAnimal(indiceAnimal, animales));
      ventanaCarac.setSize(700, 500);
      ventanaCarac.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      ventanaCarac.setLocationRelativeTo(null);
      ventanaCarac.getContentPane().setLayout(new BoxLayout(ventanaCarac.getContentPane(), BoxLayout.Y_AXIS));

      //--------------------------------------------------------panel perfil-------------------------------------------------------------------------

      JPanel panelPerfil = new JPanel();
      panelPerfil.setPreferredSize(new Dimension(500, 140));
      panelPerfil.setBackground(new Color(200, 180, 155));

      ImageIcon fotoPerfilGato = new ImageIcon(createCircleImage(devolverRutaAnimal(indiceAnimal, animales), 110, new Color(80, 55, 30))); 
      JLabel fotoLabelGato = new JLabel(fotoPerfilGato);
      fotoLabelGato.setHorizontalAlignment(JLabel.CENTER);
      panelPerfil.add(fotoLabelGato);

      panelPerfil.setBorder(BorderFactory.createTitledBorder(
          BorderFactory.createLineBorder(new Color(80, 55, 30)),
          animales[indiceAnimal].sonidoAnimales(), TitledBorder.LEFT, TitledBorder.TOP, null, new Color(80, 55, 30)
      ));

      //----------------------------------------------------------panel info-------------------------------------------------------------------------

      JPanel panelInfo = new JPanel();
      panelInfo.setPreferredSize(new Dimension(500, 260));
      panelInfo.setBackground(new Color(233, 220, 209));
      panelInfo.setLayout(new BorderLayout(20, 20));

      Animal animal = animales[indiceAnimal];

      // -----------titulo del gato en grande--------

      JLabel nombreGato = new JLabel(animal.getNombre(), JLabel.CENTER);
      nombreGato.setFont(new Font("Verdana", Font.BOLD, 36));
      nombreGato.setForeground(new Color(80, 55, 30));
      panelInfo.add(nombreGato, BorderLayout.NORTH);

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
      double edad = animal.getEdad();
      String gatoEdad;
      if (edad == Math.floor(edad)) {
      //número entero (sin decimales)
          gatoEdad = String.format("%.0f", edad);
      } else {
      //número decimal no entero
          gatoEdad = String.format("%.1f", edad);
      }

      //resalto de etiquetas AI generated
      JLabel edadLabel = new JLabel("<html><b>Edad:</b> " + gatoEdad + " años</html>", JLabel.CENTER);// poner en negrita edad
      edadLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
      edadLabel.setBorder(bordeDatos);
      edadLabel.setForeground(new Color(80, 55, 30));
      panelDatos.add(edadLabel);

      //sexo
      JLabel sexoLabel = new JLabel("<html><b>Sexo:</b> " + animal.getSexo() + "</html>", JLabel.CENTER);
      sexoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
      sexoLabel.setBorder(bordeDatos);
      sexoLabel.setForeground(new Color(80, 55, 30));
      panelDatos.add(sexoLabel);

      //raza
      JLabel razaLabel = new JLabel("<html><b>Raza:</b> " + animal.getRaza() + "</html>", JLabel.CENTER);
      razaLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
      razaLabel.setBorder(bordeDatos);
      razaLabel.setForeground(new Color(80, 55, 30));
      panelDatos.add(razaLabel);

      //peso
      JLabel pesoLabel = new JLabel("<html><b>Peso:</b> " + animal.getPeso() + " kg</html>", JLabel.CENTER);
      pesoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
      pesoLabel.setBorder(bordeDatos);
      pesoLabel.setForeground(new Color(80, 55, 30));
      panelDatos.add(pesoLabel);

      //personalidad
      JLabel personalidadLabel = new JLabel("<html><b>Personalidad:</b> " + animal.getDescripcion_personalidad() + "</html>", JLabel.CENTER);
      personalidadLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
      personalidadLabel.setForeground(new Color(80, 55, 30));
      personalidadLabel.setBorder(BorderFactory.createCompoundBorder(
          BorderFactory.createLineBorder(new Color(80, 55, 30), 2, true),
          BorderFactory.createEmptyBorder(10, 20, 10, 20)
      ));
      panelDatos.add(personalidadLabel);

      //rasgos
      JLabel rasgosLabel = new JLabel("<html><b>Rasgos:</b> " + animal.getDescripcion_fisica() + "</html>", JLabel.CENTER);
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
      botonAdopt.setForeground(new Color(80, 55, 30));

      botonAdopt.addActionListener(e -> {
          if(animal.isAdoptado() == false) {
              VentanaConfirmacion confirmacion = new VentanaConfirmacion(indiceAnimal, animales, user, gestor); 
              animal.setAdoptado(true);
              //AQUI INSERTARMASCOTA Y ACTUALIZAR ANIMAL PARA CONFIRMAR QUE ESTA ADOPTADO
              gestor.actualizarAnimal(indiceAnimal+1);
              
              
          } else {
              JOptionPane.showMessageDialog(null,
                  "¡" + animal.getNombre() + " ya ha sido adoptado!");

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
      botonAtras.setForeground(new Color(80, 55, 30));

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
          ventanaCarac.setVisible(false);
          // Si quieres reactivar una ventana principal, ponlo aquí
      });

      // ---------añadir al frame-----------

      ventanaCarac.add(panelPerfil);
      ventanaCarac.add(panelInfo);
      ventanaCarac.setVisible(true);
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
  
  
  //-----------------------------------------------------FUNCION PARA SABER QUE TIPO DE ANIMAL ES----------------------------------------------
  private String devolverTipoAnimal(int indiceAnimal, Animal[] animales) {
	  
	  String tipo = "";
	  if(animales[indiceAnimal] instanceof Gato) {
		  tipo = "gato";
	  }
	  else if(animales[indiceAnimal] instanceof Pajaro) {
		  tipo = "pájaro";

	  }else if (animales[indiceAnimal] instanceof Perro) {
		  tipo = "perro";
		  
	  } else if (animales[indiceAnimal] instanceof Roedor) {
		  tipo = "roedor";
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

