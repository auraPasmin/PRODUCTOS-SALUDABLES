package modelo;


import java.io.EOFException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.lang.Thread;


public class CVendedor extends Thread{
    private boolean runThread = true;
    
    public CVendedor(String vendedor, JTextArea text) {
        nameVendedor = vendedor;
        textArea = text;
    }
    
    public void stopThread() {
        runThread = false;
    }
    
    @Override
    public void run() {
            iniciarVendedor();
    }
    
    public void iniciarVendedor() {
        
        try {
            servidor = new ServerSocket(9009);
            while (runThread) {
                try {
                    starServer();
                    outputInputOfData();
                    processInformation();
                }
                catch(EOFException ex) {
                    showMessage("El cliente se ha salido");
                }
                catch(NullPointerException e) {
                    e.getMessage();
                }
                finally {
                    closeVendedor();
                    break;
                }
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getLocalizedMessage());
        }
    }
    
    public void starServer() {
        showMessage("Iniciando conexion\n");
        try {
            showMessage("Esperando...\n");
            socket = servidor.accept();
            showMessage("Se ha establecido la conexion");
        }
        catch(IOException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido establecer la conexion");
            stopThread();
            e.getMessage();
        }
    }
    
    public void outputInputOfData() {
        try {
            salida = new ObjectOutputStream(socket.getOutputStream());
            salida.flush();
            
            entrada = new ObjectInputStream(socket.getInputStream());
            showMessage("Ya se puede enviar mensajes\n");
        }
        catch(IOException e) {
            e.getMessage();
        }
    }
    
    public void processInformation() throws IOException {
        do {
            try {
                PaqueteDeDatos paquetecliente;
                paquetecliente = (PaqueteDeDatos) entrada.readObject();
                mensajeToSave += printPaquete(paquetecliente);
                showMessage(printPaquete(paquetecliente));
            }
            catch(ClassNotFoundException e) {
                e.getMessage();
            }
        }while(socket.isConnected());
    }
    
    public void closeVendedor() {
        showMessage("Se ha cerrado la conexion");
        try {
            if(servidor != null)
                servidor.close();
            if(socket != null)
                socket.close();
            if(entrada != null)
                entrada.close();
            if(salida != null)
                salida.close();
            runThread = false;
        }
        catch(IOException | NullPointerException e) {
            System.out.println("error al cerrar");
        }
    }
    
    public void sendMessages(String message) {
        PaqueteDeDatos paqueteServidor = null;
        try {
            paqueteServidor = new PaqueteDeDatos();
            paqueteServidor.setNameUser(nameVendedor);
            paqueteServidor.setMensaje(message);
            
            salida.writeObject(paqueteServidor);
            mensajeToSave += printPaquete(paqueteServidor);
            showMessage(printPaquete(paqueteServidor));
        }
        catch(IOException e) {
            e.getMessage();
        }
    }
    
    public String printPaquete(PaqueteDeDatos paquete) {
        return ("\n" + paquete.getNameUser() + "\n" + paquete.getMensaje() + "\n");
    }
    
    public void showMessage(String message) {
        textArea.append(message);
    }
    
    public String saveChat() {
        return mensajeToSave;
    }
    
    public void interruptServer() {
        stopThread();
        closeVendedor();
    }
    
    
    private JTextArea textArea;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private String mensajeaEnviar = "";
    private String mensajeToSave = "";
    private String nameVendedor;
    private ServerSocket servidor;
    private Socket socket;
}
