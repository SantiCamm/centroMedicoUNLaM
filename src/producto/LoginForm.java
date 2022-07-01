package producto;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm {

	private static Logger logger = Logger.getLogger(LoginForm.class.getName());

	private JFrame frame;
	private JTextField tf1;
	private JPasswordField pf1;

	private Login login;
	private int cant_pruebas = 0;
	private String usuario;
	private String clavedef;
	private char clave[];
	private boolean resultado;

	/*
	 * Main, ejecuta la aplicacion
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Constructor
	 */
	public LoginForm() {

		login = new Login();
		initialize();
		logger.info("Iniciando aplicación.");
	}

	/*
	 * Inicializa el contenido del JFrame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 521, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Medicus - Login");

		JLabel lblLogin = new JLabel("Medicus");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblLogin.setBounds(192, 23, 335, 48);
		frame.getContentPane().add(lblLogin);

		JLabel lblRegistroDePacientes = new JLabel("Login");
		lblRegistroDePacientes.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblRegistroDePacientes.setBounds(211, 71, 92, 37);
		frame.getContentPane().add(lblRegistroDePacientes);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUsuario.setBounds(52, 148, 86, 27);
		frame.getContentPane().add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblContrasea.setBounds(52, 196, 122, 27);
		frame.getContentPane().add(lblContrasea);

		final JLabel lblMsj = new JLabel("");
		lblMsj.setBounds(120, 312, 288, 14);
		frame.getContentPane().add(lblMsj);

		tf1 = new JTextField();
		tf1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				usuario = new String(tf1.getText());
			}
		});
		tf1.setBounds(176, 148, 127, 27);
		frame.getContentPane().add(tf1);
		tf1.setColumns(10);

		final JButton btnIngresar = new JButton("INGRESAR");
		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIngresar.setEnabled(false);
		btnIngresar.addActionListener(new ActionListener() {
			/*
			 * Evento que se ejecuta al presionar "Ingresar", valida el usuario y contraseña llamando al metodo de
			 * otra clase			
			 */
			public void actionPerformed(ActionEvent arg0) {

				if (tf1.getText().isEmpty() || clavedef.equals(""))
					lblMsj.setText("Los campos tienen errores, corregir");
				else {
					resultado = login.validarLogin(usuario, clavedef);
					if (resultado) {
						logger.info("Login correcto."); 
						frame.dispose();
						MenuPrincipalForm p = new MenuPrincipalForm();
						p.pack();
						p.setVisible(true);
					} else {
						logger.info("Login incorrecto.");
						cant_pruebas++;
						lblMsj.setText(
								"Error, le quedan " + Integer.toString(5 - cant_pruebas) + " posibilidades de login.");
					}

					if (cant_pruebas == 5) {
						JOptionPane.showMessageDialog(null,
								"Se han utilizado las 5 posibilidades de login, el programa se cerrará.",
								"Error en login", JOptionPane.ERROR_MESSAGE);
						System.exit(0);
					}
				}

			}
		});
		btnIngresar.setBounds(196, 249, 106, 23);
		frame.getContentPane().add(btnIngresar);

		pf1 = new JPasswordField();
		pf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {

				btnIngresar.setEnabled(true);
			}
		});
		pf1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				clave = pf1.getPassword();
				clavedef = new String(clave);

				if (clavedef.equals(""))
					btnIngresar.setEnabled(false);

			}
		});
		pf1.setBounds(176, 196, 127, 27);
		frame.getContentPane().add(pf1);
		MenuPrincipalForm.CenteredFrame(frame);

	}

}
