package gui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Domain.Gato;
import Domain.Perfil;
import Domain.Roedor;
import Domain.Usuario;

public class VentanaInicioSesion extends JFrame {
	//private VentanaPrincipal ventanaPrincipal;
	private Gato[] gatos;
	private Roedor[] roedores;

    public VentanaInicioSesion(Gato[] gatos, Roedor[] roedores) {
    	//this.ventanaPrincipal = ventanaPrincipal;
        // configuración de la ventana -------------------------------------------------------------------------
        this.setTitle("Inicio de Sesión");
        this.setSize(380, 260);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout(10, 10));

        // crear fuentes ---------------------------------------------------------------------------------------
        Font fuenteTexto = new Font("Arial", Font.PLAIN, 16);
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 22);

        // crear colores ---------------------------------------------------------------------------------------
        Color marron = new Color(193, 129, 66); 
        Color beige = new Color(250, 240, 230); // “linen”, muy usado en interfaces suaves

        ArrayList<Usuario> usuarios = leerCSV("usuarios.csv");

        // ------------------------------------------------------------------------------------------------------

        // panel superior ---------------------------------------------------------------------------------------
        JPanel superiorPanel = new JPanel();
        superiorPanel.setBackground(beige);

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
        registerPanel.setBackground(marron);
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
        JLabel username = new JLabel("Username");
        username.setFont(fuenteTexto);
        
        JLabel contraseña = new JLabel("Contraseña");
        contraseña.setFont(fuenteTexto);

        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 0, 10));
        fieldsPanel.setOpaque(false); // para que mantenga el fondo rosa
        // -----------------------------------------------------

        JTextField rellenarUsername = new JTextField(14);
        JPasswordField rellenarContraseña = new JPasswordField(14);

        fieldsPanel.add(username);
        fieldsPanel.add(rellenarUsername);

        fieldsPanel.add(contraseña);
        fieldsPanel.add(rellenarContraseña);



        registerPanel.add(fieldsPanel);


        // -----------------------------------------------------

        this.add(registerPanel, BorderLayout.CENTER);

        // ------------------------------------------------------------------------------------------------------

        // panel de botón de acceso -----------------------------------------------------------------------------

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(beige);

        // borde del panel -----------------------------------------------------
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // botones  ------------------------------------------------------------
        JButton acButton = new JButton("Acceder");
        JButton regButton = new JButton("Registrarse");

        // listener para que abra la ventana principal -------------------------
        acButton.addActionListener(e -> {
            String contraseñaIngresada = new String(rellenarContraseña.getPassword());

            if (rellenarUsername.getText().isEmpty() || contraseñaIngresada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Rellena todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean encontrado = false;
            for (Usuario u : usuarios) {
                if (u.getUsername().equals(rellenarUsername.getText())) {
                    if (u.getPassword().equals(contraseñaIngresada)) {
                    	VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(gatos,roedores, u);
                        ventanaPrincipal.setVisible(true);
                        this.dispose();
                        encontrado = true;
                        break; // salimos porque ya encontramos al usuario correcto
                    }
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
    
    private ArrayList<Usuario> leerCSV(String rutaArchivo) {
    	ArrayList<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                // Saltar la cabecera
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                // Dividir por comas y validar
                String[] valores = linea.split(",");
                if (valores.length < 8) continue; // Saltar filas mal formadas

                Usuario user = new Usuario();
                // Nombre,Apellido,Edad,Username,Email,Telefono,TarjetaBancaria,Contraseña
                user.setNombre(valores[0].trim());
                user.setApellido(valores[1].trim());
                user.setEdad(Integer.parseInt(valores[2]));
                user.setUsername(valores[3].trim());
                user.setEmail(valores[4].trim());
                user.setTelefono(valores[5].trim());
                user.setTarjeta_bancaria(valores[6]);
                user.setPassword(valores[7].trim());

                usuarios.add(user);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                this,
                "No se pudo leer el archivo: " + e.getMessage(),
                "Error de lectura",
                JOptionPane.ERROR_MESSAGE
            );
        }
        return usuarios;
    }





}
