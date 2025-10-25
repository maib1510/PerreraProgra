package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class VentanaGatos extends JFrame {

	private static final long serialVersionUID = 1L;


	VentanaGatos() {

		setTitle("GATOS");
		setSize(850, 600);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//------------------------FRAME CON TITULO---------------------------
		JPanel frame = new JPanel();
		frame.setLayout(new BorderLayout());
		
		JLabel titulo = new JLabel();
		titulo.setText("GATOS PARA ADOPTAR");
		titulo.setHorizontalAlignment(SwingConstants.CENTER); //PARA PONER EN EL CENTRO CON BORDERLAYOUT
		titulo.setFont(new Font("Verdana", Font.BOLD, 20));
		titulo.setForeground(new Color(255, 140, 0));
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
			        BorderFactory.createLineBorder(Color.BLACK), "nombreGato", TitledBorder.LEFT, TitledBorder.TOP
			));
			paneles[i] = panel;
			panelPrincipal.add(panel);
				
		}


		//------------------------LABELS---------------------------
		
		//array de labels (12 labels)
		JLabel[] labels = new JLabel[12];

		//añadir cada label al panel y poner una imagen para cada una
		for (int i = 0; i < 12; i ++) {
			//JLabel label = crearImagen("C:/Users/mireia.otaduy/Downloads/gato" + (i+1) + ".jpg", 200, 150); para fotos diferentes
			JLabel label = crearImagen("C:/Users/mireia.otaduy/Downloads/gato1.jpg", 200, 150);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			labels[i] = label;
			paneles[i].add(label);
		}

		//------------------------BUTTONS---------------------------
		
		//array de botones (12 botones) para adoptar
		JButton[] botones = new JButton[12];


		//botones adoptar
		for(int i = 0; i < 12; i++) {
			JButton boton = new JButton();
			boton.setText("Adoptar");
			boton.setMaximumSize(new Dimension(100, 30)); // ancho x alto
			boton.setAlignmentX(Component.CENTER_ALIGNMENT); // centrado horizontal
			botones[i] = boton;
			paneles[i].add(boton);
		}

		//listener botones (ir a otra ventana/panel para ver caracteristicas del gato y poder adoptarlo)


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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaGatos ventana = new VentanaGatos();
		System.out.println(ventana);
	}

}
