package vistas;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class ChatCliente extends JFrame implements ActionListener{
    
    private boolean continuar = true;
	
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
		lblNombreUsuario.setBounds(50, 11, 60, 14);
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
                        if(continuar) {
                            OutputInputOfData();
			    process();
                        }
		}
		catch(EOFException e) {
			showMessage("Se termino la seccion");
		}
		catch(IOException | NullPointerException e) {
                    System.out.println("this 1");
                    this.dispose();
		}
		finally {
                    if(continuar)
			closeCliente();
		}
	}
	
	public void startClienteConexion() {
		showMessage("Iniciando conexion\n");
		try {
			cliente = new Socket(InetAddress.getByName(direccionIP), 9009);
                        showMessage("conexion Lista\n");
		}
		catch(IOException | NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Parece que no hay vendedores disponibles", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                        continuar = false;
                        dispose();
                        System.out.println(e.getMessage());
		}
	}
	
	public void OutputInputOfData() {
		try {
			salida = new ObjectOutputStream(cliente.getOutputStream());
			salida.flush();
			
			entrada = new ObjectInputStream(cliente.getInputStream());
			showMessage("Output/Input -> ready\n");
		}
		catch(IOException | NullPointerException e) {
			System.out.println("soy outputinput");
		}
	}
	
	public void process() throws IOException{
		txtMensaje.setEditable(true);
		btnSend.setEnabled(true);
                
		do {
			try {
				PaqueteDeDatos paqueteVendedor;
				paqueteVendedor = (PaqueteDeDatos)entrada.readObject();
				
                                mensajeToSave += printPaquete(paqueteVendedor);
				showMessage(printPaquete(paqueteVendedor));
			}
			catch(ClassNotFoundException | NullPointerException e) {
				System.out.println("soy process");
			}
			
		}while(cliente.isConnected());
	}
        
	public void closeCliente() {
		showMessage("Fin del Chat\n");
		txtMensaje.setEditable(false);
		btnSend.setEnabled(false);
		
		try {
			entrada.close();
			salida.close();
			cliente.close();
		}
		catch(IOException e) {
                    System.out.println("soy yo perras");
			e.getCause();
		}
	}
	
	public void sendMessages(String message) {
		PaqueteDeDatos paqueteCliente = null;
		try {
			paqueteCliente = new PaqueteDeDatos();
			paqueteCliente.setNameUser(nameCliente);
			paqueteCliente.setMensaje(message);
			
			salida.writeObject(paqueteCliente);
			//salida.flush();
			mensajeToSave += printPaquete(paqueteCliente);
			showMessage(printPaquete(paqueteCliente));
		}
		catch(IOException e) {
			showMessage("No se pudo escribir el mensaje\n\n");
		}
	}
        
        public String printPaquete(PaqueteDeDatos paquete) {
            String nameVendedor = "";
            String mensaje = "";
            
            nameVendedor = paquete.getNameUser();
            mensaje = paquete.getMensaje();
            return ("\n" + nameVendedor + "\n" + mensaje + "\n");
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
                        //System.out.print(mensajeToSave);
                        writeToFile.write(mensajeToSave);
                        writeToFile.close();
                    }
                }
                catch(IOException e) {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar su archivo", "Error", JOptionPane.WARNING_MESSAGE);
                }
                finally {
                    //System.exit(0);
                    dispose();
                }
            }
            else
                //System.exit(0);
                dispose();
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
        private String mensajeToSave = "";
}

