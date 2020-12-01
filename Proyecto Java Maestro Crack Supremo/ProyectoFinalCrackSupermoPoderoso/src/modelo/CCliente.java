/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class CCliente extends Thread{
    
    private boolean continuar = true;
        public void run(){
            try {
                iniciarCliente();
            } catch (IOException ex) {
                System.out.println("no hay vendedores");
            }
        }
	public CCliente(String cliente, String direccion,JTextArea text) {
            texto = text;
            nameCliente = cliente;
            direccionIP = direccion;
	}
        
        public void iniciarCliente() throw IOEception {
            try {
                startClienteConexion();
                if(continuar) {
                    outputInputOfData();
                    process();
                }
            }
            catch(EOFException e) {
                e.getMessage();
            }
            finally {
                if(continuar)
                    closeCliente();
            }
        }
	
	/*public void iniciarCliente() throws IOException {

			startClienteConexion();
                        if(continuar) {
                            OutputInputOfData();
			    process();
                        }

                    if(continuar)
			closeCliente();
		
	}*/
	
        public void startClienteConexion() throws UnknownHostException, IOException {
            cliente = new Socket(InetAddress.getByName(direccionIP), 9009);
        }
	/*public void startClienteConexion() throws UnknownHostException, IOException {
		showMessage("Iniciando conexion\n");

			cliente = new Socket(InetAddress.getByName(direccionIP), 9009);
                        showMessage("conexion Lista\n");

	}*/
	public void outputInputOfData() {
            try {
                salida = new ObjectOutputStream(cliente.getOutputStream());
                salida.flush();
                
                entrada = new ObjectInputStream(cliente.getInputStream());
            }
            catch(IOException | NullPointerException e) {
                e.getMessage();
            }
        }
        
	/*public void outputInputOfData() {
		try {
			salida = new ObjectOutputStream(cliente.getOutputStream());
			salida.flush();
			
			entrada = new ObjectInputStream(cliente.getInputStream());
			showMessage("Output/Input -> ready\n");
		}
		catch(IOException | NullPointerException e) {
			e.getMessage();
		}
	}*/
	public void process() throws IOException {
            do {
                try {
                    PaqueteDeDatos paqueteVendedor = (PaqueteDeDatos)entrada.readObject();
                    mensajeToSave += printPaquete(paqueteVendedor);
                }
                catch(ClassNotFoundException | NullPointerException e) {
                    e.getMessage();
		}
            }while(cliente.isConnected());
        }
	/*public void process() throws IOException{
		do {
			try {
				PaqueteDeDatos paqueteVendedor;
				paqueteVendedor = (PaqueteDeDatos)entrada.readObject();
				
                                mensajeToSave += printPaquete(paqueteVendedor);
				showMessage(printPaquete(paqueteVendedor));
			}
			catch(ClassNotFoundException | NullPointerException e) {
				e.getMessage();
			}
			
		}while(cliente.isConnected());
	}*/
        
	public void closeCliente() {
            showMessage("Fin del Chat\n");
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
		PaqueteDeDatos paqueteCliente = null;
		try {
			paqueteCliente = new PaqueteDeDatos();
			paqueteCliente.setNameUser(nameCliente);
			paqueteCliente.setMensaje(message);
			salida.writeObject(paqueteCliente);
			mensajeToSave += printPaquete(paqueteCliente);
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
	
	/*public void showMessage(String message) {
		texto.append(message);
	}*/
	
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
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private String mensajeaEnviar = "";
	private String nameCliente;
	private String direccionIP;
	private Socket cliente;
        private JTextArea texto;
        private String mensajeToSave = "";
}

