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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class CCliente extends Thread{
    
    private boolean continuar = true;
        public void run(){
            iniciarCliente();
        }
	public CCliente(String cliente, String direccion,JTextArea text) {
            texto = text;
            nameCliente = cliente;
            direccionIP = direccion;
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
                    //this.dispose();
                    e.getMessage();
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
			e.getMessage();
		}
	}
	
	public void process() throws IOException{
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
	}
        
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
		texto.append(message);
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
        


	

	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private String mensajeaEnviar = "";
	private String nameCliente;
	private String direccionIP;
	private Socket cliente;
        private JTextArea texto;
        private String mensajeToSave = "";
}

