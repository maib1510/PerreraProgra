package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaPerfil extends JFrame {
    private JLabel fotoLabel;
    private ImageIcon fotoPerfil;
    private JFrame ventanaAnimales;

    // de momento pongo esto, ya crearé las clases 
    private Perfil perfil = new Perfil("/Users/maialenbarredomuro/Desktop/PerreraProgra_/imagenes/Image.jpeg", Color.BLACK);
    private Cliente cliente = new Cliente("Usuario", "usuario@email.com");

    public VentanaPerfil(JFrame ventanaAnterior) {
        this.ventanaAnimales = ventanaAnterior;

        // ------- CONFIGURACIÓN DE VENTANA -------
        setTitle("Perfil de Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // -------- PANEL SUPERIOR: MENÚ ----------
        JPanel panelMenu = new JPanel();
        
        // borde
        panelMenu.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Menú",
                TitledBorder.CENTER,
                TitledBorder.TOP)
        );
        
        // fondo
        panelMenu.setBackground(new Color(245, 245, 245));
        panelMenu.setLayout(new FlowLayout());

        JButton animales = new JButton("Animales");
        JButton tienda = new JButton("Tienda");
        JButton perfilBtn = new JButton("Perfil");

        // volver a la ventana anterior
        animales.addActionListener(e -> {
            ventanaAnimales.setVisible(true);
            dispose();
        });
        
        // añadir los botones
        panelMenu.add(perfilBtn);
        panelMenu.add(animales);
        panelMenu.add(tienda);
        
        // añadir el panel
        add(panelMenu, BorderLayout.NORTH);

        
        
        // --- FOTO DE PERFIL -------
        fotoPerfil = new ImageIcon(createCircleImage(perfil.getFotoPerfilPath(), 100, perfil.getColorBorde()));
        fotoLabel = new JLabel(fotoPerfil);
        fotoLabel.setHorizontalAlignment(JLabel.CENTER);

        // ------- PANEL INFERIOR CON INFO Y BOTONES ----------
        JPanel infoPanel = new JPanel(new GridLayout(5, 1));
        infoPanel.setBorder(new EmptyBorder(0, 15, 5, 15));
        infoPanel.add(new JLabel("Nombre: " + cliente.getNombre()));
        infoPanel.add(new JLabel("Email: " + cliente.getEmail()));

        JButton changeColorButton = new JButton("Cambiar color de círculo");
        changeColorButton.addActionListener(new ChangeColorListener());
        infoPanel.add(changeColorButton);

        JButton changeImageButton = new JButton("Cambiar imagen");
        changeImageButton.addActionListener(new ChangeImageListener());
        infoPanel.add(changeImageButton);

        add(fotoLabel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    

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


    // --- CAMBIO DE COLOR ---
    private class ChangeColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color nuevoColor = JColorChooser.showDialog(VentanaPerfil.this, "Selecciona un color", perfil.getColorBorde()); // abre un selector de color con el color actual como predeterminado
            if (nuevoColor != null) { // si el usuario seleccionó un color y no canceló
                perfil.setColorBorde(nuevoColor); // guarda el nuevo color en el perfil
                fotoPerfil = new ImageIcon(createCircleImage(perfil.getFotoPerfilPath(), 100, nuevoColor)); // genera una nueva imagen circular con el borde actualizado
                fotoLabel.setIcon(fotoPerfil); // actualiza el JLabel con la nueva imagen
                fotoLabel.revalidate(); // fuerza la actualización de la interfaz (opcional, asegura que el layout se refresque)
                fotoLabel.repaint(); // repinta el JLabel para mostrar el cambio inmediatamente
            }
        }
    }

    // --- CAMBIO DE IMAGEN ---
    private class ChangeImageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecciona una nueva imagen de perfil");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));

            int resultado = fileChooser.showOpenDialog(VentanaPerfil.this);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
                if (archivoSeleccionado != null) {
                    try {
                        BufferedImage nuevaImagen = ImageIO.read(archivoSeleccionado);
                        if (nuevaImagen != null) {
                            String imagePath = "imagenes/" + cliente.getNombre() + ".jpg";
                            File destino = new File(imagePath);
                            destino.getParentFile().mkdirs();
                            ImageIO.write(nuevaImagen, "jpg", destino);

                            fotoPerfil = new ImageIcon(createCircleImage(imagePath, 100, perfil.getColorBorde()));
                            fotoLabel.setIcon(fotoPerfil);
                            fotoLabel.revalidate();
                            fotoLabel.repaint();
                        } else {
                            JOptionPane.showMessageDialog(VentanaPerfil.this, "La imagen seleccionada no es válida.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    // --- CLASES DE PRUEBA ------
    private static class Perfil {
        private String fotoPerfilPath;
        private Color colorBorde;
        public Perfil(String fotoPerfilPath, Color colorBorde) {
            this.fotoPerfilPath = fotoPerfilPath;
            this.colorBorde = colorBorde;
        }
        public String getFotoPerfilPath() { return fotoPerfilPath; }
        public Color getColorBorde() { return colorBorde; }
        public void setColorBorde(Color c) { this.colorBorde = c; }
        public int calcularPuntos() { return 123; }
    }

    private static class Cliente {
        private String nombre, email;
        public Cliente(String n, String e) { nombre = n; email = e; }
        public String getNombre() { return nombre; }
        public String getEmail() { return email; }
    }
}
