package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modelo.*;
import vistas.VistaChat;

public class ControladorCCliente {
    CCliente chatCliente;
    VistaChat Vista;
    
    public ControladorCCliente(String cliente, String direccion) {
        Vista = new VistaChat();
        chatCliente = new CCliente(cliente, direccion, Vista.getChat());
        //vista.addListenerbtn(new ChatController());
        Vista.addListenerjBotonEnviar(new ChatController());
        Vista.addWindowClose(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String messageSave = chatCliente.saveChat();
                if(!messageSave.equals(""))
                    Vista.guardarChat(messageSave);
                
                chatCliente.stopThread();
            }
        });
        
        chatCliente.start();
        Vista.setUsuario("Cliente " + cliente);
        Vista.setVisible(true);   
    }
    class ChatController implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getActionCommand().equals("Enviar")){
                //chatCliente.sendMessages(vista.getMessage());
                chatCliente.sendMessages(Vista.getMessage());
            }
        }
    
    }
    
}
