package gui;

import Domain.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import DB.GestorBD;

public class VentanaMascotas extends JFrame {

    private Usuario user;
    private JFrame ventanaPerfil;
    private JPanel panelMascotas;
    private GestorBD gestor;

    public VentanaMascotas(JFrame ventanaPerfil, Usuario user, GestorBD gestor) {
        this.user = user;
        this.ventanaPerfil = ventanaPerfil;
        this.gestor = gestor;

        setTitle("Mascotas de " + user.getNombre());
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 240, 245)); // rosa suave

        Font fuenteTexto = new Font("Arial", Font.BOLD, 12);
        Font fuenteTitulos = new Font("Arial", Font.BOLD, 14);
        Color forestGreen = new Color(30, 77, 69);
        Color tealGreen = new Color(0, 127, 110);
        
        // --- PANEL SUPERIOR: MENÚ -------------------------------------------------
        JPanel panelMenu = new JPanel();
        
        // borde
        TitledBorder bordeMenu = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE,2),
                "MENÚ",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                fuenteTitulos
        );
        
        bordeMenu.setTitleColor(Color.WHITE);
        panelMenu.setBorder(bordeMenu);
        
        
        // fondo del panel menu 
        panelMenu.setBackground(forestGreen);
        panelMenu.setLayout(new FlowLayout());

        JButton perfilBtn = new JButton("Perfil");
        perfilBtn.addActionListener(e -> {
            ventanaPerfil.setVisible(true);
            dispose();
        });
        perfilBtn.setFont(fuenteTitulos);  
        perfilBtn.setForeground(forestGreen);
        
        panelMenu.add(perfilBtn);
        add(panelMenu, BorderLayout.NORTH);

        // --- PANEL CENTRAL CON MASCOTAS -----------------------------------------
        panelMascotas = new JPanel();
        panelMascotas.setLayout(new GridLayout(0, 2, 10, 10));
        panelMascotas.setBackground(forestGreen);
        panelMascotas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(panelMascotas);
        scroll.setBackground(forestGreen);
        TitledBorder bordeScroll = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(tealGreen,2),
                "Tus Mascotas",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                fuenteTitulos);
        bordeScroll.setTitleColor(tealGreen);
        scroll.setBorder(bordeScroll);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);

        // Cargar las mascotas del CSV
        cargarMascotas();

        setVisible(true);
    }

    private void cargarMascotas() {
        panelMascotas.removeAll();

        ArrayList<Animal> mascotas = gestor.obtenerMascotasUsuario(user);
        user.setMascotas(mascotas);

        if (mascotas.isEmpty()) {
            JLabel noPets = new JLabel("No tienes mascotas registradas.", JLabel.CENTER);
            noPets.setFont(new Font("SansSerif", Font.BOLD, 14));
            panelMascotas.add(noPets);
        } else {
            for (Animal m : mascotas) {
                panelMascotas.add(crearTarjetaMascota(m));
            }
        }

        panelMascotas.revalidate();
        panelMascotas.repaint();
    }

    private JPanel crearTarjetaMascota(Animal mascota) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(0, 127, 110));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Imagen circular de la mascota
        JLabel fotoLabel = new JLabel();
        fotoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        System.out.println(mascota.getImagenPath());
        fotoLabel.setIcon(new ImageIcon(createCircularImage("imagenes/" + mascota.getTipoAnimal().toLowerCase() +"_2/" + mascota.getNombre() + ".png", 80)));
        card.add(fotoLabel);
        card.add(Box.createVerticalStrut(10));

        JLabel nombreLbl = new JLabel("<html><b>" + mascota.getNombre() + "</b></html>", JLabel.CENTER);
        nombreLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        nombreLbl.setForeground(Color.WHITE);
        nombreLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nombreLbl);

        JLabel tipoLbl = new JLabel("Tipo: " + mascota.getRaza(), JLabel.CENTER);
        tipoLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        tipoLbl.setForeground(Color.WHITE);
        JLabel edadLbl = new JLabel("Edad: " + mascota.getEdad() + " años", JLabel.CENTER); 
        edadLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        edadLbl.setForeground(Color.WHITE);


        card.add(tipoLbl);
        card.add(edadLbl);

        return card;
    }

    private Image createCircularImage(String imagePath, int size) {
        try {
            BufferedImage original = ImageIO.read(new File(imagePath));
            Image scaled = original.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            BufferedImage circular = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = circular.createGraphics();
            g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, size, size));
            g2.drawImage(scaled, 0, 0, null);
            g2.dispose();
            return circular;
        } catch (IOException e) {
            return new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        }
    }

   
}

