package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class VentanaInicioSesion extends JFrame {
	private VentanaPrincipal ventanaPrincipal;

    public VentanaInicioSesion(VentanaPrincipal ventanaPrincipal) {
        // configuración de la ventana -------------------------------------------------------------------------
        this.setTitle("Inicio de Sesión");
        this.setSize(380, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout(10, 10));

        // crear fuentes ---------------------------------------------------------------------------------------
        Font fuenteTexto = new Font("Arial", Font.PLAIN, 16);
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 22);

        // crear colores ---------------------------------------------------------------------------------------
        Color rosa = new Color(255, 182, 193);
        Color azul = new Color(204, 236, 247);



        // ------------------------------------------------------------------------------------------------------

        // panel superior ---------------------------------------------------------------------------------------
        JPanel superiorPanel = new JPanel();
        superiorPanel.setBackground(azul);

        // borde del panel ------------------------------------
        superiorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // label con titulo ------------------------------------
        JLabel informacion = new JLabel("INICIA SESIÓN");
        informacion.setForeground(Color.BLACK);
        informacion.setFont(fuenteTitulo);
        superiorPanel.add(informacion);

        // -----------------------------------------------------

        this.add(superiorPanel, BorderLayout.NORTH);

        // ------------------------------------------------------------------------------------------------------

        // panel de registro (centro) ------------------------------------------
        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(rosa);
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));

        // borde del panel -----------------------------------------------------
        registerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK),
                        "Introduce tus datos para iniciar sesión",
                        TitledBorder.CENTER,
                        TitledBorder.TOP,
                        fuenteTexto
                    ),
                    BorderFactory.createEmptyBorder(20, 50, 20, 30) // margen interno
            ));


        // -----------------------------------------------------
        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(fuenteTexto);

        JLabel email = new JLabel("Email");
        email.setFont(fuenteTexto);

        JLabel contraseña = new JLabel("Contraseña");
        contraseña.setFont(fuenteTexto);

        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 0, 10));
        fieldsPanel.setOpaque(false); // para que mantenga el fondo rosa
        // -----------------------------------------------------

        JTextField rellenarNombre = new JTextField(14);
        JTextField rellenarEmail = new JTextField(14);
        JPasswordField rellenarContraseña = new JPasswordField(14);

        fieldsPanel.add(nombre);
        fieldsPanel.add(rellenarNombre);

        fieldsPanel.add(email);
        fieldsPanel.add(rellenarEmail);

        fieldsPanel.add(contraseña);
        fieldsPanel.add(rellenarContraseña);



        registerPanel.add(fieldsPanel);


        // -----------------------------------------------------

        this.add(registerPanel, BorderLayout.CENTER);

        // ------------------------------------------------------------------------------------------------------

        // panel de botón de acceso -----------------------------------------------------------------------------

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(azul);

        // borde del panel -----------------------------------------------------
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // botones  ------------------------------------------------------------
        JButton acButton = new JButton("Acceder");
        JButton regButton = new JButton("Registrarse");

        // listener para que abra la ventana principal -------------------------
        acButton.addActionListener(e -> { // la hace visible, no la crea
			SwingUtilities.invokeLater(() -> ventanaPrincipal.setVisible(true));
			this.dispose();
        });

        regButton.addActionListener(e -> {
            VentanaRegistro ventanaRegistro = new VentanaRegistro(this);
            SwingUtilities.invokeLater(() -> ventanaRegistro.setVisible(true));
            this.setVisible(false);
        });



        // -----------------------------------------------------

        bottomPanel.add(acButton);
        bottomPanel.add(regButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // ------------------------------------------------------------------------------------------------------
        this.setVisible(true);
    }



}
