package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


	public VentanaGatos(Gato[] gatos) {

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

		//---------------------------------------------------------------------------------------------------------------------------------------

		//-----------------------------------------------VENTANA CARACTERISTICAS GATO--------------------------------------------------------------

		//panel que se abre despues de darle al boton 'ver mas' para ver mejor las caracteristicas de cada gato

		//crear ventana y panel
		JFrame ventanaCarac = new JFrame("Características del gato");
		ventanaCarac.setSize(500, 400);
		ventanaCarac.setDefaultCloseOperation(HIDE_ON_CLOSE);
		ventanaCarac.setLocationRelativeTo(null);

		JPanel panelVer = new JPanel();

		ventanaCarac.add(panelVer);

		//listener boton
		for(int i = 0; i < 12; i++) {
			botones[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ventanaCarac.add(panelVer);
					ventanaCarac.setVisible(true);

				}

			});
		}

		frame.add(jscroll);
		add(frame);
		setVisible(true);

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


}
