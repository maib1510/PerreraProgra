package gui;

import Domain.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import DB.GestorBD;

public class VentanaPerfil extends JFrame {
    private JLabel fotoLabel;
    private ImageIcon fotoPerfil;
    private JFrame ventanaAnimales;
    private Usuario user;
    private GestorBD gestor;

    // de momento pongo esto, ya crearé las clases 
    private Perfil perfil = new Perfil("imagenes/fotosPerfil/Image.jpeg", Color.BLACK);
    
    public VentanaPerfil(JFrame ventanaAnterior, Gato[] gatos, Roedor[] roedores, Pajaro[] pajaros, Perro[] perros, Usuario user, GestorBD gestor) {
        this.ventanaAnimales = ventanaAnterior;
        this.user = user;
        this.gestor = gestor;
    
        // ------- CONFIGURACIÓN DE VENTANA -------------------------------------------------------------------------------------------
        setTitle("Perfil de Usuario");
        setSize(500, 420);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---------------------------------------------------------------------------
        Font fuenteTexto = new Font("Arial", Font.BOLD, 12);
        Font fuenteTitulos = new Font("Arial", Font.BOLD, 14);
        Color forestGreen = new Color(30, 77, 69);
        Color tealGreen = new Color(0, 127, 110);

        // -------- PANEL SUPERIOR: MENÚ ----------------------------------------------------------------------------------------------
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

        JButton animales = new JButton("Animales");
        JButton tienda = new JButton("Tienda");
        JButton perfilBtn = new JButton("Perfil");	
        
        animales.setFont(fuenteTitulos);
        animales.setForeground(forestGreen);
        
        tienda.setFont(fuenteTitulos);
        tienda.setForeground(forestGreen);
        
        perfilBtn.setFont(fuenteTitulos);  
        perfilBtn.setForeground(forestGreen);
        
        
        animales.addActionListener(e -> {
            ventanaAnimales.setVisible(true);
            dispose();
        });
        
        
        tienda.addActionListener(e -> {
        	VentanaTienda ventanaTienda = new VentanaTienda(ventanaAnimales, gatos, roedores, pajaros, perros, user, gestor);
        	ventanaTienda.setVisible(true);
        	dispose();
        });       
        // añadir los botones 
        panelMenu.add(perfilBtn);
        panelMenu.add(animales);
        panelMenu.add(tienda);
        
        // añadir el panel 
        add(panelMenu, BorderLayout.NORTH);

        
        
        // --- FOTO DE PERFIL ------------------------------------------------------------------------------------------------------------
        fotoPerfil = new ImageIcon(createCircleImage(perfil.getFotoPerfilPath(), 70, perfil.getColorBorde()));
        fotoLabel = new JLabel(fotoPerfil);
        fotoLabel.setHorizontalAlignment(JLabel.CENTER);
        
        

     // ------- PANEL MEDIO CON FOTO DE PERFIL E INFO ---------------------------------------------------------------
        JPanel centroPanel = new JPanel();
        centroPanel.setLayout(new BoxLayout(centroPanel, BoxLayout.Y_AXIS));
        centroPanel.setBackground(tealGreen); // rosa pastel suave
        
     // Creamos el borde con título
        TitledBorder infoBorder = new TitledBorder(
                BorderFactory.createLineBorder(Color.WHITE,2),
                "INFORMACIÓN",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                fuenteTitulos
        );

        // Le pintamos el título de blanco porque alguien tiene que darle estilo
        infoBorder.setTitleColor(Color.WHITE);

        // Lo metemos dentro del compound con su margen interno
        centroPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        infoBorder,
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                )
        );

        

        // FOTO CENTRADA
        fotoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centroPanel.add(fotoLabel);
        centroPanel.add(Box.createVerticalStrut(15)); // espacio entre foto e info

        // INFO CENTRADA
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // margen interno
            ));

        // etiquetas con estilo
        infoPanel.add(new JLabel("<html><b>Nombre:</b> " + user.getNombre() + " " + user.getApellido() + "</html>"));
        infoPanel.add(new JLabel("<html><b>Telf:</b> " + user.getTelefono() + "</html>"));
        infoPanel.add(new JLabel("<html><b>Email:</b> " + user.getEmail() + "</html>"));
        infoPanel.add(new JLabel("<html><b>Tarjeta:</b> " + user.getTarjeta_bancaria() + "</html>"));

        // estilo de fuente y color
        for (Component c : infoPanel.getComponents()) {
            if (c instanceof JLabel) {
                c.setFont(fuenteTexto);
                c.setForeground(tealGreen);
            }
        }

        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centroPanel.add(infoPanel);
        centroPanel.add(Box.createVerticalStrut(15));

        // BOTÓN “ver mascotas”
        JButton mascotasButton = new JButton("Ver mascotas");
        
        mascotasButton.setForeground(forestGreen);
        mascotasButton.setFont(fuenteTitulos);
        mascotasButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        mascotasButton.addActionListener(e -> {
        	VentanaMascotas ventanaMascotas = new VentanaMascotas(this, user, gestor);
        	ventanaMascotas.setVisible(true);
        	this.setVisible(false);
        });
        centroPanel.add(mascotasButton);


        // PANEL DE BOTONES ABAJO
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        TitledBorder borderPers = new TitledBorder(
                BorderFactory.createLineBorder(Color.WHITE,2),
                "PERSONALIZACIÓN",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                fuenteTitulos
        );

        borderPers.setTitleColor(Color.WHITE);

        buttonPanel.setBorder(borderPers);

        
        buttonPanel.setBackground(forestGreen);
        
        JButton changeColorButton = new JButton("color círculo");
        changeColorButton.setForeground(forestGreen);
        changeColorButton.setFont(fuenteTitulos);
        changeColorButton.addActionListener(new ChangeColorListener());
        
        
        JButton changeImageButton = new JButton("Cambiar imagen");
        changeImageButton.setForeground(forestGreen);
        changeImageButton.setFont(fuenteTitulos);
        
        JButton eliminarCuentaButton = new JButton("Eliminar cuenta");
        eliminarCuentaButton.setForeground(Color.RED);
        eliminarCuentaButton.setFont(fuenteTitulos);

        
        changeImageButton.addActionListener(new ChangeImageListener());
        
        
        eliminarCuentaButton.addActionListener(e -> {

            int opcion = JOptionPane.showConfirmDialog(
                VentanaPerfil.this,
                "¿Seguro que quieres eliminar tu cuenta?\nEsta acción no se puede deshacer.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {

                boolean ok = gestor.eliminarUsuario(user.getId_usuario()) && gestor.eliminarPerfil(user.getId_usuario());

                if (ok) {
                    JOptionPane.showMessageDialog(
                        VentanaPerfil.this,
                        "Cuenta eliminada correctamente.",
                        "Cuenta eliminada",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    // Volver al login
                    new VentanaInicioSesion(gatos, roedores, pajaros, perros, gestor).setVisible(true);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(
                        VentanaPerfil.this,
                        "No se pudo eliminar la cuenta.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        
        buttonPanel.add(changeColorButton);
        buttonPanel.add(changeImageButton);
        buttonPanel.add(eliminarCuentaButton);        // Añadir todo al layout principal
        add(panelMenu, BorderLayout.NORTH);
        add(centroPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

                

        
    

        setVisible(true);

    }
    

    private BufferedImage createCircleImage(String imagePath, int diameter, Color borderColor) {
        BufferedImage originalImage = null;

        // Intenta cargar como recurso (útil si la imagen está dentro del proyecto/JAR)
        try {
            InputStream is = getClass().getResourceAsStream(imagePath);
            if (is != null) {
                originalImage = ImageIO.read(is);
            } else {
                File file = new File(imagePath);
                if (file.exists()) {
                    originalImage = ImageIO.read(file);
                } else {
                    System.err.println("Imagen no encontrada: " + imagePath);
                    return null;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            return null;
        }

        if (originalImage == null) {
            System.err.println("Error: la imagen no se pudo cargar.");
            return null;
        }

        Image scaledImage = originalImage.getScaledInstance(diameter, diameter, Image.SCALE_SMOOTH);
        BufferedImage circularImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = circularImage.createGraphics();
        g.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
        g.drawImage(scaledImage, 0, 0, null);
        g.setClip(null);
        g.setColor(borderColor);
        g.setStroke(new BasicStroke(3));
        g.drawOval(0, 0, diameter - 1, diameter - 1);
        g.dispose();

        return circularImage;
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
                            String imagePath = "imagenes/fotosPerfil" + user.getPerfil().getUsername() + ".jpg";
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
