package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Domain.Usuario;

public class VentanaTienda extends JFrame {
    private static final long serialVersionUID = 1L;
	private JFrame ventanaAnimales;
	private Usuario user;
	
	VentanaTienda(JFrame ventanaAnterior, Usuario user) {
		this.ventanaAnimales = ventanaAnterior;
		 
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
        
        
		this.setVisible(false);
	}

}
