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
    private boolean runthread = true;
    private boolean continuar = true;
    
    public CCliente(String cliente, String direccion, JTextArea text) {
        texto = text;
        nameCliente = cliente;
        direccionIP = direccion;
    }
    
    public void stopThread() {
        runthread = false;
    }
    
    @Override
    public void run() {
        do {
            try {
                starCliente();
                if(continuar) {
                    outputInputOfData();
                    processInformation();
                }
            }
            catch(EOFException e) {
                showMessage("Se ha terminado la seccion");
            }
            catch(IOException e) {
                e.getMessage();
            }
            finally {
                if(continuar)
                    closeCliente();
            }
        } while(runthread);
    }
    
    public void starCliente() {
        try {
            cliente = new Socket(InetAddress.getByName(direccionIP), 9009);
        }
        catch(IOException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "No hay vendedores disponibles");
            runthread = false;
            continuar = false;
        }
    }
    
    public void outputInputOfData() {
        try {
            salida = new ObjectOutputStream(cliente.getOutputStream());
            salida.flush();
            
            entrada = new ObjectInputStream(cliente.getInputStream());
            showMessage("¡Bienvenido!, un ascesor lo atendera pronto");
        }
        catch(IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void processInformation() throws IOException{
        do {
            try {
                PaqueteDeDatos paqueteVendedor = (PaqueteDeDatos) entrada.readObject();
                mensajeToSave += printPaquete(paqueteVendedor);
                showMessage(printPaquete(paqueteVendedor));
            }
            catch(ClassNotFoundException | NullPointerException e) {
                e.getMessage();
            }
        } while(cliente.isConnected());
    }
    
    public void closeCliente() {
        showMessage("Se ha Finalizado el chat");
        try {
            cliente.close();
            entrada.close();
            salida.close();
            runthread = false;
        }
        catch(IOException e) {
            e.getMessage();
        }
    }
    
    public void sendMessages(String message) {
        PaqueteDeDatos paquetecliente = null;
        try {
            paquetecliente = new PaqueteDeDatos();
            paquetecliente.setNameUser(nameCliente);
            paquetecliente.setMensaje(message);
            salida.writeObject(paquetecliente);
            
            mensajeToSave += printPaquete(paquetecliente);
            showMessage(printPaquete(paquetecliente));
        }
        catch(IOException e) {
            System.out.println("No se pudo escribir el mensaje");
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
    
    public String saveChat() {
        return mensajeToSave;
    }
	
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private String mensajeaEnviar = "";
	private String nameCliente;
	private String direccionIP;
	private Socket cliente;
        private JTextArea texto;
        private String mensajeToSave = "";
}

