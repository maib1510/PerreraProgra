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

public class VentanaMascotas extends JFrame {

    private Usuario user;
    private JFrame ventanaPerfil;
    private JPanel panelMascotas;

    public VentanaMascotas(JFrame ventanaPerfil, Usuario user) {
        this.user = user;
        this.ventanaPerfil = ventanaPerfil;

        setTitle("Mascotas de " + user.getNombre());
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 240, 245)); // rosa suave

        // --- PANEL SUPERIOR: MENÚ -------------------------------------------------
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(204, 236, 247));
        panelMenu.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Menú",
            TitledBorder.CENTER,
            TitledBorder.TOP
        ));

        JButton perfilBtn = new JButton("Perfil");
        perfilBtn.addActionListener(e -> {
            ventanaPerfil.setVisible(true);
            dispose();
        });

        panelMenu.add(perfilBtn);
        add(panelMenu, BorderLayout.NORTH);

        // --- PANEL CENTRAL CON MASCOTAS -----------------------------------------
        panelMascotas = new JPanel();
        panelMascotas.setLayout(new GridLayout(0, 2, 10, 10));
        panelMascotas.setBackground(new Color(255, 250, 250));
        panelMascotas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(panelMascotas);
        scroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Tus Mascotas",
            TitledBorder.CENTER,
            TitledBorder.TOP
        ));
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);

        // Cargar las mascotas del CSV
        cargarMascotas();

        setVisible(true);
    }

    private void cargarMascotas() {
        panelMascotas.removeAll();

        ArrayList<Animal> mascotas = cargarMascotasDesdeCSV();
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
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Imagen circular de la mascota
        JLabel fotoLabel = new JLabel();
        fotoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        System.out.println(mascota.getImagenPath());
        fotoLabel.setIcon(new ImageIcon(createCircularImage(mascota.getImagenPath(), 80)));
        card.add(fotoLabel);
        card.add(Box.createVerticalStrut(10));

        JLabel nombreLbl = new JLabel("<html><b>" + mascota.getNombre() + "</b></html>", JLabel.CENTER);
        nombreLbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        nombreLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nombreLbl);

        JLabel tipoLbl = new JLabel("Tipo: " + mascota.getRaza(), JLabel.CENTER);
        tipoLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel edadLbl = new JLabel("Edad: " + mascota.getEdad() + " años", JLabel.CENTER);
        edadLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

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

    // --- Cargar mascotas desde CSV según tipo ---
    public ArrayList<Animal> cargarMascotasDesdeCSV() {
    	ArrayList<Animal> mascotasUsuario = new ArrayList<>();
        String rutaCSV = "mascotas.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            br.readLine(); // Saltar cabecera
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 9) continue; // esperar columna extra: tipo

                String nombre = datos[0];
                String sexo = datos[1];
                float edad = Float.parseFloat(datos[2]);
                String raza = datos[3];
                float peso = Float.parseFloat(datos[4]);
                String descPers = datos[5];
                String descFis = datos[6];
                boolean adoptado = Boolean.parseBoolean(datos[7]);
                String tipo = datos[8];

                if (datos[9].equals(user.getUsername())) {
                	Animal m;
                    switch (tipo.toLowerCase()) {
                        case "perro":
                            m = new Perro(nombre, sexo, edad, raza, peso, descPers, descFis, adoptado);
                            break;
                        case "gato":
                            m = new Gato(nombre, sexo, edad, raza, peso, descPers, descFis, adoptado);
                            break;
                        default:
                            continue; // ignorar si no se reconoce el tipo
                    }

                    mascotasUsuario.add(m);
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mascotasUsuario;
    }
}

