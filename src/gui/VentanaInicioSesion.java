package gui;

import java.awt.*;
import DB.GestorBD;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Domain.Gato;
import Domain.Pajaro;
import Domain.Perfil;
import Domain.Perro;
import Domain.Roedor;
import Domain.Usuario;

public class VentanaInicioSesion extends JFrame {
    private Gato[] gatos;
	private Roedor[] roedores;
	private Pajaro[] pajaros;
	private Perro[] perros;
	private Usuario user;
	private GestorBD gestor;
	private Perfil perfil;

    public VentanaInicioSesion(Gato[] gatos, Roedor[] roedores, Pajaro[] pajaros, Perro[] perros, GestorBD gestor) {
        this.gatos = gatos;
        this.perros = perros;
        this.pajaros = pajaros;
        this.roedores = roedores;
        this.gestor = gestor;

        // Configuración de la ventana
        this.setTitle("Inicio de Sesión");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        // Fuentes
        Font fuenteTexto = new Font("Arial", Font.BOLD, 16);
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 24);

        // Colores
        Color verdeAgua = new Color(175, 216, 214); 
        Color azulClaro = new Color(115, 181, 201);
        Color celeste = new Color(200, 230, 240); // Celeste pastel


        this.getContentPane().setBackground(azulClaro);

        // Leer usuarios desde CSV
        // Panel superior con título
        JPanel superiorPanel = new JPanel();
        superiorPanel.setBackground(azulClaro);
        superiorPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));

        JLabel lblTitulo = new JLabel("INICIA SESIÓN");
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(celeste);
        superiorPanel.add(lblTitulo);

        this.add(superiorPanel, BorderLayout.NORTH);

        // Panel central con campos
        JPanel registerPanel = new JPanel(new GridBagLayout());
        registerPanel.setBackground(verdeAgua);
        registerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 2),
                        "Introduce tus datos para iniciar sesión",
                        TitledBorder.CENTER,
                        TitledBorder.TOP,
                        fuenteTexto
                ),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(fuenteTexto);
        gbc.gridx = 0; gbc.gridy = 0;
        registerPanel.add(lblUsername, gbc);

        JTextField txtUsername = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0;
        registerPanel.add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(fuenteTexto);
        gbc.gridx = 0; gbc.gridy = 1;
        registerPanel.add(lblPassword, gbc);

        JPasswordField txtPassword = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        registerPanel.add(txtPassword, gbc);

        this.add(registerPanel, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(azulClaro);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));

        JButton btnAcceder = new JButton("Acceder");
        btnAcceder.setForeground(azulClaro);
        btnAcceder.setFont(fuenteTexto);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setForeground(azulClaro);
        btnRegistrarse.setFont(fuenteTexto);

        bottomPanel.add(btnAcceder);
        bottomPanel.add(btnRegistrarse);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // Acción de login
        btnAcceder.addActionListener(e -> {
    String usernameIngresado = txtUsername.getText();
    String contraseñaIngresada = new String(txtPassword.getPassword());

    if (usernameIngresado.isEmpty() || contraseñaIngresada.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Rellena todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Llamada al método que obtiene el usuario con su perfil desde la BD
    Usuario user = gestor.obtenerUsuarioConPerfil(usernameIngresado, contraseñaIngresada);

    if (user != null) {
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(gatos, roedores, pajaros, perros, user, gestor);
        ventanaPrincipal.setVisible(true);
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
    }
});


        //getRootPane().setDefaultButton(btnAcceder);
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnAcceder.doClick();
                }
            }
        });
        // Acción de registro
        btnRegistrarse.addActionListener(e -> {
            VentanaRegistro ventanaRegistro = new VentanaRegistro(this);
            ventanaRegistro.setVisible(true);
            this.setVisible(false);
        });

        // Enter también hace login
        txtPassword.addActionListener(e -> btnAcceder.doClick());

        this.setVisible(true);
    }}

 


