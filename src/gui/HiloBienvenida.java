package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

//JWindow AI generated
public class HiloBienvenida extends JWindow implements Runnable {
    private String imagePath;
    private int segundos;

    public HiloBienvenida(String imagePath, int segundos) {
        this.imagePath = imagePath;
        this.segundos = segundos;

        // Obtener tamaño de la pantalla:
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = screenSize.width;
        int alto = screenSize.height;

        try {
            BufferedImage original = ImageIO.read(new File(imagePath));
            Image imagenEscalada = original.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(imagenEscalada));
            getContentPane().add(label, BorderLayout.CENTER);
            setSize(ancho, alto);
        } catch (IOException e) {
            JLabel label = new JLabel("No se pudo cargar la imagen");
            getContentPane().add(label, BorderLayout.CENTER);
            setSize(400, 200);
        }
        setLocationRelativeTo(null); // Centrada (aplicado automáticamente al usar todo pantalla)
    }

    @Override
    public void run() {
        setVisible(true);
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setVisible(false);
        dispose();
    }
}
