package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Domain.Producto;
import Domain.Usuario;

public class VentanaTienda extends JFrame {
    private static final long serialVersionUID = 1L;
	private JFrame ventanaAnimales;
	private Usuario user;
	private JPanel panelProductos;
	
	VentanaTienda(JFrame ventanaAnterior, Usuario user) {
		this.ventanaAnimales = ventanaAnterior;
		 this.user = user;   
		 
		this.setTitle("Tienda de Productos");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		// -------- PANEL SUPERIOR: MENÚ ----------------------------------------------------------------------------------------------
        JPanel panelMenu = new JPanel();
        
        // borde
        panelMenu.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Menú",
                TitledBorder.CENTER,
                TitledBorder.TOP)
        );
        
        // fondo del panel menu 
        panelMenu.setBackground(new Color(204, 236, 247));
        panelMenu.setLayout(new FlowLayout());

        JButton animales = new JButton("Animales");
        JButton tienda = new JButton("Tienda");
        JButton perfilBtn = new JButton("Perfil");

        // volver a la ventana anterior 
        animales.addActionListener(e -> {
            ventanaAnimales.setVisible(true);
            dispose();
        });
        
        perfilBtn.addActionListener(e -> {
        	VentanaPerfil ventanaPerfil = new VentanaPerfil(this, user);
			ventanaPerfil.setVisible(false);
			SwingUtilities.invokeLater(() -> ventanaPerfil.setVisible(true));
			this.setVisible(false);
		});
        
        // añadir los botones 
        panelMenu.add(perfilBtn);
        panelMenu.add(animales);
        panelMenu.add(tienda);
        
        // añadir el panel 
        add(panelMenu, BorderLayout.NORTH);
        
        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 2, 10, 10));
        panelProductos.setBackground(new Color(255, 250, 250));
        panelProductos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(panelProductos);
        scroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Productos",
            TitledBorder.CENTER,
            TitledBorder.TOP
        ));
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);

        // Cargar las mascotas del CSV
        cargarProductos();
        
		this.setVisible(false);
	}
	
	private void cargarProductos() {
	    String rutaCSV = "productos.csv";   // cámbiala si está en otra carpeta

	    try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {

	        String linea;
	        boolean primera = true;

	        while ((linea = br.readLine()) != null) {

	            // Saltar cabecera
	            if (primera) {
	                primera = false;
	                continue;
	            }

	            String[] datos = linea.split(";");

	            if (datos.length != 4) continue;

	            // Crear el Producto
	            String id = datos[0];
	            String nombre = datos[1];
	            String categoria = datos[2];
	            double precio = Double.parseDouble(datos[3]);

	            Producto p = new Producto(id, nombre, categoria, precio);

	            // --- Crear panel visual para el producto ---
	            JPanel item = new JPanel();
	            item.setLayout(new GridLayout(3, 1));
	            item.setBorder(BorderFactory.createTitledBorder(nombre));

	            item.add(new JLabel("Categoría: " + categoria));
	            item.add(new JLabel("Precio: " + precio + "€"));
	            item.add(new JLabel("ID: " + id));

	            panelProductos.add(item);
	        }

	    } catch (Exception e) {
	        System.out.println("Error al leer productos: " + e.getMessage());
	    }

	    panelProductos.revalidate();
	    panelProductos.repaint();
	}


}
