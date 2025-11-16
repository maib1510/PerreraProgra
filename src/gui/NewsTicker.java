package gui;
import javax.swing.*;
import java.awt.*;

public class NewsTicker extends JFrame {
    private static final long serialVersionUID = 1L;
    /*
     * Muestra un carrusel de textos (noticias) e imágenes en JLabels,
     * cambiando cada 5 segundos. Al pausar, muestra 'Noticias pausadas' durante 4 segundos.
     */
    private static String[] noticias = {
        "¡Han llegado nuevas mascotas!",
        "Ofertas especiales en la tienda esta semana.",
        "Consejos para los primeros días en casa de tu nuevo amigo.",
        "Recomendación de pienso y alimentación",
        "¡No te pierdas la nueva gama de pelotas!"
    };
    
    private Thread hiloPausa;
    private boolean pausado = false;
    private JLabel lblImage;
    private JButton btnPause;
    private JButton btnStop;
    private VentanaPrincipal ventanaPrincipal;

    private Thread hiloNoticias;

    public NewsTicker(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setTitle("Carrusel de Noticias");
        setSize(500, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        lblImage = new JLabel("", JLabel.CENTER);
        lblImage.setPreferredSize(new Dimension(500, 700));
        add(lblImage, BorderLayout.CENTER);  

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPause = new JButton("Pausar");
        btnStop = new JButton("Salir");
        panelBotones.add(btnPause);
        panelBotones.add(btnStop);
        panelBotones.setBackground(new Color(255, 255, 204));        
        add(panelBotones, BorderLayout.SOUTH);

        btnPause.addActionListener(e -> {
            if (!pausado) {
                // Pausa las noticias
            	hiloNoticias.interrupt();
                pausarNoticias();
                btnPause.setText("Reanudar");
                pausado = true;
            } else {
                // Reanuda las noticias
            	hiloPausa.interrupt();
                iniciarNoticias();
                btnPause.setText("Pausar");
                pausado = false;
            }
        });

        iniciarNoticias();
    }

    private void iniciarNoticias() {
        hiloNoticias = new Thread(() -> {
            int index = 0;
            while (!Thread.currentThread().isInterrupted()) {
                actualizarNoticias(index);
                try {
                    Thread.sleep(5000); // Cambia cada 5 segundos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                index = (index + 1) % noticias.length;
            }
        });

        hiloNoticias.start();
    }

    private void actualizarNoticias(int index) {
        SwingUtilities.invokeLater(() -> {
            String ruta = "news/news" + (index + 1) + ".png";
            ImageIcon iconoOriginal = new ImageIcon(ruta);
            Image imgEscalada = iconoOriginal.getImage().getScaledInstance(
                lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(imgEscalada));
        });
    }

    private void pausarNoticias() {
       
        hiloPausa = new Thread(() -> {
            SwingUtilities.invokeLater(() -> {
            	 String ruta = "news/newspausa.png";
                 ImageIcon iconoOriginal = new ImageIcon(ruta);
                 Image imgEscalada = iconoOriginal.getImage().getScaledInstance(
                     lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
                 lblImage.setIcon(new ImageIcon(imgEscalada));
                 
            });
            
            
        });
        hiloPausa.start();
        
    }

    private void detenerNoticias() {
        hiloNoticias.interrupt();
        this.dispose();
        ventanaPrincipal.setVisible(true);
    }
}
