package vistas;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class ChatServidor extends JFrame implements ActionListener{
	
	/**
	 * Create the application.
	 */
	public ChatServidor(String vendedor) {
		setResizable(false);
		nameVendedor = vendedor;
		initialize();
        txtMensaje.setEditable(false);
        btnSend.setEnabled(false);
        iniciarVendedor();
	}

	private void initialize() {
		setTitle( "Vendedor" );
		setBounds(100, 100, 450, 337);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		lblNombreUsuario.setBounds(50, 11, 48, 14);
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
		
		btnSend.addActionListener(this);
		txtMensaje.addActionListener(this);
		setVisible(true);
	}
	
	public void iniciarVendedor() {
		try {
			servidor = new ServerSocket(9009);
			while(true) {
				try {
					startServer();
					OutputInputOfData();
					process();
				}
				catch(EOFException e) {
					showMessage("Se termino la seccion");
				}
				finally {
					closeCliente();
				}
			}
		}
		catch(IOException e) {
			e.getMessage();
		}
		
	}
	
	public void startServer() {
		showMessage("Iniciando conexion\n");
		try {
			showMessage("Esperando ... \n");
			socket = servidor.accept();
			showMessage("Se ha establecido la conexion\n");
		}
		catch(IOException e) {
			showMessage("Error no se ha podido establecer la conexion");
			showMessage(e.getMessage());
		}
	}
	
	public void OutputInputOfData() {
		try {
			salida = new ObjectOutputStream(socket.getOutputStream());
			salida.flush();
			
			entrada = new ObjectInputStream(socket.getInputStream());
			showMessage("Flujos de entrada/salida listos\n");
		}
		catch(IOException e) {
			showMessage(e.getMessage());
		}
	}
	
	public void process() throws IOException{
		String nameCliente;
		String mensaje;
		txtMensaje.setEditable(true);
		btnSend.setEnabled(true);
		showMessage("Conexion exitosa\n");
		
		do {
			try {
				PaqueteDeDatos paqueteCliente;
				paqueteCliente = (PaqueteDeDatos)entrada.readObject();
				nameCliente = paqueteCliente.getNameCliente();
				mensaje = paqueteCliente.getMensaje();
				
				showMessage("\n" + nameCliente + "\n" + mensaje + "\n");
			}
			catch(ClassNotFoundException e) {
				showMessage("Ten cuidado con quien hablas");
			}
			
		}while(socket.isConnected());
	}
	
	public void closeCliente() {
		showMessage("Se ha cerrado la conexion\n");
		txtMensaje.setEditable(false);
		btnSend.setEnabled(false);
		
		try {
			entrada.close();
			salida.close();
			socket.close();
		}
		catch(IOException e) {
			e.getCause();
		}
	}
	
	public void sendMessages(String message) {
		PaqueteDeDatos datagramaCliente = null;
		try {
			datagramaCliente = new PaqueteDeDatos();
			datagramaCliente.setNameCliente(nameVendedor);
			datagramaCliente.setMensaje(message);
			
			salida.writeObject(datagramaCliente);
			//salida.flush();
			
			showMessage(nameVendedor + "\n" + message + "\n");
		}
		catch(IOException e) {
			showMessage("No se pudo escribir el mensaje\n\n");
		}
	}
	
	public void showMessage(String message) {
		textArea.append(message);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		mensajeaEnviar = txtMensaje.getText();
		sendMessages(mensajeaEnviar);
		txtMensaje.setText("");
		
		if(event.getSource() == "enviar") {
			mensajeaEnviar = txtMensaje.getText();
			sendMessages(mensajeaEnviar);
			txtMensaje.setText("");
		}
	}

	public static void main(String[] args) {
		ChatServidor server = new ChatServidor("helicoptero de batalla");
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
	private String nameVendedor;
	private ServerSocket servidor;
	private Socket socket;
	private JScrollPane scroll;
}

