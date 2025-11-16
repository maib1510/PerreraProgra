package gui;

import javax.swing.*;
import java.awt.*;

public class HiloCargar extends JDialog implements Runnable {
    // esta variable guarda la tarea que quiero hacer después de cargar
    private Runnable tareaPosterior;

    //---------------------------------------------CIRCULO CARGA (AI generated)----------------------------------------------------------------
    
    // este es un componente que muestra un círculo animado de carga
    private static class CircularProgressIndicator extends JComponent {
        private int angle = 0;
        public CircularProgressIndicator() {
            // crea un temporizador que actualiza el ángulo cada 60 ms para animar el círculo
            Timer timer = new Timer(60, e -> {
                angle += 8;
                if (angle >= 360) angle = 0;
                repaint(); // repinta para actualizar la animación
            });
            timer.start();
            setPreferredSize(new Dimension(48, 48));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            // activa la mejora de gráficos para que se vea más suave
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int size = Math.min(getWidth(), getHeight());
            int thickness = 10;
            int arcAngle = 80;
            // configura el grosor y color del círculo
            g2.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(new Color(105, 70, 38));
            // dibuja un arco que rota según el ángulo
            g2.drawArc(
                thickness,
                thickness,
                size - thickness * 2,
                size - thickness * 2,
                angle, arcAngle
            );
            g2.dispose();
        }
    }

    //-----------------------------------------------------HILO-----------------------------------------------------------
    
    // constructor recibe el panel padre y la tarea a ejecutar después
    public HiloCargar(JFrame parent, Runnable tareaPosterior) {
        super(parent, "", true); //super de jdialog
        this.tareaPosterior = tareaPosterior;
        setUndecorated(true); // sin bordes

        // -------------------------------------------------panel fondo-----------------------------------------------------------
        JPanel fondoPanel = new JPanel();
        fondoPanel.setBackground(new Color(233, 220, 209));
        fondoPanel.setBorder(BorderFactory.createLineBorder(new Color(80, 55, 30), 3, true));
        fondoPanel.setLayout(new BoxLayout(fondoPanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------mensaje-------------------------------------------------------------
        JLabel mensaje = new JLabel("Cargando... Por favor, espera");
        mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensaje.setFont(new Font("Serif", Font.BOLD, 16));
        mensaje.setForeground(new Color(97, 62, 18));
        mensaje.setHorizontalAlignment(SwingConstants.CENTER);

        // ------------------------------------------------objeto circulo---------------------------------------------------------
        CircularProgressIndicator indicador = new CircularProgressIndicator();
        JPanel indicadorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        indicadorPanel.setOpaque(false);
        indicadorPanel.add(indicador);

        // organiza los componentes verticalmente y los centra
        fondoPanel.add(Box.createVerticalGlue());
        fondoPanel.add(mensaje);
        fondoPanel.add(Box.createVerticalStrut(18));
        fondoPanel.add(indicadorPanel);
        fondoPanel.add(Box.createVerticalGlue());

        add(fondoPanel, BorderLayout.CENTER);

        setSize(320, 190);
        setLocationRelativeTo(parent); // centra el diálogo
    }

    //-----------------------------------------------------------run hilo--------------------------------------------------------------
    @Override
    public void run() {
        // muestra el diálogo de carga
        SwingUtilities.invokeLater(() -> setVisible(true));
        try {
            Thread.sleep(4000); // espera 4 segundos 
        } catch (InterruptedException ignored) {}

        // cierra el diálogo y ejecuta la tarea posterior (abrir ventanaPerros)
        SwingUtilities.invokeLater(() -> {
            setVisible(false);
            dispose();
            if (tareaPosterior != null) tareaPosterior.run();
        });
    }
}
