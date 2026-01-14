package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import DB.GestorBD;
import Domain.Gato;
import Domain.Pajaro;
import Domain.Perro;
import Domain.Producto;
import Domain.Roedor;
import Domain.Usuario;

public class VentanaTienda extends JFrame {
    private static final long serialVersionUID = 1L;
    private JFrame ventanaAnimales;
    private Usuario user;
    private JTable tablaProductos;
    private List<Producto> listaProductos;
    private GestorBD gestor;

    public VentanaTienda(JFrame ventanaAnterior, Gato[] gatos, Roedor[] roedores, Pajaro[] pajaros, Perro[] perros, Usuario user, GestorBD gestor) {
        this.ventanaAnimales = ventanaAnterior;
        this.user = user;
        this.gestor = gestor;

        this.setTitle("Tienda de Productos");
        this.setSize(850, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));

        // definir colores que vamos a usar
        Color mantequilla = new Color(255, 232, 128); // banda superior
        Color ocre = new Color(187, 144, 38);
        Font fuente = new Font("Arial", Font.BOLD, 15);
        
        // -------- PANEL SUPERIOR: MENÚ ----------------------------------------------------------------------------------------------
        JPanel panelMenu = new JPanel();
        
        TitledBorder bordeMenu = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ocre,2),
                "MENÚ",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                fuente
        );
        bordeMenu.setTitleColor(ocre);
        panelMenu.setBorder(bordeMenu);
        panelMenu.setBackground(mantequilla);
        
        // botones para el menú --------------------------------
        JButton animales = new JButton("Animales");
        JButton tienda = new JButton("Tienda");
        JButton perfilBtn = new JButton("Perfil");
        
        animales.setForeground(ocre);
        tienda.setForeground(ocre);
        perfilBtn.setForeground(ocre);
        
        animales.setFont(fuente);
        tienda.setFont(fuente);
        perfilBtn.setFont(fuente);

        animales.addActionListener(e -> {
            ventanaAnimales.setVisible(true);
            dispose();
        });

        perfilBtn.addActionListener(e -> {
            VentanaPerfil ventanaPerfil = new VentanaPerfil(this, gatos, roedores, pajaros, perros, user, gestor);
            ventanaPerfil.setVisible(false);
            SwingUtilities.invokeLater(() -> ventanaPerfil.setVisible(true));
            this.setVisible(false);
        });

        panelMenu.add(perfilBtn);
        panelMenu.add(animales);
        panelMenu.add(tienda);
        add(panelMenu, BorderLayout.NORTH);

        // -------- TABLA DE PRODUCTOS -----------------------------------------------------------------------------------------------
        listaProductos = gestor.cargarProductos(); // llena la listaProductos desde CSV

        tablaProductos = new JTable(new TiendaModel(listaProductos));
        tablaProductos.setFillsViewportHeight(true);
        tablaProductos.setRowHeight(40);
        tablaProductos.setShowGrid(true);
        tablaProductos.setGridColor(Color.BLACK);
        tablaProductos.setForeground(Color.BLACK);
        tablaProductos.setFont(new Font("Arial", Font.PLAIN, 12));

        // -------- RENDERER ----------------------------------------------------------------------------------------------------------
        DefaultTableCellRenderer centroRenderer = new DefaultTableCellRenderer();
        centroRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tablaProductos.getColumnCount(); i++) {
            tablaProductos.getColumnModel().getColumn(i).setCellRenderer(centroRenderer);
        }
        // --------------------------------------------------------------------------------------------------
        tablaProductos.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);

            
                
                    String categoria = value.toString().toLowerCase();

                    switch (categoria) {
                        case "perro":   
                        	label.setIcon(cargarIcono("imagenes/iconosTienda/animales/perro.jpg",30,30));
                        	break;
                        case "gato":    
                        	label.setIcon(cargarIcono("imagenes/iconosTienda/animales/gato.jpg",30,30));
                        	break;
                        case "pajaro":  
                        	label.setIcon(cargarIcono("imagenes/iconosTienda/animales/pajaro.jpg",30,30));
                        	break;
                        case "roedor":  
                        	label.setIcon(cargarIcono("imagenes/iconosTienda/animales/roedor.jpg",30,30));
                        	break;
                        default:        
                        	label.setForeground(Color.BLACK);
                    }
                    label.setBackground(Color.WHITE);
                

                return label;
            }
        });
        // --------------------------------------------------------------------------------------------------
        tablaProductos.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setOpaque(true);
                label.setFont(new Font("Arial", Font.PLAIN, 12));
                
                // Ejemplo: color para categoría
                
                int unidades = Integer.valueOf(value.toString());
                Color naranjaSuave = new Color(255, 180, 120);
                Color amarilloClaritito = new Color(255, 255, 180);
                Color rojoClaro = new Color(255, 150, 150);
                Color blanco = Color.WHITE;

                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                    label.setForeground(table.getSelectionForeground());
                } else if (unidades == 0){
                	label.setBackground(rojoClaro);
                    label.setForeground(Color.BLACK);
                } else if (unidades < 10) {
                    label.setBackground(naranjaSuave);
                    label.setForeground(Color.BLACK);
                } else if (unidades < 50) {
                    label.setBackground(amarilloClaritito);
                    label.setForeground(Color.BLACK);
                } else {
                    label.setBackground(blanco);
                    label.setForeground(Color.BLACK);
                }
                label.setText(String.valueOf(unidades));
                return label;
            } 
        });
  
        // --------------------------------------------------------------------------------------------------
        tablaProductos.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setHorizontalAlignment(JLabel.CENTER);
                
                boolean isAgotado = Boolean.parseBoolean(value.toString());
                Color rojoClaro = new Color(255, 150, 150);
                Color verdeClaro = new Color(150, 255, 150);

                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                    label.setForeground(table.getSelectionForeground());
                } else {
                    label.setBackground(isAgotado ? rojoClaro : verdeClaro);
                    label.setForeground(Color.BLACK);
                }

                String rutaIcono = isAgotado ? "imagenes/iconosTienda/unidades/soldOut.jpg"
                                             : "imagenes/iconosTienda/unidades/onSale.jpg";
                label.setIcon(cargarIcono(rutaIcono, 28, 28));
                return label;
            }

        });
        // --------------------------------------------------------------------------------------------------

        JTableHeader header = tablaProductos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(mantequilla);
        header.setForeground(ocre);
        header.setOpaque(true);

        	
        

     // Listener para seleccionar fila
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tablaProductos.getSelectedRow();
                if (fila >= 0) {
                    Producto p = listaProductos.get(fila);

                    // Crear diálogo
                    JDialog dialog = new JDialog(VentanaTienda.this, "Comprar " + p.getNombre(), true);
                    dialog.setSize(300, 150);
                    dialog.setLocationRelativeTo(VentanaTienda.this);
                    dialog.setLayout(new FlowLayout());
                    dialog.setBackground(mantequilla);
                   

                    JLabel lbl = new JLabel("Cantidad a comprar (disponibles: " + p.getUnidadesDisponibles() + "):");
                    JTextField txtCantidad = new JTextField(5);
                
                    ImageIcon iconoOriginal = new ImageIcon("imagenes/iconosTienda/unidades/buy.jpg");
                    int ancho = 50;
                    int alto = iconoOriginal.getIconHeight() * ancho / iconoOriginal.getIconWidth(); 
                    Image img = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                    ImageIcon iconoComprar = new ImageIcon(img);

                    JButton btnComprar = new JButton(iconoComprar);
                    btnComprar.setToolTipText("Comprar"); // texto que aparece al pasar el ratón
                   


                    dialog.add(lbl);
                    dialog.add(txtCantidad);
                    dialog.add(btnComprar);

                    // Acción botón comprar
                 // Acción botón comprar
                 // Acción botón comprar
                    btnComprar.addActionListener(e -> {
                        try {
                            int cantidad = Integer.parseInt(txtCantidad.getText());
                            if (cantidad <= 0) {
                                JOptionPane.showMessageDialog(dialog, "Introduce una cantidad válida");
                                return;
                            }
                            if (cantidad > p.getUnidadesDisponibles()) {
                                JOptionPane.showMessageDialog(dialog, "No hay suficientes unidades disponibles");
                                return;
                            }
                            
                            // Preguntar método de pago
                            String[] opciones = {"Usar tarjeta guardada", "Introducir nueva tarjeta", "Cancelar"};
                            int opcion = JOptionPane.showOptionDialog(
                                    dialog,
                                    "¿Cómo quieres pagar?",
                                    "Método de pago",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    opciones,
                                    opciones[0]
                            );

                            if (opcion == 2 || opcion == JOptionPane.CLOSED_OPTION) {
                                return;
                            }

                            String tarjetaUsada;

                            if (opcion == 0) {
                                tarjetaUsada = user.getTarjeta_bancaria();
                            } else {
                                tarjetaUsada = JOptionPane.showInputDialog(dialog, "Introduce tu nueva tarjeta:");
                                if (tarjetaUsada == null || tarjetaUsada.trim().isEmpty()) {
                                    JOptionPane.showMessageDialog(dialog, "No se ha introducido una tarjeta válida.");
                                    return;
                                }
                            }

                            // Calcular total
                            double total = cantidad * p.getPrecio();
                            
                            // ----------------------------------------------------------------------------------------
                            JDialog progresoDialog = new JDialog(VentanaTienda.this, "Procesando pago...", true);
                            JProgressBar barra = new JProgressBar(0, 100);
                            barra.setStringPainted(true);
                            progresoDialog.add(barra);
                            progresoDialog.setSize(300, 80);
                            progresoDialog.setLocationRelativeTo(VentanaTienda.this);
                            progresoDialog.setFont(fuente);

                            new Thread(() -> {
                                for (int i = 0; i <= 100; i++) {
                                    try { Thread.sleep(20); } catch (InterruptedException ex) {}
                                    final int valor = i;
                                    SwingUtilities.invokeLater(() -> barra.setValue(valor));
                                }
                                SwingUtilities.invokeLater(() -> progresoDialog.dispose());
                            }).start();

                            progresoDialog.setVisible(true);
                            
                            // ----------------------------------------------------------------------------------------
                         // Actualizar producto
                         // Actualizar producto en memoria
                            p.setUnidadesDisponibles(p.getUnidadesDisponibles() - cantidad);

                            if (p.getUnidadesDisponibles() <= 0) {
                                p.setUnidadesDisponibles(0);     // por si se pasa  
                                p.setAgotado(true);
                            }

                            // Guardarlo en BD
                            gestor.actualizarProducto(p);


                            ((TiendaModel) tablaProductos.getModel()).fireTableRowsUpdated(fila, fila);
                            //guardarProductosEnCSV();
                            // Mensaje de confirmación
                            JOptionPane.showMessageDialog(dialog,
                                    "Has comprado " + cantidad + " unidades de " + p.getNombre()
                                            + "\nPrecio unitario: " + p.getPrecio() + "€"
                                            + "\nTotal: " + total + "€"
                                            + "\nPagado con la tarjeta: " + tarjetaUsada);

                            // Generar ticket
                            StringBuilder ticket = new StringBuilder();
                            ticket.append("********** TICKET DE COMPRA **********\n");
                            ticket.append("Producto: ").append(p.getNombre()).append("\n");
                            ticket.append("Cantidad: ").append(cantidad).append("\n");
                            ticket.append("Precio unitario: ").append(p.getPrecio()).append(" €\n");
                            ticket.append("Total pagado: ").append(total).append(" €\n");
                            ticket.append("Tarjeta: ").append(tarjetaUsada).append("\n");
                            ticket.append("Fecha: ").append(java.time.LocalDateTime.now()).append("\n");
                            ticket.append("****************************************");

                            // Mostrar ticket
                            JTextArea area = new JTextArea(ticket.toString());
                            area.setEditable(false);
                            area.setFont(new Font("Monospaced", Font.PLAIN, 12));
                            JScrollPane scroll = new JScrollPane(area);

                            JOptionPane.showMessageDialog(
                                    dialog,
                                    scroll,
                                    "Ticket de compra",
                                    JOptionPane.INFORMATION_MESSAGE
                            );

                            // Cerrar diálogo al final del todo
                            dialog.dispose();

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(dialog, "Introduce un número válido");
                        }
                    });



                    dialog.setVisible(true);
                }
            }
        });

        // ----------------------------------------------------------------------------------------------------------------------------

        JScrollPane scroll = new JScrollPane(tablaProductos);
        TitledBorder bordeScroll = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ocre,2),
                "Productos",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                fuente
        );
        bordeScroll.setTitleColor(ocre);
        scroll.setBorder(bordeScroll);
        scroll.setBackground(mantequilla);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);
        

        this.setVisible(true);
    }

    
    public static ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(ruta);
        Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
    
    private void guardarProductosEnCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("productos.csv"))) {

            // Cabecera correcta
            //npw.println("id;nombre;categoria;precio;unidades;agotado");

            for (Producto prod : listaProductos) {
                pw.println(
                    prod.getId_producto() + ";" +
                    prod.getNombre() + ";" +
                    prod.getCategoria() + ";" +
                    prod.getPrecio() + ";" +
                    prod.getUnidadesDisponibles() + ";" +
                    prod.isAgotado()
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error guardando productos.csv");
        }
    }
    
    



}

