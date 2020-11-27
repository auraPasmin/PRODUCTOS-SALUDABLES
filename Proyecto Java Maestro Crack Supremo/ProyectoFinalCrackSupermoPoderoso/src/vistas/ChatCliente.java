package vistas;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class ChatCliente extends JFrame implements ActionListener{
	
	/**
	 * Create the application.
	 */
	public ChatCliente(String cliente, String direccion) {
		setResizable(false);
		nameCliente = cliente;
		direccionIP = direccion;
		initialize();
        txtMensaje.setEditable(false);
        btnSend.setEnabled(false);
        iniciarCliente();
	}

	private void initialize() {
		setTitle( "Cliente" );
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
		
		icono = new JLabel(nameCliente);
		icono.setBackground(Color.WHITE);
		icono.setBounds(10, 0, 30, 30);
		menu.add(icono);
		icono.setIcon(new ImageIcon(getClass().getResource("/recurso/user.png")));
		
		lblNombreUsuario = new JLabel(nameCliente);
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
                addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        guardarChat();
                    }
                });
		setVisible(true);
	}
	
	public void iniciarCliente() {
		try {
			startClienteConexion();
			OutputInputOfData();
			process();
		}
		catch(EOFException e) {
			showMessage("Se termino la seccion");
		}
		catch(IOException e) {
			e.getMessage();
		}
		finally {
			closeCliente();
		}
	}
	
	public void startClienteConexion() {
		showMessage("Iniciando conexion\n");
		try {
			cliente = new Socket(InetAddress.getByName(direccionIP), 9009);
		}
		catch(IOException e) {
			showMessage("Error no se ha podido establecer la conexion");
			showMessage(e.getMessage());
		}
	}
	
	public void OutputInputOfData() {
		try {
			salida = new ObjectOutputStream(cliente.getOutputStream());
			salida.flush();
			
			entrada = new ObjectInputStream(cliente.getInputStream());
			showMessage("Flujos de entrada/salida listos\n");
		}
		catch(IOException e) {
			showMessage(e.getMessage());
		}
	}
	
	public void process() throws IOException{
		String nameVendedor;
		String mensaje;
		txtMensaje.setEditable(true);
		btnSend.setEnabled(true);
		do {
			try {
				PaqueteDeDatos paqueteVendedor;
				paqueteVendedor = (PaqueteDeDatos)entrada.readObject();
				nameVendedor = paqueteVendedor.getNameCliente();
				mensaje = paqueteVendedor.getMensaje();
                                mensajeToSave += "\n" + nameVendedor + "\n" + mensaje + "\n";
				showMessage("\n" + nameVendedor + "\n" + mensaje + "\n");
			}
			catch(ClassNotFoundException e) {
				showMessage("Ten cuidado con quien hablas");
			}
			
		}while(cliente.isConnected());
	}
	
	public void closeCliente() {
		showMessage("Se ha cerrado la conexion\n");
		txtMensaje.setEditable(false);
		btnSend.setEnabled(false);
		
		try {
			entrada.close();
			salida.close();
			cliente.close();
		}
		catch(IOException e) {
			e.getCause();
		}
	}
	
	public void sendMessages(String message) {
		PaqueteDeDatos datagramaCliente = null;
		try {
			datagramaCliente = new PaqueteDeDatos();
			datagramaCliente.setNameCliente(nameCliente);
			datagramaCliente.setMensaje(message);
			
			salida.writeObject(datagramaCliente);
			//salida.flush();
			mensajeToSave += nameCliente + "\n" + message + "\n";
			showMessage(nameCliente + "\n" + message + "\n");
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
        
        public void guardarChat() {
            
            int opcion = JOptionPane.showConfirmDialog(null, "¡¿Deseas guardar registro de la conversación?!",
                    "Mensaje", JOptionPane.OK_CANCEL_OPTION);
            
            if(opcion == JOptionPane.OK_OPTION) {
                try {
                    
                    JFileChooser selectFile = new JFileChooser();
                    selectFile.showSaveDialog(this);
                    File archivo = selectFile.getSelectedFile();
                
                    if(archivo != null) {
                        FileWriter writeToFile = new FileWriter(archivo + ".txt");
                        writeToFile.write(mensajeToSave);
                        writeToFile.close();
                    }
                }
                catch(IOException e) {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar su archivo", "Error", JOptionPane.WARNING_MESSAGE);
                }
                finally {
                    System.exit(0);
                }
            }
            else
                System.exit(0);
        }

	public static void main(String[] args) {
		ChatCliente cliente = new ChatCliente("Tsubaki", "127.0.0.1");
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
	private String nameCliente;
	private String direccionIP;
	private Socket cliente;
	private JScrollPane scroll;
        private String mensajeToSave;
}

