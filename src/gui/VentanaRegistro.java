package gui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import DB.GestorBD;
import Domain.Animal;
import Domain.Perfil;
import Domain.Usuario;

public class VentanaRegistro extends JFrame {
	private VentanaInicioSesion ventanaInicioSesion;
	private GestorBD gestor;

    public VentanaRegistro(VentanaInicioSesion ventanaInicioSesion, GestorBD gestor) {
        this.ventanaInicioSesion = ventanaInicioSesion;
        this.gestor = gestor;

        this.setTitle("registro");
        this.setSize(450,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout(10, 10));
       
        Font fuenteTexto = new Font("Arial", Font.BOLD, 16);
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 24);

        // Colores
        Color azulClaro = new Color(115, 181, 201);
        Color celeste = new Color(200, 230, 240); // Celeste pastel
        // ------------------------------------------------------------------------------------------------------
        this.setBackground(azulClaro);
        // panel superior ---------------------------------------------------------------------------------------
        JPanel superiorPanel = new JPanel();
        superiorPanel.setBackground(azulClaro);

        // borde del panel ------------------------------------
        //superiorPanel.setBorder(BorderFactory.createLineBorder(celeste,2));

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
        registerPanel.setBackground(azulClaro);
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));

        // borde del panel -----------------------------------------------------
        TitledBorder bordeReg = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(celeste,2),
                "Introduce tus datos para iniciar sesión",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                fuenteTexto
        );
        bordeReg.setTitleColor(celeste);
        registerPanel.setBorder(BorderFactory.createCompoundBorder(
        		bordeReg,
        		BorderFactory.createEmptyBorder(20, 50, 20, 30))); // margen interno));
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
        bottomPanel.setBackground(azulClaro);

        // borde del panel -----------------------------------------------------
        TitledBorder bordeBottom = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(celeste,2),
                "Completar y volver a la ventana de inicio de sesión",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                fuenteTexto);
        bordeBottom.setTitleColor(celeste);
        
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
                return;
            }

            // Validar y parsear edad
            int edadVal;
            try {
                edadVal = Integer.parseInt(rellenarEdad.getText().trim());
                if (edadVal < 0) throw new NumberFormatException("Edad negativa");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introduce una edad válida (número entero).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Recuperar username y contraseña
            String usernameVal = rellenarUsername.getText().trim();
            String passwordVal = new String(rellenarContraseña.getPassword()).trim();

            if (usernameVal.isEmpty() || passwordVal.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username y contraseña son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Construir objeto Usuario y Perfil
            Usuario nuevo = new Usuario();
            nuevo.setNombre(rellenarNombre.getText().trim());
            nuevo.setApellido(rellenarApellido.getText().trim());
            nuevo.setEdad(edadVal);
            nuevo.setEmail(rellenarEmail.getText().trim());
            nuevo.setTelefono(rellenarTelf.getText().trim());
            nuevo.setTarjeta_bancaria(rellenarTarjeta.getText().trim());
            nuevo.setMascotas(new ArrayList<Animal>()); // vacía al crear

            Perfil perfil = new Perfil();
            perfil.setUsername(usernameVal);
            perfil.setPassword(passwordVal);
            perfil.setUsuario(nuevo);
            nuevo.setPerfil(perfil);

            // Insertar en BD usando GestorBD
            boolean ok = false;
            if (gestor != null) {
                ok = gestor.insertarUsuario(nuevo);
            } else {
                // Si no se pasó GestorBD, mostrar mensaje (modo offline)
                JOptionPane.showMessageDialog(this, "GestorBD no disponible: el usuario no se guardará en la base de datos. Volviendo al login.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                ok = true; // permitir continuar al login aunque no esté persistido
            }

            if (ok) {
                JOptionPane.showMessageDialog(this, "Registro completado correctamente. Ya puedes iniciar sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                if (ventanaInicioSesion != null) {
                    ventanaInicioSesion.setVisible(true);
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el usuario. Revisa la consola para más detalles.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });



        // -----------------------------------------------------

        bottomPanel.add(compButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }


}
