package vistas;
import modelo.PaqueteDeDatos;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class ChatServidor extends JFrame {
	
	/**
	 * Create the application.
	 */
	public ChatServidor(String vendedor) {
		setResizable(false);
		nameVendedor = vendedor;
		initialize();
            txtMensaje.setEditable(true);
            btnSend.setEnabled(true);
            btnSend.setFocusable(true);
	}

	private void initialize() {
                setTitle( "Vendedor" );
		setBounds(100, 100, 450, 337);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		menu = new JPanel();
		menu.setBackground(Color.WHITE);
		menu.setBorder(UIManager.getBorder("CheckBox.border"));
		menu.setBounds(2, 0, 434, 32);
		getContentPane().add(menu);
		menu.setLayout(null);
		
		icono = new JLabel(nameVendedor);
		icono.setBackground(Color.WHITE);
		icono.setBounds(10, 0, 30, 30);
		menu.add(icono);
		icono.setIcon(new ImageIcon(getClass().getResource("/recurso/user.png")));
		
		lblNombreUsuario = new JLabel(nameVendedor);
		lblNombreUsuario.setForeground(Color.RED);
		lblNombreUsuario.setBounds(50, 11, 100, 14);
		menu.add(lblNombreUsuario);
		
		
		textArea = new JTextArea();
                //textArea.setBounds(10, 43, 424, 223);
                //getContentPane().add(textArea);
                scroll = new JScrollPane(textArea);
                scroll.setBounds(2, 31, 432, 247);
                getContentPane().add(scroll);
        
		txtMensaje = new JTextField();
		txtMensaje.setBounds(2, 277, 344, 22);
		getContentPane().add(txtMensaje);
		txtMensaje.setColumns(10);
		
		btnSend = new JButton("enviar");
		btnSend.setBackground(Color.GREEN);
		btnSend.setBounds(345, 276, 94, 23);
		getContentPane().add(btnSend);
		

		setVisible(true);
	}
        public JTextArea getText(){
            return textArea;
        }
        public String getMessage(){
            return txtMensaje.getText();
        }
	public void addListenerbtn(ActionListener al){
            btnSend.addActionListener(al);
        }
        
	

	

	private JTextField txtMensaje;
	private JTextArea textArea;
	private JLabel lblNombreUsuario;
	private JPanel menu;
	private JLabel icono;
	private JButton btnSend;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private String mensajeaEnviar = "";
        private String mensajeToSave = "";
	private String nameVendedor;
	private ServerSocket servidor;
	private Socket socket;
	private JScrollPane scroll;
}