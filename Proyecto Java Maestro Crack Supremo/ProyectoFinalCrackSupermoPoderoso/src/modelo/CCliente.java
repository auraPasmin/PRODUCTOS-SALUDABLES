
package modelo;

import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class CCliente extends Thread{
    @Override
    public void run(){
        try {
            iniciarCliente();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private boolean continuar = true;
	
	/**
	 * Create the application.
	 */
	public CCliente(String cliente, String direccion) {
		nameCliente = cliente;
		direccionIP = direccion;


	}


	
	public void iniciarCliente() throws ClassNotFoundException {
		try {
			startClienteConexion();
                        if(continuar) {
                            OutputInputOfData();
			    process();
                        }
		}
		catch(IOException | NullPointerException e) {
                    System.out.println("this 1");
                    JOptionPane.showMessageDialog(null,"No hay cliente");
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
        } catch (UnknownHostException ex) {
            Logger.getLogger(CCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
                showMessage("conexion Lista\n");
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
	
	public void process() throws IOException, ClassNotFoundException{
                
		do {
                    PaqueteDeDatos paqueteVendedor;
                    paqueteVendedor = (PaqueteDeDatos)entrada.readObject();
                    mensajeToSave += printPaquete(paqueteVendedor);
                    showMessage(printPaquete(paqueteVendedor));
			
		}while(cliente.isConnected());
	}
        
	public void closeCliente() {
		showMessage("Fin del Chat\n");

		
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
                        //System.out.println(printPaquete(paqueteCliente));
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
            System.out.println(message);
	}

	
//	@Override
//	public void actionPerformed(ActionEvent event) {
//		mensajeaEnviar = txtMensaje.getText();
//		sendMessages(mensajeaEnviar);
//		txtMensaje.setText("");
//		
//		if(event.getSource() == "enviar") {
//			mensajeaEnviar = txtMensaje.getText();
//			sendMessages(mensajeaEnviar);
//			txtMensaje.setText("");
//		}
//	}
        
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
//                        //System.out.print(mensajeToSave);
//                        writeToFile.write(mensajeToSave);
//                        writeToFile.close();
//                    }
//                }
//                catch(IOException e) {
//                    JOptionPane.showMessageDialog(null, "No se pudo guardar su archivo", "Error", JOptionPane.WARNING_MESSAGE);
//                }
//                finally {
//                    //System.exit(0);
//                    dispose();
//                }
//            }
//           
//        }


	

	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private String mensajeaEnviar = "",nameCliente,direccionIP;
	private Socket cliente;

        private String mensajeToSave = "";
}
