package gui;
import javax.swing.*;
import java.awt.*;

/*
 * Muestra un carrusel de textos (noticias) en un JLabel, cambiando cada 2 segundos.
Al presionar "Pausar", se detiene la animación y se muestra un mensaje de "Noticias pausadas" por 5 segundos.
Luego, el carrusel se reanuda automáticamente.
 */


public class NewsTicker extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel lblNews;
    private JButton btnPause;
    private JButton btnStop;
    private VentanaPrincipal ventanaPrincipal;
    
    private static String[] noticias = {
        "¡Han llegado nuevas mascotas!",
        "Ofertas especiales en la tienda esta semana.",
        "Consejos para los primeros días en casa de tu nuevo amigo.",
        "Recomendación de pienso y alimentación",
        "¡No te pierdas la nueva gama de pelotas!"
    };

    private Thread hiloNoticias;

    public NewsTicker(VentanaPrincipal ventanaPrincipal) {
    	this.ventanaPrincipal = ventanaPrincipal;
    	
        setLayout(new BorderLayout());

        lblNews = new JLabel("", JLabel.CENTER);
        lblNews.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblNews, BorderLayout.CENTER);

        btnPause = new JButton("Pausar");
        add(btnPause, BorderLayout.SOUTH);
        
        btnStop = new JButton("Salir");
        add(btnStop, BorderLayout.SOUTH);
        
        btnStop.addActionListener(e -> {
        	detenerNoticias();
        });

        iniciarNoticias();

        btnPause.addActionListener(e -> {
            btnPause.setEnabled(false);
            pausarNoticias();
        });

        setTitle("Carrusel de Noticias");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Método para iniciar el carrusel de noticias
    private void iniciarNoticias() {
        hiloNoticias = new Thread(() -> {
            int index = 0;
            while (!Thread.currentThread().isInterrupted()) {
                actualizarNoticias(noticias[index]);
                try {
                    Thread.sleep(2000); // Cambia cada 2 segundos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                index = (index + 1) % noticias.length;
            }
        });

        hiloNoticias.start();
    }

    // Actualiza el texto de la noticia
    private void actualizarNoticias(String noticia) {
        SwingUtilities.invokeLater(() -> lblNews.setText(noticia));
    }

    // Método para pausar el carrusel y mostrar un mensaje por 5 segundos
    private void pausarNoticias() {
        hiloNoticias.interrupt();

        new Thread(() -> {
            actualizarNoticias("Noticias pausadas...");
            try {
                Thread.sleep(5000); // Espera 5 segundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            btnPause.setEnabled(true);
            iniciarNoticias();
        }).start();
    }
    
    private void detenerNoticias() {
        hiloNoticias.interrupt();
        this.dispose();
        ventanaPrincipal.setVisible(true);
        
    }

    
}
