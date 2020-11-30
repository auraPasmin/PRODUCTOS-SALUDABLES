package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.*;
import vistas.ChatCliente;

public class ControladorCCliente {
    CCliente chatCliente;
    ChatCliente vista;

    public ControladorCCliente(String cliente, String direccion) {
        vista = new ChatCliente(cliente, direccion);
        chatCliente = new CCliente(cliente, direccion, vista.getText());
        chatCliente.start();
        vista.addListenerbtn(new ChatController());
    }
    class ChatController implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getActionCommand().equals("enviar")){
                chatCliente.sendMessages(vista.getMessage());
            }
            System.out.println(ae.getActionCommand());
        }
    
    }
    
}
