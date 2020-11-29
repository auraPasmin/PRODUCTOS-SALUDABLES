package modelo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import vistas.ChatServidor;

public class CVendedor extends Thread{
        @Override
        public void run(){
            iniciarVendedor();
        }
	public CVendedor(String vendedor, JTextArea text) {
            nameVendedor = vendedor;
            textArea = text;
            
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

		showMessage("Conexion exitosa\n");
		
		do {
			try {
				PaqueteDeDatos paqueteCliente;
				paqueteCliente = (PaqueteDeDatos)entrada.readObject();
				mensajeToSave += printPaquete(paqueteCliente);
				showMessage(printPaquete(paqueteCliente));
			}
			catch(ClassNotFoundException e) {
				showMessage("Ten cuidado con quien hablas");
			}
			
		}while(socket.isConnected());
	}
	
	public void closeCliente() {
		showMessage("Se ha cerrado la conexion\n");
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
			datagramaCliente.setNameUser(nameVendedor);
			datagramaCliente.setMensaje(message);
			
			salida.writeObject(datagramaCliente);
			//salida.flush();
			mensajeToSave += printPaquete(datagramaCliente);
			showMessage(printPaquete(datagramaCliente));
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

        
//        
//        public void guardarChat() {
//            
//            int opcion = JOptionPane.showConfirmDialog(null, "¡¿Deseas guardar registro de la conversación?!",
//                    "Mensaje", JOptionPane.OK_CANCEL_OPTION);
//            
//            if(opcion == JOptionPane.OK_OPTION) {
//                try {
//                    
//                    JFileChooser selectFile = new JFileChooser();
//                    selectFile.showSaveDialog(this);
//                    File archivo = selectFile.getSelectedFile();
//                
//                    if(archivo != null) {
//                        FileWriter writeToFile = new FileWriter(archivo + ".txt");
//                        writeToFile.write(mensajeToSave);
//                        writeToFile.close();
//                    }
//                }
//                catch(IOException e) {
//                    JOptionPane.showMessageDialog(null, "No se pudo guardar su archivo", "Error", JOptionPane.WARNING_MESSAGE);
//                }
//                finally {
//                    System.exit(0);
//                }
//            }
//            else
//                System.exit(0);
//        }


	private JTextArea textArea;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private String mensajeaEnviar = "";
        private String mensajeToSave = "";
	private String nameVendedor;
	private ServerSocket servidor;
	private Socket socket;
}
