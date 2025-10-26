package gui;

import Domain.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaPerfil extends JFrame {
    private JLabel fotoLabel;
    private ImageIcon fotoPerfil;
    private JFrame ventanaAnimales;

    // de momento pongo esto, ya crearé las clases 
    private Perfil perfil = new Perfil("imagenes/fotosPerfil/Image.jpeg", Color.BLACK);
    private Usuario user = new Usuario("nombreUsuario", "nombre", "apellido", new ArrayList<>(), 20, 000000000, "usuario@email.com", 1010238491);
    
    public VentanaPerfil(JFrame ventanaAnterior) {
        this.ventanaAnimales = ventanaAnterior;

        // ------- CONFIGURACIÓN DE VENTANA -------------------------------------------------------------------------------------------
        setTitle("Perfil de Usuario");
        setSize(400, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

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
        
        tienda.addActionListener(e -> {
			VentanaTienda ventanaTienda = new VentanaTienda(this);
			ventanaTienda.setVisible(false);
			SwingUtilities.invokeLater(() -> ventanaTienda.setVisible(true));
			this.setVisible(false);
		});
        
        // añadir los botones 
        panelMenu.add(perfilBtn);
        panelMenu.add(animales);
        panelMenu.add(tienda);
        
        // añadir el panel 
        add(panelMenu, BorderLayout.NORTH);

        
        
        // --- FOTO DE PERFIL ------------------------------------------------------------------------------------------------------------
        fotoPerfil = new ImageIcon(createCircleImage(perfil.getFotoPerfilPath(), 80, perfil.getColorBorde()));
        fotoLabel = new JLabel(fotoPerfil);
        fotoLabel.setHorizontalAlignment(JLabel.CENTER);
        
        

     // ------- PANEL MEDIO CON FOTO DE PERFIL E INFO ---------------------------------------------------------------
        JPanel centroPanel = new JPanel();
        centroPanel.setLayout(new BoxLayout(centroPanel, BoxLayout.Y_AXIS));
        centroPanel.setBackground(new Color(255, 182, 193)); // rosa pastel suave
        
        centroPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                    "Información",
                    TitledBorder.CENTER,
                    TitledBorder.TOP
                ),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // margen interno
        ));
        
        //centroPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // FOTO CENTRADA
        fotoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centroPanel.add(fotoLabel);
        centroPanel.add(Box.createVerticalStrut(15)); // espacio entre foto e info

        // INFO CENTRADA
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // margen interno
            ));

        // etiquetas con estilo
        infoPanel.add(new JLabel("<html><b>Nombre:</b> " + user.getNombre() + " " + user.getApellido() + "</html>"));
        infoPanel.add(new JLabel("<html><b>Telf:</b> " + user.getTelefono() + "</html>"));
        infoPanel.add(new JLabel("<html><b>Email:</b> " + user.getEmail() + "</html>"));
        infoPanel.add(new JLabel("<html><b>Tarjeta:</b> " + user.getTarjeta_bancaria() + "</html>"));

        // estilo de fuente y color
        Font infoFont = new Font("SansSerif", Font.PLAIN, 13);
        for (Component c : infoPanel.getComponents()) {
            if (c instanceof JLabel) {
                c.setFont(infoFont);
                c.setForeground(new Color(60, 60, 60));
            }
        }

        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centroPanel.add(infoPanel);
        centroPanel.add(Box.createVerticalStrut(15));

        // BOTÓN “ver mascotas”
        JButton mascotasButton = new JButton("Ver mascotas");
        mascotasButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centroPanel.add(mascotasButton);


        // PANEL DE BOTONES ABAJO
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        buttonPanel.setBorder(
                BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                    "Personalización",
                    TitledBorder.CENTER,
                    TitledBorder.TOP
                )
        );
        
        buttonPanel.setBackground(new Color(204, 236, 247));
        JButton changeColorButton = new JButton("color círculo");
        changeColorButton.addActionListener(new ChangeColorListener());
        
        
        JButton changeImageButton = new JButton("Cambiar imagen");
        changeImageButton.addActionListener(new ChangeImageListener());
        
        buttonPanel.add(changeColorButton);
        buttonPanel.add(changeImageButton);

        // Añadir todo al layout principal
        add(panelMenu, BorderLayout.NORTH);
        add(centroPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

                

        
    

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
                fotoPerfil = new ImageIcon(createCircleImage(perfil.getFotoPerfilPath(), 80, nuevoColor)); // genera una nueva imagen circular con el borde actualizado
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
                            String imagePath = "imagenes/" + user.getNombre() + ".jpg";
                            File destino = new File(imagePath);
                            destino.getParentFile().mkdirs();
                            ImageIO.write(nuevaImagen, "jpg", destino);

                            fotoPerfil = new ImageIcon(createCircleImage(imagePath, 80, perfil.getColorBorde()));
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
    }
}
