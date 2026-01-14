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

        setTitle("registro");
        setSize(450, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        Font fuenteTexto = new Font("Arial", Font.BOLD, 16);
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 24);

        Color azulClaro = new Color(115, 181, 201);
        Color celeste = new Color(200, 230, 240);

        // ---------- PANEL SUPERIOR ----------
        JPanel superiorPanel = new JPanel();
        superiorPanel.setBackground(azulClaro);

        JLabel informacion = new JLabel("REGÍSTRATE");
        informacion.setFont(fuenteTitulo);
        superiorPanel.add(informacion);

        add(superiorPanel, BorderLayout.NORTH);

        // ---------- PANEL CENTRAL ----------
        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(azulClaro);
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));

        TitledBorder bordeReg = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(celeste, 2),
                "Introduce tus datos para iniciar sesión",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                fuenteTexto
        );
        bordeReg.setTitleColor(celeste);

        registerPanel.setBorder(BorderFactory.createCompoundBorder(
                bordeReg,
                BorderFactory.createEmptyBorder(20, 50, 20, 30)
        ));

        JPanel fieldsPanel = new JPanel(new GridLayout(8, 2, 0, 10));
        fieldsPanel.setOpaque(false);

        JLabel nombre = new JLabel("Nombre");
        JLabel apellido = new JLabel("Apellido");
        JLabel edad = new JLabel("Edad");
        JLabel username = new JLabel("Username");
        JLabel email = new JLabel("Email");
        JLabel telefono = new JLabel("Teléfono");
        JLabel tarjeta = new JLabel("Tarjeta Bancaria");
        JLabel contraseña = new JLabel("Contraseña");

        JLabel[] labels = {nombre, apellido, edad, username, email, telefono, tarjeta, contraseña};
        for (JLabel l : labels) l.setFont(fuenteTexto);

        JTextField rellenarNombre = new JTextField(14);
        JTextField rellenarApellido = new JTextField(14);
        JTextField rellenarEdad = new JTextField(14);
        JTextField rellenarUsername = new JTextField(14);
        JTextField rellenarEmail = new JTextField(14);
        JTextField rellenarTelf = new JTextField(14);
        JTextField rellenarTarjeta = new JTextField(14);
        JPasswordField rellenarContraseña = new JPasswordField(14);

        JTextField[] campos = {
                rellenarNombre, rellenarApellido, rellenarEdad,
                rellenarUsername, rellenarEmail, rellenarTelf,
                rellenarTarjeta, rellenarContraseña
        };

        fieldsPanel.add(nombre);      fieldsPanel.add(rellenarNombre);
        fieldsPanel.add(apellido);    fieldsPanel.add(rellenarApellido);
        fieldsPanel.add(edad);        fieldsPanel.add(rellenarEdad);
        fieldsPanel.add(username);    fieldsPanel.add(rellenarUsername);
        fieldsPanel.add(email);       fieldsPanel.add(rellenarEmail);
        fieldsPanel.add(telefono);    fieldsPanel.add(rellenarTelf);
        fieldsPanel.add(tarjeta);     fieldsPanel.add(rellenarTarjeta);
        fieldsPanel.add(contraseña);  fieldsPanel.add(rellenarContraseña);

        registerPanel.add(fieldsPanel);
        add(registerPanel, BorderLayout.CENTER);

        // ---------- PANEL INFERIOR ----------
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(azulClaro);

        JButton compButton = new JButton("Completar");
        JButton cancelButton = new JButton("Cancelar");

        compButton.addActionListener(e -> {

            // Campos vacíos
            for (JTextField campo : campos) {
                if (campo.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Hay campos vacíos.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // Edad
            int edadVal;
            try {
                edadVal = Integer.parseInt(rellenarEdad.getText().trim());
                if (edadVal < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Edad no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String emailVal = rellenarEmail.getText().trim();
            if (!emailValido(emailVal)) {
                JOptionPane.showMessageDialog(this, "Email no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String telefonoVal = rellenarTelf.getText().trim();
            if (!telefonoValido(telefonoVal)) {
                JOptionPane.showMessageDialog(this, "Teléfono no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String tarjetaVal = rellenarTarjeta.getText().trim();
            if (!tarjetaValida(tarjetaVal)) {
                JOptionPane.showMessageDialog(this, "Tarjeta bancaria no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String usernameVal = rellenarUsername.getText().trim();
            String passwordVal = new String(rellenarContraseña.getPassword()).trim();

            Usuario nuevo = new Usuario();
            nuevo.setNombre(rellenarNombre.getText().trim());
            nuevo.setApellido(rellenarApellido.getText().trim());
            nuevo.setEdad(edadVal);
            nuevo.setEmail(emailVal);
            nuevo.setTelefono(telefonoVal);
            nuevo.setTarjeta_bancaria(tarjetaVal);
            nuevo.setMascotas(new ArrayList<>());

            Perfil perfil = new Perfil();
            perfil.setUsername(usernameVal);
            perfil.setPassword(passwordVal);
            perfil.setUsuario(nuevo);
            nuevo.setPerfil(perfil);

            boolean ok = gestor != null && gestor.insertarUsuario(nuevo);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Registro completado correctamente.");
                ventanaInicioSesion.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            ventanaInicioSesion.setVisible(true);
            dispose();
        });

        bottomPanel.add(compButton);
        bottomPanel.add(cancelButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // ---------- VALIDACIONES ----------
    private boolean emailValido(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean telefonoValido(String telefono) {
        return telefono.matches("^[6789]\\d{8}$");
    }

    private boolean tarjetaValida(String tarjeta) {
        return tarjeta.matches("^\\d{16}$");
    }
}
