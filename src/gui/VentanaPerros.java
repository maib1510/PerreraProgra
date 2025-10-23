import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class VentanaPerros extends JFrame{

private static final long serialVersionUID = 1L;
	
	//Declaración de los componentes de la ventana
	private JButton btn1, btn2, btn3, btn4, btn5, btn6, btnATRAS;
	private JPanel pNorte, pSur, pEste, pOeste, pCentro, pBotones, pGridInfo, pInfo;
	private JLabel lblTitulo, lblBusqueda, lblFiltrar, imagen; // Imagen lo borraré cuando ponga una Imagen --------- !!!!
	private JLabel lblNombre, lblSexo, lblEdad, lblRaza, lblPeso, lblPersonalidad, lblFisico;
	private JTextField txt2;
	private JComboBox<String> cbFiltros;
	private JFrame vActual;
	
	public VentanaPerros() {
		super();
		
		vActual = this; // decir que ventana es
		
		setBounds(400, 200, 700, 500);
		
		//Instanciamos los paneles
		pNorte = new JPanel();
		pSur = new JPanel();
		
		pBotones= new JPanel();
		pBotones.setLayout(new GridLayout(6,0)); // botones de perros (panel izda)
		
		pInfo= new JPanel();
		pInfo.setLayout(new GridLayout(7,0)); // Panel de info del perro (bloque de abajo del panel GridInfo)
		pGridInfo= new JPanel();
		pGridInfo.setLayout(new GridLayout(2,0)); // info de perros (panel drcha)
		
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(0,2)); // el panel Centro entero (botones izda, info drcha)
		
		pEste = new JPanel();
		pOeste = new JPanel();
		
		//Añadimos los paneles al panel principal de la ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		//Creación de los componentes
		btn1= new JButton("Perro1");
		btn2= new JButton("Perro2");
		btn3= new JButton("Perro3");
		btn4= new JButton("Perro4");
		btn5= new JButton("Perro5");
		btn6= new JButton("Perro6");
		btnATRAS = new JButton("Atrás");
		
		lblTitulo= new JLabel("PERROS");
		lblBusqueda= new JLabel("Búsqueda: ");
		lblFiltrar= new JLabel("Filtrar por: ");
		
		txt2= new JTextField(20);
		
		imagen= new JLabel("IMAGEN");      // BORRAR CUANDO TENGA IMAGEN ----------  !!!!!!!!
		lblNombre= new JLabel("Nombre: Laika");
		lblSexo= new JLabel("Sexo: Hembra");
		lblEdad= new JLabel("Edad: 3");
		lblRaza= new JLabel("Raza: Pomeranian");
		lblPeso= new JLabel("Peso: 2 kg");
		lblPersonalidad= new JLabel("Personalidad: Divertida, activa");
		lblFisico= new JLabel("Fisico: Naranja con manchitas blancas");
		
		String [] filtros = {"Edad","Raza", "Peso", "Personalidad", "Físico" }; // La idea es que cuando clique "Personalidad le salga opciones (Travieso, divertido, alegre...)". Lo mismo con fisico.  
		cbFiltros = new JComboBox<>(filtros);
		cbFiltros.setSelectedItem(null);
		
		//Añadimos los componentes a la ventana
		pNorte.add(lblTitulo);
		pNorte.add(lblBusqueda);
		pNorte.add(txt2);
		pNorte.add(lblFiltrar);
		pNorte.add(cbFiltros);
		
		pSur.add(btnATRAS);
		
		//Para añadir en el panel central que es un GridLayout, tenemos que hacerlo
		//en el orden en el que queremos que aparezca, de izda a dcha y de arriba a abajo
		pBotones.add(btn1);
		pBotones.add(btn2);
		pBotones.add(btn3);
		pBotones.add(btn4);
		pBotones.add(btn5);
		pBotones.add(btn6);
		
		pGridInfo.add(imagen);
		
		pInfo.add(lblNombre);
		pInfo.add(lblSexo);
		pInfo.add(lblEdad);
		pInfo.add(lblRaza);
		pInfo.add(lblPeso);
		pInfo.add(lblPersonalidad);
		pInfo.add(lblFisico);

		pCentro.add(pBotones);
		//pGridInfo.add(pInfo);
		//pCentro.add(pGridInfo);		
		
		//Añadir los listeners a los componentes
		btnATRAS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); //Cerrar la aplicación
			}
		});
		
		
		btn1.addActionListener(new ActionListener() { // cuando clique sobre el botón "Perros"	
		@Override
		public void actionPerformed(ActionEvent e) {
			// Que enseñe la info de ese perro en un Grid a la derecha del mismo VentanaPerros
			// lblNombre, lblSexo, lblEdad, lblRaza, lblPeso, lblPersonalidad, lblFisico;
			// ACTUALIZAR PANELCENTRO Y ENSEÑARLO:
			pGridInfo.add(pInfo);
			pCentro.add(pGridInfo);	
			//Hacer que muestre el NUEVO PANEL
			pCentro.revalidate();
			pCentro.repaint();		
			}
		});
		
		
		//La última sentencia siempre setVisible(true);
		setVisible(true);
	}
	
	//Para no repetir código a la hora de vaciar los campos, vamos a crear un método que se encargue de ello
	/*public void vaciarCampos() {
		txtNombreUsuario.setText("");
		txtContraseniaUsuario.setText("");
	}*/
	
	public static void main(String[] args) {
		VentanaPerros v = new VentanaPerros();
	}
}
