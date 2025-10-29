package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class VentanaRegistro extends JFrame {
	private VentanaInicioSesion ventanaInicioSesion;

    public VentanaRegistro(VentanaInicioSesion ventanaInicioSesion) {
        this.setTitle("registro");
        this.setSize(450,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
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
        superiorPanel.setBackground(rosa);

        // borde del panel ------------------------------------
        superiorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // label con titulo ------------------------------------
        JLabel informacion = new JLabel("REGISTRATE");
        informacion.setForeground(Color.BLACK);
        informacion.setFont(fuenteTitulo);
        superiorPanel.add(informacion);

        // -----------------------------------------------------

        this.add(superiorPanel, BorderLayout.NORTH);

        // ------------------------------------------------------------------------------------------------------

        // panel de registro (centro) ------------------------------------------
        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(azul);
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
        
        JLabel apellido = new JLabel("Apellido");
        apellido.setFont(fuenteTexto);
        
        JLabel username = new JLabel("Username");
        username.setFont(fuenteTexto);
        
		JLabel edad = new JLabel("Edad");
		edad.setFont(fuenteTexto);
		        
        JLabel email = new JLabel("Email");
        email.setFont(fuenteTexto);
        
        JLabel telefono = new JLabel("Teléfono");
        telefono.setFont(fuenteTexto);
        
        JLabel tarjeta = new JLabel("TarjetaBancaria");
        tarjeta.setFont(fuenteTexto);
        
        JLabel contraseña = new JLabel("Contraseña");
        contraseña.setFont(fuenteTexto);

        JPanel fieldsPanel = new JPanel(new GridLayout(8, 2, 0, 10));
        fieldsPanel.setOpaque(false); // para que mantenga el fondo rosa
        
        // -----------------------------------------------------

        JTextField rellenarNombre = new JTextField(14);
        JTextField rellenarApellido = new JTextField(14);
        JTextField rellenarEdad = new JTextField(14);
        JTextField rellenarUsername = new JTextField(14);
        JTextField rellenarEmail = new JTextField(14);
        JTextField rellenarTelf = new JTextField(14);
        JTextField rellenarTarjeta = new JTextField(14);
        JPasswordField rellenarContraseña = new JPasswordField(14);

        fieldsPanel.add(nombre);
        fieldsPanel.add(rellenarNombre);
        
        fieldsPanel.add(apellido);
        fieldsPanel.add(rellenarApellido);
        
        fieldsPanel.add(edad);
        fieldsPanel.add(rellenarEdad);
        
        fieldsPanel.add(username);
        fieldsPanel.add(rellenarUsername);

        fieldsPanel.add(email);
        fieldsPanel.add(rellenarEmail);
        
        fieldsPanel.add(telefono);
        fieldsPanel.add(rellenarTelf);
        
        fieldsPanel.add(tarjeta);
        fieldsPanel.add(rellenarTarjeta);

        fieldsPanel.add(contraseña);
        fieldsPanel.add(rellenarContraseña);



        registerPanel.add(fieldsPanel);

        JTextField[] campos = {rellenarNombre, rellenarApellido, rellenarEdad, rellenarUsername, rellenarEmail, rellenarTelf, rellenarTarjeta, rellenarContraseña};
        // -----------------------------------------------------

        this.add(registerPanel, BorderLayout.CENTER);

        // ------------------------------------------------------------------------------------------------------

        // panel de botón de acceso -----------------------------------------------------------------------------

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(rosa);

        // borde del panel -----------------------------------------------------
        bottomPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Completar y volver a la ventana de inicio de sesión",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                fuenteTexto
            ));

        // botones  ------------------------------------------------------------
        JButton compButton = new JButton("Completar");

        // listener para que abra la ventana principal -------------------------
        compButton.addActionListener(e -> {
            boolean vacio = false;
            
            for (JTextField campo : campos) {
                if (campo.getText().trim().isEmpty()) {
                    vacio = true;
                    break;
                }
            }
            
            if (vacio) {
                JOptionPane.showMessageDialog(
                    this,
                    "Hay un campo vacío",
                    "Error de validación",
                    JOptionPane.WARNING_MESSAGE
                );
            } else {
                ventanaInicioSesion.setVisible(true);
                this.dispose();
            }
        });



        // -----------------------------------------------------

        bottomPanel.add(compButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }


}
