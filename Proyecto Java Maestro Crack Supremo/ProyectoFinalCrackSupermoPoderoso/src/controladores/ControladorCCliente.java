package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
